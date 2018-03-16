package cpu;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhao.ye on 15/03/2018.
 */
public class TestException {

//    public static void main(String[] args) {
//        int i = 0;
//        String x = null;
//        while (i < 1000000) {
//            try {
//                x.toString();
//            } catch (Exception e) {
//                int length = e.getStackTrace().length;
//                System.out.println("length: " + length);
//                System.out.println("i: " + i);
//                e.printStackTrace();
//                if (length == 0) {
//                    return;
//                }
//            }
//            i ++;
//        }
//
//    }

    public static void main(String[] args) {
        List<String> strList = new ArrayList<>();
        strList.add("aaa");
        System.out.println(JSON.toJSONString(strList));
    }
}
