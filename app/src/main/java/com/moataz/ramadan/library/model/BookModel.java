package com.moataz.ramadan.library.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

// implement Serializable interface is important to send the book object to another activity
public class BookModel implements Serializable {

    @SerializedName("title")
    private String title;
    @SerializedName("author")
    private String author;
    @SerializedName("details")
    private String details;
    @SerializedName("imageURL")
    private String imgUrl;
    @SerializedName("rating")
    private float rating;
    @SerializedName("pages")
    private String pages;
    @SerializedName("downloadUrl")
    private String downloadURL;

    public BookModel() {
    }

    public BookModel(String title, String author, String details, String imgUrl, float rating, String pages, String downloadURL) {
        this.title = title;
        this.author = author;
        this.details = details;
        this.imgUrl = imgUrl;
        this.rating = rating;
        this.pages = pages;
        this.downloadURL = downloadURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }
}
