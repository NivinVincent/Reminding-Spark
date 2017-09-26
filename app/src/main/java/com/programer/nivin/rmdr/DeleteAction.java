package com.programer.nivin.rmdr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Nivin Vincent on 9/25/2017.
 */
public class DeleteAction extends BroadcastReceiver {
    Intent in;
    int id;
    @Override
    public void onReceive(Context context, Intent intent) {
        id = intent.getIntExtra("id",0);
        DBhelper dBhelper = new DBhelper(context);
        dBhelper.removedata(id);



    }
}
