package com.y2k2.sharingaccount;

/**
 * Created by my on 2018-07-11.
 */

public class NotificationInfo {
    private String title;
    private CharSequence text;
    private CharSequence subText;
    private String packageName;
    public NotificationInfo(){}
    public void setTitle(String title){
        this.title=title;
    }
    public void setText(CharSequence text){
        this.text=text;
    }
    public void setSubText(CharSequence subText){
        this.subText=subText;
    }
    public void setPackageName(String packageName){
        this.packageName=packageName;
    }
    public String getTitle(){
        return this.title;
    }
    public String getPackageName(){
        return this.packageName;
    }
    public CharSequence getText(){
        return this.text;
    }
    public CharSequence getSubText(){
        return this.subText;
    }

}
