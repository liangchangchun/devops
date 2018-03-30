package com.zww.api.model;

/**
 * Created by SUN on 2018/1/7.
 */

/**
 * 用户默认头像昵称类
 */
public class InitializeHeads {

    private String nikeName;

    public InitializeHeads(String nikeName, String headimgurl) {
        this.nikeName = nikeName;
        this.headimgurl = headimgurl;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    private String headimgurl;
}
