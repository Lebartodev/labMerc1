package com.lebartodev.labmerc1.presenter;

import com.lebartodev.labmerc1.model.Item;
import com.lebartodev.labmerc1.model.ItemModel;
import com.lebartodev.labmerc1.model.ItemObs;
import com.lebartodev.labmerc1.view.fragment.AddPage;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func3;
import rx.subjects.PublishSubject;

/**
 * Created by Александр on 07.11.2016.
 */

public class AddPresenter implements BaseAddPresenter {
    private Subscription titleSubscription, confirmSubscription, checkSubscription;
    private PublishSubject<Integer> colorSubject = PublishSubject.create();
    private AddPage pAdd;
    private ItemModel model = null;

    public AddPresenter(final AddPage pAdd, Observable<CharSequence> titleObs, final Observable<Void> clickObs) {
        this.pAdd = pAdd;
        model = new ItemObs();
        titleSubscription = titleObs
                .debounce(100, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {
                        if (charSequence.length() > 3) {
                            AddPresenter.this.pAdd.showColors();
                            checkTitle(charSequence.toString());
                        }


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
            public Object call(final CharSequence charSequence, final Boolean aBoolean, final Integer integer) {

                if (charSequence.length() > 3 && aBoolean && integer != -1) {
                    AddPresenter.this.pAdd.showButton();
                } else {
                    AddPresenter.this.pAdd.hideButton();
                }

                clickObs.subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (charSequence.length() > 3 && aBoolean && integer != -1) {
                            model.addItem(charSequence.toString(), integer);
                            AddPresenter.this.pAdd.onAddItem();
                        }
                    }
                });


                return null;
            }
        }).subscribe();


    }


    @Override
    public void onPickColor(int color) {
        colorSubject.onNext(color);
    }


    @Override
    public void checkTitle(String title) {
        model.checkName(title);

    }

    @Override
    public void onStop() {
        confirmSubscription.unsubscribe();
        titleSubscription.unsubscribe();
        checkSubscription.unsubscribe();

    }
}
