package com.programer.nivin.rmdr;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Calendar;


public class HomePage extends AppCompatActivity {
android.support.v7.widget.Toolbar toolbar;
    Spinner spinner,vehicle_spinner;
    String selected,spinner_item;
    ImageView imageView;
    final String bday="";
    EditText edt_home_name;
    TextView txtv_home_date;
    Button btn_home_submit;
    LinearLayout layout;
    DBhelper dBhelper;
    static int img_flag=0;
    AlertDialog alert;
    //AlertDialog alertDialog;
    int year,month,day;
    //Array for spinner
    String Spin_array[]={"Select the category","Birthday","vehicle"};
    String Spin_array1[]={"Select the Certificate","Insurance","Tax","Pollution"};

    ArrayAdapter<String> adapter,adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        dBhelper = new DBhelper(this);

        edt_home_name = (EditText)findViewById(R.id.et_home_name);
        txtv_home_date = (TextView)findViewById(R.id.tv_home_date);
        imageView = (ImageView)findViewById(R.id.home_image);
        btn_home_submit = (Button)findViewById(R.id.btn_home_submit);
        vehicle_spinner =(Spinner)findViewById(R.id.drop_down_vehicle);
        layout = (LinearLayout)findViewById(R.id.birthday_layout);

        Calendar cal_time = Calendar.getInstance();
        cal_time.set(Calendar.HOUR_OF_DAY, 11);
        cal_time.set(Calendar.MINUTE, 50);
        cal_time.set(Calendar.SECOND, 03);

        Intent pop_up_time = new Intent(HomePage.this,MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(),100, pop_up_time,
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, cal_time.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);


                //.......... Date picker ......
        txtv_home_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(HomePage.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date = String.valueOf(dayOfMonth)+"-"+String.valueOf(monthOfYear+1);
                        txtv_home_date.setText(date);
                    }
                }, year, month, day);
                datePicker.show();

            }
        });



        //.......... Button submit ...........

        btn_home_submit.setOnClickListener(new View.OnClickListener() {
            String name, category, date,v_spinner;

            @Override
            public void onClick(View v) {

                    if(selected.equals("Birthday"))
                    {
                        name = edt_home_name.getText().toString();
                        date = txtv_home_date.getText().toString();
                        if (name.trim().equals("")) {
                            AlertMessage("Error Message", "Submit failed...\nName Required..");
                        /*alertDialog.setMessage("Submit failed...\nName Required..");
                        alertDialog.show();*/
                        } else if (date.equals("Select the Date")) {
                            AlertMessage("Error Message", "Submit failed...\nSelect the Date..");
                           /* alertDialog.setMessage("Submit failed...\nSelect the Date..");
                            alertDialog.show();*/
                        } else {


                            dBhelper.addValue(new Remind(name,spinner_item,bday,date));


                            AlertMessage("Success Message", "Submit Successfull......\nView option, Top Right..\n" + "category = " + selected + "\nNmae = " + name + "\nDate = " + date);
                            layout.setVisibility(View.GONE);
                            imageView.setVisibility(View.INVISIBLE);
                            edt_home_name.setText("");
                            txtv_home_date.setText("Select the Date");
                            spinner.setAdapter(adapter);
                        /*alertDialog.setMessage("Submit Successfull......\n"+"category = "+selected+"\nNmae = "+name+"\nDate = "+date);
                        alertDialog.show();*/
                        }
                    }else {
                        name = edt_home_name.getText().toString();
                        date = txtv_home_date.getText().toString();
                        //v_spinner = vehicle_spinner.getSelectedItem().toString();
                        if (name.trim().equals("")) {
                            AlertMessage("Error Message", "Submit failed...\nName Required..");
                        }else if(vehicle_spinner.getSelectedItem().toString().equals("Select the Certificate")){
                            AlertMessage("Error Message", "Submit failed...\nSelect the Certificate..");

                        }else if (date.equals("Select the Date")) {
                                AlertMessage("Error Message", "Submit failed...\nSelect the Date..");
                        }else {
                            v_spinner = vehicle_spinner.getSelectedItem().toString();
                            dBhelper.addValue(new Remind(name,spinner_item,v_spinner,date));

                            AlertMessage("Success Message", "Submit Successfull......\nView option, Top Right..\n" + "category = " + selected + "\nName = " + name +"\nCertificate = "+v_spinner+ "\nDate = " + date);
                            layout.setVisibility(View.GONE);
                            imageView.setVisibility(View.INVISIBLE);
                            edt_home_name.setText("");
                            txtv_home_date.setText("Select the Date");
                            vehicle_spinner.setAdapter(adapter1);
                            spinner.setAdapter(adapter);
                        }

                    }


            }
        });

        //........ submit button close .........


        // ..............  toolbar start  ...........
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar1);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //............... toolbar close ..............

        //........ spinner start .....
        spinner =(Spinner)findViewById(R.id.drop_down);

        adapter = new ArrayAdapter<String>(HomePage.this,R.layout.spinner_item,Spin_array);
        spinner.setAdapter(adapter);
        adapter1 = new ArrayAdapter<String>(HomePage.this,R.layout.spinner_item,Spin_array1);
        vehicle_spinner.setAdapter(adapter1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = spinner.getSelectedItem().toString();

                if (!selected.equals("Select the category")) {
                    spinner_item = selected;
                    if (selected.equals("Birthday")) {
                        img_flag=1;
                        layout.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.VISIBLE);
                        vehicle_spinner.setVisibility(View.GONE);
                        imageView.setImageResource(R.drawable.birthday_wish);
                        spinner.setSelection(position);
                    } else if (selected.equals("vehicle")) {
                        img_flag=2;
                        layout.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.VISIBLE);
                        vehicle_spinner.setVisibility(View.VISIBLE);
                        imageView.setImageResource(R.drawable.vehicles);
                        spinner.setSelection(position);
                    }

                }else{
                    imageView.setVisibility(View.INVISIBLE);
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //......... spinner close ...........



    }

public void AlertMessage(String title, String msg){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(title);
    builder.setMessage(msg)
            .setCancelable(false)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //do things
                    alert.dismiss();
                }
            });
    alert = builder.create();
    alert.show();


}

    public void Pending_Intent_class(){

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_viewdetails) {
            startActivity(new Intent(HomePage.this,Details.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

    }
}
