package slj.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.media.SoundPool;
import android.media.AudioManager;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnClickListener {
        /**
         * Called when the activity is first created.
         */

        private ImageButton button_segue;
        private SoundPool mSePlayer;
        private int[] mSound = new int[5];

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            button_segue = (ImageButton) findViewById(R.id.button_segue);
            button_segue.setOnClickListener(this);

            mSePlayer = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            mSound[0] = mSePlayer.load(getApplicationContext(), R.raw.baththapon, 1);


        }

        @Override
        public void onRestart( ){
            super.onRestart();
            setContentView(R.layout.activity_main);
        }

        public void onClick(View v) {
                Intent intent = new Intent(this, Game_Activity.class);
                startActivityForResult(intent, 0);

                mSePlayer.play(mSound[0], 1.0f, 1.0f, 0, 0, 1.0f);

        }

    }

