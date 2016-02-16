package com.example.thinkpad.icareold;

import android.content.Context;

import com.example.thinkpad.icareold.db.DemoDBManager;
import com.example.thinkpad.icareold.db.UserDao;
import com.example.thinkpad.icareold.domain.RobotUser;
import com.example.thinkpad.icareold.domain.User;
import com.example.thinkpad.icareold.model.DefaultHXSDKModel;

import java.util.List;
import java.util.Map;

/**
 * Created by Lijianhao on 2016/2/1.
 */
public class DemoHXSDKModel extends DefaultHXSDKModel {
    public DemoHXSDKModel(Context ctx) {
        super(ctx);
    }

    public boolean getUseHXRoster() {
        return true;
    }

    // demo will switch on debug mode
    public boolean isDebugMode(){
        return true;
    }

    public boolean saveContactList(List<User> contactList) {
        UserDao dao = new UserDao(context);
        dao.saveContactList(contactList);
        return true;
    }

    public Map<String, User> getContactList() {
        UserDao dao = new UserDao(context);
        return dao.getContactList();
    }

    public void saveContact(User user){
        UserDao dao = new UserDao(context);
        dao.saveContact(user);
    }

    public Map<String, RobotUser> getRobotList(){
        UserDao dao = new UserDao(context);
        return dao.getRobotUser();
    }

    public boolean saveRobotList(List<RobotUser> robotList){
        UserDao dao = new UserDao(context);
        dao.saveRobotUser(robotList);
        return true;
    }

    public void closeDB() {
        DemoDBManager.getInstance().closeDB();
    }

    @Override
    public String getAppProcessName() {
        return context.getPackageName();
    }


}
