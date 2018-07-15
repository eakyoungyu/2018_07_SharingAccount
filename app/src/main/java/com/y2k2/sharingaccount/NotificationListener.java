package com.y2k2.sharingaccount;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

/**
 * Created by my on 2018-07-10.
 */

public class NotificationListener extends NotificationListenerService {
    private static final String TAG=NotificationListener.class.getSimpleName();
    private StatementHandlerByBank statementHandlerByBank=new StatementHandlerByBank();
    private String bankName;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Log.d(TAG, "onNotificationPosted() - " + sbn.toString());
        Log.d(TAG, "PackageName:" + sbn.getPackageName());
        Log.d(TAG, "PostTime:" + sbn.getPostTime());

        NotificationInfo notificationInfo=new NotificationInfo();
        Notification notificatin = sbn.getNotification();
        Bundle extras = notificatin.extras;
        notificationInfo.setPackageName(sbn.getPackageName());
        notificationInfo.setTitle(extras.getString(Notification.EXTRA_TITLE));
        int smallIconRes = extras.getInt(Notification.EXTRA_SMALL_ICON);
        Bitmap largeIcon = ((Bitmap) extras.getParcelable(Notification.EXTRA_LARGE_ICON));
        notificationInfo.setText(extras.getCharSequence(Notification.EXTRA_TEXT));
        notificationInfo.setSubText(extras.getCharSequence(Notification.EXTRA_SUB_TEXT));


        bankName=statementHandlerByBank.checkPackageName(notificationInfo.getPackageName());
        if(bankName!=null && notificationInfo.getText()!=null) {
            Log.d(TAG, "extractBankStatementInfo()" + notificationInfo.getText());
            Intent statementInfo=statementHandlerByBank.extractBankStatementInfo(notificationInfo, bankName);
            sendBroadcast(statementInfo);
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.d(TAG, "onNotificationRemoved() - " + sbn.toString());
    }

}