package com.moataz.ramadan.library.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.moataz.ramadan.R;
import com.moataz.ramadan.library.adapter.BookListStarAdapter;
import com.moataz.ramadan.library.model.BookModel;
import com.moataz.ramadan.library.utils.BookCallBack;
import com.moataz.ramadan.library.viewmodel.BookListViewModel;
import com.moataz.ramadan.main.ui.activity.MainActivity;
import com.moataz.ramadan.main.utils.IOnBackPressed;

import java.util.List;

public class BookContentActivity extends AppCompatActivity implements BookCallBack {

    private List<BookModel> bookModelList;
    private BookListStarAdapter adapter;
    private BookListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_content);
        initializeView();
        initializeDisplayContent();

        initializeContent();
        initializeViewModel();
    }

    private void initializeView() {
        //make the status bar white with black icons
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        // make the app support only arabic "Right to left", even if the language of the device on english or others
        ViewCompat.setLayoutDirection(getWindow().getDecorView(), ViewCompat.LAYOUT_DIRECTION_RTL);

    }

    private void initializeDisplayContent() {
        // get the data
        String title  = getIntent().getExtras().getString("title");
        String author = getIntent().getExtras().getString("author");
        String details = getIntent().getExtras().getString("details") ;
        String imageURL = getIntent().getExtras().getString("imageURL");
        String pages = getIntent().getExtras().getString("pages");
        String downloadUrl = getIntent().getExtras().getString("downloadUrl");
        // findView
        TextView textTitle = findViewById(R.id.book_title_content);
        TextView textAuthor = findViewById(R.id.book_author_content);
        TextView textDetails = findViewById(R.id.book_details_content);
        ImageView img = findViewById(R.id.book_cover_img_content);
        TextView textPages = findViewById(R.id.pages_num_content);

        // setting values to each view
        textTitle.setText(title);
        textAuthor.setText(author);
        textDetails.setText(details);
        Glide.with(this)
                .load(imageURL)
                .into(img);
        textPages.setText(pages);
        // set Rating
        RatingBar bookRating = findViewById(R.id.book_rating_content);
        bookRating.setRating((float) 5);
        // initializeDownloadBook
        Button download = findViewById(R.id.but_download_book);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(downloadUrl));
                startActivity(i);
            }
        });
        // back to last activity
        Button btBack = findViewById(R.id.button_back_books_content);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    private void initializeContent() {
        RecyclerView recyclerView = findViewById(R.id.rc_book_star);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter =  new BookListStarAdapter(this, bookModelList, this);
        recyclerView.setAdapter(adapter);
    }

    private void initializeViewModel() {
        viewModel = ViewModelProviders.of(this).get(BookListViewModel.class);
        viewModel.getBooksListObserver().observe(this, new Observer<List<BookModel>>() {
            @Override
            public void onChanged(List<BookModel> bookModels) {
                if(bookModels != null) {
                    bookModelList = bookModels;
                    adapter.setBookStarList(bookModels);
                }
            }
        });
        viewModel.makeApiCallBookStar();
    }

    @Override
    public void onBookItemClick(int position, ImageView imgContainer, ImageView imgBook, TextView title, TextView author, TextView pages, TextView details, RatingBar rating) {
        finish();
        Intent intent = new Intent(this, BookContentActivity.class);
        intent.putExtra("bookObject", bookModelList.get(position));
        intent.putExtra("title",bookModelList.get(position).getTitle());
        intent.putExtra("author",bookModelList.get(position).getAuthor());
        intent.putExtra("details",bookModelList.get(position).getDetails());
        intent.putExtra("imageURL",bookModelList.get(position).getImgUrl());
        intent.putExtra("rating",bookModelList.get(position).getRating());
        intent.putExtra("pages",bookModelList.get(position).getPages());
        intent.putExtra("downloadUrl",bookModelList.get(position).getDownloadURL());

        // start the activity with scene transition
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}