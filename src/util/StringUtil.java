package util;

public interface StringUtil {
    static boolean isPureDigit(String str){
        int len = str.length();
        for(int i = 0; i < len; i++){
            if(!Character.isDigit(str.charAt(i)))
                return false;
        }
        return true;
    }
}
