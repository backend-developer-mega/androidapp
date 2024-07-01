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
        if (i2 == 0) {
            ArrayList<kidsLearningDataModel> arrayList = new ArrayList<>();
            this.learningDataModelArrayList = arrayList;
            arrayList.add(new kidsLearningDataModel(R.drawable.a, "A for Apple", "Apple"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.b, "B for Ball", "Ball"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.c, "C for Cat", "Cat"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.d, "D for Dog", "Dog"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.e, "E for Elephant", "Elephant"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.f, "F for Fish", "Fish"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.g, "G for Goat", "Goat"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.h, "H for Horse", "Horse"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.i, "I for Ice cream", "Ice cream"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.j, "J for Joker", "Joker"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.k, "K for Kite", "Kite"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.l, "L for Lion", "Lion"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.m, "M for Monkey", "Monkey"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.n, "N for Nest", "Nest"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.o, "O for Orange", "Orange"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.p, "P for Parrot", "Parrot"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.q, "Q for Queen", "Queen"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.r, "R for Rabbit", "Rabbit"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.s, "S for Sun", "Sun"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.t, "T for Train", "Train"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.u, "U for Umbrella", "Umbrella"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.v, "V for Violin", "Violin"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.w, "W for Watch", "Watch"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.x, "X for Xylophone", "Xylophone"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.y, "Y for Yak", "Yak"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.z, "Z for Zebra", "Zebra"));
            int i = i2;
            return;
        }
        int i3 = i2;
        if (i3 == 1) {
            ArrayList<kidsLearningDataModel> arrayList2 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList2;
            arrayList2.add(new kidsLearningDataModel(R.drawable.zero_0, "Zero", "Zero"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.one_1, "One", "One"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.two_2, "Two", "Two"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.three_3, "Three", "Three"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.four_4, "Four", "Four"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.five_5, "Five", "Five"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.six_6, "Six", "Six"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.seven_7, "Seven", "Seven"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.eight_8, "Eight", "Eight"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.nine_9, "Nine", "Nine"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.ten_10, "Ten", "Ten"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.eleven_11, "Eleven", "Eleven"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.twelve_12, "Twelve", "Twelve"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.thirteen_13, "Thirteen", "Thirteen"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.fourteen_14, "Fourteen", "Fourteen"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.fifteen_15, "Fifteen", "Fifteen"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.sixteen_16, "Sixteen", "Sixteen"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.seventeen_17, "Seventeen", "Seventeen"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.eighteen_18, "Eighteen", "Eighteen"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.nineteen_19, "Nineteen", "Nineteen"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.twenty_20, "Twenty", "Twenty"));
        } else if (i3 == 2) {
            ArrayList<kidsLearningDataModel> arrayList3 = new ArrayList<>();
            this.learningDataModelArrayList = arrayList3;
            arrayList3.add(new kidsLearningDataModel(R.drawable.green, "Green", "Green"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.pink, "Pink", "Pink"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.red, "Red", "Red"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.black, "Black", "Black"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.aqua, "Aqua", "Aqua"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.blue, "Blue", "Blue"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.brown, "Brown", "Brown"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.slate, "Slate", "Slate"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.violet, "Violet", "Violet"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.white, "White", "White"));
            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.yellow, "Yellow", "Yellow"));
        } else {
            String str = "Train";
            String str2 = "Umbrella";
            String str3 = "Watch";
            String str4 = "Orange";
            String str5 = "Apple";
            if (i3 == 3) {
                ArrayList<kidsLearningDataModel> arrayList4 = new ArrayList<>();
                this.learningDataModelArrayList = arrayList4;
                arrayList4.add(new kidsLearningDataModel(R.drawable.circle, "Circle", "Circle"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.square, "Square", "Square"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.decagon, "Decagon", "Decagon"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.ellipse, "Ellipse", "Ellipse"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.hexagon, "Hexagon", "Hexagon"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.triangle, "Triangle", "Triangle"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.octagon, "Octagon", "Octagon"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.parallelogram, "Parallelogram", "Parallelogram"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.pentagon, "Pentagon", "Pentagon"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.rectangle, "Rectangle", "Rectangle"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.rhombus, "Rhombus", "Rhombus"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.right_triangle, "Right Triangle", "Right Triangle"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.trapezoid, "Trapezoid", "Trapezoid"));
            } else if (i3 == 4) {
                ArrayList<kidsLearningDataModel> arrayList5 = new ArrayList<>();
                this.learningDataModelArrayList = arrayList5;
                arrayList5.add(new kidsLearningDataModel(R.drawable.bear, "Bear", "Bear"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.bison, "Bison", "Bison"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.black_leopard, "Black Leopard", "Black Leopard"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.cheetah, "Cheetah", "Cheetah"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.chimpanzee, "Chimpanzee", "Chimpanzee"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.chipmuck, "Chipmuck", "Chipmuck"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.cougar, "Cougar", "Cougar"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.deer, "Deer", "Deer"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.elephant, "Elephant", "Elephant"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.fox, "Fox", "Fox"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.giraffe, "Giraffe", "Giraffe"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.gorilla, "Gorilla", "Gorilla"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.hedgehog, "Hedgehog", "Hedgehog"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.hippopotamus, "Hippopotamus", "Hippopotamus"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.hyena, "Hyena", "Hyena"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.jackal, "Jackal", "Jackal"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.jaguar, "Jaguar", "Jaguar"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.kangaroo, "Kangaroo", "Kangaroo"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.koala_bear, "Koala Bear", "Koala Bear"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.lion, "Lion", "Lion"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.meerkat, "Meerkat", "Meerkat"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.mongoose, "Mongoose", "Mongoose"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.monkey, "Monkey", "Monkey"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.opossum, "Opossum", "Opossum"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.otter, "Otter", "Otter"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.panda, "Panda", "Panda"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.polar_bear, "Polar Bear", "Polar Bear"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.porcupine, "Porcupine", "Porcupine"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.raccoon, "Raccoon", "Raccoon"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.red_panda, "Red Panda", "Red Panda"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.rhinoceros, "Rhinoceros", "Rhinoceros"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.scimitar_oryx, "Scimitar Oryx", "Scimitar Oryx"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.squirrel, "Squirrel", "Squirrel"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.tiger, "Tiger", "Tiger"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.wolf, "Wolf", "Wolf"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.wombat, "Wombat", "Wombat"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.zebra, "Zebra", "Zebra"));
            } else if (i3 == 5) {
                ArrayList<kidsLearningDataModel> arrayList6 = new ArrayList<>();
                this.learningDataModelArrayList = arrayList6;
                arrayList6.add(new kidsLearningDataModel(R.drawable.canary, "Canary", "Canary"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.crow, "Crow", "Crow"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.dove, "Dove", "Dove"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.duck, "Duck", "Duck"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.eagle, "Eagle", "Eagle"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.hoopoe, "Hoopoe", "Hoopoe"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.hornbill, "Hornbill", "Hornbill"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.kingfisher, "Kingfisher", "Kingfisher"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.kite, "Kite", "Kite"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.lapwing, "Lapwing", "Lapwing"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.mynah, "Mynah", "Mynah"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.nightingale, "Nightingale", "Nightingale"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.owl, "Owl", "Owl"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.parrot, "Parrot", "Parrot"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.peacock, "Peacock", "Peacock"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.peahen, "Peahen", "Peahen"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.pheasant, "Pheasant", "Pheasant"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.pigeon, "Pigeon", "Pigeon"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.puffin, "Puffin", "Puffin"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.quail, "Quail", "Quail"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.robin, "Robin", "Robin"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.sparrow, "Sparrow", "Sparrow"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.swallow, "Swallow", "Swallow"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.toucan, "Toucan", "Toucan"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.vulture, "Vulture", "Vulture"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.wagtail, "Wagtail", "Wagtail"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.waxwing, "Waxwing", "Waxwing"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.woodpecker, "Woodpecker", "Woodpecker"));
            } else if (i3 == 7) {
                ArrayList<kidsLearningDataModel> arrayList7 = new ArrayList<>();
                this.learningDataModelArrayList = arrayList7;
                String str6 = str5;
                arrayList7.add(new kidsLearningDataModel(R.drawable.apple, str6, str6));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.apricot, "Apricot", "Apricot"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.avocado, "Avocado", "Avocado"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.blackberry, "Blackberry", "Blackberry"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.blackcurrant, "Blackcurrant", "Blackcurrant"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.blueberry, "Blueberry", "Blueberry"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.cherry, "Cherry", "Cherry"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.coconut, "Coconut", "Coconut"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.fig, "Fig", "Fig"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.grape, "Grape", "Grape"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.kiwi, "Kiwi", "Kiwi"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.lemon, "Lemon", "Lemon"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.lime, "Lime", "Lime"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.lychee, "Lychee", "Lychee"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.mango, "Mango", "Mango"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.nectarine, "Nectarine", "Nectarine"));
                String str7 = str4;
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.orange, str7, str7));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.papaya, "Papaya", "Papaya"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.passion, "Passion", "Passion"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.peach, "Peach", "Peach"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.pear, "Pear", "Pear"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.pineapple, "Pineapple", "Pineapple"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.plum, "Plum", "Plum"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.quince, "Quince", "Quince"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.raspberry, "Raspberry", "Raspberry"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.strawberry, "Strawberry", "Strawberry"));
                this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.watermelon, "Watermelon", "Watermelon"));
            } else {
                String str8 = "Zebra";
                String str9 = str4;
                String str10 = str5;
                String str11 = "Parrot";
                if (i3 == 6) {
                    ArrayList<kidsLearningDataModel> arrayList8 = new ArrayList<>();
                    this.learningDataModelArrayList = arrayList8;
                    arrayList8.add(new kidsLearningDataModel(R.drawable.arum_lily, "Arum Lily", "Arum Lily"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.aster, "Aster", "Aster"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.bird_of_paradise, "Bird Of Paradise", "Bird Of Paradise"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.bougainvillea, "Bougainvillea", "Bougainvillea"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.buttercup, "Buttercup", "Buttercup"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.calendula, "Calendula", "Calendula"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.canna, "Canna", "Canna"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.cockscomb, "Cockscomb", "Cockscomb"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.daffodils, "Daffodils", "Daffodils"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.dahlia, "Dahlia", "Dahlia"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.daisy, "Daisy", "Daisy"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.dianthus, "Dianthus", "Dianthus"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.gladiolus, "Gladiolus", "Gladiolus"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.hibiscus, "Hibiscus", "Hibiscus"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.jasmine, "Jasmine", "Jasmine"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.lavender, "Lavender", "Lavender"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.lilac, "Lilac", "Lilac"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.lily, "Lily", "Lily"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.lotus, "Lotus", "Lotus"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.morning_glory, "Morning Glory", "Morning Glory"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.nerium_oleander, "Nerium Oleander", "Nerium Oleander"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.orchids, "Orchids", "Orchids"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.peony, "Peony", "Peony"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.periwinkle, "Periwinkle", "Periwinkle"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.petunia, "Petunia", "Petunia"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.poppy, "Poppy", "Poppy"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.purple_mallow, "Purple Mallow", "Purple Mallow"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.rose, "Rose", "Rose"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.sunflower, "Sunflower", "Sunflower"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.tulips, "Tulips", "Tulips"));
                } else if (i3 == 8) {
                    ArrayList<kidsLearningDataModel> arrayList9 = new ArrayList<>();
                    this.learningDataModelArrayList = arrayList9;
                    arrayList9.add(new kidsLearningDataModel(R.drawable.january, "January", "January"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.february, "February", "February"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.march, "March", "March"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.april, "April", "April"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.may, "May", "May"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.june, "June", "June"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.july, "July", "July"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.august, "August", "August"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.september, "September", "September"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.october, "October", "October"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.november, "November", "November"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.december, "December", "December"));
                } else if (i3 == 9) {
                    ArrayList<kidsLearningDataModel> arrayList10 = new ArrayList<>();
                    this.learningDataModelArrayList = arrayList10;
                    arrayList10.add(new kidsLearningDataModel(R.drawable.asparagus, "Asparagus", "Asparagus"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.broccoli, "Broccoli", "Broccoli"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.brussels_sprouts, "Brussels Sprouts", "Brussels Sprouts"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.carrot, "Carrot", "Carrot"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.cauliflower, "Cauliflower", "Cauliflower"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.cucumber, "Cucumber", "Cucumber"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.eggplant, "Eggplant", "Eggplant"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.garlic, "Garlic", "Garlic"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.lettuce, "Lettuce", "Lettuce"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.mint, "Mint", "Mint"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.mushroom, "Mushroom", "Mushroom"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.okra, "Okra", "Okra"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.onion, "Onion", "Onion"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.peaspeas, "Peaspeas", "Peaspeas"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.potato, "Potato", "Potato"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.radish, "Radish", "Radish"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.red_cabbage, "Red Cabbage", "Red Cabbage"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.spinach, "Spinach", "Spinach"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.squash, "Squash", "Squash"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.string_beans, "String Beans", "String Beans"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.tomato, "Tomato", "Tomato"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.turnip, "Turnip", "Turnip"));
                } else if (i3 == 10) {
                    ArrayList<kidsLearningDataModel> arrayList11 = new ArrayList<>();
                    this.learningDataModelArrayList = arrayList11;
                    arrayList11.add(new kidsLearningDataModel(R.drawable.ankle, "Ankle", "Ankle"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.arm, "Arm", "Arm"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.chest, "Chest", "Chest"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.ear, "Ear", "Ear"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.elbow, "Elbow", "Elbow"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.eye, "Eye", "Eye"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.fingers, "Fingers", "Fingers"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.foot, "Foot", "Foot"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.hair, "Hair", "Hair"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.knee, "Knee", "Knee"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.leg, "Leg", "Leg"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.lips, "Lips", "Lips"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.mouth, "Mouth", "Mouth"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.neck, "Neck", "Neck"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.nose, "Nose", "Nose"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.shoulder, "Shoulder", "Shoulder"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.stomach, "Stomach", "Stomach"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.thigh, "Thigh", "Thigh"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.thumb, "Thumb", "Thumb"));
                    this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.toe, "Toe", "Toe"));
                } else {
                    String str12 = str9;
                    String str13 = "Monkey";
                    String str14 = "Lion";
                    String str15 = "Kite";
                    if (i3 == 11) {
                        ArrayList<kidsLearningDataModel> arrayList12 = new ArrayList<>();
                        this.learningDataModelArrayList = arrayList12;
                        arrayList12.add(new kidsLearningDataModel(R.drawable.shirt, "Shirt", "Shirt"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.shoes, "Shoes", "Shoes"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.pyjamas, "Pyjamas", "Pyjamas"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.sock, "Sock", "Sock"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.gloves, "Gloves", "Gloves"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.skirt, "Skirt", "Skirt"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.slipper, "Slipper", "Slipper"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.sweater, "Sweater", "Sweater"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.bathrobe, "Bathrobe", "Bathrobe"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.jeans, "Jeans", "Jeans"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.boot, "Boot", "Boot"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.dress, "Dress", "Dress"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.overalls, "Overalls", "Overalls"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.shorts, "Shorts", "Shorts"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.jacket, "Jacket", "Jacket"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.scarf, "Scarf", "Scarf"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.belt, "Belt", "Belt"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.hat, "Hat", "Hat"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.glasses, "Glasses", "Glasses"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.suit, "Suit", "Suit"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.veterinarian, "Veterinarian", "Veterinarian"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.tailor, "Tailor", "Tailor"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.waiter, "Waiter", "Waiter"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.welder, "Welder", "Welder"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.traffic_warden, "Traffic Warden", "Traffic Warden"));
                    } else if (i3 == 12) {
                        ArrayList<kidsLearningDataModel> arrayList13 = new ArrayList<>();
                        this.learningDataModelArrayList = arrayList13;
                        arrayList13.add(new kidsLearningDataModel(R.drawable.argentina, "Argentina", "Argentina"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.austria, "Austria", "Austria"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.belgium, "Belgium", "Belgium"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.brazil, "Brazil", "Brazil"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.cambodia, "Cambodia", "Cambodia"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.canada, "Canada", "Canada"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.croatia, "Croatia", "Croatia"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.cuba, "Cuba", "Cuba"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.denmark, "Denmark", "Denmark"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.england, "England", "England"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.france, "France", "France"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.germany, "Germany", "Germany"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.greece, "Greece", "Greece"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.indian, "Indian", "Indian"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.indonesia, "Indonesia", "Indonesia"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.italy, "Italy", "Italy"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.japan, "Japan", "Japan"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.laos, "Laos", "Laos"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.malaysia, "Malaysia", "Malaysia"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.mexico, "Mexico", "Mexico"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.myanmar, "Myanmar", "Myanmar"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.netherlands, "Netherlands", "Netherlands"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.pakistan, "Pakistan", "Pakistan"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.philippine, "Philippine", "Philippine"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.portugal, "Portugal", "Portugal"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.russia, "Russia", "Russia"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.saudi_arabia, "Saudi Arabia", "Saudi Arabia"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.singapore, "Singapore", "Singapore"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.south_korea, "South Korea", "South Korea"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.spain, "Spain", "Spain"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.sweden, "Sweden", "Sweden"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.thailand, "Thailand", "Thailand"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.united_states, "United States", "United States"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.vietnam, "Vietnam", "Vietnam"));
                    } else if (i3 == 13) {
                        ArrayList<kidsLearningDataModel> arrayList14 = new ArrayList<>();
                        this.learningDataModelArrayList = arrayList14;
                        arrayList14.add(new kidsLearningDataModel(R.drawable.pizza, "Pizza", "Pizza"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.biscuits, "Biscuits", "Biscuits"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.chip, "Chip", "Chip"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.cake, "Cake", "Cake"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.noodles, "Noodles", "Noodles"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.water, "Water", "Water"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.sandwich, "Sandwich", "Sandwich"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.ice_cream, "Ice Cream", "Ice Cream"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.beer, "Beer", "Beer"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.hamburger, "Hamburger", "Hamburger"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.tea, "Tea", "Tea"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.ham, "Ham", "Ham"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.yogurt, "Yogurt", "Yogurt"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.chocolate, "Chocolate", "Chocolate"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.rice, "Rice", "Rice"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.soda, "Soda", "Soda"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.juice, "Juice", "Juice"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.coffee, "Coffee", "Coffee"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.bread, "Bread", "Bread"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.soup, "Soup", "Soup"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.butter, "Butter", "Butter"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.cheese, "Cheese", "Cheese"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.milk, "Milk", "Milk"));
                    } else if (i3 == 14) {
                        ArrayList<kidsLearningDataModel> arrayList15 = new ArrayList<>();
                        this.learningDataModelArrayList = arrayList15;
                        arrayList15.add(new kidsLearningDataModel(R.drawable.arrow, "Arrow", "Arrow"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.circle, "Circle", "Circle"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.cone, "Cone", "Cone"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.crescent, "Crescent", "Crescent"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.cube, "Cube", "Cube"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.cuboid, "Cuboid", "Cuboid"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.cylinder, "Cylinder", "Cylinder"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.diamond, "Diamond", "Diamond"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.heart, "Heart", "Heart"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.hexagon, "Hexagon", "Hexagon"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.oval, "Oval", "Oval"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.parallelogram, "Parallelogram", "Parallelogram"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.pentagon, "Pentagon", "Pentagon"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.polygon, "Polygon", "Polygon"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.pyramid, "Pyramid", "Pyramid"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.rectangle, "Rectangle", "Rectangle"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.sphere, "Sphere", "Sphere"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.star, "Star", "Star"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.trapezoid, "Trapezoid", "Trapezoid"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.triangle, "Triangle", "Triangle"));
                    } else if (i3 == 15) {
                        ArrayList<kidsLearningDataModel> arrayList16 = new ArrayList<>();
                        this.learningDataModelArrayList = arrayList16;
                        arrayList16.add(new kidsLearningDataModel(R.drawable.bookcase, "Bookcase", "Bookcase"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.chair, "Chair", "Chair"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.newspaper, "Newspaper", "Newspaper"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.sofasofa, "Sofa", "Sofa"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.picture, "Picture", "Picture"));
                        String str16 = str3;
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.watch, str16, str16));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.brush, "Brush", "Brush"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.television, "Television", "Television"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.table, "Table", "Table"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.coin, "Coin", "Coin"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.phone, "Phone", "Phone"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.bar_stool, "Bar Stool", "Bar Stool"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.laptop, "Laptop", "Laptop"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.mirror, "Mirror", "Mirror"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.scissors, "Scissors", "Scissors"));
                        String str17 = str2;
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.umbrella, str17, str17));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.clock, "Clock", "Clock"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.bucket, "Bucket", "Bucket"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.cup, "Cup", "Cup"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.key, "Key", "Key"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.door, "Door", "Door"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.glass, "Glass", "Glass"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.armchair, "Armchair", "Armchair"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.window, "Window", "Window"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.knife, "Knife", "Knife"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.wallet, "Wallet", "Wallet"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.bottle, "Bottle", "Bottle"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.mobile_phone, "Mobile Phone", "Mobile Phone"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.bed, "Bed", "Bed"));
                        this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.lock, "Lock", "Lock"));
                    } else {
                        String str18 = str2;
                        String str19 = str3;
                        if (i3 == 16) {
                            ArrayList<kidsLearningDataModel> arrayList17 = new ArrayList<>();
                            this.learningDataModelArrayList = arrayList17;
                            arrayList17.add(new kidsLearningDataModel(R.drawable.accountant, "Accountant", "Accountant"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.architect, "Architect", "Architect"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.astronomer, "Astronomer", "Astronomer"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.author, "Author", "Author"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.baker, "Baker", "Baker"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.bricklayer, "Bricklayer", "Bricklayer"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.butcher, "Butcher", "Butcher"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.carpenter, "Carpenter", "Carpenter"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.chef, "Chef", "Chef"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.cleaner, "Cleaner", "Cleaner"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.dentist, "Dentist", "Dentist"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.doctor, "Doctor", "Doctor"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.driver, "Driver", "Driver"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.dustman, "Dustman", "Dustman"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.electrician, "Electrician", "Electrician"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.engineer, "Engineer", "Engineer"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.farmer, "Farmer", "Farmer"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.firefighter, "Firefighter", "Firefighter"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.florist, "Florist", "Florist"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.gardener, "Gardener", "Gardener"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.hairdresser, "Hairdresser", "Hairdresser"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.journalist, "Journalist", "Journalist"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.judge, "Judge", "Judge"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.lawyer, "Lawyer", "Lawyer"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.lecturer, "Lecturer", "Lecturer"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.librarian, "Librarian", "Librarian"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.lifeguard, "Lifeguard", "Lifeguard"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.mechanics, "Mechanics", "Mechanics"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.model, ExifInterface.TAG_MODEL, ExifInterface.TAG_MODEL));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.nurse, "Nurse", "Nurse"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.optician, "Optician", "Optician"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.painter, "Painter", "Painter"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.pharmacist, "Pharmacist", "Pharmacist"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.photographer, "Photographer", "Photographer"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.pilot, "Pilot", "Pilot"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.plumber, "Plumber", "Plumber"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.receptionist, "Receptionist", "Receptionist"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.scientist, "Scientist", "Scientist"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.soldier, "Soldier", "Soldier"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.student, "Student", "Student"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.tailor, "Tailor", "Tailor"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.traffic_warden, "Traffic Warden", "Traffic Warden"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.veterinarian, "Veterinarian", "Veterinarian"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.waiter, "Waiter", "Waiter"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.welder, "Welder", "Welder"));
                        } else if (i3 == 17) {
                            ArrayList<kidsLearningDataModel> arrayList18 = new ArrayList<>();
                            this.learningDataModelArrayList = arrayList18;
                            arrayList18.add(new kidsLearningDataModel(R.drawable.board, "Board", "Board"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.book, "Book", "Book"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.chair, "Chair", "Chair"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.compass, "Compass", "Compass"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.computer, "Computer", "Computer"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.desk, "Desk", "Desk"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.dictionary, "Dictionary", "Dictionary"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.eraser, "Eraser", "Eraser"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.globe, "Globe", "Globe"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.map, "Map", "Map"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.notebook, "Notebook", "Notebook"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.pen, "Pen", "Pen"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.pencil, "Pencil", "Pencil"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.ruler, "Ruler", "Ruler"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.school_bag, "School bag", "School bag"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.teacher, "Teacher", "Teacher"));
                        } else if (i3 == 18) {
                            ArrayList<kidsLearningDataModel> arrayList19 = new ArrayList<>();
                            this.learningDataModelArrayList = arrayList19;
                            arrayList19.add(new kidsLearningDataModel(R.drawable.chess, "Chess", "Chess"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.windsurfing, "Windsurfing", "Windsurfing"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.bowling, "Bowling", "Bowling"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.karate, "Karate", "Karate"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.ice_skating, "Ice Skating", "Ice Skating"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.table_tennis, "Table Tennis", "Table Tennis"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.badminton, "Badminton", "Badminton"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.swimming, "Swimming", "Swimming"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.football, "Football", "Football"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.hockey, "Hockey", "Hockey"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.equestrian, "Equestrian", "Equestrian"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.cycling, "Cycling", "Cycling"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.diving, "Diving", "Diving"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.judo, "Judo", "Judo"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.golf, "Golf", "Golf"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.baseball, "Baseball", "Baseball"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.volleyball, "Volleyball", "Volleyball"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.surfing, "Surfing", "Surfing"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.skateboarding, "Skateboarding", "Skateboarding"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.skiing, "Skiing", "Skiing"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.archery, "Archery", "Archery"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.canoeing, "Canoeing", "Canoeing"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.running, "Running", "Running"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.billiards, "Billiards", "Billiards"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.fencing, "Fencing", "Fencing"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.tennis, "Tennis", "Tennis"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.basketball, "Basketball", "Basketball"));
                        } else if (i3 == 19) {
                            ArrayList<kidsLearningDataModel> arrayList20 = new ArrayList<>();
                            this.learningDataModelArrayList = arrayList20;
                            arrayList20.add(new kidsLearningDataModel(R.drawable.ambulance, "Ambulance", "Ambulance"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.bike, "Bike", "Bike"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.boat, "Boat", "Boat"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.bus, "Bus", "Bus"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.car, "Car", "Car"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.container_truck, "Container Truck", "Container Truck"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.fire_truck, "Fire Truck", "Fire Truck"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.helicopter, "Helicopter", "Helicopter"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.motorbike, "Motorbike", "Motorbike"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.plane, "Plane", "Plane"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.police_car, "Police Car", "Police Car"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.ship, "Ship", "Ship"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.subway, "Subway", "Subway"));
                            String str20 = str;
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.train, str20, str20));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.truck, "Truck", "Truck"));
                        } else if (i3 == 20) {
                            ArrayList<kidsLearningDataModel> arrayList22 = new ArrayList<>();
                            this.learningDataModelArrayList = arrayList22;
                            arrayList22.add(new kidsLearningDataModel(R.drawable.ambulance, "Ambulance", "Ambulance"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.bike, "Bike", "Bike"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.boat, "Boat", "Boat"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.bus, "Bus", "Bus"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.car, "Car", "Car"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.container_truck, "Container Truck", "Container Truck"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.fire_truck, "Fire Truck", "Fire Truck"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.helicopter, "Helicopter", "Helicopter"));
                        } else {
                            ArrayList<kidsLearningDataModel> arrayList21 = new ArrayList<>();
                            this.learningDataModelArrayList = arrayList21;
                            arrayList21.add(new kidsLearningDataModel(R.drawable.a, "A for Apple", str10));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.b, "B for Ball", "Ball"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.c, "C for Cat", "Cat"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.d, "D for Dog", "Dog"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.e, "E for Elephant", "Elephant"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.f, "F for Fish", "Fish"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.g, "G for Goat", "Goat"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.h, "H for Horse", "Horse"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.i, "I for Ice cream", "Ice cream"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.j, "J for Joker", "Joker"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.k, "K for Kite", str15));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.l, "L for Lion", str14));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.m, "M for Monkey", str13));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.n, "N for Nest", "Nest"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.o, "O for Orange", str12));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.p, "P for Parrot", str11));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.q, "Q for Queen", "Queen"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.r, "R for Rabbit", "Rabbit"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.s, "S for Sun", "Sun"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.t, "T for Train", str));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.u, "U for Umbrella", str18));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.v, "V for Violin", "Violin"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.w, "W for Watch", str19));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.x, "X for Xylophone", "Xylophone"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.y, "Y for Yak", "Yak"));
                            this.learningDataModelArrayList.add(new kidsLearningDataModel(R.drawable.z, "Z for Zebra", str8));
                        }
                    }
                }
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        appControl.loadAd(ads_layout);
    }
}
