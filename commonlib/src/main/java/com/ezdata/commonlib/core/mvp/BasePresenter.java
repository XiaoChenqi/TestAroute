package com.ezdata.commonlib.core.mvp;


//import com.ezdata.xcqframeopencv.data.DataManager;

import com.ezdata.commonlib.data.DataManager;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * <p>
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 * <p>
 * Created by MSI-PC on 2018/4/3.
 */

public class BasePresenter<T extends MvpView> implements Presenter<T> {

    //protected Subscription subscription;
    private T mMvpView;
    public DataManager mDataManager;

    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(T mvpView) {
        this.mMvpView = mvpView;
        this.mDataManager = DataManager.getInstance();
    }

    @Override
    public void detachView() {
        this.mMvpView = null;
        unsubscribe();
    }

    /**
     * 解绑 订阅对象
     */
    protected void unsubscribe() {
        if(mCompositeSubscription != null && !mCompositeSubscription.isUnsubscribed()){
            mCompositeSubscription.unsubscribe();
        }
    }

    /**
     * 添加 订阅对象
     * @param sub
     */
    protected void addSubscription(Subscription sub){
        if(mCompositeSubscription == null){
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(sub);
    }

    public T getMvpView() {
        return mMvpView;
    }


    public boolean isViewAttached() {
        return mMvpView != null;
    }


    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }


    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
