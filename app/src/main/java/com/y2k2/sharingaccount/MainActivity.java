package com.y2k2.sharingaccount;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.y2k2.sharingaccount.R;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private final static String TAG=MainActivity.class.getSimpleName();
    private boolean isPermissionAllowed;
    private RecyclerAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutMananger;
    private StatementHandlerByBank statementHandlerByBank=new StatementHandlerByBank();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        isPermissionAllowed=isNotiPermissionAllowed();

        if(!isPermissionAllowed){
            Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            startActivity(intent);
        }
        registerBroadcast();


        mAdapter=new RecyclerAdapter();     //여기 인자로 기존 노티 추가
        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        mLayoutMananger=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutMananger);
        mRecyclerView.setAdapter(mAdapter);


    }
    private boolean isNotiPermissionAllowed() {
        Set<String> notiListenerSet = NotificationManagerCompat.getEnabledListenerPackages(this);
        String myPackageName = getPackageName();

        for(String packageName : notiListenerSet) {
            if(packageName == null) {
                continue;
            }
            if(packageName.equals(myPackageName)) {
                return true;
            }
        }

        return false;
    }
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent!=null){
                Log.d(TAG, "onReceive");

                BankStatementInfo newStatement = statementHandlerByBank.putBankStatementInfo(intent, intent.getStringExtra("bankname"));

                Log.d(TAG, "updateUI "+newStatement.getAccount());
                updateUI(newStatement);
            }

        }
    };
    public void registerBroadcast(){
        IntentFilter filter = new IntentFilter();

        //???
        filter.addAction(BroadcastActions.GETNOTI);
        registerReceiver(mBroadcastReceiver, filter);
    }


    public void unregisterBroadcast(){
        unregisterReceiver(mBroadcastReceiver);
    }
    private void updateUI(BankStatementInfo bankStatementInfo){
        mAdapter.add(bankStatementInfo);
    }
}
