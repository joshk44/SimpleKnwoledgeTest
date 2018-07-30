/*
 * Created by Jose Ferreyra on 7/30/18 2:53 AM
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 7/30/18 2:51 AM
 *
 */

package com.joseferreyra.knowledgetest.ui;

import com.joseferreyra.knowledgetest.communication.dto.Article;

import java.util.List;

public interface ListInteraction {

    /**
     * Call this method after a server response in order to update the Data.
     * @param articles An array of articles data transfer object.
     */
    void listUpdate (List<Article> articles);

    /**
     * Open a webview once that the user touch an item.
     * @param position the position of the item on the list.
     */
    void openItem (int position);

}
