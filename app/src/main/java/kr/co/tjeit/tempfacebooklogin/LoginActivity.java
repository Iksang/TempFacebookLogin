package kr.co.tjeit.tempfacebooklogin;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginActivity extends BaseActivity {

    private com.facebook.login.widget.LoginButton fbBtn;
    CallbackManager cm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindViews();
        setupEvent();
        setValues();

    }

    @Override
    public void setupEvent() {

    }

    @Override
    public void setValues() {
        cm = CallbackManager.Factory.create();
        fbBtn.registerCallback(cm, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        ProfileTracker pt = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if(currentProfile == null){
                    Toast.makeText(mContext, "로그아웃 처리 완료", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.putExtra("이름",currentProfile.getName());
                    startActivity(intent);
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cm.onActivityResult(requestCode,resultCode, data);
    }

    @Override
    public void bindViews() {
        this.fbBtn = (LoginButton) findViewById(R.id.fbBtn);
    }
}
