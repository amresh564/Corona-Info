package com.aapps.coronastatus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int delay= 5;
        new Handler().postDelayed(new Runnable()
        {
            public void run()
            {
                startActivity(new Intent(Splash.this,ActivityFinal.class));
                finish();
            }
        },delay*1000);
    }
}
