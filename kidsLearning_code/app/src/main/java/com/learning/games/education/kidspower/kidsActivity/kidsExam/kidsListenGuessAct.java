package com.learning.games.education.kidspower.kidsActivity.kidsExam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.learning.games.education.kidspower.R;
import com.learning.games.education.kidspower.kidsAdapter.kidsListenGuessAdapter;
import com.learning.games.education.kidspower.kidsCustom.kidsAppControl;
import com.learning.games.education.kidspower.kidsCustom.kidsConstant;
import com.learning.games.education.kidspower.kidsUtils.Utils;

import com.learning.games.education.kidspower.kidsModel.kidsLearningDataModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class kidsListenGuessAct extends AppCompatActivity {
    Context context;
    int correctPosition;
    public ArrayList<kidsLearningDataModel> examQuestionAnswerList;
    ImageView iVQuestion;
    ArrayList<kidsLearningDataModel> learningDataModelArrayList;
    kidsListenGuessAdapter listenGuessAdapter;
    RelativeLayout llAdView;
    LinearLayout llAdViewFacebook;
    Random random;
    RecyclerView rv_exam;
    TextView tvName;
    TextView txtTitleSubHome;
    RelativeLayout ads_layout;
    kidsAppControl appControl;
    String category;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_exam_listen_guess);
        getSupportActionBar().hide();
        this.context = this;
        initDefine();

        ads_layout=findViewById(R.id.ads_layout);
        appControl= (kidsAppControl) getApplication();
    }

    private void initDefine() {
        this.iVQuestion = (ImageView) findViewById(R.id.iVQuestion);
        this.rv_exam = (RecyclerView) findViewById(R.id.rv_exam);
        this.tvName = (TextView) findViewById(R.id.tvName);
        this.txtTitleSubHome = (TextView) findViewById(R.id.txtTitleSubHome);

        
        Intent intent = getIntent();
        prepareDataForLearning(intent.getIntExtra("categoryPosition", 0));
        this.txtTitleSubHome.setText(intent.getStringExtra("SubCate"));
        this.category = intent.getStringExtra("SubCate");
        getRandomArray();
    }

    private void getRandomArray() {
        this.random = new Random();
        this.examQuestionAnswerList = new ArrayList<>();
        this.correctPosition = this.random.nextInt(this.learningDataModelArrayList.size());
        if (Utils.getPref(kidsConstant.SOUND, true).booleanValue()) {
            kidsAppControl.textToSpeech.speak(this.learningDataModelArrayList.get(this.correctPosition).showTitle, 0, (HashMap) null);
        }
        this.tvName.setText(this.learningDataModelArrayList.get(this.correctPosition).showTitle);
        Glide.with(this.context).load(Integer.valueOf(R.drawable.btn_sound)).apply((BaseRequestOptions<?>) new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(this.iVQuestion);
        this.iVQuestion.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce_amn));
        this.examQuestionAnswerList.add(this.learningDataModelArrayList.get(this.correctPosition));
        do {
            int number = this.random.nextInt(this.learningDataModelArrayList.size());
            if (!this.examQuestionAnswerList.contains(this.learningDataModelArrayList.get(number))) {
                this.examQuestionAnswerList.add(this.learningDataModelArrayList.get(number));
            }
        } while (this.examQuestionAnswerList.size() != 4);
        Collections.shuffle(this.examQuestionAnswerList);
        setRvAdapter();
    }

    public void onClickSound(View view) {
        if (Utils.getPref(kidsConstant.SOUND, true).booleanValue()) {
            kidsAppControl.textToSpeech.speak(this.learningDataModelArrayList.get(this.correctPosition).showTitle, 0, (HashMap) null);
        }
    }

    private void setRvAdapter() {
        this.rv_exam.setLayoutManager(new GridLayoutManager(this.context, 2, RecyclerView.VERTICAL, false));
        kidsListenGuessAdapter listenGuessAdapter2 = new kidsListenGuessAdapter(this.context, this.examQuestionAnswerList, this.learningDataModelArrayList.get(this.correctPosition));
        this.listenGuessAdapter = listenGuessAdapter2;
        this.rv_exam.setAdapter(listenGuessAdapter2);
    }

    public void onClickNext(View view) {
        getRandomArray();
    }

    public void onClickPrev(View view) {
        getRandomArray();
    }

    public void onClickBack(View view) {
        finish();
    }

    public void prepareDataForLearning(int i2) {
        //if (i2 == 0) {
        if (this.category.equals("Greetings 1")){
            ArrayList<kidsLearningDataModel> arrayList = new ArrayList<>();
            this.learningDataModelArrayList = arrayList;
            arrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_1, "Hello", "Hello"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_2, "Good morning", "Good morning"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_3, "Good afternoon", "Good afternoon"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_4, "Good evening", "Good evening"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_5, "Good night", "Good night"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_6, "Bye", "Bye"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_7, "Goodbye", "Goodbye"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_8, "See you", "See you"));
            int i = i2;
            return;
        }
        int i3 = i2;
        //if (i3 == 1) {
        if (this.category.equals("Greetings 2")){
            ArrayList<kidsLearningDataModel> arrayList2 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList2;
            arrayList2.add(new kidsLearningDataModel(R.drawable.unit1_week2_1, "How are you?", "How are you?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week2_2, "I am fine. Thank you!", "I am fine. Thank you!"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week2_3, "How are you today? ", "How are you today? "));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week2_4, "I am fine", "I am fine"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week2_5, "How are you today?  ", "How are you today?  "));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week2_6, "I am OK", "I am OK"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week2_7, "I am great", "I am great"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week2_8, "How are you?", "How are you?"));
        //} else if (i3 == 2) {
        } else if (this.category.equals("Classroom Commands")){
            ArrayList<kidsLearningDataModel> arrayList3 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList3;
            arrayList3.add(new kidsLearningDataModel(R.drawable.unit1_week3_1, "Open your book", "Open your book"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week3_2, "Listen", "Listen"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week3_3, "Repeat", "Repeat"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week3_4, "Close your book", "Close your book"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week3_5, "Stand up", "Stand up"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week3_6, "Sit down", "Sit down"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week3_7, "Raise your hand", "Raise your hand"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week3_8, "Be quiet", "Be quiet"));
        //} else if (i3 == 3) {
        } else if (this.category.equals("Saying Your Name")){
            ArrayList<kidsLearningDataModel> arrayList4 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList4;
            arrayList4.add(new kidsLearningDataModel(R.drawable.unit1_week4_1, "What’s your name?", "What’s your name?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week4_2, "I am Michelle", "I am Michelle"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week4_3, "What’s your name?", "What’s your name?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week4_4, "My name is Ana", "My name is Ana"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week4_5, "What’s your name?", "What’s your name?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week4_6, "I am John", "I am John"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week4_7, "What’s your name?", "What’s your name?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week4_8, "My name is Lily", "My name is Lily"));
        //} else if (i3 == 4) {
        } else if (this.category.equals("Introducing Myself")){
            ArrayList<kidsLearningDataModel> arrayList5 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList5;
            arrayList5.add(new kidsLearningDataModel(R.drawable.unit1_week5_1, "Good morning! I am Michelle.", "Good morning! I am Michelle."));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week5_2, "Hello! My name is Lily", "Hello! My name is Lily"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week5_3, "Good afternoon! I am Lily.", "Good afternoon! I am Lily."));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week5_4, "Hello! My name is Michelle.", "Hello! My name is Michelle."));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week5_5, "Good evening! I am John.", "Good evening! I am John."));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week5_6, "Hello! My name is Ana", "Hello! My name is Ana"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week5_7, "Good evening! I am Ana.", "Good evening! I am Ana."));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week5_8, "Hello! My name is John.", "Hello! My name is John."));
        //} else if (i3 == 5) {
        } else if (this.category.equals("Test 1")){
            ArrayList<kidsLearningDataModel> arrayList6 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList6;
            arrayList6.add(new kidsLearningDataModel(R.drawable.unit1_week6_1, "Hello", "Hello"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week6_2, "Good morning", "Good morning"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week6_3, "What’s your name?", "What’s your name?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week6_4, "I am Michelle", "I am Michelle"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week6_5, "Stand up", "Stand up"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week6_6, "Sit down", "Sit down"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week6_7, "Good evening! I am Ana.", "Good evening! I am Ana."));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week6_8, "Hello! My name is John.", "Hello! My name is John."));

        //} else if (i3 == 6) {
        } else if (this.category.equals("School Facilities")){
            ArrayList<kidsLearningDataModel> arrayList7 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList7;
            arrayList7.add(new kidsLearningDataModel(R.drawable.unit2_week1_1, "The principal's office", "School Facilities"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_2, "The classroom ", "School Facilities"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_3, "The computer room", "School Facilities"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_4, "The library", "School Facilities"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_5, "The restrooms", "School Facilities"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_6, "The playground", "School Facilities"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_7, "The basketball court", "School Facilities"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_8, "The soccer field", "School Facilities"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_9, "The garden", "School Facilities"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_10, "The parking lot", "School Facilities"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_11, "The swimming pool", "School Facilities"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_12, "The drama room", "School Facilities"));
        //} else if (i3 == 7) {
        } else if (this.category.equals("School Supplies")){
            ArrayList<kidsLearningDataModel> arrayList8 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList8;
            arrayList8.add(new kidsLearningDataModel(R.drawable.unit2_week2_1, "Pencil", "School Supplies"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_2, "Pen", "School Supplies"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_3, "Pencil case", "School Supplies"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_4, "Colored pencils", "School Supplies"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_5, "Eraser", "School Supplies"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_6, "Sharpener", "School Supplies"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_7, "Markers", "School Supplies"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_8, "Scissors", "School Supplies"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_9, "Glue", "School Supplies"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_10, "Glue stick", "School Supplies"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_11, "Notebook", "School Supplies"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_12, "Backpack", "School Supplies"));
        //} else if (i3 == 8) {
        } else if (this.category.equals("What Is It?")){
            ArrayList<kidsLearningDataModel> arrayList9 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList9;
            arrayList9.add(new kidsLearningDataModel(R.drawable.unit2_week3_1, "What are they? They are pencils", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_2, "What is it? It is a pencil", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_3, "What are they? They are erasers", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_4, "What is it? It is a eraser", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_5, "What are they? They are books ", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_6, "What is it? It is a book", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_7, "What are they? They are rulers", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_8, "What is it? It is a ruler", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_9, "What are they? They are pens", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_10, "What is it? It is a pen", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_11, "What are they? They are pencil cases ", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_12, "What is it? It is a pencil case", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_13, "What are they? They are clocks", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_14, "What is it? It is a clock", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_15, "What are they? They are crayons ", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_16, "What is it? It is a crayon", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_17, "What are they? They are staplers", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_18, "What is it? It is a stapler", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_19, "What are they? They are notebooks", "What Is It?"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_20, "What is it? It is a notebook", "What Is It?"));
        //} else if (i3 == 9) {
        } else if (this.category.equals("Our First Board Game")){
            ArrayList<kidsLearningDataModel> arrayList10 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList10;
            arrayList10.add(new kidsLearningDataModel(R.drawable.unit2_week4_1, "Instructions", "Our First Board Game"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week4_2, "Toss the dice", "Our First Board Game"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week4_3, "Flip a coin", "Our First Board Game"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week4_4, "Look at the picture", "Our First Board Game"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week4_5, "Say its name", "Our First Board Game"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week4_6, "Look at the illustration", "Our First Board Game"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week4_7, "Checkers", "Our First Board Game"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week4_8, "Dominoes", "Our First Board Game"));
        //} else if (i3 == 10) {
        } else if (this.category.equals("Test 2")){
            ArrayList<kidsLearningDataModel> arrayList11 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList11;
            arrayList11.add(new kidsLearningDataModel(R.drawable.unit2_week5_1, "The principal's office", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week5_2, "The classroom ", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week5_3, "Scissors", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week5_4, "Glue", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week5_5, "What are they? They are books ", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week5_6, "What is it? It is a book", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week5_7, "Flip a coin", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week5_8, "Look at the picture", "Test"));

        } else {
            ArrayList<kidsLearningDataModel> arrayList21 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList21;
            arrayList21.add(new kidsLearningDataModel(R.drawable.unit1_week1_1, "Hello", "Hello"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_2, "Good morning", "Good morning"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_3, "Good afternoon", "Good afternoon"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_4, "Good evening", "Good evening"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_5, "Good night", "Good night"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_6, "Bye", "Bye"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_7, "Goodbye", "Goodbye"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_8, "See you", "See you"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //appControl.loadAd(ads_layout);
    }
}
