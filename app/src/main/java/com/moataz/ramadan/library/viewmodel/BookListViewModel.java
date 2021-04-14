package com.moataz.ramadan.library.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.moataz.ramadan.library.model.BookModel;
import com.moataz.ramadan.library.request.APIService;
import com.moataz.ramadan.library.request.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookListViewModel extends ViewModel {

    private MutableLiveData<List<BookModel>> bookList;
    private Call<List<BookModel>> call,callBooksStar;

    public BookListViewModel(){
        bookList = new MutableLiveData<>();
    }

    public MutableLiveData<List<BookModel>> getBooksListObserver() {
        return bookList;
    }

    public void makeApiCall() {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        call = apiService.getBookList();
        call.enqueue(new Callback<List<BookModel>>() {
            @Override
            public void onResponse(Call<List<BookModel>> call, Response<List<BookModel>> response) {
                bookList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<BookModel>> call, Throwable t) {
                bookList.postValue(null);
            }
        });
    }

    public void makeApiCallBookStar() {
        // for books star
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        callBooksStar = apiService.getBookStarList();
        callBooksStar.enqueue(new Callback<List<BookModel>>() {
            @Override
            public void onResponse(Call<List<BookModel>> callBooksStar, Response<List<BookModel>> response) {
                bookList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<BookModel>> callBooksStar, Throwable t) {
                bookList.postValue(null);
            }
        });
    }
}
