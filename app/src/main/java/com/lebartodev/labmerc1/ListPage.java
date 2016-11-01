package com.lebartodev.labmerc1;

import com.lebartodev.labmerc1.model.Item;

/**
 * Created by Александр on 01.11.2016.
 */

public interface ListPage {
    void addItem(Item item);
    void deleteItem(int position);


}
