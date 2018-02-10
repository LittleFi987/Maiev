package com.ych.monitor.component;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by chenhao.ye on 22/01/2018.
 */
@Slf4j
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
