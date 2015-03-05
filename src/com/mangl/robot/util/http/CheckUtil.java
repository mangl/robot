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
	 * 创 建 人： YAR</br>
	 * 创建时间： 2015-3-3</br>
	 * 返回类型： true 或 false </br>
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
	 * 方法描述： 判断手机连接的是哪种网络 </br>
	 * 返回值：  OFF_LINE 未联网，ON_LINE_GPRS GPRS连接 1， ON_LINE_WLAN WLAN连接</br>
	 * 创 建 人：YAR </br>
	 * 创建时间： 2015-3-3</br>
	 * 返回类型：0——未联网，1——GPRS网络，2——WLAN。
	 * */
	public static int getWlanState(Context context)
	{
		 ConnectivityManager mConnectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		 boolean wifi=mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
		 boolean gprs=mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
		 if(gprs)
		 {
			 return 1;//GPRS网
		 }
		 else if(wifi)
		 {
			 return 2;//WIFI网
		 }
		 else
		 {
			 return 0;//无网
		 }
	}
	
	/**
	 * 方法描述： 判断手机是否开启GPS </br> 
	 * 创 建 人： YAR</br>
	 * 创建时间：2015-3-3</br>
	 * @param Context </br>
	 * @return true表示开启
	 * */
	public static boolean isGPS(Context context)
	{
		 // 通过GPS卫星定位，定位级别可以精确到街
		 LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE); 
		 boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER); 
         // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位） 
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
	 * 方法描述： 打开系统网络设置界面 </br>
	 * 创 建 人： </br>
	 * 创建时间： </br>
	 * */
	public static boolean openSttingForWlan(Context context)
	{
		Intent intent=null;
        //判断手机系统的版本  即API大于10 就是3.0或以上版本 
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
	 * 方法描述： 打开系统GPS设置界面 </br>
	 * 创 建 人： </br>
	 * 创建时间：2015-3-3 </br>
	 * 返回类型：true或false</br>
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
	 * 方法描述： 尝试帮用户直接开启GPS </br>
	 * 创 建 人： </br>
	 * 创建时间： </br>
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
