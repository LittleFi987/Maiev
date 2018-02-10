package test;

/**
 * Created by chenhao.ye on 15/11/2017.
 */
public class MonitorLoader {

    private volatile Boolean loadFlag = false;

    enum Instance {
        INSTANCE;

        private MonitorLoader monitorLoader;

        Instance() {
            monitorLoader = new MonitorLoader();
        }

        public MonitorLoader getInstance() {
            return monitorLoader;
        }
    }

    public static MonitorLoader getInstace() {
        return Instance.INSTANCE.getInstance();
    }

    public void loadComplete() {
        if (loadFlag == false) {
            loadFlag = true;
        }
    }

    public void outClassName(String className) {
        if (loadFlag) {
            System.out.println(className);
        }
    }

}
