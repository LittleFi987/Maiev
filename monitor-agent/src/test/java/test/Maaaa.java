package test;

import com.google.common.base.Joiner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by chenhao.ye on 10/01/2018.
 */
public class Maaaa {

    public static void main(String[] args) {

        String prefix = "http://www.termins.io/";

        HashMap<String, Object> params = new HashMap<>();

        params.put("a1", "11");

        params.put("a2", "22");

        HashMap<String, String> bizParams = new HashMap<>();

        bizParams.put("aaa", "bbb");

        bizParams.put("bb", "cc");

        params.put("bizContent", bizParams);

        String join = Joiner.on('&').withKeyValueSeparator("=").join(params);


        System.out.println(join);

        StringBuilder bizBuilder = new StringBuilder();

        bizBuilder.append(join);

        bizBuilder.append("&biz_content={");
        Iterator<Map.Entry<String, String>> iterator = bizParams.entrySet().iterator();

        while (iterator.hasNext()) {
            String key = iterator.next().getKey();
            String value = bizParams.get(key);
            bizBuilder.append("\"" + key + "\"" + ":" + "\"" + value + "\",");
        }
        bizBuilder.append("}");

        System.out.println(prefix + bizBuilder.toString());
    }


}
