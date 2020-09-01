package util;

import java.util.Arrays;

public class StringUtils {

    public static String capitalizeString(String s) {
        if(s == null) return null;
        if(s.length() == 1) return  s.toUpperCase();
        return s.substring(0,1).toUpperCase()+s.substring(1);
    }

    public static String replaceSequencesInString(String target,Pair<String,String> ...sequences) {
        for(Pair<String,String> pair: sequences) {
            target = target.replace(pair.getFirst(), pair.getSecond());
        }
        return target;
    }
}