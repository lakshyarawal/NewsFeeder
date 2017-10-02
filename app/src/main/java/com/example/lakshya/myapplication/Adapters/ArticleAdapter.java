package com.example.lakshya.myapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lakshya.myapplication.Network.Article;
import com.example.lakshya.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private Context mContext;
    private ArrayList<Article> articleArrayList;
    private ArticleListener mListener;

    public interface ArticleListener {
        void onItemClick(View view, int position);
    }

    public ArticleAdapter(Context mContext, ArrayList<Article> articleResults , ArticleListener articleListener) {
        this.mContext = mContext;
        this.articleArrayList = articleResults;
        this.mListener = articleListener;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.article_layout, parent, false);
        return new ArticleViewHolder(itemView , mListener);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        Article articleResult = articleArrayList.get(position);
        holder.authorTextView.setText(articleResult.getAuthor());
        holder.contentTextView.setText(articleResult.getDescription());
        holder.titleTextView.setText(articleResult.getTitle());
        Picasso.with(mContext)
                .load(articleResult.getUrlToImage())
                .placeholder(R.drawable.place_holder)
                .fit()
                .centerInside()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (articleArrayList !=null)return articleArrayList.size();
        return 0;
    }




    public static class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView authorTextView,contentTextView,titleTextView;
        ImageView imageView;
        ArticleListener mArticleListener;


        public ArticleViewHolder(View itemView , ArticleListener articleListener) {
            super(itemView);
         mArticleListener =  articleListener;
            itemView.setOnClickListener(this);
            titleTextView = itemView.findViewById(R.id.Title);
            authorTextView = itemView.findViewById(R.id.Author);
            contentTextView = itemView.findViewById(R.id.Content);
            imageView = itemView.findViewById(R.id.urlToImage);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION){

                mArticleListener.onItemClick(view,position);
            }
        }
    }
}

