package slj.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.Display;
import android.view.View;
import android.content.Context;
import android.view.WindowManager;

/**
 * Created by Tomi on 2015/07/23.
 */
public class ImageResizeView extends View{
    private Bitmap bmp;
    private int dp_w;
    private int dp_h;


    public ImageResizeView(Context context, int resourceName){
        super(context);

        //リソースからBitmap作成
        bmp = BitmapFactory.decodeResource(context.getResources(),resourceName);

        //WindowManager取得
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);

        //Displayインスタンス作成
        Display dp = wm.getDefaultDisplay();

        //ディスプレイサイズ取得
        dp_w = dp.getWidth();
        dp_h = dp.getHeight();

    }

    @Override
    protected void onDraw(Canvas canvas){
        // 背景色
        canvas.drawColor(Color.BLACK);
        // イメージ画像リサイズ
        bmp = Bitmap.createScaledBitmap(bmp, dp_w, dp_h , true);
        // 描画
        canvas.drawBitmap(bmp, 0, 0, null);
    }

}
