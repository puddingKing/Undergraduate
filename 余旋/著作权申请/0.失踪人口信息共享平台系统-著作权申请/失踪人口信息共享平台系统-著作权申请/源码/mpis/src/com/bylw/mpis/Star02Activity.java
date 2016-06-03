package com.bylw.mpis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.Message;
import com.bb.R;

import edu.self.LoginActivity;



import java.util.ArrayList;
import java.util.List;


public class Star02Activity extends Activity {

    private int[] backgrounds = new int[]{
            R.drawable.guide01,
            R.drawable.guide02,
            R.drawable.guide03,
            R.drawable.guide04
            
    };

    private ImageView[] views = new ImageView[]{};

    private int lp = 0;
    private ViewPager mViewPager;
    private boolean onTouch = false;
    private Button buttonskip;  //定义一个变量，实现用按钮跳转
    
    private android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println("what " + msg.what);
            switch (msg.what) {
                case 0:
                    if(!onTouch){
                        mHandler.removeMessages(1);
                        mHandler.sendEmptyMessageDelayed(1,200);
                    }
                    mHandler.sendEmptyMessageDelayed(0,2000);
                    break;
                case 1:
                    if (mViewPager.getCurrentItem() != mViewPager.getAdapter().getCount()) {
                        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                    }
                    break;
                case 2:
                    onTouch = false;
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star02);
        
        findViewById(R.id.buttonskip);
        buttonskip=(Button) findViewById(R.id.buttonskip);
        buttonskip.setOnClickListener(new OnClickListener()
	    {
	             public void onClick(View v)  {
	            	    Intent intent=new Intent();
	            	    intent.setClass(Star02Activity.this,LoginActivity.class) ; //Context 上下文 
	                    startActivity(intent);//打开 
	                    
	             }
	    }); 

        this.mViewPager = (ViewPager) this.findViewById(R.id.viewpager);
        {
            views = new ImageView[]{
                    (ImageView) this.findViewById(R.id.v1),
                    (ImageView) this.findViewById(R.id.v2),
                    (ImageView) this.findViewById(R.id.v3),
                    (ImageView) this.findViewById(R.id.v4),
                    (ImageView) this.findViewById(R.id.v5),
                    
            };
        }

        ArrayList<View> mList = new ArrayList<View>();
        for (int i = 0; i < 4; i++) {
            RelativeLayout relativeLayout = new RelativeLayout(getApplicationContext());
            relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            relativeLayout.setBackgroundResource(backgrounds[i]);
            mList.add(relativeLayout);
        }

//        final View view = View.inflate(getApplicationContext(), R.layout.view_01, null);
        //-----------------------------------
        
       
        RelativeLayout layout8 = new RelativeLayout(getApplicationContext());
        layout8.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout8.setBackgroundResource(R.drawable.guide05);
        
        final Button button = new Button(getApplicationContext());
        button.setText("开始印上你的足迹");
        button.setTextSize(20);
        button.setBackgroundResource(R.drawable.button_long_green);
        RelativeLayout.LayoutParams layoutParams_button = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams_button.addRule(RelativeLayout.CENTER_IN_PARENT);
        layoutParams_button.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams_button.bottomMargin = 250;
        button.setLayoutParams(layoutParams_button);

        layout8.addView(button);
        

        TextView textView = new TextView(getApplicationContext());
        textView.setText("希望，从这里开始发芽");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        RelativeLayout.LayoutParams layoutParams_textView = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams_textView.topMargin = 300;
        textView.setLayoutParams(layoutParams_textView);

        layout8.addView(textView);

        
        //----------------------------------
        mList.add(layout8);
        
        mHandler.sendEmptyMessageDelayed(0,3000);//msg.what 0 和msg.what 1 处理的自动滑动

        final MPagerAdapter adapter = new MPagerAdapter(mList);
        this.mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                System.out.println("onPageSelected = " + i);
                switch (i) {
                    case 4:
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Star02Activity.this, LoginActivity.class);
                                startActivity(intent);
                                Star02Activity.this.finish();
                            }
                        });
                        break;
                    default:
                        break;
                }
                if (i > views.length) {
                    return;
                }
                views[lp].setBackgroundResource(R.drawable.page_indicator_unfocused);
                views[i].setBackgroundResource(R.drawable.page_indicator_focused);

                lp = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        this.mViewPager.setAdapter(adapter);
        this.mViewPager.setCurrentItem(0);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private class MPagerAdapter extends PagerAdapter {
        List<View> mList;

        MPagerAdapter(List<View> mList) {
            this.mList = mList;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mList.get(position));
            return mList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }
    }
}

