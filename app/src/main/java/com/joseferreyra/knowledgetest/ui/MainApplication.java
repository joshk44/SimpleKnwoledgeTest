/*
 * Created by Jose Ferreyra on 7/31/18 2:53 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 7/31/18 2:53 AM
 *
 */

package com.joseferreyra.knowledgetest.ui;

import android.app.Application;

import com.joseferreyra.knowledgetest.db.DaoMaster;
import com.joseferreyra.knowledgetest.db.DaoSession;

import org.greenrobot.greendao.database.Database;

public class MainApplication extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"articles-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
