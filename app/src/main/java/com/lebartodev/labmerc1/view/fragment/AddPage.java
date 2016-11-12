package com.lebartodev.labmerc1.view.fragment;

/**
 * Created by Александр on 07.11.2016.
 */

public interface AddPage {

    void onColorSelect(int color);

    void onValidatingName(boolean valid);

    void onError();

    void onAddItem();

    void showColors();
    void showButton();
    void hideButton();
}
