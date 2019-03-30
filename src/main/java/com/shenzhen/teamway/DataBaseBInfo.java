package com.shenzhen.teamway;

public class DataBaseBInfo {

    private int ipc_channel;

    private String IP;

    private int Port;

    private String UserName;

    private String Passwd;

    public int getIpc_channel() {
        return ipc_channel;
    }

    public void setIpc_channel(int ipc_channel) {
        this.ipc_channel = ipc_channel;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getPort() {
        return Port;
    }

    public void setPort(int port) {
        Port = port;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPasswd() {
        return Passwd;
    }

    public void setPasswd(String passwd) {
        Passwd = passwd;
    }
}
