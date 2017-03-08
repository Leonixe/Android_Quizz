package com.example.romain.myapplication.questions;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.romain.myapplication.R;

import java.util.ArrayList;

/**
 * Created by romain on 14/02/2017.
 */

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder>{

    private ArrayList<Question> questions;

    public QuestionsAdapter(ArrayList<Question> questions) {
        this.questions = questions;
    }

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context ctx = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View questionItemView = inflater.inflate(R.layout.question_item, parent, false);
        return new QuestionViewHolder(questionItemView);
    }

    @Override
    public void onBindViewHolder(final QuestionViewHolder holder, int position) {
        final Question question = questions.get(position);
        holder.questionText.setText(question.text);
        Context ctx = holder.itemView.getContext();
        switch (question.difficulty){
            case "easy":
                holder.circle.setImageResource(R.drawable.circle_easy);
                holder.questionDifficulty.setTextColor(ContextCompat.getColor(ctx, R.color.colorEasy));
                break;
            case "medium":
                holder.circle.setImageResource(R.drawable.circle_medium);
                holder.questionDifficulty.setTextColor(ContextCompat.getColor(ctx, R.color.colorMedium));
                break;
            case "hard":
                holder.circle.setImageResource(R.drawable.circle_hard);
                holder.questionDifficulty.setTextColor(ContextCompat.getColor(ctx, R.color.colorHard));
                break;
        }

        holder.questionCategory.setText("Category : " + question.category );
        holder.questionDifficulty.setText(question.difficulty.toUpperCase());
        holder.questionText.setText(question.text);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(holder.itemView.getContext());
                myBuilder.setTitle("Answers");
                myBuilder.setMessage(question.answer);
                myBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                myBuilder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder{

        TextView questionText;
        TextView questionDifficulty;
        TextView questionCategory;
        ImageView circle;
        public QuestionViewHolder(View itemView) {
            super(itemView);
            this.questionText = (TextView) itemView.findViewById(R.id.question_text);
            this.questionDifficulty = (TextView) itemView.findViewById(R.id.question_difficulty);
            this.questionCategory = (TextView) itemView.findViewById(R.id.question_category);
            this.circle = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
