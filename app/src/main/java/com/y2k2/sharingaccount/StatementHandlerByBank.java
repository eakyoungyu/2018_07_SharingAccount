package com.y2k2.sharingaccount;

import android.content.Intent;
import android.util.Log;

/**
 * Created by my on 2018-07-14.
 */
//은행 추가시 이 클래스 수정 & 새로운 클래스 생성
public class StatementHandlerByBank {
    private static final String TAG=StatementHandlerByBank.class.getSimpleName();
    public final String WOORI="WOORI";
    private BankWoori bankWoori=new BankWoori();
    private BankStatementInfo bankStatementInfo=new BankStatementInfo();

    public String checkPackageName(String packageName){
        switch(packageName){
            case "com.wr.alrim":
                return this.WOORI;
            default:
                return null;
        }
    }
    public Intent extractBankStatementInfo(NotificationInfo notificationInfo, String bankName){
        Intent statementInfo=new Intent(BroadcastActions.GETNOTI);
        switch (bankName){
            case WOORI:
                Log.d(TAG, "extractBankStatementInfo(): WOORI" + notificationInfo.getText());
                bankStatementInfo=bankWoori.extractBankStatementInfo(notificationInfo);
                statementInfo.putExtra("bankname", WOORI);
                statementInfo.putExtra("amount", bankStatementInfo.getAmount());
                statementInfo.putExtra("account", bankStatementInfo.getAccount());
                statementInfo.putExtra("date", bankStatementInfo.getDate());
                statementInfo.putExtra("time", bankStatementInfo.getTime());
                statementInfo.putExtra("inout", bankStatementInfo.getInOut());
                return statementInfo;
            default:
                return statementInfo;
        }
    }
    public BankStatementInfo putBankStatementInfo(Intent intent, String bankName){
        switch (bankName){
            case WOORI:
                bankStatementInfo.setTime(intent.getStringExtra("time"));
                bankStatementInfo.setDate(intent.getStringExtra("date"));
                bankStatementInfo.setAccount(intent.getStringExtra("account"));
                bankStatementInfo.setAmount(intent.getStringExtra("amount"));
                bankStatementInfo.setInOut(intent.getStringExtra("inout"));
                return bankStatementInfo;

            default:
                return bankStatementInfo;
        }
    }
}
