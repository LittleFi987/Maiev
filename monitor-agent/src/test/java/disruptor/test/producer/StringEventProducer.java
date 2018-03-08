package disruptor.test.producer;

import com.lmax.disruptor.RingBuffer;
import disruptor.test.MessageHolder;

/**
 * 消息生产者
 * Created by chenhao.ye on 11/02/2018.
 */
public class StringEventProducer {

    private final RingBuffer<MessageHolder> ringBuffer;

    public StringEventProducer(RingBuffer<MessageHolder> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }


    public void producerData(String message) {
        // 获得槽的下标
        long next = ringBuffer.next();
        try {
            // 得到消息实体
            MessageHolder messageHolder = ringBuffer.get(next);
            messageHolder.setMessage(message);
        } finally {
            ringBuffer.publish(next);
        }

    }
}
