package kr.co.tjeit.tempfacebooklogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.internal.AppEventsLoggerUtility;
import com.facebook.login.LoginManager;

import org.json.JSONObject;

public class MainActivity extends BaseActivity {

    private android.widget.ImageView profileImg;
    private android.widget.TextView idTxt;
    private android.widget.TextView nameTxt;
    private android.widget.Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setupEvent();
        setValues();
    }

    @Override
    public void setupEvent() {
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void setValues() {
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        // Application code
//                        idTxt.setText(object.toString());
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link");
        request.setParameters(parameters);
        request.executeAsync();

        idTxt.setText(getIntent().getStringExtra("이름"));
    }

    @Override
    public void bindViews() {
        this.logoutBtn = (Button) findViewById(R.id.logoutBtn);
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        this.idTxt = (TextView) findViewById(R.id.idTxt);
        this.profileImg = (ImageView) findViewById(R.id.profileImg);
    }
}
