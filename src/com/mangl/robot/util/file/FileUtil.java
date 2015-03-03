package com.mangl.robot.util.file;

import java.io.File;
import java.util.List;

/**
 * 类 描 述： 文件操作类 </br> 
 * 创 建 人： BaiB </br> 
 * 创建时间： 2015-03-03 12:06 </br> 
 * 修改备注：
 */
public class FileUtil {

	/**
	 * 方法描述： 复制单个文件 </br> 
	 * 参数： oldFile 源文件 newFile 复制后的新文件</br> 
	 * 创 建 人： </br>
	 * 创建时间： </br>
	 * */
	public static boolean copyFile(File oldFile, File newFile) {

		return true;
	}

	/**
	 * 方法描述： 复制单个文件 </br> 
	 * 参数： oldPath 源文件路径 newPath 复制后的新文件路径 </br>
	 * 创 建 人： </br>
	 * 创建时间：</br>
	 * */
	public static boolean copyFile(String oldPath, String newPath) {

		return copyFile(new File(oldPath), new File(newPath));
	}
	
	/**
	 * 方法描述： 将文件夹下的所有文件复制到新的文件夹下 </br> 
	 * 参数： oldFile 源文件夹 newFile 复制后的新文件夹 </br>
	 * 创 建 人： </br>
	 * 创建时间：</br> 
	 * */
	public static boolean copyFiles(File oldFile, File newFile){
		
		return true;
	}
	
	/**
	 * 方法描述： 将文件夹下的所有文件复制到新的文件夹下 </br> 
	 * 参数：oldPath 源文件夹路径 newPath 复制后的新文件夹路径</br>
	 * 创 建 人： </br>
	 * 创建时间：</br> 
	 * */
	public static boolean copyFiles(String oldPath, String newPath){
		
		return copyFiles(new File(oldPath), new File(newPath));
	}
	
	/**
	 * 方法描述： 将文件夹下的所有文件删除 </br> 
	 * 参数： File 源文件夹 </br>
	 * 创 建 人： </br>
	 * 创建时间：</br> 
	 * */
	public static boolean delFiles(File File){
		
		return true;
	}
	
	/**
	 * 方法描述： 将文件夹下的所有文件删除 </br> 
	 * 参数： File 源文件夹 </br>
	 * 创 建 人： </br>
	 * 创建时间：</br> 
	 * */
	public static boolean delFiles(String Path){
		
		return delFiles(new File(Path));
	}
	
	/**
	 * 方法描述： 获取文件夹下某格式的所有文件列表 </br> 
	 * 参数： File 源文件夹  suffixName 后缀名 例如 ".zip"</br>
	 * 返回值：针对该文件夹的相对路径列表</br>
	 * 创 建 人： </br>
	 * 创建时间：</br> 
	 * */
	public static List<String> getSimpleFileList(File file, String suffixName){
		
		return null;
	}
	
	/**
	 * 方法描述： 获取文件夹下某格式的所有文件列表 </br> 
	 * 参数： path 源文件夹路径  suffixName 后缀名 例如 ".zip"</br>
	 * 返回值：针对该文件夹的相对路径列表</br>
	 * 创 建 人： </br>
	 * 创建时间：</br> 
	 * */
	public static List<String> getSimpleFileList(String path, String suffixName){
		
		return getSimpleFileList(new File(path), suffixName);
	}
	
}
