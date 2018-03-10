package com.ych.monitor.component;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * Created by chenhao.ye on 22/01/2018.
 */
public abstract class Flow {

    private final String name;


    private final Table<Integer, Operation, Integer> operationFSM;

    public Flow(String name) {
        this.name = name;
        this.operationFSM = HashBasedTable.create();
        configure();
    }


    protected abstract void configure();

}
