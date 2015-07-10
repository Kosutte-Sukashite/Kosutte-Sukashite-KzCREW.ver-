package slj.myapplication;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.widget.TextView;
import slj.myapplication.Alpha;

/**
 * Created by user on 15/07/04.
 */
class MyCountDownTimer extends CountDownTimer {

    private TextView timer_txt;
    private Alpha alpha;
    private int[] mSound;
    private Boolean Flag;

       public
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    MyCountDownTimer(long millisInFuture, long countDownInterval,Context context,Alpha alpha_tmp) {
        super(millisInFuture, countDownInterval);
        timer_txt = (TextView) ((slj.myapplication.Game_Activity) context).findViewById(R.id.timerText);
        alpha = alpha_tmp;
        Flag = Boolean.FALSE;
    }

    @Override
    public void onTick(long millisUntilFinished) {
//        timer_txt.setText("TIME:"+ millisUntilFinished/1000);
        //ランダムで曇りを追加します
        alpha.alpha_random();

        //時間表示
        timer_txt.setText("TIME:"+ millisUntilFinished/1000);
    }


    @Override
    public void onFinish() {
        timer_txt.setText("終了!!");
        cancel();
        Flag = Boolean.TRUE;
    }


    public Boolean GetTimer(){
        return Flag;
    }
}
