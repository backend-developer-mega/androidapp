package com.learning.games.education.kidspower.kidsActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.games.education.kidspower.R;
import com.learning.games.education.kidspower.kidsAdapter.kidsSubHomeAdapter;
import com.learning.games.education.kidspower.kidsCustom.kidsAppControl;
import com.learning.games.education.kidspower.kidsModel.kidsLearningDataModel;

import java.util.ArrayList;

public class kidsSubAct extends AppCompatActivity {
    int TYPE;
    Context context;
    ArrayList<kidsLearningDataModel> learningDataModelArrayList;
    int position;
    RecyclerView rvSubHome;
    kidsSubHomeAdapter subHomeAdapter;
    TextView txtTitleSubHome;
    kidsAppControl appControl;
    RelativeLayout ads_layout;
    String category = "Greetings 1";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_sub);
        getSupportActionBar().hide();
        this.context = this;
        initDefine();

        ads_layout=findViewById(R.id.ads_layout);
        appControl= (kidsAppControl) getApplication();
    }

    private void initDefine() {
        this.rvSubHome = (RecyclerView) findViewById(R.id.rvSubHome);
        this.txtTitleSubHome = (TextView) findViewById(R.id.txtTitleSubHome);
        Intent intent = getIntent();
        this.position = intent.getIntExtra("categoryPosition", 0);
        this.category = intent.getStringExtra("Category");
        this.TYPE = intent.getIntExtra("Type", 0);
        this.txtTitleSubHome.setText(this.category);
        prepareDataForLearning(this.position);
    }

    private void setRvAdapter() {
        this.rvSubHome.setLayoutManager(new GridLayoutManager(this.context, 2, RecyclerView.VERTICAL, false));
        kidsSubHomeAdapter subHomeAdapter2 = new kidsSubHomeAdapter(this.context, this.learningDataModelArrayList, this.position, this.TYPE, this.category);
        this.subHomeAdapter = subHomeAdapter2;
        this.rvSubHome.setAdapter(subHomeAdapter2);
    }

    public void onClickBack(View view) {
        finish();
    }

    public void prepareDataForLearning(int i2) {
        //if (i2 == 0) {
        if (this.category.equals("Greetings 1")){
            ArrayList<kidsLearningDataModel> arrayList = new ArrayList<>();
            this.learningDataModelArrayList = arrayList;
            arrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_1, "Hello", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_2, "Good morning", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_3, "Good afternoon", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_4, "Good evening", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_5, "Good night", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_6, "Bye", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_7, "Goodbye", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week1_8, "See you", "Greetings"));
            int i = i2;
        }
        int i3 = i2;
        //if (i3 == 1) {
        if (this.category.equals("Greetings 2")){
            ArrayList<kidsLearningDataModel> arrayList2 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList2;
            arrayList2.add(new kidsLearningDataModel(R.drawable.unit1_week2_1, "How are you?", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week2_2, "I am fine. Thank you!", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week2_3, "How are you today? ", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week2_4, "I am fine", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week2_5, "How are you today?  ", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week2_6, "I am OK", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week2_7, "I am great", "Greetings"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week2_8, "How are you?", "Greetings"));
        //} else if (i3 == 2) {
        } else if (this.category.equals("Classroom Commands")){
            ArrayList<kidsLearningDataModel> arrayList3 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList3;
            arrayList3.add(new kidsLearningDataModel(R.drawable.unit1_week3_1, "Open your book", "Classroom Commands"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week3_2, "Listen", "Classroom Commands"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week3_3, "Repeat", "Classroom Commands"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week3_4, "Close your book", "Classroom Commands"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week3_5, "Stand up", "Classroom Commands"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week3_6, "Sit down", "Classroom Commands"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week3_7, "Raise your hand", "Classroom Commands"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week3_8, "Be quiet", "Classroom Commands"));
        //} else if (i3 == 3) {
        } else if (this.category.equals("Saying Your Name")){
            ArrayList<kidsLearningDataModel> arrayList4 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList4;
            arrayList4.add(new kidsLearningDataModel(R.drawable.unit1_week4_1, "What’s your name?", "Saying Your Name"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week4_2, "I am Michelle", "Saying Your Name"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week4_3, "What’s your name?", "Saying Your Name"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week4_4, "My name is Ana", "Saying Your Name"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week4_5, "What’s your name?", "Saying Your Name"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week4_6, "I am John", "Saying Your Name"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week4_7, "What’s your name?", "Saying Your Name"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week4_8, "My name is Lily", "Saying Your Name"));
        //} else if (i3 == 4) {
        } else if (this.category.equals("Introducing Myself")){
            ArrayList<kidsLearningDataModel> arrayList5 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList5;
            arrayList5.add(new kidsLearningDataModel(R.drawable.unit1_week5_1, "Good morning! I am Michelle.", "Introducing Myself"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week5_2, "Hello! My name is Lily", "Introducing Myself"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week5_3, "Good afternoon! I am Lily.", "Introducing Myself"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week5_4, "Hello! My name is Michelle.", "Introducing Myself"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week5_5, "Good evening! I am John.", "Introducing Myself"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week5_6, "Hello! My name is Ana", "Introducing Myself"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week5_7, "Good evening! I am Ana.", "Introducing Myself"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week5_8, "Hello! My name is John.", "Introducing Myself"));
        //} else if (i3 == 5) {
        } else if (this.category.equals("Test 1")){
            ArrayList<kidsLearningDataModel> arrayList6 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList6;
            arrayList6.add(new kidsLearningDataModel(R.drawable.unit1_week6_1, "Hello", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week6_2, "Good morning", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week6_3, "What’s your name?", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week6_4, "I am Michelle", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week6_5, "Stand up", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week6_6, "Sit down", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week6_7, "Good evening! I am Ana.", "Test"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.unit1_week6_8, "Hello! My name is John.", "Test"));

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
        setRvAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //appControl.loadAd(ads_layout);
    }
}
