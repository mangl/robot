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
 * �� �� ���� �ļ������� </br> �� �� �ˣ� BaiB </br> ����ʱ�䣺 2015-03-03 12:06 </br> �޸ı�ע��
 */
public class FileUtil 
{
	/**
	 * ���������� ���Ƶ����ļ� </br> 
	 * ������ oldFile Դ�ļ� newFile ���ƺ�����ļ�</br> 
	 * �� �� �ˣ�qin </br>
	 * ����ʱ�䣺 </br>
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
				// �ļ�����ʱ
				InputStream inStream = new FileInputStream(oldFile); // ����ԭ�ļ�
				FileOutputStream fs = new FileOutputStream(newFile);
				byte[] buffer = new byte[1024];
				while ((byteread = inStream.read(buffer)) != -1) 
				{
					bytesum += byteread; // �ֽ��� �ļ���С
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
	 * ���������� ���Ƶ����ļ� </br> ������ oldPath Դ�ļ�·�� newPath ���ƺ�����ļ�·�� </br> �� �� �ˣ� </br>
	 * ����ʱ�䣺</br>
	 * */
	public static boolean copyFile(String oldPath, String newPath)
	{
		return copyFile(new File(oldPath), new File(newPath));
	}

	/**
	 * ���������� ���ļ����µ������ļ����Ƶ��µ��ļ����� </br> ������ oldFile Դ�ļ��� newFile ���ƺ�����ļ��� </br> ��
	 * �� �ˣ� </br> ����ʱ�䣺</br>
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
	 * ���������� ���ļ����µ������ļ����Ƶ��µ��ļ����� </br> 
	 * ������oldPath Դ�ļ���·�� newPath ���ƺ�����ļ���·��</br>
	 * �� �� �ˣ� </br> ����ʱ�䣺</br>
	 * */
	public static boolean copyFiles(String oldPath, String newPath) 
	{
		return copyFiles(new File(oldPath), new File(newPath));
	}

	/**
	 * ���������� ���ļ����µ������ļ�ɾ�� </br> 
	 * ������ File Դ�ļ��� </br> 
	 * �� �� �ˣ� </br> ����ʱ�䣺</br>
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
	 * ���������� ���ļ����µ������ļ�ɾ�� </br> ������ File Դ�ļ��� </br> �� �� �ˣ� </br> ����ʱ�䣺</br>
	 * */
	public static boolean delFiles(String Path) 
	{
		return delFiles(new File(Path));
	}
	/**
	 * ���������� ��ȡ�ļ�����ĳ��ʽ�������ļ��б� </br> 
	 * ������ File Դ�ļ��� suffixName ��׺�� ���� ".zip"</br>
	 * ����ֵ����Ը��ļ��е����·���б�</br> 
	 * �� �� �ˣ� </br> ����ʱ�䣺</br>
	 * */
	public static List<String> getSimpleFileList(File file, String suffixName) 
	{
		List<String> list = new ArrayList<String>();
		String path = "";
		if(!file.exists())
		{
			return null;
		}
		// ����fileArray���ֵ�����
		File[] fileArray = file.listFiles();
		// ���������һ�����ļ���Ϊ�����allList ����0
	    if (null == fileArray) 
		{
		  return null;
		}
		// ƫ��Ŀ¼�µ��ļ�
		for (int i = 0; i < fileArray.length; i++)
		{
		 // ����Ǹ�Ŀ¼
		  if (fileArray[i].isDirectory()) 
		  {
		   // System.out.println("Ŀ¼: "+fileArray[i].getAbsolutePath());
		   // �ݹ����
		   getSimpleFileList(fileArray[i].getAbsoluteFile(),suffixName);
		   // ������ļ�
		  }
		  else if (fileArray[i].isFile()) 
		  {
		    // ������ԡ�����β���ļ�
		    if (fileArray[i].getName().endsWith(suffixName)) 
		    {
		     // չʾ�ļ�
		     path = suffixName + "��ʽ���ļ�·�����ļ���: " + fileArray[i].getAbsolutePath();
		     list.add(path);
		    }
		  }
		}
	 return list;
	}
	/**
	 * ���������� ��ȡ�ļ�����ĳ��ʽ�������ļ��б� </br> ������ path Դ�ļ���·�� suffixName ��׺�� ����
	 * ".zip"</br> ����ֵ����Ը��ļ��е����·���б�</br> �� �� �ˣ� </br> ����ʱ�䣺</br>
	 * */
	public static List<String> getSimpleFileList(String path, String suffixName) 
	{
		return getSimpleFileList(new File(path), suffixName);
	}
	/**
	 * ���ָ���ļ���byte����
	 * 
	 * @param filePath �ļ�·��
	 * @return byte����
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
	 * ����byte���飬�����ļ�
	 * 
	 * @param bfile
	 *            byte��
	 * @param filePath
	 *            �ļ�·��
	 * @param fileName
	 *            �ļ�����
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
				// �ж��ļ�Ŀ¼�Ƿ����
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