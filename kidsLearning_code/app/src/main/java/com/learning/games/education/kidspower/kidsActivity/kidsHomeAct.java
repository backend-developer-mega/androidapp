package com.learning.games.education.kidspower.kidsActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.games.education.kidspower.R;
import com.learning.games.education.kidspower.kidsAdapter.kidsHomeCategoriesAdapter;
import com.learning.games.education.kidspower.kidsCustom.kidsAppControl;

public class kidsHomeAct extends AppCompatActivity {
    Context context;
    kidsHomeCategoriesAdapter homeCategoriesAdapter;
    String[] homeCategoryTitles;
    public int[] mainCategoryList;
    RecyclerView rvHomeCategories;
    TextView txtExamTitle;
    int type = 1;

    RelativeLayout ads_layout;
    kidsAppControl appControl;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_home);
        getSupportActionBar().hide();
        this.context = this;
        initDefine();

        ads_layout=findViewById(R.id.ads_layout);
        appControl= (kidsAppControl) getApplication();
    }

    private void initDefine() {
        this.rvHomeCategories = (RecyclerView) findViewById(R.id.rvHomeCategories);
        this.txtExamTitle = (TextView) findViewById(R.id.txtTitleSubHome);
        int intExtra = getIntent().getIntExtra("Type", 1);
        this.type = intExtra;
        if (intExtra == 1) {
            this.txtExamTitle.setText("Preschool Kids Learning");
        } else if (intExtra == 2) {
            this.txtExamTitle.setText("Look and Choose Quiz");
        } else if (intExtra == 3) {
            this.txtExamTitle.setText("Listen and Guess");
        }
        this.homeCategoryTitles = new String[]{"Alphabets", "Numbers", "Colors", "Shapes", "Animals", "Birds", "Flowers", "Fruits", "Month", "Vegetable", "Body Parts", "Clothes", "Country", "Food", "Geometry", "House", "Jobs", "School", "Sports", "Vehicle", "My Family and My Pets"};
        this.mainCategoryList = new int[]{R.drawable.home_alphabet, R.drawable.home_number, R.drawable.home_color, R.drawable.home_shape, R.drawable.home_animal, R.drawable.home_birds, R.drawable.home_flower, R.drawable.home_fruits, R.drawable.home_month, R.drawable.home_vegetable, R.drawable.home_body_parts, R.drawable.home_clothes, R.drawable.home_country, R.drawable.home_food, R.drawable.home_geometry, R.drawable.home_house, R.drawable.home_jobs, R.drawable.home_school, R.drawable.home_sports, R.drawable.home_vehicle, R.drawable.home_vehicle2};
        setRvAdapter();
    }

    private void setRvAdapter() {
        this.rvHomeCategories.setLayoutManager(new GridLayoutManager(this.context, 2, RecyclerView.VERTICAL, false));
        kidsHomeCategoriesAdapter homeCategoriesAdapter2 = new kidsHomeCategoriesAdapter(this.context, this.mainCategoryList, this.homeCategoryTitles, this.type);
        this.homeCategoriesAdapter = homeCategoriesAdapter2;
        this.rvHomeCategories.setAdapter(homeCategoriesAdapter2);
    }

    public void onClickBack(View view) {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //appControl.loadAd(ads_layout);
    }
}
