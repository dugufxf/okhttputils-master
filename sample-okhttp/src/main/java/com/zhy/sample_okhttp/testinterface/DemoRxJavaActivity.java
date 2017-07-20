package com.zhy.sample_okhttp.testinterface;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * <pre>
 *     Created by fly
 *     e-mail : 18810086961@163.com
 *     time   : 2017/07/18
 *     desc   :
 *     version: 1.0     初始化
 *
 *  <pre>
 */
public class DemoRxJavaActivity extends AppCompatActivity {
    public static String TAG = DemoRxJavaActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        创建一个上游 Observerable
//        ObservableEmitter： Emitter是发射器的意思，那就很好猜了，这个就是用来发出事件的，它可以发出三种类型的事件，通过调用emitter的onNext(T value)、onComplete()和onError(Throwable error)就可以分别发出next事件、complete事件和error事件。
//
//        但是，请注意，并不意味着你可以随意乱七八糟发射事件，需要满足一定的规则：
//
//        上游可以发送无限个onNext, 下游也可以接收无限个onNext.
//                当上游发送了一个onComplete后, 上游onComplete之后的事件将会继续发送, 而下游收到onComplete事件之后将不再继续接收事件.
//                当上游发送了一个onError后, 上游onError之后的事件将继续发送, 而下游收到onError事件之后将不再继续接收事件.
//                上游可以不发送onComplete或onError.
//                最为关键的是onComplete和onError必须唯一并且互斥, 即不能发多个onComplete, 也不能发多个onError, 也不能先发一个onComplete, 然后再发一个onError, 反之亦然
//
//        注: 关于onComplete和onError唯一并且互斥这一点, 是需要自行在代码中进行控制, 如果你的代码逻辑中违背了这个规则, 并不一定会导致程序崩溃. 比如发送多个onComplete是可以正常运行的, 依然是收到第一个onComplete就不再接收了, 但若是发送多个onError, 则收到第二个onError事件会导致程序会崩溃.
//
//                作者：Season_zlc
//        链接：http://www.jianshu.com/p/464fa025229e
//
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "subscribe");
//                接下来介绍Disposable, 这个单词的字面意思是一次性用品,用完即可丢弃的.
// 那么在RxJava中怎么去理解它呢, 对应于上面的水管的例子,
// 我们可以把它理解成两根管道之间的一个机关, 当调用它的dispose()方法时,
// 它就会将两根管道切断, 从而导致下游收不到事件.
//                        注意: 调用dispose()并不会导致上游不再继续发送事件, 上游会继续发送剩余的事件.
//                链接：http://www.jianshu.com/p/464fa025229e
//                來源：简书
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "complete");
            }
        });
//        12-02 03:37:17.818 4166-4166/zlc.season.rxjava2demo D/TAG: subscribe
//        12-02 03:37:17.819 4166-4166/zlc.season.rxjava2demo D/TAG: 1
//        12-02 03:37:17.819 4166-4166/zlc.season.rxjava2demo D/TAG: 2
//        12-02 03:37:17.819 4166-4166/zlc.season.rxjava2demo D/TAG: 3
//        12-02 03:37:17.819 4166-4166/zlc.season.rxjava2demo D/TAG: complete
//
//        作者：Season_zlc
//        链接：http://www.jianshu.com/p/464fa025229e
//        來源：简书
    }

}
