package com.kevin.tech.dialogfliptest;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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


public class ForgetPasswordFragment extends Fragment implements View.OnClickListener {

    Unbinder unbinder;
    @BindView(R.id.et_email)
    EditText etEmil;
    @BindView(R.id.btn_back)
    Button btnBack;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnBackListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement OnBackListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_forget_password, container, false);
        unbinder = ButterKnife.bind(this, mView);
        btnBack.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    OnBackListener mCallback;

    @Override
    public void onClick(View view) {
        mCallback.goBack();
    }

    public interface OnBackListener {
        void goBack();
    }
}
