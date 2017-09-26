package com.programer.nivin.rmdr;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class Details extends AppCompatActivity {
    ListView listView;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        NotificationManager mg = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);

      listView =(ListView)findViewById(R.id.activity_list);
        DBhelper db = new DBhelper(this);
       ArrayList<Remind> contacts = (ArrayList<Remind>) db.getAllReminder();

       for (Remind cn : contacts) {
            String log = "Id: "+cn.getId()+" ,Name: " + cn.getName() + " ,category: " +
                    cn.getCategory()+" ,certificate: "+cn.getCertificate()+" ,date: "+cn.getDate();
            // Writing Contacts to log
            Log.d("Name: ", log);}
           if(adapter == null) {
               adapter = new CustomAdapter(getApplicationContext(), R.layout.details_view, contacts);

               listView.setAdapter(adapter);
           }
        mg.cancelAll();

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_viewdetails) {
            startActivity(new Intent(Details.this,Details.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
