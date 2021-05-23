package com.jiangjf.pojo;

import java.io.Serializable;

public class Teacher implements Serializable {
    private static final long serialVersionUID = -8020312513242512225L;
    private Integer id;
    private String nickName;
    private String remark;
    private String imageName;

    public Teacher() {
    }

    public Teacher(Integer id, String nickName, String remark, String imageName) {
        this.id = id;
        this.nickName = nickName;
        this.remark = remark;
        this.imageName = imageName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
