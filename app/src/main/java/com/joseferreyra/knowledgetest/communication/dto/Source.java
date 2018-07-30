
/*
 * Created by Jose Ferreyra on 7/30/18 2:53 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 7/30/18 2:51 AM
 *
 */

package com.joseferreyra.knowledgetest.communication.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
