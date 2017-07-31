package com.zmin.birthday.app.utils;

import android.Manifest;

import com.jess.arms.mvp.IView;
import com.jess.arms.utils.PermissionUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * Created by Administrator on 2017/7/31.
 */

public class PermissionUtils {

    /**
     * 请求打电话权限
     */
    public static void readContacts(final PermissionUtil.RequestPermission requestPermission, RxPermissions rxPermissions, final IView view, RxErrorHandler errorHandler) {
//先确保是否已经申请过权限
        boolean isPermissionsGranted =
                rxPermissions
                        .isGranted(Manifest.permission.READ_CONTACTS);

        if (isPermissionsGranted) {//已经申请过，直接执行操作
            requestPermission.onRequestPermissionSuccess();
        } else {//没有申请过，则申请
            rxPermissions
                    .request(Manifest.permission.READ_CONTACTS)
                    .subscribe(new ErrorHandleSubscriber<Boolean>(errorHandler) {
                        @Override
                        public void onNext(Boolean granted) {
                            if (granted) {
                                requestPermission.onRequestPermissionSuccess();
                            } else {
                                view.showMessage("request permissons failure");
                            }
                        }
                    });
        }
    }
}
