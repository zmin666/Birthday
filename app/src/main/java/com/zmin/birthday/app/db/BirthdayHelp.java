package com.zmin.birthday.app.db;

import com.zmin.birthday.mvp.model.entity.Birthday;

import org.greenrobot.greendao.AbstractDao;

/**
 * @author: ZhangMin
 * @date: 2017/6/1 17:23
 * @desc:  数据库操作实现类
 */
public class BirthdayHelp extends BaseDbHelper<Birthday,Long> {
    public BirthdayHelp(AbstractDao dao) {
        super(dao);
    }
}
