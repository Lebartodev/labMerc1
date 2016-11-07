package com.lebartodev.labmerc1.view;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.lebartodev.labmerc1.R;
import com.lebartodev.labmerc1.presenter.AddPresenter;
import com.lebartodev.labmerc1.presenter.BaseAddPresenter;
import com.lebartodev.labmerc1.utils.Consts;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.subjects.PublishSubject;

@EFragment(R.layout.fragment_add)
public class AddFragment extends Fragment implements AddPage {
    @ViewById
    ImageView itemColor;
    @ViewById
    EditText titleText;
    @ViewById
    TextInputLayout titleText_layout;
    private int selectedColor = -1;
    @ViewById
    Button addButton;
    @ViewById
    RelativeLayout palleteLayout;

    private BaseAddPresenter presenter;

    @AfterViews
    void initPresenter() {
        Observable<CharSequence> titleObs = RxTextView.textChanges(titleText);
        presenter=new AddPresenter(this,titleObs);

    }


    public void showColors() {
        palleteLayout.setVisibility(View.VISIBLE);
        itemColor.setVisibility(View.VISIBLE);
    }

    public void showButton() {
        addButton.setVisibility(View.VISIBLE);
    }

    public void hideButton() {
        addButton.setVisibility(View.INVISIBLE);
    }

    private void closeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    @Click
    void redColor() {
        presenter.onPickColor(Consts.COLOR_RED);
        closeKeyboard();
        ((GradientDrawable) itemColor.getBackground()).setColor(Color.RED);
        selectedColor = Consts.COLOR_RED;

    }

    @Click
    void orangeColor() {
        presenter.onPickColor(Consts.COLOR_ORANGE);
        closeKeyboard();
        ((GradientDrawable) itemColor.getBackground()).setColor(Color.argb(255, 255, 183, 77));
        selectedColor = Consts.COLOR_ORANGE;

    }

    @Click
    void yellowColor() {
        presenter.onPickColor(Consts.COLOR_YELLOW);
        closeKeyboard();
        ((GradientDrawable) itemColor.getBackground()).setColor(Color.YELLOW);
        selectedColor = Consts.COLOR_YELLOW;

    }

    @Click
    void greenColor() {
        presenter.onPickColor(Consts.COLOR_GREEN);
        closeKeyboard();
        ((GradientDrawable) itemColor.getBackground()).setColor(Color.GREEN);
        selectedColor = Consts.COLOR_GREEN;

    }

    @Click
    void blueColor() {
        presenter.onPickColor(Consts.COLOR_BLUE);
        closeKeyboard();
        ((GradientDrawable) itemColor.getBackground()).setColor(Color.argb(255, 105, 182, 220));
        selectedColor = Consts.COLOR_BLUE;

    }

    @Click
    void blueDarkColor() {
        presenter.onPickColor(Consts.COLOR_BLUE_DARK);
        closeKeyboard();
        ((GradientDrawable) itemColor.getBackground()).setColor(Color.BLUE);
        selectedColor = Consts.COLOR_BLUE_DARK;

    }

    @Click
    void purpleColor() {
        presenter.onPickColor(Consts.COLOR_PURPLE);
        closeKeyboard();
        ((GradientDrawable) itemColor.getBackground()).setColor(Color.argb(255, 224, 64, 251));
        selectedColor = Consts.COLOR_PURPLE;
    }

    @Click
    void whiteColor() {
        presenter.onPickColor(Consts.COLOR_TRANS);
        closeKeyboard();
        ((GradientDrawable) itemColor.getBackground()).setColor(Color.TRANSPARENT);
        selectedColor = Consts.COLOR_TRANS;
    }


    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onColorSelect(int color) {

    }

    @Override
    public void onValidatingName(boolean valid) {
        if(!valid){
            titleText_layout.setError("This title already use");
        }
        else
            titleText_layout.setErrorEnabled(false);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onAddItem() {

    }
}
