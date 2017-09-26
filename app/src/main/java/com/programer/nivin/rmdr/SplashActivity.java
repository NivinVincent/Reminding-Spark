package com.programer.nivin.rmdr;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;


public class SplashActivity extends ActionBarActivity {
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = (ImageView) findViewById(R.id.spl_img);
        TextView textView = (TextView)findViewById(R.id.spl_tv);
        Typeface typeface =Typeface.createFromAsset(getAssets(),"fonts/A.ttf");
        textView.setTypeface(typeface);
        Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_animation);
        imageView.startAnimation(animation);
        textView.setAnimation(animation);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {
            startActivity(new Intent(SplashActivity.this,HomePage.class));

            }

        }, 5000);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
