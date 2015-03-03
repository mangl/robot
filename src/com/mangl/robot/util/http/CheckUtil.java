package com.mangl.robot.util.http;

/**
 * 类 描 述： 终端网络检查类 </br> 
 * 创 建 人： BaiB </br> 
 * 创建时间： 2015-03-03 14:10 </br> 
 * 修改备注：
 */
public class CheckUtil {

	public static int OFF_LINE = 0;
	public static int ON_LINE_GPRS = 1;
	public static int ON_LINE_WLAN = 2;
	/**
	 * 方法描述： 判断手机是否连接网络 </br> 
	 * 创 建 人： </br>
	 * 创建时间： </br>
	 * */
	public static boolean isOnline(){
		return true;
	}
	
	/**
	 * 方法描述： 判断手机是否连接网络 </br>
	 * 返回值：  OFF_LINE 未联网，ON_LINE_GPRS GPRS连接， ON_LINE_WLAN WLAN连接</br>
	 * 创 建 人： </br>
	 * 创建时间： </br>
	 * */
	public static int getWlanState(){
		return 0;
	}
	
	/**
	 * 方法描述： 判断手机是否开启GPS </br> 
	 * 创 建 人： </br>
	 * 创建时间： </br>
	 * */
	public static boolean isGPS(){
		return true;
	}
	
	/**
	 * 方法描述： 打开系统网络设置界面 </br>
	 * 创 建 人： </br>
	 * 创建时间： </br>
	 * */
	public static boolean openSttingForWlan(){
		return true;
	}
	
	/**
	 * 方法描述： 打开系统GPS设置界面 </br>
	 * 创 建 人： </br>
	 * 创建时间： </br>
	 * */
	public static boolean openSttingForGPS(){
		return true;
	}
	
	/**
	 * 方法描述： 尝试帮用户直接开启GPS </br>
	 * 创 建 人： </br>
	 * 创建时间： </br>
	 * */
	public static boolean openGPS(){
		return true;
	}
}
