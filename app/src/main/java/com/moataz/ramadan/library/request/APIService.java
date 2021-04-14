package com.moataz.ramadan.library.request;

import com.moataz.ramadan.library.model.BookModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    // get all books
    @GET("books.json")
    Call<List<BookModel>> getBookList();

    // get star books
    @GET("booksstar.json")
    Call<List<BookModel>> getBookStarList();
}