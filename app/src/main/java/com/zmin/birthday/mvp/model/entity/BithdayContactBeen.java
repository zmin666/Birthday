package com.zmin.birthday.mvp.model.entity;

import java.util.List;

/**
 * @author: ZhangMin
 * @date: 2017/7/11 16:35
 * @desc:
 */
public class BithdayContactBeen {


    /**
     * code : 200
     * data : [{"head_img":"","lastlogtime":"1501510193","lunar_birthday":"0000-00-00","password":"6c350ca4c09512be12d6d614016343a7","prefer_brith":"1","realname":"'xxd'","regtime":"1496656306","sex":"0","share_data":"2","solar_birthday":"0000-00-00","supply_money":"0.00","sysid":"1","tel":"13072704383","username":"xuxiangdong"}]
     * msg : 匹配成功
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * head_img :
         * lastlogtime : 1501510193
         * lunar_birthday : 0000-00-00
         * password : 6c350ca4c09512be12d6d614016343a7
         * prefer_brith : 1
         * realname : 'xxd'
         * regtime : 1496656306
         * sex : 0
         * share_data : 2
         * solar_birthday : 0000-00-00
         * supply_money : 0.00
         * sysid : 1
         * tel : 13072704383
         * username : xuxiangdong
         */

        private String head_img;
        private String lastlogtime;
        private String lunar_birthday;
        private String password;
        private String prefer_brith;
        private String realname;
        private String regtime;
        private String sex;
        private String share_data;
        private String solar_birthday;
        private String supply_money;
        private String sysid;
        private String tel;
        private String username;

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getLastlogtime() {
            return lastlogtime;
        }

        public void setLastlogtime(String lastlogtime) {
            this.lastlogtime = lastlogtime;
        }

        public String getLunar_birthday() {
            return lunar_birthday;
        }

        public void setLunar_birthday(String lunar_birthday) {
            this.lunar_birthday = lunar_birthday;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPrefer_brith() {
            return prefer_brith;
        }

        public void setPrefer_brith(String prefer_brith) {
            this.prefer_brith = prefer_brith;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getRegtime() {
            return regtime;
        }

        public void setRegtime(String regtime) {
            this.regtime = regtime;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getShare_data() {
            return share_data;
        }

        public void setShare_data(String share_data) {
            this.share_data = share_data;
        }

        public String getSolar_birthday() {
            return solar_birthday;
        }

        public void setSolar_birthday(String solar_birthday) {
            this.solar_birthday = solar_birthday;
        }

        public String getSupply_money() {
            return supply_money;
        }

        public void setSupply_money(String supply_money) {
            this.supply_money = supply_money;
        }

        public String getSysid() {
            return sysid;
        }

        public void setSysid(String sysid) {
            this.sysid = sysid;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
