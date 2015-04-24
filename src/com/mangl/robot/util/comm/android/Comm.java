package com.mangl.robot.util.comm.android;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import android.content.Context;
import android.content.res.Configuration;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 常用工具类
 * 
 * @author baib
 * @date 2014-09-18
 * */
public class Comm {
	private static long lastClickTime;

	/**
	 * 处理按钮被连续点击的问题。
	 * 
	 * @param ms
	 *            毫秒
	 * @return boolean 是否在这段时间内连续点击
	 * */
	public static boolean isFastDoubleClick(int ms) {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < ms) {
			return true;
		}
		lastClickTime = time;
		return false;
	}

	/**
	 * 深度复制内存对象，处理在复制对象特别是集合类时的浅复制
	 * 
	 * @param srcObj
	 *            复制的目标
	 * @return cloneObj 复制后的对象（完全独立的个体）
	 * */
	public static Object depthClone(Object srcObj) {
		Object cloneObj = null;
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(out);
			oo.writeObject(srcObj);

			ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
			ObjectInputStream oi = new ObjectInputStream(in);
			cloneObj = oi.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cloneObj;
	}

	/**
	 * 判断是否横屏
	 * 
	 * @return true 横屏,false 竖屏
	 */
	public static boolean isLand(Context context) {
		Configuration cf = context.getResources().getConfiguration();
		int ori = cf.orientation;
		if (ori == Configuration.ORIENTATION_LANDSCAPE) {
			return true;
		} else if (ori == Configuration.ORIENTATION_PORTRAIT) {
			return false;
		}
		return false;
	}

	/**
	 * 打卡软键盘
	 * 
	 * @param mEditText输入框
	 * @param mContext上下文
	 */
	public static void openKeybord(EditText mEditText, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
	}

	/**
	 * 关闭软键盘
	 * 
	 * @param mEditText输入框
	 * @param mContext上下文
	 */
	public static void closeKeybord(EditText mEditText, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

		imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
	}
}
