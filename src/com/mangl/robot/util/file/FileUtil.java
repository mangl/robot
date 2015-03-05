package com.mangl.robot.util.file;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 类 描 述： 文件操作类 </br> 创 建 人： BaiB </br> 创建时间： 2015-03-03 12:06 </br> 修改备注：
 */
public class FileUtil 
{
	/**
	 * 方法描述： 复制单个文件 </br> 
	 * 参数： oldFile 源文件 newFile 复制后的新文件</br> 
	 * 创 建 人：qin </br>
	 * 创建时间： </br>
	 * */
	public static boolean copyFile(File oldFile, File newFile) 
	{
		if (oldFile == null && newFile == null) 
		{
			return false;
		}
		try 
		{
			@SuppressWarnings("unused")
			int bytesum = 0;
			int byteread = 0;
			if (oldFile.exists()) 
			{
				// 文件存在时
				InputStream inStream = new FileInputStream(oldFile); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newFile);
				byte[] buffer = new byte[1024];
				while ((byteread = inStream.read(buffer)) != -1) 
				{
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				fs.flush();
				fs.close();
				inStream.close();
			} 
			else
			{
				return false;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 方法描述： 复制单个文件 </br> 参数： oldPath 源文件路径 newPath 复制后的新文件路径 </br> 创 建 人： </br>
	 * 创建时间：</br>
	 * */
	public static boolean copyFile(String oldPath, String newPath)
	{
		return copyFile(new File(oldPath), new File(newPath));
	}

	/**
	 * 方法描述： 将文件夹下的所有文件复制到新的文件夹下 </br> 参数： oldFile 源文件夹 newFile 复制后的新文件夹 </br> 创
	 * 建 人： </br> 创建时间：</br>
	 * */
	@SuppressWarnings("resource")
	public static boolean copyFiles(File oldFile, File newFile) 
	{
		{
			if (!oldFile.exists()) 
			{
				return false;
			}
			byte[] b = new byte[(int) oldFile.length()];
			if (oldFile.isFile()) 
			{
				try 
				{
					FileInputStream is = new FileInputStream(oldFile);
					FileOutputStream ps = new FileOutputStream(newFile);
					is.read(b);
					ps.write(b);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
					return false;
				}
			} 
			else if (oldFile.isDirectory()) 
			{
				if (!oldFile.exists())
					oldFile.mkdir();
				String[] list = oldFile.list();
				for (int i = 0; i < list.length; i++) 
				{
					copyFiles(oldFile.getAbsolutePath() + "/" + list[i], newFile.getAbsolutePath() + "/" + list[i]);
				}
			}
		}
		return true;
	}
	/**
	 * 方法描述： 将文件夹下的所有文件复制到新的文件夹下 </br> 
	 * 参数：oldPath 源文件夹路径 newPath 复制后的新文件夹路径</br>
	 * 创 建 人： </br> 创建时间：</br>
	 * */
	public static boolean copyFiles(String oldPath, String newPath) 
	{
		return copyFiles(new File(oldPath), new File(newPath));
	}

	/**
	 * 方法描述： 将文件夹下的所有文件删除 </br> 
	 * 参数： File 源文件夹 </br> 
	 * 创 建 人： </br> 创建时间：</br>
	 * */
	public static boolean delFiles(File file) 
	{
		if(file.isFile())
		{
            file.delete();
        }
        if(file.isDirectory())
        {
            File[] childFile = file.listFiles();
            if(childFile == null || childFile.length == 0)
            {
                file.delete();
            }
            for(File f : childFile)
            {
            	delFiles(f);
            }
            //file.delete();
        }
		return true;
	}
	/**
	 * 方法描述： 将文件夹下的所有文件删除 </br> 参数： File 源文件夹 </br> 创 建 人： </br> 创建时间：</br>
	 * */
	public static boolean delFiles(String Path) 
	{
		return delFiles(new File(Path));
	}
	/**
	 * 方法描述： 获取文件夹下某格式的所有文件列表 </br> 
	 * 参数： File 源文件夹 suffixName 后缀名 例如 ".zip"</br>
	 * 返回值：针对该文件夹的相对路径列表</br> 
	 * 创 建 人： </br> 创建时间：</br>
	 * */
	public static List<String> getSimpleFileList(File file, String suffixName) 
	{
		List<String> list = new ArrayList<String>();
		String path = "";
		if(!file.exists())
		{
			return null;
		}
		// 创建fileArray名字的数组
		File[] fileArray = file.listFiles();
		// 如果传进来一个以文件作为对象的allList 返回0
	    if (null == fileArray) 
		{
		  return null;
		}
		// 偏历目录下的文件
		for (int i = 0; i < fileArray.length; i++)
		{
		 // 如果是个目录
		  if (fileArray[i].isDirectory()) 
		  {
		   // System.out.println("目录: "+fileArray[i].getAbsolutePath());
		   // 递归调用
		   getSimpleFileList(fileArray[i].getAbsoluteFile(),suffixName);
		   // 如果是文件
		  }
		  else if (fileArray[i].isFile()) 
		  {
		    // 如果是以“”结尾的文件
		    if (fileArray[i].getName().endsWith(suffixName)) 
		    {
		     // 展示文件
		     path = suffixName + "格式的文件路径和文件名: " + fileArray[i].getAbsolutePath();
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
	public static List<String> getSimpleFileList(String path, String suffixName) 
	{
		return getSimpleFileList(new File(path), suffixName);
	}
	/**
	 * 获得指定文件的byte数组
	 * 
	 * @param filePath 文件路径
	 * @return byte数组
	 */
	public static byte[] getBytes(String filePath) 
	{
		byte[] buffer = null;
		try 
		{
			File file = new File(URLDecoder.decode(filePath, "UTF-8"));
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) 
			{
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
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
	public static void getFile(byte[] bfile, String filePath, String fileName)
	{
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try 
		{
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory()) 
			{
				// 判断文件目录是否存在
				dir.mkdirs();
			}
			file = new File(filePath + "\\" + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bfile);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		finally 
		{
			if (bos != null) 
			{
				try
				{
					bos.close();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
			if (fos != null)
			{
				try
				{
					fos.close();
				}
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
		}
	}
}