package com.ych.monitor.filter;

/**
 * @author chenhao.ych
 * @date 2018-07-02
 */
public abstract class ClassFilter {

    /**
     * 过滤传入class
     *
     * @param str
     * @return
     */
    public abstract boolean interceptor(String str);

}
