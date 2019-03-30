package com.shenzhen.teamway;

public class DataBaseAInfo {

    private int CamSeq;

    private String IP;

    private int Port;

    private String UserName;

    private String Passwd;

    public int getCamSeq() {
        return CamSeq;
    }

    public void setCamSeq(int camSeq) {
        CamSeq = camSeq;
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

    @Override
    public String toString() {
        return "DataBaseAInfo{" +
                "CamSeq=" + CamSeq +
                ", IP='" + IP + '\'' +
                ", Port=" + Port +
                ", UserName='" + UserName + '\'' +
                ", Passwd='" + Passwd + '\'' +
                '}';
    }
}
