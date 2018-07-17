package com.y2k2.sharingaccount;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.y2k2.sharingaccount.R;

/**
 * Created by my on 2018-07-17.
 */

public class LoginActicity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignIn;
    private Button buttonSignUp;
    private Context mContext;
    private final static String TAG=LoginActicity.class.getSimpleName();

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.login_layout);


        //firebase
        mAuth=FirebaseAuth.getInstance();
        editTextEmail=(EditText) findViewById(R.id.login_email);
        editTextPassword=(EditText)findViewById(R.id.login_password);
        buttonSignIn=(Button)findViewById(R.id.login_signIn);
        buttonSignUp=(Button)findViewById(R.id.login_signUp);
        mContext=this;



        buttonSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email=editTextEmail.getText().toString();
                String password=editTextPassword.getText().toString();
                if(email.equals("")||password.equals("")) {
                    Toast.makeText(mContext, "이메일 또는 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    loginUser(email, password);
                }
            }
        });
        buttonSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //회원가입 창
            }
        });

    }
    private void loginUser(final String email, final String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user=mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(mContext, "이메일 또는 비밀번호를 다시 확인하세요.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user){
        if(user!=null) {
            Intent intent = new Intent(LoginActicity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{}
    }
}
