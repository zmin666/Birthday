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
     * data : [{"o_addtime":"1499670242","o_head_img":"","o_lunar_birthday":"0000-00-00","o_prefer_brith":"1","o_realname":"小潘","o_sex":"1","o_solar_birthday":"2017-07-20","o_uid":"5","sysid":"1"},{"o_addtime":"1499670554","o_head_img":"","o_lunar_birthday":"0000-00-00","o_prefer_brith":"1","o_realname":"小潘","o_sex":"1","o_solar_birthday":"2017-07-20","o_uid":"5","sysid":"2"},{"o_addtime":"1499676846","o_head_img":"","o_lunar_birthday":"0000-00-00","o_prefer_brith":"1","o_realname":"呵呵哈哈哈","o_sex":"2","o_solar_birthday":"2017-07-07","o_uid":"5","sysid":"3"},{"o_addtime":"1499676846","o_head_img":"","o_lunar_birthday":"0000-00-00","o_prefer_brith":"1","o_realname":"呵呵哈哈哈","o_sex":"2","o_solar_birthday":"2017-07-07","o_uid":"5","sysid":"4"},{"o_addtime":"1499676846","o_head_img":"","o_lunar_birthday":"0000-00-00","o_prefer_brith":"1","o_realname":"呵呵哈哈哈","o_sex":"2","o_solar_birthday":"2017-07-07","o_uid":"5","sysid":"5"},{"o_addtime":"1499676937","o_head_img":"","o_lunar_birthday":"0000-00-00","o_prefer_brith":"1","o_realname":"嘿嘿","o_sex":"2","o_solar_birthday":"2017-07-01","o_uid":"5","sysid":"6"},{"o_addtime":"1499677157","o_head_img":"","o_lunar_birthday":"0000-00-00","o_prefer_brith":"1","o_realname":"大锤","o_sex":"1","o_solar_birthday":"2017-07-01","o_uid":"5","sysid":"7"},{"o_addtime":"1499677189","o_head_img":"","o_lunar_birthday":"0000-00-00","o_prefer_brith":"1","o_realname":"哈哈哈","o_sex":"1","o_solar_birthday":"2017-07-01","o_uid":"5","sysid":"8"},{"o_addtime":"1499679654","o_head_img":"","o_lunar_birthday":"0000-00-00","o_prefer_brith":"1","o_realname":"哈哈哈哈哈哈哈哈","o_sex":"2","o_solar_birthday":"2017-07-06","o_uid":"5","sysid":"9"},{"o_addtime":"1499680703","o_head_img":"","o_lunar_birthday":"0000-00-00","o_prefer_brith":"1","o_realname":"哈哈哈哈","o_sex":"2","o_solar_birthday":"2017-07-01","o_uid":"5","sysid":"10"},{"o_addtime":"1499681283","o_head_img":"","o_lunar_birthday":"0000-00-00","o_prefer_brith":"1","o_realname":"哈哈哈哈","o_sex":"2","o_solar_birthday":"2017-07-01","o_uid":"5","sysid":"11"},{"o_addtime":"1499681379","o_head_img":"","o_lunar_birthday":"0000-00-00","o_prefer_brith":"1","o_realname":"近距离","o_sex":"2","o_solar_birthday":"2017-07-07","o_uid":"5","sysid":"12"},{"o_addtime":"1499739853","o_head_img":"","o_lunar_birthday":"0000-00-00","o_prefer_brith":"1","o_realname":"李经理","o_sex":"2","o_solar_birthday":"2017-07-14","o_uid":"5","sysid":"14"}]
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
         * o_addtime : 1499670242
         * o_head_img :
         * o_lunar_birthday : 0000-00-00
         * o_prefer_brith : 1
         * o_realname : 小潘
         * o_sex : 1
         * o_solar_birthday : 2017-07-20
         * o_uid : 5
         * sysid : 1
         */

        private String o_addtime;
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
