/*
 * Created by Jose Ferreyra on 7/31/18 2:34 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 7/31/18 2:34 AM
 *
 */

package com.joseferreyra.greendaogenerator;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class NewsGenerator {

    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.joseferreyra.knowledgetest.db");
        schema.enableKeepSectionsByDefault();
        addTables(schema);

        try {
            new DaoGenerator().generateAll(schema,"./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTables(final Schema schema) {
        addArticleEntities(schema);
    }

    private static Entity addArticleEntities(final Schema schema) {
        Entity article = schema.addEntity("Article");
        article.addIdProperty().primaryKey().autoincrement();
        article.addIntProperty("article_id").notNull();
        article.addStringProperty("author");
        article.addStringProperty("title");
        article.addStringProperty("description");
        article.addStringProperty("url");
        article.addStringProperty("urlToImage");
        article.addStringProperty("publishedAt");
        article.addStringProperty("sourceId");
        article.addStringProperty("sourceName");
        return article;
    }
}