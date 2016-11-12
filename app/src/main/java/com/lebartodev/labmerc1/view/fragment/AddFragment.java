package com.lebartodev.labmerc1.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jakewharton.rxbinding.view.RxMenuItem;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.lebartodev.labmerc1.R;
import com.lebartodev.labmerc1.presenter.AddPresenter;
import com.lebartodev.labmerc1.presenter.BaseAddPresenter;
import com.lebartodev.labmerc1.utils.Consts;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import rx.Observable;

@EFragment(R.layout.fragment_add)
public class AddFragment extends Fragment implements AddPage {
    @ViewById
    ImageView itemColor;
    @ViewById
    EditText titleText;
    @ViewById
    TextInputLayout titleText_layout;
    private MenuItem addItem;
    Menu menu;
    @ViewById
    RelativeLayout palleteLayout;
    private BaseAddPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @AfterViews
    void coloredCircle(){
        ((GradientDrawable) itemColor.getBackground()).setColor(Color.WHITE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            closeKeyboard();
            (getActivity()).getFragmentManager().beginTransaction().remove(this).commit();
        }
        return super.onOptionsItemSelected(menuItem);
    }
    public AddFragment() {
        setHasOptionsMenu(true);
    }


    @Override
    public void onValidatingName(boolean valid) {
        if (!valid) {
            titleText_layout.setError("This title already use");
            hideButton();
        } else
            titleText_layout.setErrorEnabled(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_menu, menu);

        this.menu = menu;

        addItem = menu.findItem(R.id.add_action);
        addItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
            }
        });
        Observable<CharSequence> titleObs = RxTextView.textChanges(titleText);
        Observable<Void> clickObs = RxMenuItem.clicks(addItem);
        presenter = new AddPresenter(this, titleObs, clickObs);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onError() {


    }

    @Override
    public void onResume() {
        super.onResume();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getActivity().findViewById(R.id.fab).setVisibility(View.INVISIBLE);
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getActivity().findViewById(R.id.fab).setVisibility(View.VISIBLE);
    }

    @Override
    public void onAddItem() {
        presenter.onStop();

        ((AppCompatActivity) getActivity()).getFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();


    }

    @Override
    public void onColorSelect(int color) {

    }

    public void showColors() {
        palleteLayout.setVisibility(View.VISIBLE);
        itemColor.setVisibility(View.VISIBLE);
    }

    public void showButton() {
        //addItem.setEnabled(true);
        menu.findItem(R.id.add_action).setVisible(true);
        //getActivity().invalidateOptionsMenu();
    }

    public void hideButton() {
        //addItem.setEnabled(false);
        menu.findItem(R.id.add_action).setVisible(false);
        //getActivity().invalidateOptionsMenu();
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

    }

    @Click
    void orangeColor() {
        presenter.onPickColor(Consts.COLOR_ORANGE);
        closeKeyboard();
        ((GradientDrawable) itemColor.getBackground()).setColor(Color.argb(255, 255, 183, 77));

    }

    @Click
    void yellowColor() {
        presenter.onPickColor(Consts.COLOR_YELLOW);
        closeKeyboard();
        ((GradientDrawable) itemColor.getBackground()).setColor(Color.YELLOW);

    }


    @Click
    void greenColor() {
        presenter.onPickColor(Consts.COLOR_GREEN);
        closeKeyboard();
        ((GradientDrawable) itemColor.getBackground()).setColor(Color.GREEN);

    }

    @Click
    void blueColor() {
        presenter.onPickColor(Consts.COLOR_BLUE);
        closeKeyboard();
        ((GradientDrawable) itemColor.getBackground()).setColor(Color.argb(255, 105, 182, 220));

    }

    @Click
    void blueDarkColor() {
        presenter.onPickColor(Consts.COLOR_BLUE_DARK);
        closeKeyboard();
        ((GradientDrawable) itemColor.getBackground()).setColor(Color.BLUE);

    }


    @Click
    void purpleColor() {
        presenter.onPickColor(Consts.COLOR_PURPLE);
        closeKeyboard();
        ((GradientDrawable) itemColor.getBackground()).setColor(Color.argb(255, 224, 64, 251));
    }

    @Click
    void whiteColor() {
        presenter.onPickColor(Consts.COLOR_TRANS);
        closeKeyboard();
        ((GradientDrawable) itemColor.getBackground()).setColor(Color.TRANSPARENT);
    }
}
