package com.programer.nivin.rmdr;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Nivin Vincent on 9/22/2017.
 */
public class MyReceiver extends BroadcastReceiver {
   // private static final int NOTIFY_ME_ID=1337;
    Calendar calendar;
    int date,month;
    String final_date,names;
    Context context;
    StringBuilder s,insurance,tax,polution;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context=context;
        DBhelper db = new DBhelper(context);
        calendar = Calendar.getInstance();
        List<Remind> contacts = db.getAllReminder();
        date = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        final_date = String.valueOf(date + 1)+"-"+String.valueOf(month+1);
        Log.i("final date", final_date);
        s = new StringBuilder();
        insurance = new StringBuilder();
        tax = new StringBuilder();
        polution = new StringBuilder();
        for (Remind cn : contacts) {
            String redate = cn.getDate();
            String cat = cn.getCategory();

            Log.i("redate..",redate);
            if (final_date.equals(redate)) {
                if(cat.equals("Birthday")){
                    s.append(cn.getName());
                    s.append(", ");
                    Log.i("append ..",s+"");
                    Note(s+"\n","Birthday\n",11221);
                }else if(cat.equals("vehicle")){
                    String cert=cn.getCertificate();
                    if(cert.equals("Insurance")){
                        insurance.append(cn.getName());
                        insurance.append(", ");
                        Note(insurance+"\n","Insurance Last date ",33243);
                        Log.i("ap_Insur",insurance+"");

                    }else if(cert.equals("Tax")){
                        tax.append(cn.getName());
                        tax.append(", ");
                        Note(tax+"","Tax last date ",5665);
                        Log.i("ap_tax..",tax+"");

                    }else if(cert.equals("Pollution")){
                        polution.append(cn.getName());
                        polution.append(", ");
                        Note(polution+"","Pollution last date ",76787);
                        Log.i("ap_pol..",polution+"");

                    }else {
                        Log.i("vehicle","else part");
                    }
                }else {

                }


            }else{
                Log.i("elseeee","eeeee");
            }


        }


    }


public void Note(String text,String type, int NOTIFY_ME_ID) {
    Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

    NotificationCompat.Builder mBuilder =
            (NotificationCompat.Builder) new NotificationCompat.Builder(context.getApplicationContext())
                    .setSmallIcon(R.drawable.ic_action_view)
                    .setContentTitle("Reminder")
                    .setSound(alarmSound)
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                    .setContentText(text+"'s "+type +" is tommarrow");

    Intent intent = new Intent(context, Details.class);
    PendingIntent pIntent = PendingIntent.getActivity(context, NOTIFY_ME_ID, intent, 0);
    mBuilder.setContentIntent(pIntent);
    NotificationManager mNotificationManager =

            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


    mNotificationManager.notify(NOTIFY_ME_ID, mBuilder.build());
    Log.i("Rec....", "enter..");




}
}