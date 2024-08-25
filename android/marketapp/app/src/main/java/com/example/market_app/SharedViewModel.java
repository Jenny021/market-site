package com.example.market_app;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Boolean> articleCreated = new MutableLiveData<>(false);

    public LiveData<Boolean> getArticleCreated() {
        return articleCreated;
    }

    public void notifyArticleCreated() {
        articleCreated.setValue(true);
    }

    public void resetArticleCreated() {
        articleCreated.setValue(false);
    }
}
