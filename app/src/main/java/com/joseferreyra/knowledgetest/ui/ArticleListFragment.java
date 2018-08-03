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
import com.joseferreyra.knowledgetest.db.ArticleDao;

import java.text.SimpleDateFormat;
import java.util.List;

public class ArticleListFragment extends Fragment implements ListInteraction{

    private RecyclerView recyclerView;
    private ArticlesAdapter adapter;
    private List<Article> articles;
    private Source source = Source.BACKEND;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
           source = Source.valueOf(bundle.getString("SourceData", Source.BACKEND.source()));
        }
    }

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

        switch (source){
            case BACKEND:
                NetworkController.requestArticles(this);
                break;
            case DATABASE:
                ArticleDao articleDao = ((MainApplication)getActivity().getApplication()).getDaoSession().getArticleDao();
                articleDao.getAll(this);
                break;

        }


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
        intent.putExtra("url", articles.get(position-1).getUrl());
        intent.putExtra("article", articles.get(position-1));
        getActivity().startActivity(intent);
    }

    public enum Source {
        DATABASE ("DATABASE"), BACKEND ("BACKEND");

        private String source;

        Source(String source) {
            this.source = source;
        }

        public String source() {
            return source;
        }
    }

}


