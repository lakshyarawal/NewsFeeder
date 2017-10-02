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

import com.example.lakshya.myapplication.Adapters.SourceAdapter;
import com.example.lakshya.myapplication.Network.ApiInterface;
import com.example.lakshya.myapplication.Network.Source;
import com.example.lakshya.myapplication.Network.SourceResponse;
import com.example.lakshya.myapplication.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LAKSHYA on 8/26/2017.
 */

public class TrendingFragment extends Fragment {
    RecyclerView sourceRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.trending_fragment, container, false);
        sourceRecyclerView = v.findViewById(R.id.sources_recycler_view);
        fetchSources();
        return v;
    }
    private void fetchSources() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<SourceResponse> call = apiInterface.getSourceResponse("en");

        call.enqueue(new Callback<SourceResponse>() {
            @Override
            public void onResponse(Call<SourceResponse> call, Response<SourceResponse> response) {
                SourceResponse sourceResponse = response.body();
                if (sourceResponse != null) {
                    ArrayList<Source> sourceArrayList = sourceResponse.getSources();
                    Log.i("Tag","article"+sourceArrayList.get(0).getId());
                    onDownloadCompleteDetails(sourceArrayList);
                }
            }



            @Override
            public void onFailure(Call<SourceResponse> call, Throwable t) {

            }
        });
    }


    private void onDownloadCompleteDetails(final ArrayList<Source> sourceArrayList) {
        Log.i("Tag","article : "+sourceArrayList.get(0).getId());
     SourceAdapter adapter = new SourceAdapter(getActivity(), sourceArrayList, new SourceAdapter.SourceListener() {
         @Override
         public void onItemClick(View view, int position) {
             Source source = sourceArrayList.get(position);
             Log.i("Tag",source.getName()+"Clicked");
             Intent i = new Intent();
             i.setAction(Intent.ACTION_VIEW);
             Uri uri = Uri.parse(source.getUrl());
             i.setData(uri);

             startActivity(i);
         }
     });
        sourceRecyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false);
        sourceRecyclerView.setLayoutManager(linearLayoutManager);
        sourceRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(sourceRecyclerView.getContext(),
                linearLayoutManager.getOrientation());
        sourceRecyclerView.addItemDecoration(dividerItemDecoration);
    }
}
