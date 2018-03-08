package disruptor.test;

import com.lmax.disruptor.EventHandler;

/**
 * Created by chenhao.ye on 11/02/2018.
 */
public class MessageHandler implements EventHandler<MessageHolder> {
    @Override
    public void onEvent(MessageHolder event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(event.getMessage());
    }
}
