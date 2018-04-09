package com.ych.core.util;


import com.google.common.base.Strings;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;


/**
 * Created by chenhao.ye on 06/04/2018.
 */
public class TradeBatchNoGenerator {


    private TradeBatchNoGenerator() {
    }

    public static final DateTimeFormatter DFT_BATCH = DateTimeFormat.forPattern("yyyyMMddHHmmss");


    /**
     * 生成规则: 时间戳(14位)+ tradeId(18位, 不足由0填充)
     */
    public static String generateBatchNo(Date happenedAt, Long tradeId) {
        String prefix = DFT_BATCH.print(new DateTime(happenedAt));
        String suffix = tradeId.toString();
        Integer trackLength = suffix.length();
        String padding = Strings.repeat("0", 18 - trackLength);
        return prefix + padding + suffix;
    }

}
