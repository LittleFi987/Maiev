package com.ych.monitor.util;

import com.ych.monitor.constant.IdWorkerConstant;

/**
 * id生成器   基于twiter的snowflake
 *
 * @Author yechenhao
 * @Date 29/04/2018
 */
public class IdWorker {
    /**
     * 描述 : 机器ID( 0 - 31 )
     */
    private long workerId;

    /**
     * 描述 : 数据中心ID( 0 - 31 )
     */
    private long datacenterId;

    /**
     * 描述 : 序列号( 0 - 4095)
     */
    private long sequence = 0L;

    /**
     * 描述 : 上次生产id时间戳
     */
    private long lastTimestamp = -1L;

    /**
     * 描述 : 构造函数
     *
     * @param workerId     workerId
     * @param datacenterId datacenterId
     */
    public IdWorker(long workerId, long datacenterId) {
        // sanity check for workerId
        if (workerId > IdWorkerConstant.MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", IdWorkerConstant.MAX_WORKER_ID));
        }
        if (datacenterId > IdWorkerConstant.MAX_DATACENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", IdWorkerConstant.MAX_DATACENTER_ID));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 描述 : 下一个ID
     *
     * @return ID
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & IdWorkerConstant.SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - IdWorkerConstant.TWEPOCH) << IdWorkerConstant.TIMESTAMP_LEFT_SHIFT) | (datacenterId << IdWorkerConstant.DATACENTER_ID_SHIFT) | (workerId << IdWorkerConstant.WORKER_ID_SHIFT) | sequence;
    }

    /**
     * 描述 : 获得下一个毫秒数
     *
     * @param lastTimestampParam lastTimestampParam
     * @return 下一个毫秒数
     */
    protected long tilNextMillis(long lastTimestampParam) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestampParam) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 描述 : 获得当前时间毫秒数
     *
     * @return 当前时间毫秒数
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }


}
