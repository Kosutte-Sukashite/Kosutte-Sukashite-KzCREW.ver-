package slj.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.AudioManager;

    public class MainActivity extends Activity implements OnClickListener {
        /**
         * Called when the activity is first created.
         */

        private Button button_segue;


        private MediaPlayer mp;
        private String path;


        private SoundPool mSoundPool;
        private int mSoundId;
        private SoundPool mSePlayer;
        private int[] mSound = new int[5];

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            button_segue = (Button) findViewById(R.id.button_segue);
            button_segue.setOnClickListener(this);

            //mp = MediaPlayer.create(this, R.raw.accept);
            //mp.start();


            mSePlayer = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            mSound[0] = mSePlayer.load(getApplicationContext(), R.raw.accept, 1);

        }

        public void onClick(View v) {
            if (v == button_segue) {
                Intent intent = new Intent(this, Game_Activity.class);
                startActivityForResult(intent, 0);


                mSePlayer.play(mSound[0], 1.0f, 1.0f, 0, 0, 1.0f);
            }
        }



    }

