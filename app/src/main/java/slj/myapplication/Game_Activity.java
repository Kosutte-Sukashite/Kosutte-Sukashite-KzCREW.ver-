package slj.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
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

    private Alpha alpha;
    private MyCountDownTimer MyTimer;

    //Musicの変数
    private MediaPlayer main_mp;
    private String path;

    //効果音の変数
    private SoundPool mSoundPool;
    private int mSoundId;
    private SoundPool mSePlayer;
    private int[] mSound = new int[5];

    private int mCount;

    View view;
    private ImageView womanView;
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_);

        //クラスの作成
        alpha = new Alpha(this);
        MyTimer = new MyCountDownTimer(30000,1000,this,alpha);

        //リソースファイルから再生 MainBGM
        try {
            if (main_mp.isPlaying()) {
                main_mp.stop();
                main_mp.release();
                main_mp = null;
                main_mp=MediaPlayer.create(this, R.raw.gamebgm);
            }
            main_mp.start();
        } catch (Exception e) {
        }

        mGestureDetector = new GestureDetector(this, mOnGestureListener);

        //効果音保存
        mSePlayer = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        mSound[0] = mSePlayer.load(getApplicationContext(), R.raw.bom, 1);

        //タイマー開始
        MyTimer.start();

        //タイマー終了
        if (MyTimer.GetTimer()){
            MyTimer.onFinish();
        }

        Handler handler =  new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                cleanupMedia(main_mp);
                cleanupView(findViewById(R.id.woman));
                Intent intent = new Intent(Game_Activity.this,ConfirmActivity.class);
                startActivity(intent);
                Game_Activity.this.finish();
            }
        },30000);

    }

    protected  void onDestroy(){
        super.onDestroy();

        cleanupMedia(main_mp);
        cleanupView(findViewById(R.id.woman));
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // TODO Auto-generated method stub
        if (event.getAction()==KeyEvent.ACTION_DOWN) {
            if(event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                return false;
            }
        }
        return super.dispatchKeyEvent(event);
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
                    alpha.alpha_control(mSound[0],mSePlayer);

                } else if (event2.getX() - event1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    // 終了位置から開始位置の移動距離が指定値より大きい
                    // X軸の移動速度が指定値より大きい
                    Toast.makeText(Game_Activity.this, "左から右", Toast.LENGTH_SHORT).show();
                    //こすった回数をカウント
                    alpha.alpha_control(mSound[0],mSePlayer);
                }

                if (event1.getY() - event2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    // 開始位置から終了位置の移動距離が指定値より大きい
                    // Y軸の移動速度が指定値より大きい
                    Toast.makeText(Game_Activity.this, "下から上", Toast.LENGTH_SHORT).show();
                    //こすった回数をカウント
                    alpha.alpha_control(mSound[0],mSePlayer);

                } else if (event2.getY() - event1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    // 終了位置から開始位置の移動距離が指定値より大きい
                    // Y軸の移動速度が指定値より大きい
                    Toast.makeText(Game_Activity.this, "上から下", Toast.LENGTH_SHORT).show();
                    //こすった回数をカウント
                    alpha.alpha_control(mSound[0],mSePlayer);
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

    private int plus(View view) {
        //こすった数だけ読み込まれる。
        this.view = view;
        mCount++;
        TextView countView = (TextView) findViewById(R.id.textView1);
        //activity_game_.xmlの表示を増やす
        countView.setText(String.valueOf(mCount));

        return  mCount;
    }

  public static final void cleanupView(View view){
      if(view instanceof ImageView){
          ImageView imageView = (ImageView)view;
          imageView.setImageDrawable(null);
      }
  }

    public static final void cleanupMedia(MediaPlayer mediaPlayer){
        try {

            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        } catch (Exception e) {
        }
    }


}
