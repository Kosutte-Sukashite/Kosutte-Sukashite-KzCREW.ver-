package slj.myapplication;


import android.media.SoundPool;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.widget.TextView;

/**
 * Created by SLJ on 2015/07/03.
 */
public class Alpha {
    private int alpha_i;
    private TextView alpha_text;
    private ImageView imageView;
    private SoundPool mSePlayer;

    //コンストラクタ
    Alpha(Context context){
        //初期化
        alpha_i = 255;

        alpha_text = (TextView) ((slj.myapplication.Game_Activity) context).findViewById(R.id.alphaText);
        imageView = (ImageView) ((slj.myapplication.Game_Activity) context).findViewById(R.id.white);

    }

    //処理

    //透過度の変更と音を鳴らす
    public void alpha_control( int mSound,SoundPool mSePlayer){
        //透過度を上げる 1回の割合の変更 0以下には行わない
        if (alpha_i > 1) {
            alpha_i -= 1;
        }

        //透過度の反映
        balance_alpha(alpha_i);
        mSePlayer.play(mSound, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    public int alpha_percent(int alpha){
        alpha = -(alpha_i -255);

        int parcent = (alpha * 100)/255;
        return parcent;
    }

    //ランダムで曇りを追加します
    public void alpha_random(){

        //ランダムで曇りを足していく 245以下の場合
        if (alpha_i < 245) {
            alpha_i += (int) (Math.random() * 10);
        }

        //項目表示
        balance_alpha(alpha_i);

        //alpha_txt.setText(String.valueOf(alpha_i));
        alpha_text.setText("透過度\n" + alpha_percent(alpha_i) + "%");
    }

    //透過度変更の割合
    public void balance_alpha(int alpha){
        imageView.setAlpha(alpha_i);
    }

}
