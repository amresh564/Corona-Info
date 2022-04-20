package com.aapps.coronastatus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;

import java.io.InputStream;

public class ActivityFinal extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    Bitmap bitmap;
    ImageView img;
    String URLIMAGE="https://www.mygov.in/sites/all/themes/mygov/images/covid/block-one.png";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        img=(ImageView)findViewById(R.id.iMageview);

        //call

        new GetImageFromURL(img).execute(URLIMAGE);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Email =new Intent(Intent.ACTION_SENDTO);
                Email.setType("text/Email");
                Email.putExtra(Intent.EXTRA_EMAIL,new String[]{"amigoxboy@gmail.com"});
                Email.putExtra(Intent.EXTRA_SUBJECT,"FeedBack");
                Email.putExtra(Intent.EXTRA_TEXT,"Dear Amresh,"+"");
                startActivity(Intent.createChooser(Email,"Send FeedBack:"));

                Snackbar.make(view, "Sending EMail To Developer", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public class GetImageFromURL extends AsyncTask<String,Void, Bitmap>
    {

        ImageView imgV;
        public GetImageFromURL(ImageView imgV)
        {
         this.imgV=imgV;
        }

        @Override
        protected Bitmap doInBackground(String... url) {
            String urlDisplay=url[0];
            bitmap =null;

            try
            {
                InputStream srt= new java.net.URL(urlDisplay).openStream();
                bitmap=BitmapFactory.decodeStream(srt);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return bitmap;

        }

        @Override
        protected void onPostExecute(Bitmap bitmap)
        {
            super.onPostExecute(bitmap);
            imgV.setImageBitmap(bitmap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_final, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
