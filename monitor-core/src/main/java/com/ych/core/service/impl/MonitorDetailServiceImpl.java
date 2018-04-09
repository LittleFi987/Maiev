package com.ych.core.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ych.core.common.Pagination;
import com.ych.core.mapper.MonitorDetailMapper;
import com.ych.core.model.MonitorDetail;
import com.ych.core.model.MonitorDetailExample;
import com.ych.core.service.MonitorDetailService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by chenhao.ye on 18/03/2018.
 */
@Service
public class MonitorDetailServiceImpl implements MonitorDetailService {

    @Resource
    private MonitorDetailMapper monitorDetailMapper;


    @Override
    public void create(MonitorDetail monitorDetail) {
        monitorDetail.setCreateTime(new Date());
        monitorDetail.setUpdateTime(new Date());
        monitorDetailMapper.insertSelective(monitorDetail);
    }

    @Override
    public void update(MonitorDetail monitorDetail) {
        monitorDetail.setUpdateTime(new Date());
        monitorDetailMapper.updateByPrimaryKeySelective(monitorDetail);
    }

    @Override
    public MonitorDetail getByItemId(Integer itemId) {
        MonitorDetailExample example = new MonitorDetailExample();
        example.createCriteria().andMonitorItemIdEqualTo(itemId);
        List<MonitorDetail> monitorDetails = monitorDetailMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(monitorDetails)) {
            return null;
        }
        return monitorDetails.get(0);
    }

    @Override
    public Pagination<MonitorDetail> paging(Integer page, Integer size) {
        MonitorDetailExample example = new MonitorDetailExample();
        Page<MonitorDetail> detailPage = PageHelper.startPage(page, size).doSelectPage(() -> {
            monitorDetailMapper.selectByExample(example);
        });
        Pagination pagination = new Pagination();
        pagination.setTotalCount(detailPage.getTotal());
        pagination.setList(detailPage.getResult());
        pagination.setPageSize(detailPage.getPageSize());
        pagination.setCurrentPage(page);
        return pagination;
    }

    @Override
    public List<MonitorDetail> sortByRequestTime() {
        MonitorDetailExample example = new MonitorDetailExample();
        example.setOrderByClause("request_time desc limit 0,10");
        return monitorDetailMapper.selectByExample(example);
    }
}
