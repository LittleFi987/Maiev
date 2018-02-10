package costtime;


import java.lang.instrument.Instrumentation;

/**
 * Created by chenhao.ye on 25/01/2018.
 */
public class Agent {

    public static void premain(String args, Instrumentation inst) {
        inst.addTransformer(new CostClassFileTransformer());
    }
}
