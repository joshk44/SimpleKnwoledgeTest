/*
 * Created by Jose Ferreyra on 7/30/18 2:53 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 7/30/18 2:51 AM
 *
 */

package com.joseferreyra.knowledgetest.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joseferreyra.knowledgetest.R;
import com.joseferreyra.knowledgetest.communication.dto.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {

    private List<Article> list;
    private Context context;
    private ListInteraction inter;

    public ArticlesAdapter(Context context, ListInteraction inter) {
        this.list = new ArrayList<>();
        this.context = context;
        this.inter = inter;
    }

    public void updateData(List<Article> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArticleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, final int position) {
        final Article article = list.get(position);
        holder.title.setText(article.getTitle());
        holder.description.setText(article.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inter != null) {
                    inter.openItem(position+1);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title_item);
            description = (TextView)itemView.findViewById(R.id.description_item);
        }
    }
}

