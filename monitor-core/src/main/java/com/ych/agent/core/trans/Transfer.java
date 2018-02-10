package com.ych.agent.core.trans;

import com.ych.agent.core.model.BaseData;
import com.ych.agent.core.model.BaseHeart;

/**
 * Created by chenhao.ye on 27/01/2018.
 */
public interface Transfer {
    void connect(String ip, String port);

    void sendData(BaseData baseData);

    void heartBeat(BaseHeart baseHeart);

    void shutdown();
}
