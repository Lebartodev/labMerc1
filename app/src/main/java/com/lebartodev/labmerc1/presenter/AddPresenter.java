package com.lebartodev.labmerc1.presenter;

import com.lebartodev.labmerc1.model.ItemModel;
import com.lebartodev.labmerc1.model.ItemObs;
import com.lebartodev.labmerc1.view.AddFragment;
import com.lebartodev.labmerc1.view.AddPage;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.subjects.PublishSubject;

/**
 * Created by Александр on 07.11.2016.
 */

public class AddPresenter implements BaseAddPresenter {
    private Subscription titleSubscription, confirmSubscription, checkSubscription, colorSubscription;
    private PublishSubject<Integer> colorSubject = PublishSubject.create();
    private AddPage pAdd;
    private ItemModel model = null;

    public AddPresenter(final AddPage pAdd, Observable<CharSequence> titleObs) {



        this.pAdd = pAdd;
        model = new ItemObs();
        titleSubscription = titleObs
                .debounce(100, TimeUnit.MILLISECONDS)
                .filter(new Func1<CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence charSequence) {
                        if (charSequence.length() > 3)
                            return true;
                        else
                            return false;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {
                        AddPresenter.this.pAdd.showColors();
                        checkTitle(charSequence.toString());


                    }
                });

        checkSubscription = model.getCheckObs().subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                AddPresenter.this.pAdd.onValidatingName(aBoolean);
            }
        });
        confirmSubscription = Observable.combineLatest(titleObs, model.getCheckObs(), colorSubject, new Func3<CharSequence, Boolean, Integer, Object>() {
            @Override
            public Object call(CharSequence charSequence, Boolean aBoolean, Integer integer) {
                if (charSequence.length() > 3 && aBoolean && integer != -1)
                    AddPresenter.this.pAdd.showButton();
                else
                    AddPresenter.this.pAdd.hideButton();
                return null;
            }
        }).subscribe();
    }


    @Override
    public void onPickColor(int color) {
        colorSubject.onNext(color);
    }

    @Override
    public void onAddItem() {

    }

    @Override
    public void checkTitle(String title) {
        model.checkName(title);

    }
}
