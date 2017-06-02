/*
******************************* Copyright (c)*********************************\
**
**                 (c) Copyright 2015, 蒋朋, china, qd. sd
**                          All Rights Reserved
**
**                           By()
**                         
**-----------------------------------版本信息------------------------------------
** 版    本: V0.1
**
**------------------------------------------------------------------------------
********************************End of Head************************************\
*/

package com.zmin.birthday.app.db;


import com.zmin.birthday.app.db.dao.BirthdayDao;

/**
 * 获取dao.
 */
public class DbUtil {
    private static BirthdayHelp birthdayHelp;


    private static BirthdayDao getDriverDao() {
        return DbCore.getDaoSession().getBirthdayDao();
    }

    public static BirthdayHelp getDriverHelper() {
        if (birthdayHelp == null) {
            birthdayHelp = new BirthdayHelp(getDriverDao());
        }
        return birthdayHelp;
    }


}
