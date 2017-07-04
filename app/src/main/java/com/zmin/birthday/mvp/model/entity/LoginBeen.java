package com.zmin.birthday.mvp.model.entity;

/**
 * @author: ZhangMin
 * @date: 2017/7/4 11:28
 * @desc:
 */
public class LoginBeen {


    /**
     * code : 200
     * data : {"lastlogtime":"1499140638","lunar_birthday":"0000-00-00","password":"1c19bcd76f4e393af31e2b4544a379de","prefer_brith":"0","realname":"","regtime":"1499140080","sex":"3","solar_birthday":"0000-00-00","supply_money":"0.00","sysid":"4","tel":"","username":"12345678"}
     * msg : 登录成功
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * lastlogtime : 1499140638
         * lunar_birthday : 0000-00-00
         * password : 1c19bcd76f4e393af31e2b4544a379de
         * prefer_brith : 0
         * realname :
         * regtime : 1499140080
         * sex : 3
         * solar_birthday : 0000-00-00
         * supply_money : 0.00
         * sysid : 4
         * tel :
         * username : 12345678
         */

        private String lastlogtime;
        private String lunar_birthday;
        private String password;
        private String prefer_brith;
        private String realname;
        private String regtime;
        private String sex;
        private String solar_birthday;
        private String supply_money;
        private String sysid;
        private String tel;
        private String username;

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
