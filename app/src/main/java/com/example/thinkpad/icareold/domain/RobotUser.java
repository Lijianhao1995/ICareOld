package com.example.thinkpad.icareold.domain;

import com.easemob.chat.EMContact;

/**
 * Created by Lijianhao on 2016/2/1.
 */
public class RobotUser extends EMContact{
    private String username;
    private String nick;
    private String header;
    private String avatar;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getNick() {
        return nick;
    }
    public void setNick(String nick) {
        this.nick = nick;
    }
    public String getHeader() {
        return header;
    }
    public void setHeader(String header) {
        this.header = header;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
