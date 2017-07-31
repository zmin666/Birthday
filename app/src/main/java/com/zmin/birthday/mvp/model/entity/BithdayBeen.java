package com.zmin.birthday.mvp.model.entity;

import java.util.List;

/**
 * @author: ZhangMin
 * @date: 2017/7/11 16:35
 * @desc:
 */
public class BithdayBeen {


    /**
     * code : 200
     * data : [{"o_addtime":"1501468621","o_del_year":"1","o_head_img":"","o_lunar_birthday":"2017-06-21","o_prefer_brith":"2","o_realname":"哈哈哈","o_sex":"2","o_solar_birthday":"2017-07-14","o_uid":"15","sysid":"62"},{"o_addtime":"1501488873","o_del_year":"2","o_head_img":"","o_lunar_birthday":"1991-07-29","o_prefer_brith":"1","o_realname":"得得","o_sex":"1","o_solar_birthday":"1991-09-07","o_uid":"15","sysid":"75"},{"o_addtime":"1501468252","o_del_year":"1","o_head_img":"","o_lunar_birthday":"2017-06-07","o_prefer_brith":"2","o_realname":"加","o_sex":"1","o_solar_birthday":"2017-07-29","o_uid":"15","sysid":"67"},{"o_addtime":"1501488847","o_del_year":"2","o_head_img":"","o_lunar_birthday":"2017-06-22","o_prefer_brith":"2","o_realname":"哦哦哦","o_sex":"2","o_solar_birthday":"2017-07-15","o_uid":"15","sysid":"74"},{"o_addtime":"1501483960","o_del_year":"1","o_head_img":"","o_lunar_birthday":"2017-07-14","o_prefer_brith":"1","o_realname":"摸摸","o_sex":"1","o_solar_birthday":"2017-09-04","o_uid":"15","sysid":"73"},{"o_addtime":"1501469541","o_del_year":"1","o_head_img":"","o_lunar_birthday":"2017-06-14","o_prefer_brith":"2","o_realname":"新增","o_sex":"1","o_solar_birthday":"2017-08-05","o_uid":"15","sysid":"70"},{"o_addtime":"1501482953","o_del_year":"1","o_head_img":"","o_lunar_birthday":"2017-07-12","o_prefer_brith":"2","o_realname":"哦哦","o_sex":"2","o_solar_birthday":"2017-09-02","o_uid":"15","sysid":"71"},{"o_addtime":"1501489446","o_del_year":"1","o_head_img":"","o_lunar_birthday":"1991-07-29","o_prefer_brith":"1","o_realname":"自由自在","o_sex":"1","o_solar_birthday":"1991-09-07","o_uid":"15","sysid":"76"},{"o_addtime":"1501490002","o_del_year":"2","o_head_img":"","o_lunar_birthday":"2017-06-08","o_prefer_brith":"2","o_realname":"摸摸","o_sex":"2","o_solar_birthday":"2017-07-01","o_uid":"15","sysid":"77"},{"o_addtime":"1501490113","o_del_year":"2","o_head_img":"","o_lunar_birthday":"2017-06-15","o_prefer_brith":"2","o_realname":"摸摸哦哦哦","o_sex":"2","o_solar_birthday":"2017-07-08","o_uid":"15","sysid":"78"},{"o_addtime":"1501490286","o_del_year":"2","o_head_img":"","o_lunar_birthday":"2017-06-08","o_prefer_brith":"2","o_realname":"摸摸","o_sex":"1","o_solar_birthday":"2017-07-01","o_uid":"15","sysid":"79"},{"o_addtime":"1501490365","o_del_year":"2","o_head_img":"","o_lunar_birthday":"2017-06-14","o_prefer_brith":"2","o_realname":"摸摸","o_sex":"1","o_solar_birthday":"2017-07-07","o_uid":"15","sysid":"80"}]
     * msg : 查询成功
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
         * o_addtime : 1501468621
         * o_del_year : 1
         * o_head_img :
         * o_lunar_birthday : 2017-06-21
         * o_prefer_brith : 2
         * o_realname : 哈哈哈
         * o_sex : 2
         * o_solar_birthday : 2017-07-14
         * o_uid : 15
         * sysid : 62
         */

        private String o_addtime;
        private String o_del_year;
        private String o_head_img;
        private String o_lunar_birthday;
        private String o_prefer_brith;
        private String o_realname;
        private String o_sex;
        private String o_solar_birthday;
        private String o_uid;
        private String sysid;

        public String getO_addtime() {
            return o_addtime;
        }

        public void setO_addtime(String o_addtime) {
            this.o_addtime = o_addtime;
        }

        public String getO_del_year() {
            return o_del_year;
        }

        public void setO_del_year(String o_del_year) {
            this.o_del_year = o_del_year;
        }

        public String getO_head_img() {
            return o_head_img;
        }

        public void setO_head_img(String o_head_img) {
            this.o_head_img = o_head_img;
        }

        public String getO_lunar_birthday() {
            return o_lunar_birthday;
        }

        public void setO_lunar_birthday(String o_lunar_birthday) {
            this.o_lunar_birthday = o_lunar_birthday;
        }

        public String getO_prefer_brith() {
            return o_prefer_brith;
        }

        public void setO_prefer_brith(String o_prefer_brith) {
            this.o_prefer_brith = o_prefer_brith;
        }

        public String getO_realname() {
            return o_realname;
        }

        public void setO_realname(String o_realname) {
            this.o_realname = o_realname;
        }

        public String getO_sex() {
            return o_sex;
        }

        public void setO_sex(String o_sex) {
            this.o_sex = o_sex;
        }

        public String getO_solar_birthday() {
            return o_solar_birthday;
        }

        public void setO_solar_birthday(String o_solar_birthday) {
            this.o_solar_birthday = o_solar_birthday;
        }

        public String getO_uid() {
            return o_uid;
        }

        public void setO_uid(String o_uid) {
            this.o_uid = o_uid;
        }

        public String getSysid() {
            return sysid;
        }

        public void setSysid(String sysid) {
            this.sysid = sysid;
        }
    }
}
