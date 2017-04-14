package com.cqut.haiyuchen.parkapp.presentation.personal;

import android.util.Log;
import com.cqut.haiyuchen.parkapp.common.EndSubscriber;
import com.cqut.haiyuchen.parkapp.common.network.APIService;
import com.cqut.haiyuchen.parkapp.data.model.Book;
import com.cqut.haiyuchen.parkapp.di.AppPresenter;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by haiyu.chen on 2017/4/12.
 */

public class PersonalPresenter extends AppPresenter<PersonalView> {
  @Inject APIService serverApi;

  @Inject public PersonalPresenter(PersonalView personalView) {
    super(personalView);
    baseView = personalView;
  }

  @Override public void attach() {

  }

  @Override public void detach() {

  }

  public void getPersonalInfo() {
    baseView.showDialog();
    serverApi.requestUrl("小王子", 0, 3)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new EndSubscriber<Book>() {
          @Override public void onEnd() {
            baseView.hideDialog();
            Log.e("end", "end");
          }

          @Override public void onNext(Book map) {
            Log.e("next", map.toString());
          }
        });
  }
}