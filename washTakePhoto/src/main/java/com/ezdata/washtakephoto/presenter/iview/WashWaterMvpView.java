package com.ezdata.washtakephoto.presenter.iview;

import com.ezdata.commonlib.core.mvp.MvpView;

public interface WashWaterMvpView extends MvpView {
    /**
     * 负载量为空
     */
    void barCodeNull();
    void barNameNull();
    void productNameNull();
    void productFormNull();
    void productModelNull();
    void potencyNull();
}
