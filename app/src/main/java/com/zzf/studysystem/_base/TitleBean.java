package com.zzf.studysystem._base;

import android.app.Activity;

/**
 * Author:create by RhythmCoder
 * Date:2023/12/16
 * Description:
 */
public class TitleBean {
    private String mTitle;
    private String mSubTitle;
    private String mInfo;
    private Class<? extends Activity> mActivityClass;

    public TitleBean(String title, Class<? extends Activity> clazz) {
        this(title, "", "", clazz);
    }

    public TitleBean(String title, String subTitle, String info, Class<? extends Activity> clazz) {
        mTitle = title;
        mSubTitle = subTitle;
        mInfo = info;
        mActivityClass = clazz;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmSubTitle() {
        return mSubTitle;
    }

    public void setmSubTitle(String mSubTitle) {
        this.mSubTitle = mSubTitle;
    }

    public Class<? extends Activity> getmActivityClass() {
        return mActivityClass;
    }

    public void setmActivityClass(Class<Activity> mActivityClass) {
        this.mActivityClass = mActivityClass;
    }

    @Override
    public String toString() {
        return "TitleBean{" + "mTitle='" + mTitle + '\'' + ", mSubTitle='" + mSubTitle + '\'' + ", mInfo='" + mInfo + '\'' + ", mActivityClass=" + mActivityClass + '}';
    }
}
