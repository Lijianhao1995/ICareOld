package com.example.thinkpad.icareold.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.thinkpad.icareold.R;

import android.os.Handler;
import android.widget.Toast;

import java.util.logging.LogRecord;

/**
 * Created by Thinkpad on 2015/11/7.
 */
public class LoginActivity extends Activity {
    public static final int ALLOW_TO_LOGIN=1;
    public static final int FORBID_TO_LOGIN=0;
    private EditText userName;
    private EditText pwd;
    private ImageButton login;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.activtiy_login);
        userName=(EditText) findViewById(R.id.editUserName);
        pwd=(EditText) findViewById(R.id.editPwd);
        login=(ImageButton) findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setBackgroundResource(R.drawable.loginactivity_login_forbid);
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
