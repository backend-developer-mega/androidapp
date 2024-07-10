package com.learning.games.education.kidspower.kidsActivity.kidsExam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.learning.games.education.kidspower.kidsAdapter.kidsExamQuestionAdapter;
import com.learning.games.education.kidspower.kidsCustom.kidsAppControl;
import com.learning.games.education.kidspower.kidsModel.kidsLearningDataModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class kidsLookChooseAct extends AppCompatActivity {
    ArrayList<ArrayList<kidsLearningDataModel>> arrOfPrevious = new ArrayList<>();
    Context context;
    int correctPosition;
    kidsExamQuestionAdapter examQuestionAdapter;
    public ArrayList<kidsLearningDataModel> examQuestionAnswerList;
    ImageView iVQuestion;
    boolean isPreviousShow = false;
    ArrayList<kidsLearningDataModel> learningDataModelArrayList;
    RelativeLayout llAdView;
    LinearLayout llAdViewFacebook;
    Random random;
    RecyclerView rv_exam;
    TextView txtTitleSubHome;
    RelativeLayout ads_layout;
    kidsAppControl appControl;
    String category = "Greetings 1";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_exam_question);
        getSupportActionBar().hide();
        this.context = this;
        initDefine();
        ads_layout=findViewById(R.id.ads_layout);
        appControl= (kidsAppControl) getApplication();
    }

    private void initDefine() {
        this.iVQuestion = (ImageView) findViewById(R.id.iVQuestion);
        this.rv_exam = (RecyclerView) findViewById(R.id.rv_exam);
        this.txtTitleSubHome = (TextView) findViewById(R.id.txtTitleSubHome);
        this.rv_exam.setLayoutManager(new GridLayoutManager(this.context, 2, RecyclerView.VERTICAL, false));
        Intent intent = getIntent();
        this.category = intent.getStringExtra("Category");
        prepareDataForLearning(intent.getIntExtra("categoryPosition", 0));
        this.txtTitleSubHome.setText(intent.getStringExtra("SubCate"));
        getRandomArray();
      
        
    }

    private void getRandomArray() {
        this.random = new Random();
        this.examQuestionAnswerList = new ArrayList<>();
        int nextInt = this.random.nextInt(this.learningDataModelArrayList.size());
        this.correctPosition = nextInt;
        this.examQuestionAnswerList.add(this.learningDataModelArrayList.get(nextInt));
        Glide.with(this.context).load(Integer.valueOf(this.learningDataModelArrayList.get(this.correctPosition).image)).apply((BaseRequestOptions<?>) new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(this.iVQuestion);
        do {
            int number = this.random.nextInt(this.learningDataModelArrayList.size());
            if (!this.examQuestionAnswerList.contains(this.learningDataModelArrayList.get(number))) {
                this.examQuestionAnswerList.add(this.learningDataModelArrayList.get(number));
                ArrayList<kidsLearningDataModel> arrayList = this.examQuestionAnswerList;
                arrayList.get(arrayList.size() - 1).setTrue(false);
            }
        } while (this.examQuestionAnswerList.size() != 4);
        Collections.shuffle(this.examQuestionAnswerList);
        setRvAdapter(this.isPreviousShow);
    }

    private void setRvAdapter(boolean isPreviousShow2) {
        if (isPreviousShow2) {
            kidsLearningDataModel learningDataModel = null;
            int i = 0;
            while (true) {
                if (i >= this.examQuestionAnswerList.size()) {
                    break;
                } else if (this.examQuestionAnswerList.get(i).isTrue()) {
                    learningDataModel = this.examQuestionAnswerList.get(i);
                    Glide.with(this.context).load(Integer.valueOf(learningDataModel.image)).apply((BaseRequestOptions<?>) new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(this.iVQuestion);
                    break;
                } else {
                    i++;
                }
            }
            this.examQuestionAdapter = new kidsExamQuestionAdapter(this.context, this.examQuestionAnswerList, learningDataModel);
        } else {
            this.examQuestionAdapter = new kidsExamQuestionAdapter(this.context, this.examQuestionAnswerList, this.learningDataModelArrayList.get(this.correctPosition));
        }
        this.rv_exam.setAdapter(this.examQuestionAdapter);
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
            arrayList7.add(new kidsLearningDataModel(R.drawable.unit2_week1_1, "The principal's office", "The principal's office"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_2, "The classroom", "The classroom"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_3, "The computer room", "The computer room"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_4, "The library", "The library"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_5, "The restrooms", "The restrooms"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_6, "The playground", "The playground"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_7, "The basketball court", "The basketball court"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_8, "The soccer field", "The soccer field"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_9, "The garden", "The garden"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_10, "The parking lot", "The parking lot"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_11, "The swimming pool", "The swimming pool"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week1_12, "The drama room", "The drama room"));
        //} else if (i3 == 7) {
        } else if (this.category.equals("School Supplies")){
            ArrayList<kidsLearningDataModel> arrayList8 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList8;
            arrayList8.add(new kidsLearningDataModel(R.drawable.unit2_week2_1, "Pencil", "Pencil"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_2, "Pen", "Pen"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_3, "Pencil case", "Pencil case"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_4, "Colored pencils", "Colored pencils"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_5, "Eraser", "Eraser"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_6, "Sharpener", "Sharpener"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_7, "Markers", "Markers"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_8, "Scissors", "Scissors"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_9, "Glue", "Glue"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_10, "Glue stick", "Glue stick"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_11, "Notebook", "Notebook"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week2_12, "Backpack", "Backpack"));
        //} else if (i3 == 8) {
        } else if (this.category.equals("What Is It?")){
            ArrayList<kidsLearningDataModel> arrayList9 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList9;
            arrayList9.add(new kidsLearningDataModel(R.drawable.unit2_week3_1, "What are they? They are pencils", "What are they? They are pencils"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_2, "What is it? It is a pencil", "What is it? It is a pencil"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_3, "What are they? They are erasers", "What are they? They are erasers"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_4, "What is it? It is a eraser", "What is it? It is a eraser"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_5, "What are they? They are books", "What are they? They are books"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_6, "What is it? It is a book", "What is it? It is a book"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_7, "What are they? They are rulers", "What are they? They are rulers"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_8, "What is it? It is a ruler", "What is it? It is a ruler"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_9, "What are they? They are pens", "What are they? They are pens"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_10, "What is it? It is a pen", "What is it? It is a pen"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_11, "What are they? They are pencil cases", "What are they? They are pencil cases"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_12, "What is it? It is a pencil case", "What is it? It is a pencil case"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_13, "What are they? They are clocks", "What are they? They are clocks"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_14, "What is it? It is a clock", "What is it? It is a clock"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_15, "What are they? They are crayons", "What are they? They are crayons"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_16, "What is it? It is a crayon", "What is it? It is a crayon"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_17, "What are they? They are staplers", "What are they? They are staplers"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_18, "What is it? It is a stapler", "What is it? It is a stapler"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_19, "What are they? They are notebooks", "What are they? They are notebooks"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week3_20, "What is it? It is a notebook", "What is it? It is a notebook"));
        //} else if (i3 == 9) {
        } else if (this.category.equals("Our First Board Game")){
            ArrayList<kidsLearningDataModel> arrayList10 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList10;
            arrayList10.add(new kidsLearningDataModel(R.drawable.unit2_week4_1, "Instructions", "Instructions"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week4_2, "Toss the dice", "Toss the dice"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week4_3, "Flip a coin", "Flip a coin"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week4_4, "Look at the picture", "Look at the picture"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week4_5, "Say its name", "Say its name"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week4_6, "Look at the illustration", "Look at the illustration"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week4_7, "Checkers", "Checkers"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week4_8, "Dominoes", "Dominoes"));
        //} else if (i3 == 10) {
        } else if (this.category.equals("Test 2")){    
            ArrayList<kidsLearningDataModel> arrayList11 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList11;
            arrayList11.add(new kidsLearningDataModel(R.drawable.unit2_week5_1, "The principal's office", "The principal's office"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week5_2, "The classroom", "The classroom"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week5_3, "Scissors", "Scissors"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week5_4, "Glue", "Glue"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week5_5, "What are they? They are books", "What are they? They are books"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week5_6, "What is it? It is a book", "What is it? It is a book"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week5_7, "Flip a coin", "Flip a coin"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit2_week5_8, "Look at the picture", "Look at the picture"));

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
