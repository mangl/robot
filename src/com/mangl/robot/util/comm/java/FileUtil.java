package com.mangl.robot.util.comm.java;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * @类描述 文件操作类 </br>
 * @创建人 BaiB </br>
 * @创建时间  2014-03-03 12:06 </br>
 * @修改备注
 */
public class FileUtil {
	private static final int BUFF_SIZE = 1048576; // 1M Byte

	/**
	 * @方法描述  复制单个文件 </br> 
	 * @参数  oldFile 源文件 newFile 复制后的新文件</br> 
	 * @创建人 qin </br>
	 * @创建时间  </br>
	 * */
	public static boolean copyFile(File oldFile, File newFile) {
		if (oldFile == null && newFile == null) {
			return false;
		}
		try {
			@SuppressWarnings("unused")
			int bytesum = 0;
			int byteread = 0;
			if (oldFile.exists()) {
				// 文件存在时
				InputStream inStream = new FileInputStream(oldFile); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newFile);
				byte[] buffer = new byte[1024];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				fs.flush();
				fs.close();
				inStream.close();
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 方法描述： 复制单个文件 </br> 参数： oldPath 源文件路径 newPath 复制后的新文件路径 </br> 创 建 人： </br>
	 * 创建时间：</br>
	 * */
	public static boolean copyFile(String oldPath, String newPath) {
		return copyFile(new File(oldPath), new File(newPath));
	}

	/**
	 * 方法描述： 将文件夹下的所有文件复制到新的文件夹下 </br> 参数： oldFile 源文件夹 newFile 复制后的新文件夹 </br> 创
	 * 建 人： </br> 创建时间：</br>
	 * */
	@SuppressWarnings("resource")
	public static boolean copyFiles(File oldFile, File newFile) {
		{
			if (!oldFile.exists()) {
				return false;
			}
			byte[] b = new byte[(int) oldFile.length()];
			if (oldFile.isFile()) {
				try {
					FileInputStream is = new FileInputStream(oldFile);
					FileOutputStream ps = new FileOutputStream(newFile);
					is.read(b);
					ps.write(b);
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			} else if (oldFile.isDirectory()) {
				if (!oldFile.exists())
					oldFile.mkdir();
				String[] list = oldFile.list();
				for (int i = 0; i < list.length; i++) {
					copyFiles(oldFile.getAbsolutePath() + "/" + list[i], newFile.getAbsolutePath() + "/" + list[i]);
				}
			}
		}
		return true;
	}

	/**
	 * 方法描述： 将文件夹下的所有文件复制到新的文件夹下 </br> 参数：oldPath 源文件夹路径 newPath 复制后的新文件夹路径</br>
	 * 创 建 人： </br> 创建时间：</br>
	 * */
	public static boolean copyFiles(String oldPath, String newPath) {
		return copyFiles(new File(oldPath), new File(newPath));
	}

	/**
	 * 方法描述： 将文件夹下的所有文件删除 </br> 参数： File 源文件夹 </br> 创 建 人： </br> 创建时间：</br>
	 * */
	public static boolean delFiles(File file) {
		if (file.isFile()) {
			file.delete();
		}
		if (file.isDirectory()) {
			File[] childFile = file.listFiles();
			if (childFile == null || childFile.length == 0) {
				file.delete();
			}
			for (File f : childFile) {
				delFiles(f);
			}
			// file.delete();
		}
		return true;
	}

	/**
	 * 方法描述： 将文件夹下的所有文件删除 </br> 参数： File 源文件夹 </br> 创 建 人： </br> 创建时间：</br>
	 * */
	public static boolean delFiles(String Path) {
		return delFiles(new File(Path));
	}

	/**
	 * 方法描述： 获取文件夹下某格式的所有文件列表 </br> 参数： File 源文件夹 suffixName 后缀名 例如 ".zip"</br>
	 * 返回值：针对该文件夹的相对路径列表</br> 创 建 人： </br> 创建时间：</br>
	 * */
	public static List<String> getSimpleFileList(File file, String suffixName) {
		List<String> list = new ArrayList<String>();
		String path = "";
		if (!file.exists()) {
			return null;
		}
		// 创建fileArray名字的数组
		File[] fileArray = file.listFiles();
		// 如果传进来一个以文件作为对象的allList 返回0
		if (null == fileArray) {
			return null;
		}
		// 偏历目录下的文件
		for (int i = 0; i < fileArray.length; i++) {
			// 如果是个目录
			if (fileArray[i].isDirectory()) {
				// 递归调用
				list.addAll(getSimpleFileList(fileArray[i].getAbsoluteFile(), suffixName));

			} else if (fileArray[i].isFile()) {
				// 如果是以“”结尾的文件
				if (suffixName == null || fileArray[i].getName().endsWith(suffixName)) {
					// 展示文件
					path = fileArray[i].getAbsolutePath();
					Log.e("@@@@@", path);
					list.add(path);
				}
			}
		}
		return list;
	}

	/**
	 * 方法描述： 获取文件夹下某格式的所有文件列表 </br> 参数： path 源文件夹路径 suffixName 后缀名 例如
	 * ".zip"</br> 返回值：针对该文件夹的相对路径列表</br> 创 建 人： </br> 创建时间：</br>
	 * */
	public static List<String> getSimpleFileList(String path, String suffixName) {
		return getSimpleFileList(new File(path), suffixName);
	}

	/**
	 * 获得指定文件的byte数组
	 * 
	 * @param filePath
	 *            文件路径
	 * @return byte数组
	 */
	public static byte[] getBytes(String filePath) {
		byte[] buffer = null;
		try {
			File file = new File(URLDecoder.decode(filePath, "UTF-8"));
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	/**
	 * 根据byte数组，生成文件
	 * 
	 * @param bfile
	 *            byte流
	 * @param filePath
	 *            文件路径
	 * @param fileName
	 *            文件名称
	 */
	public static void getFile(byte[] bfile, String filePath, String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory()) {
				// 判断文件目录是否存在
				dir.mkdirs();
			}
			file = new File(filePath + "\\" + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bfile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * @description 从assets文件夹中拷贝数据到sd卡中
	 * @param context
	 *            上下文环境
	 * @param assetsNamee
	 *            资源文件名
	 * @param strOutFilePath
	 *            拷贝到指定路径
	 * @throws IOException
	 */
	public static void copyDataToSD(Context context, String assetsNamee, String strOutFilePath) throws IOException {
		InputStream myInput;
		OutputStream myOutput = new FileOutputStream(strOutFilePath + "/" + assetsNamee);
		myInput = context.getAssets().open(assetsNamee);
		byte[] buffer = new byte[1024];
		int length = myInput.read(buffer);
		while (length > 0) {
			myOutput.write(buffer, 0, length);
			length = myInput.read(buffer);
		}
		myOutput.flush();
		myInput.close();
		myOutput.close();
	}

	/**
	 * @description 获取文件夹的大小
	 * 
	 * @param f
	 *            文件夹
	 * @return size 文件大小
	 * @throws Exception
	 */
	public static long getFileSize(File f) throws Exception {
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSize(flist[i]);
			} else {
				size = size + flist[i].length();
			}
		}
		return size;
	}

	/**
	 * @description 加载本地图片
	 * 
	 * @param url
	 *            本地图片地址
	 * @return Bitmap
	 */
	public static Bitmap getLoacalBitmap(String url) {
		try {
			FileInputStream fis = new FileInputStream(url);
			return BitmapFactory.decodeStream(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @description 文件夹内是否存在文件。是返回true
	 * 
	 * @param file
	 *            文件夹
	 * @return true/false
	 */
	public static boolean havefile(File file) {
		File[] files = file.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					havefile(files[i]);
				} else {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @description 获取文件内容
	 * @param strFilePath
	 *            文件地址
	 * @return content 文件内容字符串
	 * @throws IOException
	 */
	public static String ReadTxtFile(String strFilePath) throws IOException {
		String path = strFilePath;
		String content = ""; // 文件内容字符串
		// 打开文件
		File file = new File(path);
		// 如果path是传递过来的参数，可以做一个非目录的判断
		if (!file.isDirectory()) {
			InputStream instream = new FileInputStream(file);
			if (instream != null) {
				InputStreamReader inputreader = new InputStreamReader(instream);
				BufferedReader buffreader = new BufferedReader(inputreader);
				String line;
				// 分行读取
				while ((line = buffreader.readLine()) != null) {
					content += line;
				}
				instream.close();
			}
		}
		return content;
	}

	/**
	 * @description 解压缩ZIP文件，将ZIP文件里的内容解压到targetDIR目录下
	 * @param zipName
	 *            待解压缩的ZIP文件名 /mnt/sdcard/ce.zip
	 * @param targetBaseDirName
	 *            目标目录 /mnt/sdcard/cache/
	 */
	public static void upzipFile(String zipFileName, String targetBaseDirName) throws IOException {
		if (!targetBaseDirName.endsWith(File.separator)) {
			targetBaseDirName += File.separator;
		}

		// 根据ZIP文件创建ZipFile对象
		@SuppressWarnings("resource")
		ZipFile myZipFile = new ZipFile(zipFileName);
		ZipEntry entry = null;
		String entryName = null;
		String targetFileName = null;
		byte[] buffer = new byte[4096];
		int bytes_read;
		// 获取ZIP文件里所有的entry
		Enumeration<?> entrys = myZipFile.entries();
		// 遍历所有entry
		while (entrys.hasMoreElements()) {
			entry = (ZipEntry) entrys.nextElement();
			// 获得entry的名字
			entryName = entry.getName();
			targetFileName = targetBaseDirName + entryName;
			if (entry.isDirectory()) {
				// 如果entry是一个目录，则创建目录
				new File(targetFileName).mkdirs();
				continue;
			} else {
				// 如果entry是一个文件，则创建父目录
				new File(targetFileName).getParentFile().mkdirs();
			}
			// 否则创建文件
			File targetFile = new File(targetFileName);
			// System.out.println("创建文件：" + targetFile.getAbsolutePath());
			// 打开文件输出流
			FileOutputStream os = new FileOutputStream(targetFile);
			// 从ZipFile对象中打开entry的输入流
			InputStream is = myZipFile.getInputStream(entry);
			while ((bytes_read = is.read(buffer)) != -1) {
				os.write(buffer, 0, bytes_read);
			}
			// 关闭流
			os.close();
			is.close();
		}
	}

	/**
	 * @description 压缩文件
	 * @param resFile
	 *            需要压缩的文件（夹） F://cc/ or F://abc.txt
	 * @param zipout
	 *            压缩的目的文件
	 * @param rootpath
	 *            压缩的文件路径
	 * @throws FileNotFoundException
	 *             找不到文件时抛出
	 * @throws IOException
	 *             当压缩过程出错时抛出
	 */
	public static void zipFile(File resFile, ZipOutputStream zipout, String rootpath) throws FileNotFoundException, IOException {
		rootpath = rootpath + (rootpath.trim().length() == 0 ? "" : File.separator) + resFile.getName();
		rootpath = new String(rootpath.getBytes("8859_1"), "UTF-8");
		if (resFile.isDirectory()) {
			File[] fileList = resFile.listFiles();
			for (File file : fileList) {
				zipFile(file, zipout, rootpath);
			}
		} else {
			byte buffer[] = new byte[BUFF_SIZE];
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(resFile), BUFF_SIZE);
			zipout.putNextEntry(new ZipEntry(rootpath));
			int realLength;
			while ((realLength = in.read(buffer)) != -1) {
				zipout.write(buffer, 0, realLength);
			}
			in.close();
			zipout.flush();
			zipout.closeEntry();
		}
	}

	/**
	 * @description 
	 *              将存放在sourceFilePath目录下的源文件,打包成fileName名称的ZIP文件,并存放到zipFilePath
	 * 
	 * @param sourceFilePath
	 *            待压缩的文件路径
	 * @param zipFilePath
	 *            压缩后存放路径
	 * @param fileName
	 *            压缩后文件的名称
	 * @return flag 压缩是否成功
	 */
	public static boolean fileToZip(String sourceFilePath, String zipFilePath, String fileName) throws IOException {
		boolean flag = false;
		File sourceFile = new File(sourceFilePath);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		if (sourceFile.exists() == false) {
		} else {
			File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
			File[] sourceFiles = sourceFile.listFiles();
			if (null == sourceFiles || sourceFiles.length < 1) {
			} else {
				fos = new FileOutputStream(zipFile);
				zos = new ZipOutputStream(new BufferedOutputStream(fos));
				byte[] bufs = new byte[1024 * 10];
				for (int i = 0; i < sourceFiles.length; i++) {
					// 创建ZIP实体,并添加进压缩包
					// if(sourceFiles[i].getName().contains(".p12")||sourceFiles[i].getName().contains(".truststore")){
					ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
					zos.putNextEntry(zipEntry);
					// 读取待压缩的文件并写进压缩包里
					fis = new FileInputStream(sourceFiles[i]);
					bis = new BufferedInputStream(fis, 1024 * 10);
					int read = 0;
					while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
						zos.write(bufs, 0, read);
					}
					bis.close();
					fis.close();
					// }
				}
				flag = true;
			}
			zos.close();
			fos.close();
		}
		return flag;
	}
}