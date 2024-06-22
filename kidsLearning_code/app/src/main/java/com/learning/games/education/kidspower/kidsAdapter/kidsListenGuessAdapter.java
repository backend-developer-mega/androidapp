package com.learning.games.education.kidspower.kidsAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.learning.games.education.kidspower.R;
import com.learning.games.education.kidspower.kidsCustom.kidsAppControl;
import com.learning.games.education.kidspower.kidsCustom.kidsConstant;
import com.learning.games.education.kidspower.kidsUtils.Utils;
import com.learning.games.education.kidspower.kidsModel.kidsLearningDataModel;

import java.util.ArrayList;
import java.util.HashMap;

public class kidsListenGuessAdapter extends RecyclerView.Adapter<kidsListenGuessAdapter.ViewHolder> {
    Context context;
    ArrayList<kidsLearningDataModel> examQuestionAnswerList;
    kidsLearningDataModel learningDataModel;

    public kidsListenGuessAdapter(Context context2, ArrayList<kidsLearningDataModel> examQuestionAnswerList2, kidsLearningDataModel learningDataModel2) {
        this.context = context2;
        this.examQuestionAnswerList = examQuestionAnswerList2;
        this.learningDataModel = learningDataModel2;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView iVExamAnswer;
        LinearLayout lloutExamAnswer;

        ViewHolder(View view) {
            super(view);
            this.cardView = (CardView) view.findViewById(R.id.cvCardSubHome);
            this.iVExamAnswer = (ImageView) view.findViewById(R.id.iVExamAnswer);
            this.lloutExamAnswer = (LinearLayout) view.findViewById(R.id.lloutExamAnswer);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_exam_phonics, viewGroup, false));
    }

    public void onBindViewHolder(final ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        Glide.with(this.context).load(Integer.valueOf(this.examQuestionAnswerList.get(i).image)).apply((BaseRequestOptions<?>) new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(viewHolder.iVExamAnswer);
        viewHolder.cardView.startAnimation(AnimationUtils.loadAnimation(this.context, R.anim.bubble_anim));
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (kidsListenGuessAdapter.this.learningDataModel.showTitle.equals(kidsListenGuessAdapter.this.examQuestionAnswerList.get(i).showTitle)) {
                    Toast.makeText(kidsListenGuessAdapter.this.context, "Correct Answer", Toast.LENGTH_SHORT).show();
                    viewHolder.lloutExamAnswer.setBackgroundColor(kidsListenGuessAdapter.this.context.getResources().getColor(R.color.colorCorrect));
                    if (Utils.getPref(kidsConstant.SOUND, true).booleanValue()) {
                        kidsAppControl.textToSpeech.speak("Correct Answer", 1, (HashMap) null);
                        return;
                    }
                    return;
                }
                Toast.makeText(kidsListenGuessAdapter.this.context, "Wrong Answer", Toast.LENGTH_SHORT).show();
                viewHolder.lloutExamAnswer.setBackgroundColor(kidsListenGuessAdapter.this.context.getResources().getColor(R.color.colorWrong));
                if (Utils.getPref(kidsConstant.SOUND, true).booleanValue()) {
                    kidsAppControl.textToSpeech.speak("Wrong Answer", 1, (HashMap) null);
                }
            }
        });
    }

    public int getItemCount() {
        return this.examQuestionAnswerList.size();
    }
}
