package com.test.trace;

/**
 * @author chenhao.ych
 * @date 2018-06-09
 */
public class LocalServiceImpl implements LocalService {

    @Override
    public String hi(String name) {
        return "local service hi: " + name;
    }
}
