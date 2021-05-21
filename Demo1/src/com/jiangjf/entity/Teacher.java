package com.jiangjf.entity;

import java.io.Serializable;

public class Teacher implements Serializable {
    private static final long serialVersionUID = 5888712670436851602L;
    private Integer id;
    private String nickName;
    private String remark;
    private String imageName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Teacher() {
    }

    public Teacher(int id, String nickName, String remark, String imageName) {
        this.id = id;
        this.nickName = nickName;
        this.remark = remark;
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", remark='" + remark + '\'' +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}
