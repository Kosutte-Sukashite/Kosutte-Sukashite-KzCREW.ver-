package slj.kzcrew;


import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

/**
 * Created by SLJ on 2015/07/03.
 */
public class Alpha {
    private int alpha_i;
    private TextView alpha_text;
    private ImageView imageView;
    private int cnt=0;

    //透過度値
    public int parcent;
    //コンストラクタ
    Alpha(Context context){
        //初期化
        alpha_i = 255;

        //透過度表示用TextView
        alpha_text = (TextView) ((slj.kzcrew.Game_Activity) context).findViewById(R.id.alphaText);
        //透過画像
        imageView = (ImageView) ((slj.kzcrew.Game_Activity) context).findViewById(R.id.white);

    }


    //透過度の変更と音を鳴らす
    public void alpha_control(){
        cnt = cnt + 1;

        if (cnt == 5) {
            cnt = 0;
            if(alpha_i >0) {
                alpha_i = alpha_i - 1;
                //画像の透過度を変化
                setAlphaValueInImageView();
                //テキストビューに透過度表示
                setTextInAlphaTxt();
            }
        }
    }

    public int alpha_percent(){
        int num = 255 -alpha_i;
        parcent = (num * 100)/255;
        return parcent;
    }

    //画像の透過度を変化
    public void setAlphaValueInImageView(){
        imageView.setAlpha(alpha_i);
    }

    //透過度を戻す
    public void backAlphaValue(int num){
        alpha_i = alpha_i + num;
    }

    //透過度をTextViewに表示
    public void setTextInAlphaTxt(){
        int per = alpha_percent();
        alpha_text.setText("すかし度\n" + per + "%");
    }

}

