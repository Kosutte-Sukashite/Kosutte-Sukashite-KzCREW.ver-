package slj.myapplication;

/**
 * Created by Tomi on 2015/07/15.
 */
public class TxtCreator {
    private String txt1 = "test1";
    private String txt2 = "test2";
    private String txt3 = "test3";
    private String txt4 = "test4";

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

        if (100>= cnt && cnt >=80){
            txt = txt4;
        }

        return  txt;
    }

}
