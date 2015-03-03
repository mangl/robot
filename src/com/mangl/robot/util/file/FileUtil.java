package com.mangl.robot.util.file;

import java.io.File;
import java.util.List;

/**
 * �� �� ���� �ļ������� </br> 
 * �� �� �ˣ� BaiB </br> 
 * ����ʱ�䣺 2015-03-03 12:06 </br> 
 * �޸ı�ע��
 */
public class FileUtil {

	/**
	 * ���������� ���Ƶ����ļ� </br> 
	 * ������ oldFile Դ�ļ� newFile ���ƺ�����ļ�</br> 
	 * �� �� �ˣ� </br>
	 * ����ʱ�䣺 </br>
	 * */
	public static boolean copyFile(File oldFile, File newFile) {

		return true;
	}

	/**
	 * ���������� ���Ƶ����ļ� </br> 
	 * ������ oldPath Դ�ļ�·�� newPath ���ƺ�����ļ�·�� </br>
	 * �� �� �ˣ� </br>
	 * ����ʱ�䣺</br>
	 * */
	public static boolean copyFile(String oldPath, String newPath) {

		return copyFile(new File(oldPath), new File(newPath));
	}
	
	/**
	 * ���������� ���ļ����µ������ļ����Ƶ��µ��ļ����� </br> 
	 * ������ oldFile Դ�ļ��� newFile ���ƺ�����ļ��� </br>
	 * �� �� �ˣ� </br>
	 * ����ʱ�䣺</br> 
	 * */
	public static boolean copyFiles(File oldFile, File newFile){
		
		return true;
	}
	
	/**
	 * ���������� ���ļ����µ������ļ����Ƶ��µ��ļ����� </br> 
	 * ������oldPath Դ�ļ���·�� newPath ���ƺ�����ļ���·��</br>
	 * �� �� �ˣ� </br>
	 * ����ʱ�䣺</br> 
	 * */
	public static boolean copyFiles(String oldPath, String newPath){
		
		return copyFiles(new File(oldPath), new File(newPath));
	}
	
	/**
	 * ���������� ���ļ����µ������ļ�ɾ�� </br> 
	 * ������ File Դ�ļ��� </br>
	 * �� �� �ˣ� </br>
	 * ����ʱ�䣺</br> 
	 * */
	public static boolean delFiles(File File){
		
		return true;
	}
	
	/**
	 * ���������� ���ļ����µ������ļ�ɾ�� </br> 
	 * ������ File Դ�ļ��� </br>
	 * �� �� �ˣ� </br>
	 * ����ʱ�䣺</br> 
	 * */
	public static boolean delFiles(String Path){
		
		return delFiles(new File(Path));
	}
	
	/**
	 * ���������� ��ȡ�ļ�����ĳ��ʽ�������ļ��б� </br> 
	 * ������ File Դ�ļ���  suffixName ��׺�� ���� ".zip"</br>
	 * ����ֵ����Ը��ļ��е����·���б�</br>
	 * �� �� �ˣ� </br>
	 * ����ʱ�䣺</br> 
	 * */
	public static List<String> getSimpleFileList(File file, String suffixName){
		
		return null;
	}
	
	/**
	 * ���������� ��ȡ�ļ�����ĳ��ʽ�������ļ��б� </br> 
	 * ������ path Դ�ļ���·��  suffixName ��׺�� ���� ".zip"</br>
	 * ����ֵ����Ը��ļ��е����·���б�</br>
	 * �� �� �ˣ� </br>
	 * ����ʱ�䣺</br> 
	 * */
	public static List<String> getSimpleFileList(String path, String suffixName){
		
		return getSimpleFileList(new File(path), suffixName);
	}
	
}
