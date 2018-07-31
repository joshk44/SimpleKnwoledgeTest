/*
 * Created by Jose Ferreyra on 7/31/18 1:42 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 7/31/18 1:42 AM
 *
 */

package com.joseferreyra.knowledgetest.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.joseferreyra.knowledgetest.R;

public class ViewArticle extends AppCompatActivity {

    WebView mainWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_article);

        Intent intent = getIntent();
        String value = intent.getStringExtra("url");

        mainWebView = findViewById(R.id.article_web_view);
        mainWebView.getSettings().setJavaScriptEnabled(true);
        mainWebView.loadUrl(value);
    }
}
