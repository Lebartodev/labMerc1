package com.lebartodev.labmerc1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.lebartodev.labmerc1.model.Item;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

@EActivity(R.id.activity_add_item)
public class AddItemActivity extends AppCompatActivity {
    @ViewById
    EditText titleText;
    @ViewById
    Button button;
    @Extra
    String title;
    @Extra
    int color=-1;
    @StringArrayRes
    String[] colors;

    @AfterViews
    void initActivity(){
        //getSupportActionBar().set
        if(color!=-1){
            button.setText(colors[color]);
            titleText.setText(title);

        }



    }
    @Click
    void button(){
        new MaterialDialog.Builder(this)
                .title(R.string.select_color)
                .negativeText(R.string.disagree)
                .items(R.array.colors)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        Intent intent = new Intent();

                        intent.putExtra(Consts.CONTENT_TITLE, titleText.getText().toString());
                        intent.putExtra(Consts.CONTENT_COLOR, position);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .show();



       /* Intent intent = new Intent();

        intent.putExtra(Consts.CONTENT_TITLE, titleText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();*/
    }



}
