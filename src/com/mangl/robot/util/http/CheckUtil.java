package com.mangl.robot.util.http;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;
import android.widget.Toast;

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
	 * �� �� �ˣ� YAR</br>
	 * ����ʱ�䣺 2015-3-3</br>
	 * �������ͣ� true �� false </br>
	 * */
	public static boolean isOnline(Context context)
	{
		boolean flag = false;
		if(context != null)
		{
		 ConnectivityManager mConnectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		 NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo(); 

//		 boolean wifi=con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
//		 boolean gprs=con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
		 if (mNetworkInfo != null) 
		 { 
			 mNetworkInfo.isAvailable(); 
			 flag = true;
		 }
		 else
		 {  
		    flag = false;
		 }  
	    }
		return flag;
	}
	
	/**
	 * ���������� �ж��ֻ����ӵ����������� </br>
	 * ����ֵ��  OFF_LINE δ������ON_LINE_GPRS GPRS���� 1�� ON_LINE_WLAN WLAN����</br>
	 * �� �� �ˣ�YAR </br>
	 * ����ʱ�䣺 2015-3-3</br>
	 * �������ͣ�0����δ������1����GPRS���磬2����WLAN��
	 * */
	public static int getWlanState(Context context)
	{
		 ConnectivityManager mConnectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		 boolean wifi=mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
		 boolean gprs=mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
		 if(gprs)
		 {
			 return 1;//GPRS��
		 }
		 else if(wifi)
		 {
			 return 2;//WIFI��
		 }
		 else
		 {
			 return 0;//����
		 }
	}
	
	/**
	 * ���������� �ж��ֻ��Ƿ���GPS </br> 
	 * �� �� �ˣ� YAR</br>
	 * ����ʱ�䣺2015-3-3</br>
	 * @param Context </br>
	 * @return true��ʾ����
	 * */
	public static boolean isGPS(Context context)
	{
		 // ͨ��GPS���Ƕ�λ����λ������Ծ�ȷ����
		 LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE); 
		 boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER); 
         // ͨ��WLAN���ƶ�����(3G/2G)ȷ����λ�ã�Ҳ����AGPS������GPS��λ����Ҫ���������ڻ��ڸ������Ⱥ��ï�ܵ����ֵȣ��ܼ��ĵط���λ�� 
         boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER); 
         if (gps) 
         { 
            return true; 
         } 
         else
         {
            return false; 
         }
	}
	
	/**
	 * ���������� ��ϵͳ�������ý��� </br>
	 * �� �� �ˣ� </br>
	 * ����ʱ�䣺 </br>
	 * */
	public static boolean openSttingForWlan(Context context)
	{
		Intent intent=null;
        //�ж��ֻ�ϵͳ�İ汾  ��API����10 ����3.0�����ϰ汾 
        if(android.os.Build.VERSION.SDK_INT>10)
        {
            intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        }
        else
        {
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        context.startActivity(intent);
		
		return true;
	}
	
	/**
	 * ���������� ��ϵͳGPS���ý��� </br>
	 * �� �� �ˣ� </br>
	 * ����ʱ�䣺2015-3-3 </br>
	 * �������ͣ�true��false</br>
	 * @param Context </br>
	 * */
	public static boolean openSttingForGPS(Context context)
	{
		    boolean flagSetting = false;
		    Intent intent = new Intent();
	        intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        try 
	        {
	            context.startActivity(intent);
	                    
	        } catch(ActivityNotFoundException ex) 
	        {
	            
	            intent.setAction(Settings.ACTION_SETTINGS);
	            try 
	            {
	            	context.startActivity(intent);
	            	flagSetting = true;
	            } catch (Exception e) 
	            {
	            	flagSetting = false;
	            }
	        }
	        return flagSetting;
	}
	
	/**
	 * ���������� ���԰��û�ֱ�ӿ���GPS </br>
	 * �� �� �ˣ� </br>
	 * ����ʱ�䣺 </br>
	 * @param context</br>
	 * */
	public static void openGPS(Context context)
	{
//		Intent GPSIntent = new Intent();
//		GPSIntent.setClassName("com.android.settings", 
//                "com.android.settings.widget.SettingsAppWidgetProvider");
//		GPSIntent.addCategory("android.intent.category.ALTERNATIVE"); 
//        GPSIntent.setData(Uri.parse("custom:3"));
//        try
//        {
//        	 PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
//        } catch (CanceledException e) 
//        { 
//            e.printStackTrace(); 
//        }
		    Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE"); 
		    intent.putExtra("enabled", true); 
		    context.sendBroadcast(intent); 
		    String provider = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED); 
		    if(!provider.contains("gps")){ //if gps is disabled  
		        final Intent poke = new Intent(); 
		        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");  
		        poke.addCategory(Intent.CATEGORY_ALTERNATIVE); 
		        poke.setData(Uri.parse("3"));  
		        context.sendBroadcast(poke); 
		    } 
		
	}
}
