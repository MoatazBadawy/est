package com.moataz.ramadan.library.utils;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public interface BookCallBack {

    void onBookItemClick(int position, ImageView imgContainer, ImageView imgBook, TextView title, TextView author, TextView pages, TextView details, RatingBar rating);
}
