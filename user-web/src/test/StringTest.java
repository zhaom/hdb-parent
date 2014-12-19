/**
 * Created by user on 2014/12/18.
 */
public class StringTest {

    public static void main(String[] args){
        String u ="hdb xxdddsfdf";
        String a = u.substring(0, u.indexOf(" "));
        System.out.println("["+a+"]");
        String b = u.substring(u.indexOf(" ")+1);
        System.out.println("["+b+"]");
    }
}
