package com.aarav.thequizapp.retrofit;

import com.aarav.thequizapp.model.QuestionList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionsAPI {

    @GET("myquizapi.php")
    Call<QuestionList> getQuestions();
}
