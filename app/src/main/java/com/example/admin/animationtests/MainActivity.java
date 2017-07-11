package com.example.admin.animationtests;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import Transformers.ZoomOutSlideTransformer;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView img_pic, img_left, img_right;

    private boolean mShowingBack = false;

    int pics[] = {R.drawable.l1, R.drawable.l2, R.drawable.l3, R.drawable.l4, 0};
    int pic_num = 0;
    int anim_num = 0, anim_max = 6;

    int anims[] = {R.anim.fade_in_from_left, R.anim.fade_in_from_right, R.anim.fade_out_to_left, R.anim.fade_out_to_right,
                        R.anim.rotate_center, R.anim.rotate_corner_fade_in, R.anim.fade_in_rotate_in_from_left, 0};

    String anim_names[] = {"fade_in_from_left", "fade_in_from_right", "fade_out_to_left", "fade_out_to_right",
                                    "rotate_center", "rotate_corner_fade_in", "fade_in_rotate_in_from_left"};

    ViewPager pager;
    MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_pic = (ImageView) findViewById(R.id.main_image);
        img_left = (ImageView) findViewById(R.id.main_left);
        img_right = (ImageView) findViewById(R.id.main_right);

        img_left.setOnClickListener(this);
        img_right.setOnClickListener(this);

        img_pic.setImageResource(pics[pic_num]);
/*
        getFragmentManager()
                .beginTransaction()
                .add(R.id.container, new CardFrontFragment())
                .commit();
*/

        //Code for ViewPager
        pager = (ViewPager) findViewById(R.id.container);

        pager.setClipToPadding(false);
//        pager.setPageMargin(12);

        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(myPagerAdapter);
        pager.setPageTransformer(true, new ZoomOutSlideTransformer());

    }//onCreate()


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.main_left){

            pic_num = (pic_num+3)%4;
            setAnim();
            img_pic.setImageResource(pics[pic_num]);
            //flipCard();
        }

        if (v.getId() == R.id.main_right){

            pic_num = (pic_num+1)%4;
            setAnim();
            img_pic.setImageResource(pics[pic_num]);
            //flipCard();
        }

    }//onClick()

    public void setAnim(){

        Animation animation = AnimationUtils.loadAnimation(this, anims[anim_num]);
        Toast.makeText(this, anim_names[anim_num], Toast.LENGTH_SHORT).show();
        anim_num = (anim_num+1)%anim_max;

        img_pic.startAnimation(animation);

    }//setAnim()


    //A fragment representing the front of the card.
    public static class CardFrontFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.card_front, container, false);
        }
    }//CardFrontFragment Class

    //A fragment representing the back of the card.
    public static class CardBackFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.card_back, container, false);
        }
    }//CardBackFragment Class

    private void flipCard() {
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            mShowingBack = false;
            return;
        }

        mShowingBack = true;
/*
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)

                .replace(R.id.container, new CardBackFragment())
                .addToBackStack(null)
                .commit();
*/
    }//flipCard()


    public class MyPagerAdapter extends FragmentStatePagerAdapter{

        int count = 4;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            MyFragment1 fm = new MyFragment1();
            fm.setPosition(position);

            return fm;
        }//getItem()

        @Override
        public int getCount() {
            return count;
        }//getCount()

        @Override
        public float getPageWidth(int position){
            return 0.93f;
        }//getPageWidth()

    }//MyPagerAdapter Class

    public static class MyFragment1 extends Fragment{

        int pos = 0;

        public MyFragment1(){}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View mView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView txt_1 = (TextView) mView.findViewById(R.id.frag_text);
            RelativeLayout rel = (RelativeLayout) mView.findViewById(R.id.frag_rel);

            switch (pos){

                case 0: txt_1.setText("1");
                        rel.setBackgroundResource(R.color.background_1);
                        break;

                case 1: txt_1.setText("2");
                        rel.setBackgroundResource(R.color.background_2);
                        break;

                case 2: txt_1.setText("3");
                        rel.setBackgroundResource(R.color.background_3);
                        break;

                case 3: txt_1.setText("4");
                        rel.setBackgroundResource(R.color.background_4);
                        break;

                default:txt_1.setText("Default");
                        rel.setBackgroundResource(R.color.background_2);
                        break;
            }

            return mView;
        }

        public void setPosition(int position) {
            pos = position;
        }

    }//MyFragment Class

}//MainActivity