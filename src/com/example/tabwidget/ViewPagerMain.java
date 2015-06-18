package com.example.tabwidget;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewPagerMain extends Activity {

	private ViewPager mPager;//ҳ������
    private List<View> listViews; // Tabҳ���б�
    private ImageView cursor;// ����ͼƬ
    private TextView t1, t2, t3;// ҳ��ͷ��
    private int offset = 0;// ����ͼƬƫ����
    private int currIndex = 0;// ��ǰҳ�����
    private int bmpW;// ����ͼƬ���
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		InitTextView();
		InitViewPager();
		InitImageView();
	}
	/**
     * ��ʼ��ͷ��
*/
    private void InitTextView() {
        t1 = (TextView) findViewById(R.id.text1);
        t2 = (TextView) findViewById(R.id.text2);
        t3 = (TextView) findViewById(R.id.text3);

        t1.setOnClickListener(new MyOnClickListener(0));
        t2.setOnClickListener(new MyOnClickListener(1));
        t3.setOnClickListener(new MyOnClickListener(2));
    }
    
    /**
     * ��ʼ��ViewPager
*/
    private void InitViewPager() {
        mPager = (ViewPager) findViewById(R.id.vPager);
        listViews = new ArrayList<View>();
        LayoutInflater mInflater = getLayoutInflater();
        listViews.add(mInflater.inflate(R.layout.lay1, null));
        listViews.add(mInflater.inflate(R.layout.lay2, null));
        listViews.add(mInflater.inflate(R.layout.lay3, null));
        mPager.setAdapter(new MyPagerAdapter(listViews));
        mPager.setCurrentItem(0);
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }
    
    /**
     * ��ʼ������
*/
    private void InitImageView() {
        cursor = (ImageView) findViewById(R.id.cursor);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher).getWidth();// ��ȡͼƬ���
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// ��ȡ�ֱ��ʿ��
        offset = (screenW / 3 - bmpW) / 2;// ����ƫ����
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        //matrix.postTranslate(0, 0);
        cursor.setImageMatrix(matrix);// ���ö�����ʼλ��
    }
    
    /**
     * ͷ��������
*/
    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mPager.setCurrentItem(index);
        }
    };
    
    /**
     * ҳ���л�����
     */
    public class MyOnPageChangeListener implements OnPageChangeListener {

    	Animation animation = null;
    	public MyOnPageChangeListener() {
		}
    	
        int one = offset * 2 + bmpW;// ҳ��1 -> ҳ��2 ƫ����
        int two = one * 2;// ҳ��1 -> ҳ��3 ƫ����
        int end=0;
        @Override
        public void onPageSelected(int arg0) {
           
            switch (arg0) {
            case 0:
            	end=108;
                if (currIndex == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, 0, 0, 0);
                }
                break;
            case 1:
            	end=468;
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, one, 0, 0);
                }
                break;
            case 2:
            	end=828;
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, two, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(one, two, 0, 0);
                }
                break;
            }
            Log.d("TAG", "------offset:"+offset+"bmpW��"+bmpW);
            currIndex = arg0;
            //animation.setFillEnabled(true);
            animation.setFillAfter(true);// True:ͼƬͣ�ڶ�������λ��
            animation.setDuration(1000);
            //animation.setRepeatCount(2); 
            //animation.setRepeatMode(Animation.REVERSE);
            cursor.setAnimation(animation);
            animation.startNow();
            //cursor.startAnimation(animation);
            animation.setAnimationListener(new AnimationListener(){  
                public void onAnimationStart(Animation animation) {  
                    // TODO Auto-generated method stub  
                      
                }  
  
                public void onAnimationEnd(Animation animation) {  
                    // TODO Auto-generated method stub  
                	//super.onAnimationEnd();  
                    Matrix matrix = new Matrix();
                    // matrix.postTranslate(offset, 0);
                    matrix.postTranslate(end, 0);
                    cursor.setImageMatrix(matrix);// ���ö�����ʼλ��
                    Log.d("TAG", "------end:"+end);
                }  
  
                public void onAnimationRepeat(Animation animation) {  
                    // TODO Auto-generated method stub  
                      
                }  
                  
            });  
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }
    
}
