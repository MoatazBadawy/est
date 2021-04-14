package com.moataz.ramadan.library.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.moataz.ramadan.R;
import com.moataz.ramadan.library.adapter.BookListAdapter;
import com.moataz.ramadan.library.model.BookModel;
import com.moataz.ramadan.library.ui.activity.BookContentActivity;
import com.moataz.ramadan.library.utils.BookCallBack;
import com.moataz.ramadan.library.viewmodel.BookListViewModel;
import com.moataz.ramadan.main.ui.fragment.HomeFragment;
import com.moataz.ramadan.main.utils.IOnBackPressed;

import java.util.List;

public class LibraryFragment extends Fragment implements IOnBackPressed, BookCallBack {

    private List<BookModel> bookModelList;
    private BookListAdapter adapter;
    private BookListViewModel viewModel;
    ProgressBar progressBar;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_library, container, false);

        initializeContent();
        initializeViewModel();
        return rootView;
    }

    private void initializeContent() {
        RecyclerView recyclerView = rootView.findViewById(R.id.rc_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        adapter =  new BookListAdapter(getActivity(), bookModelList, this);
        recyclerView.setAdapter(adapter);
    }

    private void initializeViewModel() {
        progressBar = rootView.findViewById(R.id.progress_bar_books);
        viewModel = ViewModelProviders.of(this).get(BookListViewModel.class);
        viewModel.getBooksListObserver().observe(getActivity(), new Observer<List<BookModel>>() {
            @Override
            public void onChanged(List<BookModel> bookModels) {
                if(bookModels != null) {
                    progressBar.setVisibility(View.GONE);
                    bookModelList = bookModels;
                    adapter.setBookList(bookModels);
                }
            }
        });
        viewModel.makeApiCall();
    }

    @Override
    public void onBookItemClick(int position, ImageView imgContainer, ImageView imgBook, TextView title, TextView author, TextView pages, TextView details, RatingBar rating) {
        Intent intent = new Intent(getActivity(), BookContentActivity.class);
        intent.putExtra("bookObject", bookModelList.get(position));
        intent.putExtra("title",bookModelList.get(position).getTitle());
        intent.putExtra("author",bookModelList.get(position).getAuthor());
        intent.putExtra("details",bookModelList.get(position).getDetails());
        intent.putExtra("imageURL",bookModelList.get(position).getImgUrl());
        intent.putExtra("rating",bookModelList.get(position).getRating());
        intent.putExtra("pages",bookModelList.get(position).getPages());
        intent.putExtra("downloadUrl",bookModelList.get(position).getDownloadURL());


        // start the activity with scene transition
        getContext().startActivity(intent);
    }

    @Override
    public boolean onBackPressed() {
        //back to first fragment when press back
        //do what you want
        HomeFragment nextFrag = new HomeFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_layout, nextFrag, "findThisFragment")
                .addToBackStack(null)
                .commit();

        return true;
    }
}