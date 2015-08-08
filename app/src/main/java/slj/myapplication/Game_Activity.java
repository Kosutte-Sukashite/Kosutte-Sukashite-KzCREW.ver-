package slj.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

//Music
import android.media.MediaPlayer;

//Music 効果音系
import android.media.SoundPool;
import android.media.AudioManager;

public class Game_Activity extends Activity {
    private Alpha alpha;
    private MyCountDownTimer MyTimer;

    //Musicの変数
    private MediaPlayer main_mp;

    //効果音の変数
    private SoundPool mSePlayer;
    private int[] mSound = new int[5];
    private boolean gamestopflag;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_);

        //クラスの作成
        alpha = new Alpha(this);
        MyTimer = new MyCountDownTimer(30000, 1000, this, alpha);

        //アニメーション用テキストビュー
        TextView animTxt = (TextView)findViewById(R.id.animeTxt);
        Animation animation= AnimationUtils.loadAnimation(this, R.animator.anim);
        animTxt.startAnimation(animation);

        //画面サイズ取得
        WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        RandomShow randomShow = new RandomShow(2000,true,animTxt,size.x,size.y);
        randomShow.execute();

       //リソースファイルから再生 MainBGM
        main_mp = MediaPlayer.create(this, R.raw.back_bgm);
        main_mp.start();

        //効果音保存
        mSePlayer = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        mSound[0] = mSePlayer.load(getApplicationContext(), R.raw.magic, 1);

        //タイマー開始
        MyTimer.start();

        gamestopflag = false;
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                cleanupMedia(main_mp);
                Intent intent = new Intent(Game_Activity.this, ConfirmActivity.class);
                intent.putExtra("txt", alpha.parcent);
                startActivity(intent);
                Game_Activity.this.finish();
            }

        }, 35000);

    }

    protected void onDestroy() {
        super.onDestroy();

        cleanupMedia(main_mp);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // TODO Auto-generated method stub
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                return false;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_MOVE) {
                //透過度を下げる
                alpha.alpha_control();
                mSePlayer.play(mSound[0], 0.5f, 0.5f, 0, 0, 1.0f);

            return  gamestopflag;
        }
        return gamestopflag;
    }

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


    public static final void cleanupMedia(MediaPlayer mediaPlayer){
        try {

            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();

            }
        } catch (Exception e) {
        }
    }


}
