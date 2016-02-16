package com.example.thinkpad.icareold.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.thinkpad.icareold.R;
import com.example.thinkpad.icareold.util.HttpCallbackListener;
import com.example.thinkpad.icareold.util.HttpUtil;
import com.example.thinkpad.icareold.util.Utility;

import org.w3c.dom.Text;
/**/


public class MainActivity extends Activity {
    private TextView City;
    private TextView Temperature;
    private TextView Weather;
    private Button refreshButton;
    private RelativeLayout weatherinfoLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.activity_main);
        City=(TextView) findViewById(R.id.weatherCity);
        Temperature=(TextView) findViewById(R.id.temperature);
        Weather=(TextView) findViewById(R.id.weather);
        refreshButton=(Button) findViewById(R.id.refresh_weather);
        weatherinfoLayout=(RelativeLayout) findViewById(R.id.weather_info_layout);
        String cityCode=getIntent().getStringExtra("city_code");
        queryWeatherInfo("101010100");

    }
    private  void queryWeatherCode(String cityCode){
        String address="http://1.weatherapi.sinaapp.com/city"+cityCode;
        queryFromServer(address,"cityCode");
    }

    private void  queryWeatherInfo(String weatherCode){
        String address="http://wthrcdn.etouch.cn/weather_mini?citykey="+weatherCode;
        queryFromServer(address,"weatherCode");

    }
    private void queryFromServer(final String address,final String type){
        HttpUtil.sendHttpRequest(address,new HttpCallbackListener() {
            @Override
            public void onFinish(final String response) {
                if("cityCode".equals(type)) {
                    if (!TextUtils.isEmpty(response)) {
                        //解析出天气代号
                        //这段再考虑考虑吧
                        //queryWeatherInfo("101010800");
                    }
                }
                    else if("weatherCode".equals(type)){
                        Utility.handleWeatherResponse(MainActivity.this,response);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showWeather();
                            }
                        });
                    }
                }


            @Override
            public void onError(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Weather.setText("同步天气失败");
                    }
                });

            }
        });
    }
    private void showWeather(){
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        City.setText("北京");
        Temperature.setText(prefs.getString("temp1","")+prefs.getString("temp2",""));
        Weather.setText(prefs.getString("weather_desp",""));
        weatherinfoLayout.setVisibility(View.VISIBLE);
        City.setVisibility(View.VISIBLE);

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
