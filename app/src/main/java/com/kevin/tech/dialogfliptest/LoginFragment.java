package com.kevin.tech.dialogfliptest;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2017/8/16.
 * <p>
 * <h3>Description:</h3>
 * <p/>
 * <p/>
 */


public class LoginFragment extends DialogFragment implements View.OnClickListener {
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.cb_auto_login)
    CheckBox cbAutoLogin;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    Unbinder unbinder;

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        try {
            mCallback = (OnForgetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnForgetListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, mView);
        tvForgetPwd.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    OnForgetListener mCallback;

    @Override
    public void onClick(View view) {
        mCallback.forgetPassword();
    }

    public interface OnForgetListener {
        void forgetPassword();
    }
}
