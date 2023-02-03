package gdufs.challenge.web.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

/* loaded from: web-0.0.1-SNAPSHOT.jar:BOOT-INF/classes/gdufs/challenge/web/model/DatabaseInfo.class */
public class DatabaseInfo implements Serializable, Info {
    private String host;
    private String port;
    private String username;
    private String password;
    private Connection connection;

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return this.host;
    }

    public String getPort() {
        return this.port;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Connection getConnection() {
        if (this.connection == null) {
            connect();
        }
        return this.connection;
    }

    private void connect() {
        String url = "jdbc:mysql://" + this.host + ":" + this.port + "/jdbc?user=" + this.username + "&password=" + this.password + "&connectTimeout=3000&socketTimeout=6000";
        try {
            this.connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // gdufs.challenge.web.model.Info
    public Boolean checkAllInfo() {
        if (this.host == null || this.port == null || this.username == null || this.password == null) {
            return false;
        }
        if (this.connection == null) {
            connect();
        }
        return true;
    }

    @Override // gdufs.challenge.web.model.Info
    public String getAllInfo() {
        return "Here is the configuration of database, host is " + this.host + ", port is " + this.port + ", username is " + this.username + ", password is " + this.password + ".";
    }
}
