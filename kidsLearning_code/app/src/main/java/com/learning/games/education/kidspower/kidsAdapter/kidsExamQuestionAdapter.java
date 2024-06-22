package com.learning.games.education.kidspower.kidsAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.games.education.kidspower.R;
import com.learning.games.education.kidspower.kidsCustom.kidsAppControl;
import com.learning.games.education.kidspower.kidsCustom.kidsConstant;
import com.learning.games.education.kidspower.kidsUtils.Utils;
import com.learning.games.education.kidspower.kidsModel.kidsLearningDataModel;

import java.util.ArrayList;
import java.util.HashMap;

public class kidsExamQuestionAdapter extends RecyclerView.Adapter<kidsExamQuestionAdapter.ViewHolder> {
    Context context;
    ArrayList<kidsLearningDataModel> examQuestionAnswerList;
    kidsLearningDataModel learningDataModel;

    public kidsExamQuestionAdapter(Context context2, ArrayList<kidsLearningDataModel> examQuestionAnswerList2, kidsLearningDataModel learningDataModel2) {
        this.context = context2;
        this.examQuestionAnswerList = examQuestionAnswerList2;
        this.learningDataModel = learningDataModel2;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        LinearLayout lloutExamAnswer;
        TextView tVExamAnswer;

        ViewHolder(View view) {
            super(view);
            this.cardView = (CardView) view.findViewById(R.id.cvCardSubHome);
            this.tVExamAnswer = (TextView) view.findViewById(R.id.tVExamAnswer);
            this.lloutExamAnswer = (LinearLayout) view.findViewById(R.id.lloutExamAnswer);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_exam_answer, viewGroup, false));
    }

    public void onBindViewHolder(final ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        viewHolder.tVExamAnswer.setText(this.examQuestionAnswerList.get(i).showTitle);
        viewHolder.cardView.startAnimation(AnimationUtils.loadAnimation(this.context, R.anim.bubble_anim));
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (kidsExamQuestionAdapter.this.learningDataModel.showTitle.equals(kidsExamQuestionAdapter.this.examQuestionAnswerList.get(i).showTitle)) {
                    Toast.makeText(kidsExamQuestionAdapter.this.context, "Correct Answer", Toast.LENGTH_SHORT).show();
                    viewHolder.lloutExamAnswer.setBackgroundColor(kidsExamQuestionAdapter.this.context.getResources().getColor(R.color.colorCorrect));
                    if (Utils.getPref(kidsConstant.SOUND, true).booleanValue()) {
                        kidsAppControl.textToSpeech.speak("Correct Answer", 0, (HashMap) null);
                        return;
                    }
                    return;
                }
                Toast.makeText(kidsExamQuestionAdapter.this.context, "Wrong Answer", Toast.LENGTH_SHORT).show();
                viewHolder.lloutExamAnswer.setBackgroundColor(kidsExamQuestionAdapter.this.context.getResources().getColor(R.color.colorWrong));
                viewHolder.tVExamAnswer.setTextColor(kidsExamQuestionAdapter.this.context.getResources().getColor(R.color.colorWhite));
                if (Utils.getPref(kidsConstant.SOUND, true).booleanValue()) {
                    kidsAppControl.textToSpeech.speak("Wrong Answer", 0, (HashMap) null);
                }
            }
        });
    }

    public int getItemCount() {
        return this.examQuestionAnswerList.size();
    }
}
