package disruptor.test;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import disruptor.test.producer.StringEventProducer;

/**
 * Created by chenhao.ye on 11/02/2018.
 */
public class DisruptorDemo {


    public static void main(String[] args) throws Exception {
        Disruptor<MessageHolder> disruptor = new Disruptor<MessageHolder>(new EventFactory<MessageHolder>() {
            @Override
            public MessageHolder newInstance() {
                return new MessageHolder();
            }
        }, 1024, DaemonThreadFactory.INSTANCE);
        disruptor.handleEventsWith(new MessageHandler());
        disruptor.start();


        // 数据装入ringBuffer中
        RingBuffer<MessageHolder> ringBuffer = disruptor.getRingBuffer();

        StringEventProducer eventProducer = new StringEventProducer(ringBuffer);

        for (int i = 0; i < 1000; i++) {
            eventProducer.producerData("producer: " + i);
        }

        Thread.sleep(10000000);
        disruptor.shutdown();

    }
}
