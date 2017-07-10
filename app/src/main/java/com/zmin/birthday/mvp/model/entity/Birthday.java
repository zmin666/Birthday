package com.zmin.birthday.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author: ZhangMin
 * @date: 2017/5/23 17:00
 * @desc:
 */
@Entity
public class Birthday implements Parcelable {
    @Id
    private String id;
    @Property(nameInDb = "NAME")
    private String name;
    //农历生日
    @Property(nameInDb = "OLD_YEAR")
    private String old_birth;
    //阳历生日
    @Property(nameInDb = "YEAR")
    private String birth;
    //生日偏好
    @Property(nameInDb = "PERFER")
    private String perfer;
    //性别
    @Property(nameInDb = "SEX")
    private String sex;

    @Keep
    public Birthday(String id, String name, String old_birth, String birth, String perfer, String sex) {
        this.id = id;
        this.name = name;
        this.old_birth = old_birth;
        this.birth = birth;
        this.perfer = perfer;
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOld_birth() {
        return old_birth;
    }

    public void setOld_birth(String old_birth) {
        this.old_birth = old_birth;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPerfer() {
        return perfer;
    }

    public void setPerfer(String perfer) {
        this.perfer = perfer;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.old_birth);
        dest.writeString(this.birth);
        dest.writeString(this.perfer);
        dest.writeString(this.sex);
    }

    protected Birthday(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.old_birth = in.readString();
        this.birth = in.readString();
        this.perfer = in.readString();
        this.sex = in.readString();
    }

    @Generated(hash = 1467867887)
    public Birthday() {
    }

    public static final Parcelable.Creator<Birthday> CREATOR = new Parcelable.Creator<Birthday>() {
        @Override
        public Birthday createFromParcel(Parcel source) {
            return new Birthday(source);
        }

        @Override
        public Birthday[] newArray(int size) {
            return new Birthday[size];
        }
    };
}
