package slj.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Service;
import android.widget.ImageView;

//Music
import android.media.MediaPlayer;

//Music 効果音系
import android.media.SoundPool;
import android.media.AudioManager;

public class Game_Activity extends Activity{
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;

    private GestureDetector mGestureDetector;

    //透過の変数
    private ImageView image;
    private int alpha_i = 255;


    //Musicの変数
    private MediaPlayer main_mp;
    private String path;

    //効果音の変数
    private SoundPool mSoundPool;
    private int mSoundId;
    private SoundPool mSePlayer;
    private int[] mSound = new int[5];


    private int mCount;
    private View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_);


        //リソースファイルから再生 MainBGM
        main_mp = MediaPlayer.create(this, R.raw.gamebgm);
        main_mp.start();

        mGestureDetector = new GestureDetector(this, mOnGestureListener);

        //効果音保存
        mSePlayer = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        mSound[0] = mSePlayer.load(getApplicationContext(), R.raw.bom, 1);


        // 30秒カウントダウンする
        new CountDownTimer(30000,1000){
            TextView timer_txt = (TextView)findViewById(R.id.timerText);
            // カウントダウン処理
            public void onTick(long millisUntilFinished){

                //ランダムで曇りを追加します
                alpha_random();

                //時間表示
                timer_txt.setText("TIME:"+ millisUntilFinished/1000);
            }
            // カウントが0になった時の処理
            public void onFinish(){
                timer_txt.setText("終了!!");
                cancel();
                main_mp.stop();
            }
        }.start();



    }

    private void stop() {

    }

    // これがないとGestureDetectorが動かない
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }



    private final GestureDetector.SimpleOnGestureListener mOnGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            try {

                //if (Math.abs(event1.getY() - event2.getY()) > SWIPE_MAX_OFF_PATH) {
                // 縦の移動距離が大きすぎる場合は無視
                //  return false;
                //今回のゲームでは咆哮指定等はしないため、削除
                // }

                if (event1.getX() - event2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    // 開始位置から終了位置の移動距離が指定値より大きい
                    // X軸の移動速度が指定値より大きい
                    Toast.makeText(Game_Activity.this, "右から左", Toast.LENGTH_SHORT).show();
                    //こすった回数をカウント
                    alpha_control();

                } else if (event2.getX() - event1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    // 終了位置から開始位置の移動距離が指定値より大きい
                    // X軸の移動速度が指定値より大きい
                    Toast.makeText(Game_Activity.this, "左から右", Toast.LENGTH_SHORT).show();
                    //こすった回数をカウント
                    alpha_control();
                }

                if (event1.getY() - event2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    // 開始位置から終了位置の移動距離が指定値より大きい
                    // Y軸の移動速度が指定値より大きい
                    Toast.makeText(Game_Activity.this, "下から上", Toast.LENGTH_SHORT).show();
                    //こすった回数をカウント
                    alpha_control();

                } else if (event2.getY() - event1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    // 終了位置から開始位置の移動距離が指定値より大きい
                    // Y軸の移動速度が指定値より大きい
                    Toast.makeText(Game_Activity.this, "上から下", Toast.LENGTH_SHORT).show();
                    //こすった回数をカウント
                    alpha_control();
                }


            } catch (Exception e) {
                // nothing

            }
            return false;
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        main_mp.stop();
        //Game_Activity.stop();
        }

    private int plus(View view) {
        //こすった数だけ読み込まれる。
        this.view = view;
        mCount++;
        TextView countView = (TextView) findViewById(R.id.textView1);
        //activity_game_.xmlの表示を増やす
        countView.setText(String.valueOf(mCount));

        return  mCount;
    }

    //透過度の変更と音を鳴らす
    private void alpha_control(){
        //透過度を上げる 1回の割合の変更 0以下には行わない
        if (alpha_i > 1) {
            alpha_i -= 1;
        }
        //透過度の反映
        image = (ImageView)findViewById(R.id.white);
        image.setAlpha(alpha_i);

        //透過度の%表示
        TextView alpha_txt = (TextView)findViewById(R.id.alphaText);
        //alpha_txt.setText(String.valueOf(alpha_i));
        alpha_txt.setText("透過度\n" + alpha_percent(alpha_i) + "%");

        //効果音を再生
        mSePlayer.play(mSound[0], 1.0f, 1.0f, 0, 0, 1.0f);

    }

    //ランダムで曇りを追加します
    private void alpha_random(){

        //ランダムで曇りを足していく 245以下の場合
        if (alpha_i < 245) {
            alpha_i += (int) (Math.random() * 10);
        }

        image = (ImageView)findViewById(R.id.white);
        image.setAlpha(alpha_i);

        TextView alpha_txt = (TextView)findViewById(R.id.alphaText);
        //alpha_txt.setText(String.valueOf(alpha_i));
        alpha_txt.setText("透過度\n" + alpha_percent(alpha_i) + "%");
    }


    private float alpha_percent(int alpha){
        alpha = -(alpha_i -255);

        float parcent = (alpha * 100)/255;
        return parcent;
    }








}
