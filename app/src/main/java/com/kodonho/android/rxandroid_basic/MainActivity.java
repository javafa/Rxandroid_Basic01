package com.kodonho.android.rxandroid_basic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Rxandroid Basic";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1. Observable 을 생성
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello RxAndroid !!!");
                subscriber.onNext("Nice to see you !!!");
                subscriber.onCompleted();
            }
        });
        // 2. Observable 을 통해 데이터를 가져온다
        observable.subscribe(new Subscriber<String>() {
                                // 얘가 옵저버
            @Override
            public void onCompleted() {
                Log.i(TAG, "Complete");
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onNext(String s) {
                Log.i(TAG, "next value="+s);
            }
        });

        observable.subscribe(new Action1<String>(){ // onNext
            @Override
            public void call(String s) {
                ((TextView)findViewById(R.id.textView)).setText(s);
            }
        },new Action1<Throwable>(){   // onError
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() { // onComplete
            @Override
            public void call() {

            }
        });
    }
}
