package com.mangl.robot.util.md5;

import java.security.MessageDigest;

/**
 * �� �� ���� MD5������ </br> 
 * ����ʱ�䣺 2015-03-04 08:45 </br>
 */
public class MD5Util 
{
	/**
	 * ���ַ�������MD5����
	 * 
	 * @param pstr �����ܵ��ַ���
	 * @return MD5 string
	 */
	public static String ToMD5(String pstr)
	{
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try
		{
			MessageDigest md5Temp = MessageDigest.getInstance("MD5");
			md5Temp.update(pstr.getBytes("UTF8"));
			byte[] md = md5Temp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		}
		catch (Exception e)
		{
			return null;
		}
	}
}
