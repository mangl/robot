package com.mangl.robot.util.http;

/**
 * �� �� ���� �ն��������� </br> 
 * �� �� �ˣ� BaiB </br> 
 * ����ʱ�䣺 2015-03-03 14:10 </br> 
 * �޸ı�ע��
 */
public class CheckUtil {

	public static int OFF_LINE = 0;
	public static int ON_LINE_GPRS = 1;
	public static int ON_LINE_WLAN = 2;
	/**
	 * ���������� �ж��ֻ��Ƿ��������� </br> 
	 * �� �� �ˣ� </br>
	 * ����ʱ�䣺 </br>
	 * */
	public static boolean isOnline(){
		return true;
	}
	
	/**
	 * ���������� �ж��ֻ��Ƿ��������� </br>
	 * ����ֵ��  OFF_LINE δ������ON_LINE_GPRS GPRS���ӣ� ON_LINE_WLAN WLAN����</br>
	 * �� �� �ˣ� </br>
	 * ����ʱ�䣺 </br>
	 * */
	public static int getWlanState(){
		return 0;
	}
	
	/**
	 * ���������� �ж��ֻ��Ƿ���GPS </br> 
	 * �� �� �ˣ� </br>
	 * ����ʱ�䣺 </br>
	 * */
	public static boolean isGPS(){
		return true;
	}
	
	/**
	 * ���������� ��ϵͳ�������ý��� </br>
	 * �� �� �ˣ� </br>
	 * ����ʱ�䣺 </br>
	 * */
	public static boolean openSttingForWlan(){
		return true;
	}
	
	/**
	 * ���������� ��ϵͳGPS���ý��� </br>
	 * �� �� �ˣ� </br>
	 * ����ʱ�䣺 </br>
	 * */
	public static boolean openSttingForGPS(){
		return true;
	}
	
	/**
	 * ���������� ���԰��û�ֱ�ӿ���GPS </br>
	 * �� �� �ˣ� </br>
	 * ����ʱ�䣺 </br>
	 * */
	public static boolean openGPS(){
		return true;
	}
}
