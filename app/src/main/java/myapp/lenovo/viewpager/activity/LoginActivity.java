package myapp.lenovo.viewpager.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;

import myapp.lenovo.viewpager.bmob.MyBmob;
import myapp.lenovo.viewpager.entity.MyUser;
import myapp.lenovo.viewpager.R;

public class LoginActivity extends Activity implements MyBmob.OperateDoneListener{
    private static final String TAG = "LoginActivity";

    private EditText curAccount;
    private EditText curPassword;
    private Button entry;
    private TextView register;
    private TextView forgetPassword;
    private TextView phoneEntry;
    private ImageButton qqLogin;
    private ImageButton weiboLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("create","haha");

        MyBmob.initBmob(LoginActivity.this);
        //autoLoginCacheUser();

        setContentView(R.layout.activity_login);
        initView();
    }

    public void initView(){
        curAccount= findViewById(R.id.account_et);
        curPassword= findViewById(R.id.password_et);
        entry= findViewById(R.id.entry_btn);
        forgetPassword= findViewById(R.id.forget_password_tv);
        register=  findViewById(R.id.register_tv);
        phoneEntry= findViewById(R.id.phone_entry_tv);
        phoneEntry.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        phoneEntry.getPaint().setAntiAlias(true);
        qqLogin= findViewById(R.id.qq_logo_iv);
        weiboLogin= findViewById(R.id.weibo_logo_iv);
        entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data=curAccount.getText().toString();
                String password=curPassword.getText().toString();
                Log.d(TAG, data + "----" + password);
                MyBmob.loginBmobUserByAccount(data,password,LoginActivity.this);
            }
        });
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        phoneEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,PhoneLoginActivity.class);
                startActivity(intent);
            }
        });
        qqLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyBmob.bmobThirdLoginByQQ(LoginActivity.this, LoginActivity.this);
            }
        });
        weiboLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    public void autoLoginCacheUser(){
        MyUser bmobUser=BmobUser.getCurrentUser(MyUser.class);
        if(bmobUser!=null){
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        MyBmob.handleActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onOperateDone(int errorCode, int which) {
        if (which == MyBmob.LOGIN_BY_ACCOUNT){
            if (errorCode > 0){
                Log.d(TAG,"login success1");
                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                Log.d(TAG,"login fail1");
                Toast.makeText(LoginActivity.this,"登录失败，请重新输入",Toast.LENGTH_SHORT).show();
                curAccount.setText("");
                curPassword.setText("");
            }
        }
        else if ((which == MyBmob.LOGIN_BY_QQ)){
            if (errorCode > 0){
                Log.d(TAG,"login success1");
                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                Log.d(TAG,"login fail1");
                Toast.makeText(LoginActivity.this,"登录失败，请重新输入",Toast.LENGTH_SHORT).show();
                curAccount.setText("");
                curPassword.setText("");
            }
        }
    }
}

