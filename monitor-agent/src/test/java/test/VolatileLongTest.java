package test;

/**
 * Created by chenhao.ye on 09/02/2018.
 */
public class VolatileLongTest implements Runnable {

    private static VolatileLong[] longs = new VolatileLong[100000];

    public final static long ITERATIONS = 500L * 1000L * 1000L;

    private final int arrayIndex;

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    public VolatileLongTest(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(String[] args) throws Exception {
        final long start = System.nanoTime();
        runTest();
        System.out.println("duration = " + (System.nanoTime() - start));
    }

    private static void runTest() throws Exception {
        // 7397419807   7633794302  7067343239   7384375066
        // 7051949954   7942890636  7315494804   7352728690
        // 7714384568   8039448712  7590915198   7365057570
        // 7226110093   7017280060  7429169375   7599560953
        Thread[] threads = new Thread[4];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new VolatileLongTest(i));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
    }

    public static class VolatileLong {

        private long p1,p2,p3,p4,p5,p6,p7;  // comment out

        public volatile long value = 0L;

        private long p8,p9,p10,p11,p12,p13,p14;
    }

}
