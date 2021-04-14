package com.moataz.ramadan.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.moataz.ramadan.R;
import com.moataz.ramadan.library.model.BookModel;
import com.moataz.ramadan.library.utils.BookCallBack;

import java.util.List;

public class BookListStarAdapter extends RecyclerView.Adapter<BookListStarAdapter.ViewHolder>{
    private Context context;
    private List<BookModel> bookList;
    public BookCallBack callBack;

    public BookListStarAdapter(Context context, List<BookModel> bookList, BookCallBack callBack) {
        this.context = context;
        this.bookList = bookList;
        this.callBack = callBack;
    }

    public void setBookStarList(List<BookModel> bookList) {
        this.bookList = bookList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_items_star_content, parent, false);
        /*
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.onClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("imageURL",bookList.get(viewHolder.getAdapterPosition()).getImgUrl());
                intent.putExtra("title",bookList.get(viewHolder.getAdapterPosition()).getTitle());
                intent.putExtra("author",bookList.get(viewHolder.getAdapterPosition()).getAuthor());
                intent.putExtra("rating",bookList.get(viewHolder.getAdapterPosition()).getRating());
                intent.putExtra("pages",bookList.get(viewHolder.getAdapterPosition()).getPages());
                intent.putExtra("details",bookList.get(viewHolder.getAdapterPosition()).getDetails());
                intent.putExtra("downloadUrl",bookList.get(viewHolder.getAdapterPosition()).getDownloadURL());

                context.startActivity(intent);
            }
        });
        */
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(holder.itemView.getContext())
                .load(this.bookList.get(position).getImgUrl())
                .transforms(new CenterCrop(),new RoundedCorners(16))
                .into(holder.imageBook);

        holder.title.setText(this.bookList.get(position).getTitle());
        holder.author.setText(this.bookList.get(position).getAuthor());
        holder.bookRating.setRating((float) 5);
        //holder.pages.setText(this.bookList.get(position).getPages());
    }

    @Override
    public int getItemCount() {
        if(this.bookList != null) {
            return this.bookList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgContainer;
        ImageView imageBook;
        TextView title,author;
        TextView pages;
        TextView details;
        RatingBar bookRating;

        public ViewHolder(View itemView) {
            super(itemView);
            imgContainer = itemView.findViewById(R.id.bcg_book_star_content);
            imageBook = itemView.findViewById(R.id.book_cover_img_star_content);
            title = itemView.findViewById(R.id.book_title_star_content);
            author = itemView.findViewById(R.id.book_author_star_content);
            //pages = itemView.findViewById(R.id.pages_num);
            bookRating = itemView.findViewById(R.id.book_rating_star_content);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.onBookItemClick(getAdapterPosition(),imgContainer,imageBook,title,author,pages,details,bookRating);
                }
            });
        }
    }
}
