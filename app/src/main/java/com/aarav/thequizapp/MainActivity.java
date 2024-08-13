package com.aarav.thequizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.aarav.thequizapp.databinding.ActivityMainBinding;
import com.aarav.thequizapp.model.Question;
import com.aarav.thequizapp.model.QuestionList;
import com.aarav.thequizapp.viewmodel.QuizViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    QuizViewModel quizViewModel;
    List<Question> questionList;

    static int result = 0;
    static int totalQuestions = 0;

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        result = 0;
        totalQuestions = 0;

        quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);

        //Displaying First Question
        displayFirstQuestion();

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayNextQuestion();
            }
        });

    }


    public void displayFirstQuestion() {
        quizViewModel.getQuestionListLiveData().observe(this, new Observer<QuestionList>() {
            @Override
            public void onChanged(QuestionList questions) {
                questionList = questions;
                int total = questionList.size();
                binding.questionNumber.setText("Question " + (i+1) + "/" + total);
                binding.tvQuestion.setText(" " + questionList.get(0).getQuestion());
                binding.radio1.setText(questions.get(0).getOption1());
                binding.radio2.setText(questions.get(0).getOption2());
                binding.radio3.setText(questions.get(0).getOption3());
                binding.radio4.setText(questions.get(0).getOption4());
            }
        });
    }

    public void displayNextQuestion(){

        if(binding.btnNext.getText().equals("Finish")){
            Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
            finish();
        }

        int selectedOption = binding.radioGroup.getCheckedRadioButtonId();
        if(selectedOption != -1){
            RadioButton rb = findViewById(selectedOption);

            totalQuestions = questionList.size();

            if ((questionList.size() - i) > 1){

                if(rb.getText().toString().equals(questionList.get(i).getCorrectOption())){
                    result++;
                    binding.tvResult.setText("Correct Answers : " + result);
                }

                i++;

                binding.questionNumber.setText("Question " + (i+1) + "/" + totalQuestions);
                binding.tvQuestion.setText(" " + questionList.get(i).getQuestion());
                binding.radio1.setText(questionList.get(i).getOption1());
                binding.radio2.setText(questionList.get(i).getOption2());
                binding.radio3.setText(questionList.get(i).getOption3());
                binding.radio4.setText(questionList.get(i).getOption4());


                if (i == (questionList.size() - 1)){
                    binding.btnNext.setText("Finish");
                }

                binding.radioGroup.clearCheck();


            }
            else{
                if(rb.getText().toString().equals(questionList.get(i).getCorrectOption())){
                    result++;
                    binding.tvResult.setText("Correct Answers : " + result);
                }
            }
        }
        else {
            Toast.makeText(this, "You need to select an option", Toast.LENGTH_LONG).show();
        }
    }

}

