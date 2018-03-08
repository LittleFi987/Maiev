import com.ych.agent.core.logging.api.ILog;
import com.ych.agent.core.logging.api.LogManager;
import com.ych.agent.core.util.AgentPackagePath;

import java.io.File;

/**
 * Created by chenhao.ye on 07/02/2018.
 */
public class FindPathTest {

//    public static final ILog log = LogManager.getLogger(FindPathTest.class);

    public static void main(String[] args) {
        // file:/Users/chenhaoye/IdeaProjects/monitor/monitor-core/target/classes/
//        File path = AgentPackagePath.getPath();
//        log.info("aaa");


        String classResourcePath = FindPathTest.class.getName().replaceAll("\\.", "/") + ".class";

        String resource = FindPathTest.class.getClassLoader().getResource(classResourcePath).toString();

        System.out.println(classResourcePath);
        System.out.println(resource);
    }

}
