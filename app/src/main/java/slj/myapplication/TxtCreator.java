package slj.myapplication;

/**
 * Created by Tomi on 2015/07/15.
 */
public class TxtCreator {
    private String txt1 = "エロさが\nたりないわ";
    private String txt2 = "見る気あるの";
    private String txt3 = "なかなかの\n手つきね！";
    private String txt4 = "す☆て☆き！";
    private String txt5= "ど変態♡";

    public String getTxt(long cnt){
        String txt="";

        if (30 > cnt && cnt >=0){
            txt = txt1;
        }

        if (50 > cnt && cnt >=30){
            txt = txt2;
        }

        if (80 > cnt && cnt >=50){
            txt = txt3;
        }

        if (100> cnt && cnt >=80){
            txt = txt4;
        }
        if(100 == cnt){
            txt = txt5;
        }

        return  txt;
    }

}
