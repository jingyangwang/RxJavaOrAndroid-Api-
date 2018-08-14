package com.example.wangjingyang.rxtest;

import android.os.Trace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import junit.framework.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCreateRx();
//        setCreateRx();
//        testJust();
//        TestApi.formArray();
//        TestApi.fromCallable();
//        TestApi.fromFuture();
//        TestApi.fromIterable();
//        TestApi.defer();
//   TestApi.timer();
//        TestApi.interval();
//    TestApi.intervalRange();
//    TestApi.range();
//    TestApi.testMap();
//        TestApi.flatMap2();
//TestApi.concatMap();
//        TestApi.buffer();
//TestApi.groupBy();
//TestApi.scan();
//        TestApi.window();

//        TestApi.concat();
//        TestApi.concatArray();

//        TestApi.merge();

//        TestApi.delayError();

//        TestApi.zip();

//   TestApi.combineLatest();

//    TestApi.reduce();

//        TestApi.collect();
//TestApi.startWith();

//    TestApi.doOnEach();

//        TestApi.doOnLifecycle();
//TestApi.doOnTerminate();

//        TestApi.doFinally();
//    TestApi.onErrorReturn();

//        TestApi.onErrorResumeNext();
// TestApi.onExceptionResumeNext();

//        TestApi.retryWhen();
//TestApi.repeat();

//        TestApi.repeatWhen();

//        TestApi.subscribeOn();

//TestApi.observeOn();

//        TestApi.filter();
//        TestApi.ofType();

//        TestApi.skip();
//        TestApi.distinct();

//        TestApi.take();

//    TestApi.debounce();

//        TestApi.all();
//        TestApi.takeWhile();

//        TestApi.skipWhile();

//        TestApi.takeUntil();

//        TestApi.skipUntil();
//        TestApi.isEmpty();
//        TestApi.amb();
//        TestApi.defaultIfEmpty();


    }



    public void setTestRx1() {
        //被观察者
        Observable observable = Observable.create(new io.reactivex.ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(io.reactivex.ObservableEmitter<Integer> e) throws Exception {
                android.util.Log.d(TAG, "=========================currentThread name: " + Thread.currentThread().getName());
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        });

//观察者
        io.reactivex.Observer observer = new io.reactivex.Observer<Integer>() {
            @Override
            public void onSubscribe(io.reactivex.disposables.Disposable d) {
                Log.d(TAG, "======================onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "======================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "======================onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "======================onComplete");
            }
        };
// 订阅
        observable.subscribe(observer);
    }


    public void setCreateRx() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("test");
                emitter.onComplete();
            }
        });
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete ");

            }
        };
        observable.subscribe(observer);
    }

    //参数合并处理  少于十个参数 注原实例传参是整数,所以我更改了传入两个数组
    public void testJust() {
//        Observable.create(new Observable<>())
        Integer[] integers1 = {1, 2, 3}, integers2 = {4, 5, 6};

        Observable.just(integers1, integers2).subscribe(new Observer<Integer[]>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer[]) {
                for (Integer integer1 : integer)
                    Log.i(TAG, "onNext " + integer1);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete ");
            }
        });

    }


}
