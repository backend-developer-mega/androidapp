package com.learning.games.education.kidspower.kidsActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.exifinterface.media.ExifInterface;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.learning.games.education.kidspower.R;
import com.learning.games.education.kidspower.kidsCustom.kidsConstant;
import com.learning.games.education.kidspower.kidsUtils.Utils;
import com.learning.games.education.kidspower.kidsCustom.kidsAppControl;
import com.learning.games.education.kidspower.kidsCustom.kidsNonSwipeAbleViewPager;
import com.learning.games.education.kidspower.kidsModel.kidsLearningDataModel;

import java.util.ArrayList;
import java.util.HashMap;

public class kidsFullScreenAct extends AppCompatActivity {
    ArrayList<kidsLearningDataModel> arrayOfImages;
    int categoryPosition;
    Context context;
    ImageView imageView;
    ImageView imgBtnNext;
    ImageView imgBtnPrev;
    ArrayList<kidsLearningDataModel> learningDataModelArrayList;
    RelativeLayout llAdView;
    LinearLayout llAdViewFacebook;
    int selectedPosition;
    TextView tvItemName;
    kidsNonSwipeAbleViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    RelativeLayout ads_layout;
    kidsAppControl appControl;
    int unitposition;
    String category;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_full_screen);
        getSupportActionBar().hide();
        this.context = this;
        initDefine();

        ads_layout=findViewById(R.id.ads_layout);
        appControl= (kidsAppControl) getApplication();
    }

    private void initDefine() {
        this.viewPager = (kidsNonSwipeAbleViewPager) findViewById(R.id.viewPager);
        this.tvItemName = (TextView) findViewById(R.id.tvItemName);
        this.imgBtnNext = (ImageView) findViewById(R.id.imgBtnNext);
        this.imgBtnPrev = (ImageView) findViewById(R.id.imgBtnPrev);
      
        
        Intent intent = getIntent();
        this.categoryPosition = intent.getIntExtra("categoryPosition", 0);
        this.selectedPosition = intent.getIntExtra("selectedPosition", 0);
        this.category = intent.getStringExtra("category");
        prepareDataForLearning(this.categoryPosition);
        setViewPagerAdapter(this.learningDataModelArrayList);
    }

    private void setViewPagerAdapter(ArrayList<kidsLearningDataModel> learningDataModelArrayList2) {
        this.arrayOfImages = learningDataModelArrayList2;
        ViewPagerAdapter viewPagerAdapter2 = new ViewPagerAdapter(learningDataModelArrayList2);
        this.viewPagerAdapter = viewPagerAdapter2;
        this.viewPager.setAdapter(viewPagerAdapter2);
        this.viewPager.setCurrentItem(this.selectedPosition);
        this.tvItemName.setText(learningDataModelArrayList2.get(this.selectedPosition).showTitle);
        this.tvItemName.startAnimation(AnimationUtils.loadAnimation(this.context, R.anim.rotation));
        YoYo.with(Techniques.RubberBand).duration(1200).repeat(1).playOn(this.tvItemName);
        if (Utils.getPref(kidsConstant.SOUND, true).booleanValue()) {
            kidsAppControl.textToSpeech.speak(learningDataModelArrayList2.get(this.selectedPosition).getSpeakTitle(), 0, (HashMap) null);
        }
        this.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float v, int i1) {
            }

            public void onPageSelected(int i) {
                kidsFullScreenAct.this.tvItemName.setText(kidsFullScreenAct.this.arrayOfImages.get(i).showTitle);
                YoYo.with(Techniques.RubberBand).duration(1200).repeat(1).playOn(kidsFullScreenAct.this.tvItemName);
                if (Utils.getPref(kidsConstant.SOUND, true).booleanValue()) {
                    kidsAppControl.textToSpeech.speak(kidsFullScreenAct.this.arrayOfImages.get(i).getSpeakTitle(), 0, (HashMap) null);
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    public void onClickBack(View view) {
        finish();
    }

    public void onClickPrev(View view) {
        if (this.viewPager.getCurrentItem() > 0) {
            kidsNonSwipeAbleViewPager nonSwipeAbleViewPager = this.viewPager;
            nonSwipeAbleViewPager.setCurrentItem(nonSwipeAbleViewPager.getCurrentItem() - 1);
        }
    }

    public void onClickNext(View view) {
        if (this.viewPager.getCurrentItem() < this.learningDataModelArrayList.size() - 1) {
            kidsNonSwipeAbleViewPager nonSwipeAbleViewPager = this.viewPager;
            nonSwipeAbleViewPager.setCurrentItem(nonSwipeAbleViewPager.getCurrentItem() + 1);
        }
    }

    class ViewPagerAdapter extends PagerAdapter {
        ArrayList<kidsLearningDataModel> arrayOfImages;
        LayoutInflater inflater;

        ViewPagerAdapter(ArrayList<kidsLearningDataModel> arrayOfImages2) {
            this.arrayOfImages = arrayOfImages2;
            this.inflater = LayoutInflater.from(kidsFullScreenAct.this.context);
        }

        public int getCount() {
            return this.arrayOfImages.size();
        }

        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = this.inflater.inflate(R.layout.viewpager_layout, container, false);
            kidsFullScreenAct.this.imageView = (ImageView) itemView.findViewById(R.id.cellImgViewPager);
            Glide.with(kidsFullScreenAct.this.context).load(Integer.valueOf(this.arrayOfImages.get(position).image)).apply((BaseRequestOptions<?>) new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(kidsFullScreenAct.this.imageView);
            YoYo.with(Techniques.Tada).duration(1200).repeat(1).playOn(kidsFullScreenAct.this.imageView);
            container.addView(itemView);
            return itemView;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((RelativeLayout) object);
        }
    }

    public void prepareDataForLearning(int i2) {
        //if (i2 == 0) {
        if (this.category.equals("Greetings 1")){
            ArrayList<kidsLearningDataModel> arrayList = new ArrayList<>();
            this.learningDataModelArrayList = arrayList;
            arrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_1, "Repeat after me: Hello", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_2, "Repeat after me: Good morning", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_3, "Repeat after me: Good afternoon", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_4, "Repeat after me: Good evening", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_5, "Repeat after me: Good night", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_6, "Repeat after me: Bye", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_7, "Repeat after me: Goodbye", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_8, "Repeat after me: See you", "Greetings"));
            int i = i2;
        }
        int i3 = i2;
        //if (i3 == 1) {
        if (this.category.equals("Greetings 2")){
            ArrayList<kidsLearningDataModel> arrayList2 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList2;
            arrayList2.add(new kidsLearningDataModel(R.drawable.unit1_week2_1, "Repeat after me: How are you?", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week2_2, "Repeat after me: I am fine. Thank you!", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week2_3, "Repeat after me: How are you today? ", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week2_4, "Repeat after me: I am fine", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week2_5, "Repeat after me: How are you today?  ", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week2_6, "Repeat after me: I am OK", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week2_7, "Repeat after me: I am great", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week2_8, "Repeat after me: How are you?", "Greetings"));
        //} else if (i3 == 2) {
        } else if (this.category.equals("Classroom Commands")){
            ArrayList<kidsLearningDataModel> arrayList3 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList3;
            arrayList3.add(new kidsLearningDataModel(R.drawable.unit1_week3_1, "Repeat after me: Open your book", "Classroom Commands"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week3_2, "Repeat after me: Listen", "Classroom Commands"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week3_3, "Repeat after me: Repeat", "Classroom Commands"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week3_4, "Repeat after me: Close your book", "Classroom Commands"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week3_5, "Repeat after me: Stand up", "Classroom Commands"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week3_6, "Repeat after me: Sit down", "Classroom Commands"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week3_7, "Repeat after me: Raise your hand", "Classroom Commands"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week3_8, "Repeat after me: Be quiet", "Classroom Commands"));
        //} else if (i3 == 3) {
        } else if (this.category.equals("Saying Your Name")){
            ArrayList<kidsLearningDataModel> arrayList4 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList4;
            arrayList4.add(new kidsLearningDataModel(R.drawable.unit1_week4_1, "Repeat after me: What’s your name?", "Saying Your Name"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week4_2, "Repeat after me: I am Michelle", "Saying Your Name"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week4_3, "Repeat after me: What’s your name?", "Saying Your Name"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week4_4, "Repeat after me: My name is Ana", "Saying Your Name"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week4_5, "Repeat after me: What’s your name?", "Saying Your Name"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week4_6, "Repeat after me: I am John", "Saying Your Name"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week4_7, "Repeat after me: What’s your name?", "Saying Your Name"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week4_8, "Repeat after me: My name is Lily", "Saying Your Name"));
        //} else if (i3 == 4) {
        } else if (this.category.equals("Introducing Myself")){
            ArrayList<kidsLearningDataModel> arrayList5 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList5;
            arrayList5.add(new kidsLearningDataModel(R.drawable.unit1_week5_1, "Repeat after me: Good morning! I am Michelle.", "Introducing Myself"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week5_2, "Repeat after me: Hello! My name is Lily", "Introducing Myself"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week5_3, "Repeat after me: Good afternoon! I am Lily.", "Introducing Myself"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week5_4, "Repeat after me: Hello! My name is Michelle.", "Introducing Myself"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week5_5, "Repeat after me: Good evening! I am John.", "Introducing Myself"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week5_6, "Repeat after me: Hello! My name is Ana", "Introducing Myself"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week5_7, "Repeat after me: Good evening! I am Ana.", "Introducing Myself"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week5_8, "Repeat after me: Hello! My name is John.", "Introducing Myself"));
        //} else if (i3 == 5) {
        } else if (this.category.equals("Test 1")){
            ArrayList<kidsLearningDataModel> arrayList6 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList6;
            arrayList6.add(new kidsLearningDataModel(R.drawable.unit1_week6_1, "Repeat after me: Hello", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week6_2, "Repeat after me: Good morning", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week6_3, "Repeat after me: What’s your name?", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week6_4, "Repeat after me: I am Michelle", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week6_5, "Repeat after me: Stand up", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week6_6, "Repeat after me: Sit down", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week6_7, "Repeat after me: Good evening! I am Ana.", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week6_8, "Repeat after me: Hello! My name is John.", "Test"));

        //} else if (i3 == 6) {
        } else if (this.category.equals("School Facilities")){
            ArrayList<kidsLearningDataModel> arrayList7 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList7;
            arrayList7.add(new kidsLearningDataModel(R.drawable.unit2_week1_1, "Repeat after me: The principal's office", "School Facilities"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_2, "Repeat after me: The classroom ", "School Facilities"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_3, "Repeat after me: The computer room", "School Facilities"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_4, "Repeat after me: The library", "School Facilities"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_5, "Repeat after me: The restrooms", "School Facilities"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_6, "Repeat after me: The playground", "School Facilities"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_7, "Repeat after me: The basketball court", "School Facilities"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_8, "Repeat after me: The soccer field", "School Facilities"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_9, "Repeat after me: The garden", "School Facilities"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_10, "Repeat after me: The parking lot", "School Facilities"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_11, "Repeat after me: The swimming pool", "School Facilities"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_12, "Repeat after me: The drama room", "School Facilities"));
        //} else if (i3 == 7) {
        } else if (this.category.equals("School Supplies")){
            ArrayList<kidsLearningDataModel> arrayList8 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList8;
            arrayList8.add(new kidsLearningDataModel(R.drawable.unit2_week2_1, "Repeat after me: Pencil", "School Supplies"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_2, "Repeat after me: Pen", "School Supplies"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_3, "Repeat after me: Pencil case", "School Supplies"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_4, "Repeat after me: Colored pencils", "School Supplies"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_5, "Repeat after me: Eraser", "School Supplies"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_6, "Repeat after me: Sharpener", "School Supplies"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_7, "Repeat after me: Markers", "School Supplies"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_8, "Repeat after me: Scissors", "School Supplies"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_9, "Repeat after me: Glue", "School Supplies"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_10, "Repeat after me: Glue stick", "School Supplies"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_11, "Repeat after me: Notebook", "School Supplies"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_12, "Repeat after me: Backpack", "School Supplies"));
        //} else if (i3 == 8) {
        } else if (this.category.equals("What Is It?")){
            ArrayList<kidsLearningDataModel> arrayList9 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList9;
            arrayList9.add(new kidsLearningDataModel(R.drawable.unit2_week3_1, "Repeat after me: What are they? They are pencils", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_2, "Repeat after me: What is it? It is a pencil", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_3, "Repeat after me: What are they? They are erasers", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_4, "Repeat after me: What is it? It is a eraser", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_5, "Repeat after me: What are they? They are books ", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_6, "Repeat after me: What is it? It is a book", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_7, "Repeat after me: What are they? They are rulers", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_8, "Repeat after me: What is it? It is a ruler", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_9, "Repeat after me: What are they? They are pens", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_10, "Repeat after me: What is it? It is a pen", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_11, "Repeat after me: What are they? They are pencil cases ", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_12, "Repeat after me: What is it? It is a pencil case", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_13, "Repeat after me: What are they? They are clocks", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_14, "Repeat after me: What is it? It is a clock", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_15, "Repeat after me: What are they? They are crayons ", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_16, "Repeat after me: What is it? It is a crayon", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_17, "Repeat after me: What are they? They are staplers", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_18, "Repeat after me: What is it? It is a stapler", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_19, "Repeat after me: What are they? They are notebooks", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_20, "Repeat after me: What is it? It is a notebook", "What Is It?"));
        //} else if (i3 == 9) {
        } else if (this.category.equals("Our First Board Game")){
            ArrayList<kidsLearningDataModel> arrayList10 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList10;
            arrayList10.add(new kidsLearningDataModel(R.drawable.unit2_week4_1, "Repeat after me: Instructions", "Our First Board Game"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week4_2, "Repeat after me: Toss the dice", "Our First Board Game"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week4_3, "Repeat after me: Flip a coin", "Our First Board Game"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week4_4, "Repeat after me: Look at the picture", "Our First Board Game"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week4_5, "Repeat after me: Say its name", "Our First Board Game"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week4_6, "Repeat after me: Look at the illustration", "Our First Board Game"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week4_7, "Repeat after me: Checkers", "Our First Board Game"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week4_8, "Repeat after me: Dominoes", "Our First Board Game"));
        //} else if (i3 == 10) {
        } else if (this.category.equals("Test 2")){
            ArrayList<kidsLearningDataModel> arrayList11 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList11;
            arrayList11.add(new kidsLearningDataModel(R.drawable.unit2_week5_1, "Repeat after me: The principal's office", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week5_2, "Repeat after me: The classroom ", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week5_3, "Repeat after me: Scissors", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week5_4, "Repeat after me: Glue", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week5_5, "Repeat after me: What are they? They are books ", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week5_6, "Repeat after me: What is it? It is a book", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week5_7, "Repeat after me: Flip a coin", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week5_8, "Repeat after me: Look at the picture", "Test"));

        } else {
            ArrayList<kidsLearningDataModel> arrayList21 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList21;
            arrayList21.add(new kidsLearningDataModel(R.drawable.unit1_week1_1, "Repeat after me: Hello", "Hello"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_2, "Repeat after me: Good morning", "Good morning"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_3, "Repeat after me: Good afternoon", "Good afternoon"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_4, "Repeat after me: Good evening", "Good evening"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_5, "Repeat after me: Good night", "Good night"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_6, "Repeat after me: Bye", "Bye"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_7, "Repeat after me: Goodbye", "Goodbye"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_8, "Repeat after me: See you", "See you"));
        }
        setViewPagerAdapter(this.learningDataModelArrayList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //appControl.loadAd(ads_layout);
    }
}
