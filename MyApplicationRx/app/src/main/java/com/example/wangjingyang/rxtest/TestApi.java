package com.example.wangjingyang.rxtest;


import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;

public class TestApi {
    public static final String TAG = "TestApi";

    //可传多余十个参数 与just（）相似
    public static void formArray() {
        Integer[] integers = {1, 2, 3};
        String [] str={"","",""};
        Observable.fromArray(str).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String integer) {
                Log.i(TAG, "onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        });
    }

    //fromCallable 返回回调callable 的返回值
    public static void fromCallable() {

        Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 11;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        });
    }


    //    返回回调函数 futureTask的值
    public static void fromFuture() {
        final FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return " futureTask ";
            }
        });
        Observable.fromFuture(futureTask).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                futureTask.run();
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "accept " + s);
            }
        });


    }


    //    fromIterable  向监听者传入一个集合
    public static void fromIterable() {

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        Observable.fromIterable(arrayList).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String string) {
                Log.i(TAG, "onNext " + string);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        });
    }


    //    只有在被观察者订阅的时候，才能创建观察者
    public static void defer() {
//
//
//        // i 要定义为成员变量
//        Integer i = 100;
//
//
//        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
//            @Override
//            public ObservableSource<? extends Integer> call() throws Exception {
//                return Observable.just(i);
//            }
//        });
//
//
//        Observer observer = new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Log.d(TAG, "================onNext " + integer);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//        i=200;
//        observable.subscribe(observer);
//
//        i = 300;
//
//        observable.subscribe(observer);
//
//
    }


    //timer() 指定时间发送给监听者
    public static void timer() {
        Observable.timer(2, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Long lon) {
                Log.i(TAG, "onNext " + lon);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        });
    }

    //        interval( )每个相同设置的时间就会增一并进行传出给监听者
    public static void interval() {
        Observable.interval(4, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Long lon) {
                Log.i(TAG, "onNext " + lon);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        });
    }


    //    intervalRange 确定开始值，结束值，以及开始的间隔
    public static void intervalRange() {
        Observable.intervalRange(2, 5, 2, 1, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Long lon) {
                Log.i(TAG, "onNext " + lon);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        });
    }

    //    range     同时发送一定范围内的序列
    public static void range() {
        Observable.range(2, 5).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer lon) {
                Log.i(TAG, "onNext " + lon);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        });
    }


    //    与range一致 ，参数换成 Long型     同时发送一定范围内的序列
    public static void rangeLong() {
        Observable.rangeLong(20, 5).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Long lon) {
                Log.i(TAG, "onNext " + lon);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        });
    }

    //     empty() & never() & error()  三个方法 可在相应的地方进行替换，分别表示发送 空方法，从不发送，以及发送错误方法。
    public void test() {
        Observable.empty().subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Object lon) {
                Log.i(TAG, "onNext " + lon);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        });
    }

    //    map 可以将被观察者发送的数据类型转变成其他的类型
    public static void testMap() {
        Observable.just(1, 2, 3).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "now " + integer;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String lon) {
                Log.i(TAG, "onNext " + lon);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        });
    }


    public static void TestPerson(List personList) {
        /*
         * 现在有一个需求就是要将 Person 集合中的每个元素中的 Plan 的 action 打印出来。 首先用 map() 来实现这个需求看看：
         * */
        Observable.fromIterable(personList)
                .map(new Function<Person, List<Plan>>() {
                    @Override
                    public List<Plan> apply(Person person) throws Exception {
                        return person.getPlanList();
                    }
                })
                .subscribe(new Observer<List<Plan>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Plan> plans) {
                        for (Plan plan : plans) {
                            List<String> planActionList = plan.getActionList();
                            for (String action : planActionList) {
                                Log.d(TAG, "==================action " + action);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //    与上个方法进行比较
    public static void flatMap() {
        List<Person> personList;
        personList = new ArrayList<>();
        List<Plan> listPlan = new ArrayList<>();
        listPlan.add(new Plan("早上", "学习"));
        listPlan.add(new Plan("中午", "吃东西 学习"));
        listPlan.add(new Plan("中午", "学习 吃东西"));
        personList.add(new Person("小明", listPlan));
        Observable.fromIterable(personList)
                .flatMap(new Function<Person, ObservableSource<Plan>>() {
                    @Override
                    public ObservableSource<Plan> apply(Person person) {
                        return Observable.fromIterable(person.getPlanList());
                    }
                })
                .flatMap(new Function<Plan, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Plan plan) throws Exception {
                        return Observable.fromIterable(plan.getActionList());
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "==================action: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //concatMap() 和 flatMap() 基本上是一样的，只不过 concatMap() 转发出来的事件是有序的，而 flatMap() 是无序的。
    public static void concatMap() {
        List<Person> personList;
        personList = new ArrayList<>();
        List<Plan> listPlan = new ArrayList<>();
        listPlan.add(new Plan("早上", "学习"));
        listPlan.add(new Plan("中午", "吃东西 学习"));
        listPlan.add(new Plan("中午", "学习 吃东西"));
        personList.add(new Person("小明", listPlan));
        listPlan.add(new Plan("早上", "蛋疼"));
        listPlan.add(new Plan("中午", "蛋疼 学习"));
        listPlan.add(new Plan("中午", "学习 蛋疼"));
        personList.add(new Person("小华", listPlan));
        Observable.fromIterable(personList)
                .concatMap(new Function<Person, ObservableSource<Plan>>() {
                    @Override
                    public ObservableSource<Plan> apply(Person person) {
                        if ("chan".equals(person.getName())) {
                            return Observable.fromIterable(person.getPlanList()).delay(10, TimeUnit.MILLISECONDS);
                        }
                        return Observable.fromIterable(person.getPlanList());
                    }
                })
                .subscribe(new Observer<Plan>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Plan plan) {
                        Log.d(TAG, "==================plan " + plan.getContent());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //测试 flatemap是否是乱序的
    public static void flatMap2() {
        List<Person> personList;
        personList = new ArrayList<>();
        List<Plan> listPlan = new ArrayList<>();
        listPlan.add(new Plan("早上", "学习"));
        listPlan.add(new Plan("中午", "吃东西 学习"));
        listPlan.add(new Plan("中午", "学习 吃东西"));
        personList.add(new Person("小明", listPlan));

        listPlan.add(new Plan("早上", "蛋疼"));
        listPlan.add(new Plan("中午", "蛋疼 学习"));
        listPlan.add(new Plan("中午", "学习 蛋疼"));
        personList.add(new Person("小华", listPlan));

        Observable.fromIterable(personList)
                .flatMap(new Function<Person, ObservableSource<Plan>>() {
                    @Override
                    public ObservableSource<Plan> apply(Person person) {
                        if ("chan".equals(person.getName())) {
                            return Observable.fromIterable(person.getPlanList()).delay(10, TimeUnit.MILLISECONDS);
                        }
                        return Observable.fromIterable(person.getPlanList());
                    }
                })
                .subscribe(new Observer<Plan>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Plan plan) {
                        Log.d(TAG, "==================plan " + plan.getContent());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    // buffer 缓存区
    public static void buffer() {
        Observable.just(1, 2, 3, 4, 5).buffer(2, 1).subscribe(new Observer<List<Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Integer> integers) {
                Log.d(TAG, "================缓冲区大小： " + integers.size());
                for (Integer i : integers) {
                    Log.d(TAG, "================元素： " + i);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    //    groupBy 将发送的数据进行分组，每个分组都会返回一个被观察者。
    public static void groupBy() {
//        Observable.just(5, 2, 3, 4, 1, 6, 8, 9, 7, 10).groupBy(new Function<Integer, Object>() {
//            @Override
//            public Object apply(Integer integer) throws Exception {
//                return integer%3;
//            }
//        }).subscribe(new Observer<GroupedObservable<Integer, Integer>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(GroupedObservable<Integer , Integer> objectIntegerGroupedObservable) {
//                objectIntegerGroupedObservable.subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "====================GroupedObservable onSubscribe ");
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        Log.d(TAG, "====================GroupedObservable onNext  groupName: " + objectIntegerGroupedObservable.getKey() + " value: " + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "====================GroupedObservable onError ");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "====================GroupedObservable onComplete ");
//                    }
//                });
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

        Observable.just(5, 2, 3, 4, 1, 6, 8, 9, 7, 10)
                .groupBy(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        return integer % 3;
                    }
                })
                .subscribe(new Observer<GroupedObservable<Integer, Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "====================onSubscribe ");
                    }

                    @Override
                    public void onNext(final GroupedObservable<Integer, Integer> integerIntegerGroupedObservable) {
                        Log.d(TAG, "====================onNext ");
                        integerIntegerGroupedObservable.subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.d(TAG, "====================GroupedObservable onSubscribe ");
                            }

                            @Override
                            public void onNext(Integer integer) {
                                Log.d(TAG, "====================GroupedObservable onNext  groupName: " + integerIntegerGroupedObservable.getKey() + " value: " + integer);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "====================GroupedObservable onError ");
                            }

                            @Override
                            public void onComplete() {
                                Log.d(TAG, "====================GroupedObservable onComplete ");
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "====================onError ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "====================onComplete ");
                    }
                });
    }

    //将数据按照一定逻辑汇聚起来   如下是求前n项的和
    public static void scan() {
//
        Observable.just(1, 2, 3, 4, 5).scan(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                Log.d(TAG, "====================apply ");
                Log.d(TAG, "====================integer " + integer);
                Log.d(TAG, "====================integer2 " + integer2);
                return integer + integer2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "====================accept " + integer);
            }
        });
    }

    /*
     * 发送指定数量的事件时，就将这些事件分为一组。window 中的 count 的参数就是代表指定的数量，例如将 count 指定为2，那么每发2个数据就会将这2个数据分成一组。
     *
     * */

    public static void window() {
        Observable.just(1, 2, 3, 4, 5).window(2).subscribe(new Observer<Observable<Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "=====================onSubscribe ");
            }

            @Override
            public void onNext(Observable<Integer> integerObservable) {
                integerObservable.subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "=====================integerObservable onSubscribe ");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "=====================integerObservable onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "=====================integerObservable onError ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "=====================integerObservable onComplete ");
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "=====================onError ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "=====================onComplete ");
            }
        });
    }

    // concat 参数最多发四个
    public static void concat() {
        Observable.concat(Observable.just(1, 2), Observable.just(3, 4), Observable.just(5, 6), Observable.just(7, 8)).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer lon) {
                Log.i(TAG, "onNext " + lon);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        });
    }

    //concatArray 参数可穿进多余四个
    public static void concatArray() {
        Observable.concatArray(Observable.just(1, 2), Observable.just(3, 4), Observable.just(5, 6), Observable.just(7, 8), Observable.just(9, 10)).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


// merge 与此相似的还有mergearray 这个方法月 concat() 作用基本一样，知识 concat() 是串行发送事件，而 merge() 并行发送事件。
//    merge 打印出来的 a1 b1 a2 b2  并行
//    concat  打印出来的是 a1，a2，a3 串行


    public static void merge() {
        Observable.merge(Observable.interval(1, TimeUnit.SECONDS).map(new Function<Long, String>() {
            @Override
            public String apply(Long aLong) throws Exception {
                return "A " + aLong;
            }
        }), Observable.interval(1, TimeUnit.SECONDS).map(new Function<Long, String>() {
            @Override
            public String apply(Long aLong) throws Exception {
                return "B " + aLong;
            }
        })).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "=====================onNext " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /*
     * concatArrayDelayError() & mergeArrayDelayError()
     *
     * */

    public static void delayError() {

//如果正常的发送Error方法，会是整个rx挂掉，所以要采取后者
//    Observable.concatArray(
//            Observable.create(new ObservableOnSubscribe < Integer > () {
//                @Override
//                public void subscribe(ObservableEmitter < Integer > e) throws Exception {
//                    e.onNext(1);
//                    e.onError(new NumberFormatException());
//                }
//            }), Observable.just(2, 3, 4))
//            .subscribe(new Observer < Integer > () {
//                @Override
//                public void onSubscribe(Disposable d) {
//
//                }
//
//                @Override
//                public void onNext(Integer integer) {
//                    Log.d(TAG, "===================onNext " + integer);
//                }
//
//                @Override
//                public void onError(Throwable e) {
//                    Log.d(TAG, "===================onError ");
//                }
//
//                @Override
//                public void onComplete() {
//
//                }
//            });


//如果采取concatArrayDelayError这个，会将数据传输完毕然后在进行onerror的调用。
        Observable.concatArrayDelayError(
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        e.onNext(1);
                        e.onError(new NumberFormatException());
                    }
                }), Observable.just(2, 3, 4))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "===================onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "===================onError ");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //zip() 多个任务的时候合并按最小的返回任务数发送 如下代码是发送的 任务数是5个 第二个事件的第六个任务数不进行发送
    public static void zip() {
        Observable.zip(Observable.intervalRange(1, 5, 1, 1, TimeUnit.SECONDS).map(new Function<Long, String>() {
            @Override
            public String apply(Long aLong) throws Exception {
                String s1 = "A" + aLong;
                Log.d(TAG, "===================A 发送的事件 " + s1);
                return s1;
            }
        }), Observable.intervalRange(1, 6, 1, 1, TimeUnit.SECONDS).map(new Function<Long, String>() {
            @Override
            public String apply(Long aLong) throws Exception {
                String s2 = "B" + aLong;
                Log.d(TAG, "===================B 发送的事件 " + s2);
                return s2;
            }
        }), new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) throws Exception {
                String res = s + s2;
                return res;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "===================onSubscribe ");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "===================onNext " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "===================onError ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "===================onComplete ");
            }
        });
    }

    //
    /*
     *
     * combineLatest() 的作用与 zip() 类似，但是 combineLatest() 发送事件的序列是与发送的时间线有关的，当 combineLatest() 中所有的 Observable 都发送了事件，只要其中有一个 Observable 发送事件，这个事件就会和其他 Observable 最近发送的事件结合起来发送，
     * */
    public static void combineLatest() {
        Observable.combineLatest(Observable.intervalRange(1, 4, 1, 1, TimeUnit.SECONDS).map(new Function<Long, String>() {
            @Override
            public String apply(Long aLong) throws Exception {
                String s1 = "A" + aLong;
                Log.d(TAG, "===================A 发送的事件 " + s1);
                return s1;
            }
        }), Observable.intervalRange(1, 5, 2, 2, TimeUnit.SECONDS).map(new Function<Long, String>() {
            @Override
            public String apply(Long aLong) throws Exception {
                String s1 = "B" + aLong;
                Log.d(TAG, "===================B 发送的事件 " + s1);
                return s1;
            }
        }), new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) throws Exception {
                String res = s + s2;
                return res;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "===================onSubscribe ");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "===================最终接收到的事件 " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "===================onError ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "===================onComplete ");
            }
        });
    }

    //    与 scan() 操作符的作用也是将发送数据以一定逻辑聚合起来，这两个的区别在于 scan() 每处理一次数据就会将事件发送给观察者，而 reduce() 会将所有数据聚合在一起才会发送事件给观察者。
//    从结果可以看到，其实就是前2个数据聚合之后，然后再与后1个数据进行聚合，一直到没有数据为止。
    public static void reduce() {
        Observable.just(1, 2, 3, 4).reduce(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                int res = integer + integer2;
                Log.d(TAG, "====================integer " + integer);
                Log.d(TAG, "====================integer2 " + integer2);
                Log.d(TAG, "====================res " + res);
                return res;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "==================accept " + integer);
            }
        });
    }

    //将参数进行合并  将数据收集到数据结构当中
    public static void collect() {
        Observable.just(1, 2, 3, 4, 5).collect(new Callable<ArrayList<Integer>>() {
            @Override
            public ArrayList<Integer> call() throws Exception {
                return new ArrayList<Integer>();
            }
        }, new BiConsumer<ArrayList<Integer>, Integer>() {
            @Override
            public void accept(ArrayList<Integer> integerArrayList, Integer integer2) throws Exception {
                integerArrayList.add(integer2);
            }
        }).subscribe(new Consumer<ArrayList<Integer>>() {
            @Override
            public void accept(ArrayList<Integer> integer) throws Exception {
                Log.d(TAG, "===============accept " + integer);
            }
        });
    }


//    startWith() & startWithArray() 前者是追加一个事件，后者可追加多个事件。  注 startWith（）优先级在startWithArray（）前面，startWithArray（）优先级在just（）前面

    public static void startWith() {
        Observable.just(1, 2, 3, 4).startWithArray(7, 8, 9, 0).startWith(5).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "=======================integer " + integer);
            }
        });
    }

    public static void delay() {

        Observable.just(1, 2, 3, 4).delay(2, TimeUnit.SECONDS).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "=======================onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "=======================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "=======================onSubscribe");
            }
        });
    }


    //doOnEach 每次调用之前都会调用该方法，自我理解 其实可以想到的是每次的初始化逻辑情况设定 注 即时回调onComplete方法前也会回调一次，但是回调参数是null
    public static void doOnEach() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                //      e.onError(new NumberFormatException());
                e.onComplete();
            }
        }).doOnEach(new Consumer<Notification<Integer>>() {
            @Override
            public void accept(Notification<Integer> objectNotification) throws Exception {
                Log.d(TAG, "==================doOnEach " + objectNotification.getValue());
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "==================onSubscribe ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "==================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "==================onError ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "==================onComplete ");
            }
        });
    }

    //doOnNext  每次调用doonnext 的都会调用一次 Observable 每发送 onNext() 之前都会先回调这个方法。
//doAfterNext() 与类似的调用onnext（）之后调用该方法
//    doOnComplete() 每发送 onComplete() 之前都会回调这个方法
//    doOnError() Observable 每发送 onError() 之前都会回调这个方法。
//    doOnSubscribe() Observable 每发送 onSubscribe() 之前都会回调这个方法
//    doOnDispose() 当调用 Disposable 的 dispose() 之后回调该方法。
    public static void doOnNext() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onComplete();
            }
        }).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "==================doOnNext " + integer);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "==================onSubscribe ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "==================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "==================onError ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "==================onComplete ");
            }
        });
    }

    //doOnLifecycle()  在回调 onSubscribe 之前回调该方法的第一个参数的回调方法，可以使用该回调方法决定是否取消订阅。
//可以看到当在 onNext() 方法进行取消订阅操作后，doOnDispose() 和 doOnLifecycle() 都会被回调。
//    可以看到当在 onNext() 方法进行取消订阅操作后，doOnDispose() 和 doOnLifecycle() 都会被回调。
    public static void doOnLifecycle() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }).doOnLifecycle(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                Log.d(TAG, "==================doOnLifecycle accept");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                Log.d(TAG, "==================doOnLifecycle Action");
            }
        })
                .doOnDispose(
                        new Action() {
                            @Override
                            public void run() throws Exception {
                                Log.d(TAG, "==================doOnDispose Action");
                            }
                        }).subscribe(new Observer<Integer>() {
            private Disposable d;

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "==================onSubscribe ");
                this.d = d;
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "==================onNext " + integer);
                d.dispose();
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "==================onError ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "==================onComplete ");
            }
        });
    }

//    doOnTerminate() & doAfterTerminate()
//    doOnTerminate 是在 onError 或者 onComplete 发送之前回调，而 doAfterTerminate 则是 onError 或者 onComplete 发送之后回调。

    public static void doOnTerminate() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
//      e.onError(new NullPointerException());
                e.onComplete();
            }
        }).doOnTerminate(new Action() {
            @Override
            public void run() throws Exception {
                Log.d(TAG, "==================doOnTerminate ");
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "==================onSubscribe ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "==================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "==================onError ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "==================onComplete ");
            }
        });
    }

    //doFinally() 所有方法执行完毕 回调这个方法
    public static void doFinally() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }).doFinally(new Action() {
            @Override
            public void run() throws Exception {
                Log.d(TAG, "==================doFinally ");
            }
        }).doOnDispose(new Action() {
            @Override
            public void run() throws Exception {
                Log.d(TAG, "==================doOnDispose ");
            }
        }).doAfterTerminate(new Action() {
            @Override
            public void run() throws Exception {
                Log.d(TAG, "==================doAfterTerminate ");
            }
        }).subscribe(new Observer<Integer>() {
            private Disposable d;

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "==================onSubscribe ");
                this.d = d;
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "==================onNext " + integer);
                d.dispose();
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "==================onError ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "==================onComplete ");
            }
        });
    }

    //    onErrorReturn() 当接受到一个 onError() 事件之后回调，返回的值会回调 onNext() 方法，并正常结束该事件序列。
    public static void onErrorReturn() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onError(new NullPointerException());
            }
        })
                .onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) throws Exception {
                        Log.d(TAG, "==================onErrorReturn " + throwable);
                        return 404;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "==================onSubscribe ");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "==================onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "==================onError ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "==================onComplete ");
                    }
                });
    }

    //    当接收到 onError() 事件时，返回一个新的 Observable，并正常结束事件序列。
    public static void onErrorResumeNext() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onError(new NullPointerException());
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> apply(Throwable throwable) throws Exception {
                Log.d(TAG, "==================onErrorResumeNext " + throwable);
                return Observable.just(4, 5, 6);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "==================onSubscribe ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "==================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "==================onError ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "==================onComplete ");
            }
        });
    }

//    onExceptionResumeNext() 与 onErrorResumeNext() 作用基本一致，但是这个方法只能捕捉 Exception。

    public static void onExceptionResumeNext() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
//                e.onError(new Error("404"));  //new Error("404") 不能被捕捉
                e.onError(new Exception("404")); //new Exception("404") 能被捕获
            }
        }).onExceptionResumeNext(new Observable<Integer>() {
            @Override
            protected void subscribeActual(Observer<? super Integer> observer) {
                observer.onNext(333);
                observer.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "==================onSubscribe ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "==================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "==================onError ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "==================onComplete ");
            }
        });
    }

    //retry()如果出现错误事件，则会重新发送所有事件序列。times 是代表重新发的次数。
    public static void retry() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onError(new Exception("404"));
            }
        }).retry(2).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "==================onSubscribe ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "==================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "==================onError ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "==================onComplete ");
            }
        });

    }

    //    retryUntil() 出现错误事件之后，可以通过此方法判断是否继续发送事件。
//    public static void retryUntil() {
//        int i;
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);
//                e.onError(new Exception("404"));
//            }
//        }).retryUntil(new BooleanSupplier() {
//            @Override
//            public boolean getAsBoolean() throws Exception {
//                if (i == 6) {
//                    return true;
//                }
//                return false;
//            }
//        }).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d(TAG, "==================onSubscribe ");
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                i += integer;
//                Log.d(TAG, "==================onNext " + integer);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "==================onError ");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "==================onComplete ");
//            }
//        });
//    }


// retryWhen()
    /*
     *
     * 当被观察者接收到异常或者错误事件时会回调该方法，这个方法会返回一个新的被观察者。如果返回的被观察者发送 Error 事件则之前的被观察者不会继续发送事件，如果发送正常事件则之前的被观察者会继续不断重试发送事件。
     *
     * */


    public static void retryWhen() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("chan");
                e.onNext("ze");
                e.onNext("de");
                e.onError(new Exception("404"));
                e.onNext("haha");
            }
        }).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        if (!throwable.toString().equals("java.lang.Exception: 404")) {
                            return Observable.just("可以忽略的异常");
                        } else {
                            return Observable.error(new Throwable("终止啦"));
                        }
                    }
                });
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "==================onSubscribe ");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "==================onNext " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "==================onError " + e.toString());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "==================onComplete ");
            }
        });
    }


    //repeat() 重复发送被观察者的事件，times 为发送次数。
    public static void repeat() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }).repeat(2).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "===================onSubscribe ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "===================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "===================onComplete ");
            }
        });
    }


    //     repeatWhen()   这个方法可以会返回一个新的被观察者设定一定逻辑来决定是否重复发送事件。

    /*
    *
    *
    * 打印结果：
05-24 11:44:33.486 9379-9379/com.example.rxjavademo D/chan: ===================onSubscribe
05-24 11:44:33.487 9379-9379/com.example.rxjavademo D/chan: ===================onComplete
下面直接看看发送 onError 事件和其他事件的打印结果。
发送 onError 打印结果：
05-24 11:46:29.507 9561-9561/com.example.rxjavademo D/chan: ===================onSubscribe
05-24 11:46:29.508 9561-9561/com.example.rxjavademo D/chan: ===================onError
发送其他事件的打印结果：
05-24 11:48:35.844 9752-9752/com.example.rxjavademo D/chan: ===================onSubscribe
===================onNext 1
===================onNext 2
===================onNext 3
===================onComplete
    *
    *
    * */
    public static void repeatWhen() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
//                return Observable.just(5,29,40);
                return Observable.empty();
                //  return Observable.error(new Exception("404"));
                //  return Observable.just(4); null;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "===================onSubscribe ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "===================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "===================onError ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "===================onComplete ");
            }
        });
    }

    //subscribeOn() 指定被观察者的线程，要注意的时，如果多次调用此方法，只有第一次有效。
    public static void subscribeOn() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "=========================currentThread name: " + Thread.currentThread().getName());
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread()).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
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
        });
    }


    //    observeOn()  指定观察者的线程，每指定一次就会生效一次。
/*
* 下表总结了 RxJava 中的调度器：

调度器
作用

Schedulers.computation( )
用于使用计算任务，如事件循环和回调处理


Schedulers.immediate( )
当前线程


Schedulers.io( )
用于 IO 密集型任务，如果异步阻塞 IO 操作。


Schedulers.newThread( )
创建一个新的线程


AndroidSchedulers.mainThread()
Android 的 UI 线程，用于操作 UI。

*
*
* */
    public static void observeOn() {
        Observable.just(1, 2, 3).observeOn(Schedulers.newThread()).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                Log.d(TAG, "======================flatMap Thread name " + Thread.currentThread().getName());
                return Observable.just("chan" + integer);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "======================onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "======================onNext Thread name " + Thread.currentThread().getName());
                Log.d(TAG, "======================onNext " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "======================onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "======================onComplete");
            }
        });
    }


//    filter()  通过一定逻辑来过滤被观察者发送的事件，如果返回 true 则会发送事件，否则不会发送。

    public static void filter() {
        Observable.just(1, 2, 3).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer < 2;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "==================onSubscribe ");
            }

            @Override
            public void onNext(Integer integer) {
                int i = 0;
                i += integer;
                Log.d(TAG, "==================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "==================onError ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "==================onComplete ");
            }
        });
    }

    //ofType() 可以过滤不符合该类型事件   ，只输出符合类型的   注该方法只能传一个类的类型值，也只能设置一次
    public static void ofType() {
        Observable.just(1, 2, 3, "chan", "zhide").ofType(Integer.class).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "==================onSubscribe ");
            }

            @Override
            public void onNext(Integer integer) {
                int i = 0;
                i += integer;
                Log.d(TAG, "==================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "==================onError ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "==================onComplete ");
            }
        });

    }

    //skip() 跳过正序某些事件，count 代表跳过事件的数量   如下设置是跳过第一个
    public static void skip() {
        Observable.just(1, 2, 3).skip(1).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "==================onSubscribe ");
            }

            @Override
            public void onNext(Integer integer) {
                int i = 0;
                i += integer;
                Log.d(TAG, "==================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "==================onError ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "==================onComplete ");
            }
        });
    }


    //    distinct()   过滤事件序列中的重复事件。 所有重复的事件都会被过滤 后面 的3,2,1不会被输出
    public static void distinct() {
        Observable.just(1, 2, 3, 3, 2, 1).distinct().subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "==================onSubscribe ");
            }

            @Override
            public void onNext(Integer integer) {
                int i = 0;
                i += integer;
                Log.d(TAG, "==================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "==================onError ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "==================onComplete ");
            }
        });
    }


//    distinctUntilChanged()  过滤掉连续重复的事件  3 是连续出现的所以把3进行过滤掉

    public static void distinctUntilChanged() {
        Observable.just(1, 2, 3, 3, 2, 1)
                .distinctUntilChanged()
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "==================onSubscribe ");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        int i = 0;
                        i += integer;
                        Log.d(TAG, "==================onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "==================onError ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "==================onComplete ");
                    }
                });
    }


    //    take() 控制观察者接收事件的数量  // 正序 只输出1,2 后面不被输出
//    takeLast() 的作用就是控制观察者只能接受事件序列的后面几件事情.
    public static void take() {
        Observable.just(1, 2, 3, 4, 5).take(2).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "==================onSubscribe ");
            }

            @Override
            public void onNext(Integer integer) {
                int i = 0;
                i += integer;
                Log.d(TAG, "==================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "==================onError ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "==================onComplete ");
            }
        });
    }

//    debounce()  如果两件事件发送的时间间隔小于设定的时间间隔则前一件事件就不会发送给观察者。  //如下 只接受到2 因为 睡眠900ms

    public static void debounce() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                Thread.sleep(900);
                e.onNext(2);
            }
        }).debounce(1, TimeUnit.SECONDS).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "===================onSubscribe ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "===================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "===================onError ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "===================onComplete ");
            }
        });
    }


    //    firstElement() && lastElement() firstElement() 取事件序列的第一个元素，lastElement() 取事件序列的最后一个元素。
    public static void firstElement() {
        Observable.just(1, 2, 3, 4).firstElement().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "====================firstElement " + integer);
            }
        });


        Observable.just(1, 2, 3, 4).lastElement().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "====================lastElement " + integer);
            }
        });
    }


//elementAt() & elementAtOrError()  elementAt() 可以指定取出事件序列中事件，但是输入的 index 超出事件序列的总数的话就不会出现任何结果。这种情况下，你想发出异常信息的话就用 elementAtOrError() 。 注：自己理解为越界


    public static void elementAt() {
        Observable.just(1, 2, 3, 4).elementAt(0).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "====================elementAt " + integer);
            }
        });
        Observable.just(1, 2, 3, 4)
                .elementAtOrError(5)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "====================accept " + integer);
                    }
                });
    }


//all()  判断事件序列是否全部满足某个事件，如果都满足则返回 true，反之则返回 false。


    public static void all() {  //注意 是全部，如果其中一个元素不满足，则返回false
        Observable.just(1, 2, 3, 4).all(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {

                return integer < 5;
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                Log.d(TAG, "====================accept " + aBoolean);
            }
        });
    }


//    takeWhile()  可以设置条件，当某个数据满足条件时就会发送该数据，反之则不发送。 如下设置 只发送1,2 其他的不予发送处理

    public static void takeWhile() {
        Observable.just(1, 2, 3, 4, 5).takeWhile(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer < 3;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "========================integer " + integer);
            }
        });
    }


    //    skipWhile() 可以设置条件，当某个数据满足条件时不发送该数据，反之则发送。 因为1,2 不满足所以1,2 不发送，3，4 满足，所以3,4 发送。
    public static void skipWhile() {
        Observable.just(1, 2, 3, 4).skipWhile(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer < 3;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "========================integer " + integer);
            }
        });
    }

    //takeUntil()  可以设置条件，当事件满足此条件时，下一次的事件就不会被发送了。 因为3 满足所以4 不被发送
    public static void takeUntil() {
        Observable.just(1, 2, 3, 4).takeUntil(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer > 2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "========================integer " + integer);
            }
        });
    }
//    skipUntil() 当 skipUntil() 中的 Observable 发送事件了，原来的 Observable 才会发送事件给观察者。 后者如果发送成功前者才会被发送，但是因为存在着时间周期所以当第二个发送4后，第一个被发送5

    public static void skipUntil() {
        Observable.intervalRange(1, 5, 0, 1, TimeUnit.SECONDS).skipUntil(Observable.intervalRange(6, 5, 3, 1, TimeUnit.SECONDS)).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "========================onSubscribe ");
            }

            @Override
            public void onNext(Long along) {
                Log.d(TAG, "========================onNext " + along);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "========================onError ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "========================onComplete ");
            }
        });
    }


    //      sequenceEqual() 判断两个 Observable 发送的事件是否相同。 判断两个对象是否一样
    public static void sequenceEqual() {
        Observable.sequenceEqual(Observable.just(1, 2, 3, 4), Observable.just(1, 2, 3, 4)).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                Log.d(TAG, "========================accept " + aBoolean);
            }
        });
    }

    //contains()  判断事件序列中是否含有某个元素，如果有则返回 true，如果没有则返回 false。
    public static void contains() {
        Observable.just(1, 2, 3, 4).contains(3).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                Log.d(TAG, "========================accept " + aBoolean);
            }
        });
    }

    //isEmpty() 判断事件序列是否为空。
    public static void isEmpty() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onComplete();
            }
        }).isEmpty()
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.d(TAG, "========================onNext " + aBoolean);
                    }
                });
    }

//amb() 要传入一个 Observable 集合，但是只会发送最先发送事件的 Observable 中的事件，其余 Observable 将会被丢弃。
/*
* 06-08 18:56:10.207 31341-31375/com.example.wangjingyang.rxtest D/TestApi: ========================accept 6
06-08 18:56:11.207 31341-31375/com.example.wangjingyang.rxtest D/TestApi: ========================accept 7
06-08 18:56:12.207 31341-31375/com.example.wangjingyang.rxtest D/TestApi: ========================accept 8
06-08 18:56:13.207 31341-31375/com.example.wangjingyang.rxtest D/TestApi: ========================accept 9
06-08 18:56:14.207 31341-31375/com.example.wangjingyang.rxtest D/TestApi: ========================accept 10
发送第二个，第一个就被摒弃掉了
*
*
* */
    public static void amb() {
        ArrayList<Observable<Long>> list = new ArrayList<>();

        list.add(Observable.intervalRange(1, 5, 2, 1, TimeUnit.SECONDS));
        list.add(Observable.intervalRange(6, 5, 0, 1, TimeUnit.SECONDS));


        Observable.amb(list).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.d(TAG, "========================accept " + aLong);
            }
        });
    }

    //     defaultIfEmpty()  如果观察者只发送一个 onComplete() 事件，则可以利用这个方法发送一个值。
    public static void defaultIfEmpty() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

            }
        }).defaultIfEmpty(666).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "========================accept " + integer);
            }
        });
    }


}
