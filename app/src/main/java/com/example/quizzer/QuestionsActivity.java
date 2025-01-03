package com.example.quizzer;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity {

    private TextView question, noIndicator;
    private FloatingActionButton bookmarkIcon;
    private LinearLayout optionsContainer;
    private Button shareBtn, nextBtn;
    private int count = 0;
    private List<QuestionModel> list;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_questions);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        question = findViewById(R.id.question);
        noIndicator = findViewById(R.id.no_indicator);
        bookmarkIcon = findViewById(R.id.bookmark_icon);
        optionsContainer = findViewById(R.id.options_container);
        shareBtn = findViewById(R.id.share_btn);
        nextBtn = findViewById(R.id.next_btn);

        list = new ArrayList<>();
        list.add(new QuestionModel("Qestion 1","a","b","c","d","a"));
        list.add(new QuestionModel("Qestion 2","a","b","c","d","b"));
        list.add(new QuestionModel("Qestion 3","a","b","c","d","c"));
        list.add(new QuestionModel("Qestion 4","a","b","c","d","d"));
        list.add(new QuestionModel("Qestion 5","a","b","c","d","a"));
        list.add(new QuestionModel("Qestion 6","a","b","c","d","b"));
        list.add(new QuestionModel("Qestion 7","a","b","c","d","c"));
        list.add(new QuestionModel("Qestion 8","a","b","c","d","d"));
        list.add(new QuestionModel("Qestion 9","a","b","c","d","a"));
        list.add(new QuestionModel("Qestion 10","a","b","c","d","b"));

        for (int i = 0; i < 4; i++){
            optionsContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                playanim(question,0, list.get(position).getQuestion());
            }
        });
    }

    private void playanim(final View view, final int value, final String data) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animator) {
                        if (value == 0) {
                            String option = "";
                            if (count == 0){
                                option = list.get(position).getOptionA();
                            }else if (count == 1){
                                option = list.get(position).getOptionB();
                            }else if (count == 2){
                                option = list.get(position).getOptionC();
                            }else if (count == 3){
                                option = list.get(position).getOptionD();
                            }
                            playanim(optionsContainer.getChildAt(count),0,option);
                            count++;
                        }
                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animator) {
                        ((TextView)view).setText(data);
                        if (value == 0) {
                            playanim(view,1, data);
                        }
                    }

                    @Override
                    public void onAnimationCancel(@NonNull Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(@NonNull Animator animator) {

                    }
                });

    }

}