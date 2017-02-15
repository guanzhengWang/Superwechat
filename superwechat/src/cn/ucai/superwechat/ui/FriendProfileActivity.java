package cn.ucai.superwechat.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.easeui.domain.User;
import com.hyphenate.easeui.utils.EaseUserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.I;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.SuperWeChatHelper;
import cn.ucai.superwechat.domain.Result;
import cn.ucai.superwechat.net.NetDao;
import cn.ucai.superwechat.net.OnCompleteListener;
import cn.ucai.superwechat.utils.MFGT;
import cn.ucai.superwechat.utils.ResultUtils;

public class FriendProfileActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.user_head_avatar)
    ImageView userHeadAvatar;
    @BindView(R.id.tv_usernick)
    TextView tvUsernick;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.btn_sendMessage)
    Button btnSendMessage;
    @BindView(R.id.btn_sendVideo)
    Button btnSendVideo;
    @BindView(R.id.btn_addContact)
    Button btnAddContact;
    User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        imgBack.setVisibility(View.VISIBLE);
        txtTitle.setVisibility(View.VISIBLE);
        txtTitle.setText(R.string.title_user_profile);

        user = (User) getIntent().getSerializableExtra(I.User.TABLE_NAME);
        if (user != null) {
            showUserInfo();
        } else {
            String username=getIntent().getStringExtra(I.User.USER_NAME);
            if(username==null){
                MFGT.finish(this);
            }else {
                syncUserInfo(username);
            }
        }
    }

    private void syncUserInfo(String username) {
        NetDao.GetUserByUsername(this, username, new OnCompleteListener<String>() {
            @Override
            public void onSuccess(String s) {
                if(s!=null){
                    Result result = ResultUtils.getResultFromJson(s, User.class);
                    if(result!=null){
                        if(result.isRetMsg()){
                            User u= (User) result.getRetData();
                            if(u!=null){
                                user=u;
                                showUserInfo();
                            }
                        }
                    }
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void showUserInfo() {
        tvUsernick.setText(user.getMUserNick());
        EaseUserUtils.setUserAvatarbyPath(this, user.getAvatar(), userHeadAvatar);
        tvUsername.setText("微信号:" + user.getMUserName());
        if (isFriend()) {
            btnSendMessage.setVisibility(View.VISIBLE);
            btnSendVideo.setVisibility(View.VISIBLE);
        } else {
            btnAddContact.setVisibility(View.VISIBLE);
        }
    }

    private boolean isFriend() {
        User u = SuperWeChatHelper.getInstance().getAppContactList().get(user.getMUserName());
        if (u == null) {
            return false;
        } else {
            SuperWeChatHelper.getInstance().saveAppContact(user);
            return true;
        }
    }

    @OnClick(R.id.img_back)
    public void imgBack() {
        MFGT.finish(this);
    }

    @OnClick(R.id.btn_addContact)
    public void addContact() {
        Log.e("FriendProfile","添加好友"+user);
        MFGT.gotoAddFriendActivity(this,user.getMUserName());
    }
}
