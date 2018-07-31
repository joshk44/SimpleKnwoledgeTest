/*
 * Created by Jose Ferreyra on 7/30/18 2:53 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 7/30/18 2:51 AM
 *
 */

package com.joseferreyra.knowledgetest.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joseferreyra.knowledgetest.R;
import com.joseferreyra.knowledgetest.communication.NetworkController;
import com.joseferreyra.knowledgetest.communication.dto.Article;

import java.util.List;

public class ArticleListFragment extends Fragment implements ListInteraction{

    private RecyclerView recyclerView;
    private ArticlesAdapter adapter;
    private List<Article> articles;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.articles_list);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        adapter = new ArticlesAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);

        NetworkController.requestArticles(this);

    }

    @Override
    public void listUpdate(List<Article> articles) {
        adapter.updateData(articles);
        this.articles = articles;
    }

    @Override
    public void openItem(int position) {
        Log.d("Test", "position" + position);
        Intent intent = new Intent(getActivity(), ViewArticle.class);
        intent.putExtra("url", articles.get(position).getUrl());
        getActivity().startActivity(intent);
    }
}
