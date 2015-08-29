package slj.kzcrew;

import android.widget.TextView;

import java.util.Random;


/**
 * Created by Tomi on 2015/07/26.
 */
public class RandomShow extends AbstractPeriodicTask {
    private TextView txtView;
    private float maxX;
    private float maxY;
    public RandomShow(long period, boolean isDaemon, TextView txtView,float maxX,float maxY){
        super(period,isDaemon);
        this.txtView = txtView;
        this.maxX = maxX;
        this.maxY = maxY;

    }

    @Override
    protected void invokersMethod() {
        Random random = new Random();
        float x = random.nextInt((int)maxX) - 100;
        float y = random.nextInt((int)maxY) - 100;
        txtView.setX(x);
        txtView.setTranslationY(y);
    }


}