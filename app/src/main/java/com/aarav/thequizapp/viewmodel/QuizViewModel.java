package com.aarav.thequizapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.aarav.thequizapp.model.QuestionList;
import com.aarav.thequizapp.repository.QuizRepository;

public class QuizViewModel extends ViewModel {
    QuizRepository quizRepository = new QuizRepository();

    LiveData<QuestionList> questionListLiveData;

    public QuizViewModel() {
        this.questionListLiveData = quizRepository.getQuestionsFormatAPI();
    }

    public LiveData<QuestionList> getQuestionListLiveData() {
        return questionListLiveData;
    }
}
