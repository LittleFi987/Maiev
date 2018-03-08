package disruptor.test.producer;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import disruptor.test.MessageHolder;

/**
 * Created by chenhao.ye on 11/02/2018.
 */
public class MessageEventProducerWithTranslator {

    private final RingBuffer<MessageHolder> ringBuffer;

    public MessageEventProducerWithTranslator(RingBuffer<MessageHolder> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static EventTranslatorOneArg<MessageHolder, String> TRANSLATOR = new EventTranslatorOneArg<MessageHolder, String>() {
        @Override
        public void translateTo(MessageHolder event, long sequence, String arg0) {
            event.setMessage(arg0);
        }
    };

    public void producerData(String data) {
        ringBuffer.publishEvent(TRANSLATOR, data);
    }


}
