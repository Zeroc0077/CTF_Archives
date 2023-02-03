package gdufs.challenge.web.model;

import java.io.Serializable;

/* loaded from: web-0.0.1-SNAPSHOT.jar:BOOT-INF/classes/gdufs/challenge/web/model/UserInfo.class */
public class UserInfo implements Serializable, Info {
    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    @Override // gdufs.challenge.web.model.Info
    public Boolean checkAllInfo() {
        return Boolean.valueOf((this.username == null || this.password == null) ? false : true);
    }

    @Override // gdufs.challenge.web.model.Info
    public String getAllInfo() {
        return "Your username is " + this.username + ", and your password is " + this.password + ".";
    }
}
