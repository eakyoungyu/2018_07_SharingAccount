package com.y2k2.sharingaccount;

import android.util.Log;

/**
 * Created by my on 2018-07-14.
 */

public class BankWoori {
    private static final String TAG=BankWoori.class.getSimpleName();
    private BankStatementInfo bankStatementInfo=new BankStatementInfo();
    private String text[];
    private final String SEPARATOR=" ";
    public BankStatementInfo extractBankStatementInfo(NotificationInfo notificationInfo){
        Log.d(TAG, "extractBankStatementInfo(): BankWoori" + notificationInfo.getText());
        text=notificationInfo.getText().toString().split(SEPARATOR);

        //입출금 초기화
        if(text[0].contains("출금")){
            bankStatementInfo.setInOut(bankStatementInfo.OUT);
        }else if(text[0].contains("입금")){
            bankStatementInfo.setInOut(bankStatementInfo.IN);
        }else{
            bankStatementInfo.setInOut(bankStatementInfo.ERROR);
        }

        //금액 초기화
        /*int idx=text[1].indexOf("원");
        bankStatementInfo.setAmount(text[1].substring(0, idx).replaceAll(",", ""));*/
        bankStatementInfo.setAmount(text[1]);

        //계좌 초기화
        bankStatementInfo.setAccount(text[2].replace("계좌", ""));

        //날짜, 시간 초기화
        bankStatementInfo.setDate(text[3]);
        bankStatementInfo.setTime(text[4]);
        Log.d(TAG, bankStatementInfo.getInOut()+""+bankStatementInfo.getAmount()+""+bankStatementInfo.getAccount()+""+bankStatementInfo.getDate()+""+bankStatementInfo.getTime());

        return bankStatementInfo;
    }
}
