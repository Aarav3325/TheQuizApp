package com.aarav.thequizapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aarav.thequizapp.model.Question;
import com.aarav.thequizapp.model.QuestionList;
import com.aarav.thequizapp.retrofit.QuestionsAPI;
import com.aarav.thequizapp.retrofit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizRepository {

    private QuestionsAPI questionsAPI;

    public QuizRepository() {
        this.questionsAPI = new RetrofitInstance().getRetrofit().create(QuestionsAPI.class);
    }

    public LiveData<QuestionList> getQuestionsFormatAPI(){
        MutableLiveData<QuestionList> data = new MutableLiveData<>();


        Call<QuestionList> response = questionsAPI.getQuestions();

        response.enqueue(new Callback<QuestionList>() {
            @Override
            public void onResponse(Call<QuestionList> call, Response<QuestionList> response) {
                QuestionList questionList = response.body();
                data.setValue(questionList);
            }

            @Override
            public void onFailure(Call<QuestionList> call, Throwable throwable) {

            }
        });

        return data;
    }
}
