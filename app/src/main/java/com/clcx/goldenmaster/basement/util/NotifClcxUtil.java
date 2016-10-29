package com.clcx.goldenmaster.basement.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.clcx.goldenmaster.R;

/**
 * 通知工具类
 * 
 * @author Nosensmile_L
 * 
 */
public class NotifClcxUtil {

	private NotificationManager manager;

	// 双重检查加锁
	private static NotifClcxUtil instance = null;

	private NotifClcxUtil() {
	}

	public static NotifClcxUtil getInstance() {
		if (instance == null) {
			synchronized (NotifClcxUtil.class) {
				if (instance == null) {
					instance = new NotifClcxUtil();
				}
			}
		}
		return instance;
	}

	/**
	 * 
	 * @param notiId
	 *            根据这个判断取消哪一条通知
	 * @param context
	 * @param notifyContent
	 *            通知文字内容
	 * @param intentRequestCode
	 *            intent的请求码
	 * @param cls
	 *            点击后跳转到的目标activity
	 */
	public void createNormalNotification(int notiId, Context context,
			String notifyContent, int intentRequestCode, Class<?> cls) {

		manager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification mNotification = new Notification();
		mNotification.icon = R.mipmap.ic_launcher;
		mNotification.tickerText = context.getResources().getString(
				R.string.app_name)
				+ ":您有新消息！";
		mNotification.when = System.currentTimeMillis();
		mNotification.number = 1;
		mNotification.flags = Notification.FLAG_AUTO_CANCEL; // FLAG_AUTO_CANCEL表明当通知被用户点击时，通知将被清除。

		RemoteViews rView = new RemoteViews(context.getPackageName(),
				R.layout.notification_remote_view);
		rView.setTextViewText(R.id.noti_tv_content, notifyContent);

		mNotification.contentView = rView;
		mNotification.contentIntent = PendingIntent.getActivity(context,
				intentRequestCode, new Intent(context, cls),
				PendingIntent.FLAG_UPDATE_CURRENT);

		manager.notify(notiId, mNotification);
		rView = null;
		mNotification = null;

	}

	/**
	 * 取消通知
	 * 
	 * @param notiId
	 *            跟上面的那个对应
	 */
	public void cancelNotification(int notiId) {
		if (manager != null) {
			manager.cancel(notiId);
		}

	}
}
