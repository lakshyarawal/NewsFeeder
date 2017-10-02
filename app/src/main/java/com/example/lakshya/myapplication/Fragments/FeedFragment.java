package com.example.lakshya.myapplication.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lakshya.myapplication.Adapters.ArticleAdapter;
import com.example.lakshya.myapplication.Network.ApiInterface;
import com.example.lakshya.myapplication.Network.Article;
import com.example.lakshya.myapplication.Network.ArticleResponse;
import com.example.lakshya.myapplication.Network.Keys;
import com.example.lakshya.myapplication.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FeedFragment extends Fragment {
    RecyclerView articleRecyclerView;
    ArticleAdapter articleAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.feed_fragment, container, false);
        articleRecyclerView = v.findViewById(R.id.article_recycler_view);
        fetchReviews();
        return v;
    }


    private void fetchReviews() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<ArticleResponse> call = apiInterface.getArticleResponse("the-next-web","latest",Keys.API_KEY);

        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                ArticleResponse articleResponse = response.body();
                if (articleResponse != null) {
                    ArrayList<Article> articleArrayList = articleResponse.getArticles();
                    Log.i("Tag","article"+articleArrayList.get(0).getAuthor());
                    onDownloadCompleteDetails(articleArrayList);
                }
            }
            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                Log.d("error", t.getMessage());
            }
        });

    }

    private void onDownloadCompleteDetails(final ArrayList<Article> articleArrayList) {
        Log.i("Tag","article : "+articleArrayList.get(0).getAuthor());
        articleAdapter = new ArticleAdapter(getActivity(), articleArrayList, new ArticleAdapter.ArticleListener() {
            @Override
            public void onItemClick(View view, int position) {

                Article article = articleArrayList.get(position);
                Log.i("Tag",article.getTitle()+"Clicked");
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(article.getUrl());
                i.setData(uri);

                startActivity(i);
            }
        });
        articleRecyclerView.setAdapter(articleAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false);
        articleRecyclerView.setLayoutManager(linearLayoutManager);
        articleRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(articleRecyclerView.getContext(),
                linearLayoutManager.getOrientation());
        articleRecyclerView.addItemDecoration(dividerItemDecoration);
    }
}