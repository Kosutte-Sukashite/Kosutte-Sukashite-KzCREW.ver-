package slj.myapplication;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class Game_Activity extends Activity {
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;

    private GestureDetector mGestureDetector;
    private int mCount;
    private View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_);

        mGestureDetector = new GestureDetector(this, mOnGestureListener);
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
                    plus(view);
                } else if (event2.getX() - event1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    // 終了位置から開始位置の移動距離が指定値より大きい
                    // X軸の移動速度が指定値より大きい
                    Toast.makeText(Game_Activity.this, "左から右", Toast.LENGTH_SHORT).show();
                    //こすった回数をカウント
                    plus(view);
                }

                if (event1.getY() - event2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    // 開始位置から終了位置の移動距離が指定値より大きい
                    // Y軸の移動速度が指定値より大きい
                    Toast.makeText(Game_Activity.this, "下から上", Toast.LENGTH_SHORT).show();
                    //こすった回数をカウント
                    plus(view);

                } else if (event2.getY() - event1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    // 終了位置から開始位置の移動距離が指定値より大きい
                    // Y軸の移動速度が指定値より大きい
                    Toast.makeText(Game_Activity.this, "上から下", Toast.LENGTH_SHORT).show();
                    //こすった回数をカウント
                    plus(view);
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

    private void plus(View view) {
        //こすった数だけ読み込まれる。
        this.view = view;
        mCount++;
        TextView countView = (TextView) findViewById(R.id.textView1);
        //activity_game_.xmlの表示を増やす
        countView.setText(String.valueOf(mCount));
    }






}
