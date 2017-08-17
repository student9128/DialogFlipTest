package com.kevin.tech.dialogfliptest;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2017/8/16.
 * <p>
 * <h3>Description:</h3>
 * <p/>
 * <p/>
 */


public class LoginActivity extends AppCompatActivity implements LoginFragment.OnForgetListener, ForgetPasswordFragment.OnBackListener {
    @BindView(R.id.fl_content)
    FrameLayout flContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
//        if (savedInstanceState == null) {
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.add(R.id.fl_content, new LoginFragment()).commit();

    }

    @Override
    public void forgetPassword() {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.card_flip_left_in, R.animator.card_flip_left_out,
                        R.animator.card_flip_right_in, R.animator.card_flip_right_out)
                .replace(R.id.fl_content, new ForgetPasswordFragment())
                .addToBackStack(null).commit();
    }

    @Override
    public void goBack() {
        getFragmentManager().popBackStack();
    }

}
