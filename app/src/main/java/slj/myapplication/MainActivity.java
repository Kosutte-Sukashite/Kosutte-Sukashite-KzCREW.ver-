
package slj.myapplication;

import android.app.Activity;

import android.os.Bundle;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

    public class MainActivity extends Activity implements OnClickListener {
        /**
         * Called when the activity is first created.
         */

        private Button button_segue;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            button_segue = (Button) findViewById(R.id.button_segue);
            button_segue.setOnClickListener(this);

        }

        public void onClick(View v) {
            if (v == button_segue) {
                Intent intent = new Intent(this, Game_Activity.class);
                startActivityForResult(intent, 0);

            }
        }



    }

