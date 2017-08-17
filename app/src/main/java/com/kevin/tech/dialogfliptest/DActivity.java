package com.kevin.tech.dialogfliptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by <a href="http://blog.csdn.net/student9128">JingPeng</a> on 2017/8/17.
 * <p>
 * <h3>Description:</h3>
 * <p/>
 * <p/>
 */


public class DActivity extends AppCompatActivity {
    private RelativeLayout mContentRl;
    private ImageView mLogoIv;
    private TextView mDescTv;
    private Button mOpenBtn;

    private int centerX;
    private int centerY;
    private int depthZ = 200;
    private int duration = 3000;
    private Rotate3dAnimation openAnimation;
    private Rotate3dAnimation closeAnimation;

    private boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d);

        mContentRl = (RelativeLayout) findViewById(R.id.rl_content);
        mLogoIv = (ImageView) findViewById(R.id.iv_logo);
        mDescTv = (TextView) findViewById(R.id.tv_desc);
        mOpenBtn = (Button) findViewById(R.id.btn_open);

    }

    /**
     * 卡牌文本介绍打开效果：注意旋转角度
     */
    private void initOpenAnim() {
        //从0到90度，顺时针旋转视图，此时reverse参数为true，达到90度时动画结束时视图变得不可见，
        openAnimation = new Rotate3dAnimation(0, 90, centerX, centerY, depthZ, true);
        openAnimation.setDuration(duration);
        openAnimation.setFillAfter(true);
        openAnimation.setInterpolator(new AccelerateInterpolator());
        openAnimation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLogoIv.setVisibility(View.VISIBLE);
                mDescTv.setVisibility(View.GONE);

                //从270到360度，顺时针旋转视图，此时reverse参数为false，达到360度动画结束时视图变得可见
                Rotate3dAnimation rotateAnimation = new Rotate3dAnimation(270, 360, centerX, centerY, depthZ, false);
                rotateAnimation.setDuration(duration);
                rotateAnimation.setFillAfter(true);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                mContentRl.startAnimation(rotateAnimation);
            }
        });
    }

    /**
     * 卡牌文本介绍关闭效果：旋转角度与打开时逆行即可
     */
    private void initCloseAnim() {
        closeAnimation = new Rotate3dAnimation(360, 270, centerX, centerY, depthZ, true);
        closeAnimation.setDuration(duration);
        closeAnimation.setFillAfter(true);
        closeAnimation.setInterpolator(new AccelerateInterpolator());
        closeAnimation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLogoIv.setVisibility(View.GONE);
                mDescTv.setVisibility(View.VISIBLE);

                Rotate3dAnimation rotateAnimation = new Rotate3dAnimation(90, 0, centerX, centerY, depthZ, false);
                rotateAnimation.setDuration(duration);
                rotateAnimation.setFillAfter(true);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                mContentRl.startAnimation(rotateAnimation);
            }
        });
    }

    public void onClickView(View v) {
        //以旋转对象的中心点为旋转中心点，这里主要不要再onCreate方法中获取，因为视图初始绘制时，获取的宽高为0
        centerX = mContentRl.getWidth() / 2;
        centerY = mContentRl.getHeight() / 2;
        if (openAnimation == null) {
            initOpenAnim();
            initCloseAnim();
        }

        //用作判断当前点击事件发生时动画是否正在执行
        if (openAnimation.hasStarted() && !openAnimation.hasEnded()) {
            return;
        }
        if (closeAnimation.hasStarted() && !closeAnimation.hasEnded()) {
            return;
        }

        //判断动画执行
        if (isOpen) {
            mContentRl.startAnimation(openAnimation);

        } else {
            mContentRl.startAnimation(closeAnimation);

        }

        isOpen = !isOpen;
        mOpenBtn.setText(isOpen ? "关闭" : "打开");
    }
}
