package cn.ucai.superwechat.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.utils.MFGT;

public class GuideActivity extends AppCompatActivity {

    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_register)
    Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_login, R.id.bt_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                MFGT.startLoginActivity(this);
                break;
            case R.id.bt_register:
                MFGT.startRegisterActivity(this);
                break;
        }
    }
}
