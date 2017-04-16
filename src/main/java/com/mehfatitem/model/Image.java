package com.mehfatitem.model;

// Image classi (ORM)
public class Image {

    public int imgId;
    public String imgName;
    public String imgDesc;
    public long imgLength;
    public long saveDate;
    public int userId;

    public Image(String imgName, String imgDesc, long imgLength, long saveDate, int userId) {
        this.imgName = imgName;
        this.imgDesc = imgDesc;
        this.imgLength = imgLength;
        this.saveDate = saveDate;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Image() {

    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    public long getImgLength() {
        return imgLength;
    }

    public void setImgLength(long imgLength) {
        this.imgLength = imgLength;
    }

    public long getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(long saveDate) {
        this.saveDate = saveDate;
    }
}
