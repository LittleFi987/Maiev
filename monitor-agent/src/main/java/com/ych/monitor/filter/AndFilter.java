package com.ych.monitor.filter;

/**
 * @author chenhao.ych
 * @date 2018-07-02
 */
public class AndFilter extends ClassFilter {

    private ClassFilter classFilter;

    private ClassFilter clazzFilter;

    public AndFilter(ClassFilter classFilter, ClassFilter clazzFilter) {
        this.classFilter = classFilter;
        this.clazzFilter = clazzFilter;
    }

    @Override
    public boolean interceptor(String str) {
        return classFilter.interceptor(str) && clazzFilter.interceptor(str);
    }
}
