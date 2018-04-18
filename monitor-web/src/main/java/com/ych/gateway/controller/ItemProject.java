package com.ych.gateway.controller;

import com.ych.core.common.Pagination;
import com.ych.core.common.Response;
import com.ych.core.dto.MonitorItemDTO;
import com.ych.core.handler.ItemHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by chenhao.ye on 18/03/2018.
 */
@RestController
public class ItemProject {

    @Resource
    private ItemHandler itemHandler;


    @GetMapping("/api/item/page")
    public Response paging(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Pagination<MonitorItemDTO> paging = itemHandler.paging(page, size);
        return Response.success(paging);
    }

}
