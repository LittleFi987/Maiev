import com.ych.agent.core.logging.api.ILog;
import com.ych.agent.core.logging.api.LogManager;

import java.util.regex.Matcher;

/**
 * Created by chenhao.ye on 10/02/2018.
 */
public class LogTest {

    private static final ILog logger = LogManager.getLogger(LogTest.class);

    public static void main(String[] args) {

        logger.info("aaaa");

//        String log = "index demo: {} , dd: {}??";
//        System.out.println(log("index demo: {} , dd: {}??", "i am demo", "dd"));
//        System.out.println(replaceParam("index demo: {} , dd: {}??", "i am demo", "dd"));
//        System.out.println("sfdfs".indexOf("{}"));
    }

    private static String log(String message, Object... paraments) {
        StringBuilder stringBuilder = null;
        int paramentIndex = 0;
        while (message.indexOf("{}") > 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(message.substring(0, message.indexOf("{}")));
            stringBuilder.append(paraments[paramentIndex]);
            stringBuilder.append(message.substring(message.indexOf("{}") + 2, message.length()));
            message = stringBuilder.toString();
            paramentIndex ++;
        }
        return message;
    }
    private static String replaceParam(String message, Object... parameters) {
        int startSize = 0;
        int parametersIndex = 0;
        int index;
        String tmpMessage = message;
        while ((index = message.indexOf("{}", startSize)) != -1) {
            if (parametersIndex >= parameters.length) {
                break;
            }
            tmpMessage = tmpMessage.replaceFirst("\\{\\}", Matcher.quoteReplacement(String.valueOf(parameters[parametersIndex++])));
            startSize = index + 2;
        }
        return tmpMessage;
    }

}
