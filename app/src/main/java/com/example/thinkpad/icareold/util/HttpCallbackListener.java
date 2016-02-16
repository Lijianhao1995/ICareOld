package com.example.thinkpad.icareold.util;

/**
 * Created by Lijianhao on 2015/12/7.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
