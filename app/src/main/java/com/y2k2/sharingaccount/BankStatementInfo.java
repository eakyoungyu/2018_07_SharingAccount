package com.y2k2.sharingaccount;

/**
 * Created by my on 2018-07-14.
 */

public class BankStatementInfo {
    private String inOut;      //출금일 경우 true
    private String amount;
    private CharSequence account;
    private String date;
    private String time;

    public final String OUT="OUT";
    public final String IN="IN";
    public final String ERROR="ERROR";

    public String getInOut() {
        return inOut;
    }

    public String getAmount() {
        return amount;
    }

    public CharSequence getAccount() {
        return account;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setInOut(String inOut) {
        this.inOut = inOut;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setAccount(CharSequence account) {
        this.account = account;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
