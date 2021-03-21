package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;


public class MainActivity extends AppCompatActivity {

    ImageView bgapp, clover,img_nd, img_sach, img_theloai, img_hoaDonn, img_topMonAn, img_TK ;
    LinearLayout textsplash, texthome, menus;
    Animation frombottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* ẩn ActionBar */
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

//        setContentView(R.layout.activity_loading_screen);
        setContentView(R.layout.activity_main);
        //setID
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);

        bgapp = (ImageView) findViewById(R.id.bgapp);
        clover=(ImageView) findViewById(R.id.clover);
        textsplash = (LinearLayout) findViewById(R.id.textsplash);
        texthome = (LinearLayout) findViewById(R.id.texthome);
        menus = (LinearLayout) findViewById(R.id.menus);
        img_nd=(ImageView) findViewById(R.id.img_nd);
        img_sach=(ImageView) findViewById(R.id.img_Sach);
        img_theloai = (ImageView) findViewById(R.id.img_theloai);
        img_hoaDonn = findViewById(R.id.img_hoaDon);
        img_topMonAn = findViewById(R.id.img_sachBC);
        img_TK = findViewById(R.id.img_thongKe);
        /* Sk click */
//        img_nd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, User_activity.class);
//                startActivity(intent);
//            }
//        });

        img_theloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListTheLoaiActivity.class);
                startActivity(intent);
            }
        });

        img_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListMonAnActivity.class);
                startActivity(intent);
            }
        });

        img_hoaDonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListHoaDonActivity.class);
                startActivity(intent);
            }
        });

        img_topMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListBanChayActivity.class);
                startActivity(intent);
            }
        });

        img_TK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListThongKeActivity.class);
                startActivity(intent);
            }
        });


        /* Animation view */
        bgapp.animate().translationY(-1900).setDuration(800).setStartDelay(300);
        clover.animate().alpha(0).setDuration(800).setStartDelay(600);
        textsplash.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(300);

        texthome.startAnimation(frombottom);
        menus.startAnimation(frombottom);
    }
}