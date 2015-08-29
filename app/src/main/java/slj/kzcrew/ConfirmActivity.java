package slj.kzcrew;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class ConfirmActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

//        ImageButton btn = (ImageButton)findViewById(R.id.backButton);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ConfirmActivity.this, MainActivity.class);
//                // 次画面のアクティビティ起動
//                startActivity(intent);
//            }
//        });
//
//        //weページリンク作成
//       ImageButton linkBtn = (ImageButton)findViewById(R.id.linkButton);
//       linkBtn.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://peatix.com/event/105262/"));
//               startActivity(intent);
//           }
//       });

    }
}
