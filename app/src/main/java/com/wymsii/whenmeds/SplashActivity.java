package com.wymsii.whenmeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

public class SplashActivity extends AppCompatActivity {
    private long ms=0;
    private long splashTime = 2000;
    private boolean splashActive = true;
    private boolean pausd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(KeyEvent.KEYCODE_MENU == keyCode){
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if(KeyEvent.KEYCODE_BACK == keyCode){
            finish();

        }
        return false;
    }
}
