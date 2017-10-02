package com.example.lakshya.myapplication.Adapters;

import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lakshya.myapplication.Network.Source;
import com.example.lakshya.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by LAKSHYA on 10/1/2017.
 */

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.SourceViewHolder> {
    private Context mContext;
    private ArrayList<Source> sourceArrayList;
    private SourceListener mListener;
    public interface SourceListener {
        void onItemClick(View view, int position);
    }

    public SourceAdapter(Context mContext, ArrayList<Source> sourceArrayList, SourceListener mListener) {
        this.mContext = mContext;
        this.sourceArrayList = sourceArrayList;
        this.mListener = mListener;
    }

    @Override
    public SourceAdapter.SourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.source_layout, parent, false);
        return new SourceViewHolder(itemView , mListener);
    }

    @Override
    public void onBindViewHolder(SourceAdapter.SourceViewHolder holder, int position) {
        Source sourceResult = sourceArrayList.get(position);
        holder.categoryTextView.setText(sourceResult.getCategory());
        holder.descriptionTextView.setText(sourceResult.getDescription());
        holder.titleTextView.setText(sourceResult.getName());
    }

    @Override
    public int getItemCount() {
        if (sourceArrayList !=null)return sourceArrayList.size();
        return 0;
    }
    public static class SourceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView categoryTextView,descriptionTextView,titleTextView;
        SourceAdapter.SourceListener mSourceListener;


        public SourceViewHolder(View itemView , SourceAdapter.SourceListener sourceListener) {
            super(itemView);
            mSourceListener = sourceListener;
            itemView.setOnClickListener(this);
            titleTextView = itemView.findViewById(R.id.sourceTitle);
            categoryTextView = itemView.findViewById(R.id.sourceCategory);
            descriptionTextView = itemView.findViewById(R.id.sourceDescription);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION){

                mSourceListener.onItemClick(view,position);
            }
        }
    }
}
