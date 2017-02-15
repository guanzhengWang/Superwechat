package cn.ucai.superwechat.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.hyphenate.easeui.domain.User;

import cn.ucai.superwechat.I;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.ui.AddContactActivity;
import cn.ucai.superwechat.ui.AddFriendActivity;
import cn.ucai.superwechat.ui.ChatActivity;
import cn.ucai.superwechat.ui.FriendProfileActivity;
import cn.ucai.superwechat.ui.GuideActivity;
import cn.ucai.superwechat.ui.LoginActivity;
import cn.ucai.superwechat.ui.MainActivity;
import cn.ucai.superwechat.ui.RegisterActivity;
import cn.ucai.superwechat.ui.SettingsActivity;
import cn.ucai.superwechat.ui.UserProfileActivity;


/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class MFGT {
    public static void finish(Activity activity){
        activity.finish();
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
    public static void startActivity(Activity context, Class<?> cla){
        context.startActivity(new Intent(context,cla));
        context.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }
    public static void startActivity(Activity context, Intent intent){
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }
    public static void startLoginActivity(Activity activity){
        startActivity(activity,LoginActivity.class);
    }
    public static void startRegisterActivity(Activity activity){
        startActivity(activity, RegisterActivity.class);
    }
    public static void gotoGuideActivity(Activity activity){
        startActivity(activity, GuideActivity.class);
    }

    public static void gotoSettingActivity(FragmentActivity activity) {
        startActivity(activity, SettingsActivity.class);
    }
    public static void gotoLoginActivityClear(Activity activity) {
        startActivity(activity,new Intent(activity,LoginActivity.class).
                setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

    public static void gotoUserProfileActivity(FragmentActivity activity) {
        startActivity(activity, UserProfileActivity.class);
    }
    public static void gotoAddContact(Activity activity){
        startActivity(activity, AddContactActivity.class);
    }

    public static void gotoFriend(Activity activity, User user) {
        startActivity(activity,new Intent(activity,FriendProfileActivity.class).putExtra(I.User.TABLE_NAME,user));
    }
    public static void gotoFriend(Activity activity, String username) {
        startActivity(activity,new Intent(activity,FriendProfileActivity.class).putExtra(I.User.USER_NAME,username));
    }
    public static void gotoAddFriendActivity(Activity activity, String username) {
        startActivity(activity,new Intent(activity,AddFriendActivity.class).putExtra(I.User.USER_NAME,username));
    }

    public static void gotoChat(Activity activity, String username) {
        startActivity(activity,new Intent(activity, ChatActivity.class)
                .putExtra("userId", username));
    }

    public static void gotoMain(Activity activity) {
        startActivity(activity, MainActivity.class);
    }
}
