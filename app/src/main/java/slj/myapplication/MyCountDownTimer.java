package slj.myapplication;

import android.os.CountDownTimer;

/**
 * Created by user on 15/07/04.
 */
class MyCountDownTimer extends CountDownTimer {
       public
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    MyCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
//        timer_txt.setText("TIME:"+ millisUntilFinished/1000);
    }


    @Override
    public void onFinish() {

    }
}
