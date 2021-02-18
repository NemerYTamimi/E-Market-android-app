package com.example.project.UIFragments.Goods;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GoodsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GoodsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }

}