package com.ych.monitor.servlet;

import com.ych.monitor.Monitor;
import com.ych.monitor.bean.MonitorBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 暴露给服务端的servlet接口
 *
 * Created by Stay on 2017/1/7  17:51.
 */
public class MonitorServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        Map<String,MonitorBean> map = Monitor.getMonitorData();
        PrintWriter out = response.getWriter();
        out.write(listToJsonStr(mapToList(map)));
        out.flush();
        out.close();
    }
    private List<MonitorBean> mapToList(Map<String,MonitorBean> map){
        List<MonitorBean> list = new ArrayList<>();
        Iterator<Map.Entry<String,MonitorBean>> iterator =  map.entrySet().iterator();
        while (iterator.hasNext()){
            list.add(iterator.next().getValue());
        }
        return list;
    }

    private String listToJsonStr(List<MonitorBean> monitorBeanList) {
        String jsonStr = "[";
        MonitorBean monitorBean = null;
        for (int i = 0; i < monitorBeanList.size(); i++) {
            monitorBean = monitorBeanList.get(i);
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("{");
            strBuilder.append("\"monitorName\":");
            strBuilder.append("\"" + monitorBean.getMonitorName() + "\",");
            strBuilder.append("\"host\":");
            strBuilder.append("\"" + monitorBean.getHost() + "\",");
            strBuilder.append("\"requestType\":");
            strBuilder.append("\"" + monitorBean.getRequestType() + "\",");
            strBuilder.append("\"date\":");
            strBuilder.append("" + monitorBean.getDate().getTime() + ",");
            strBuilder.append("\"maxRequestTime\":");
            strBuilder.append("" + monitorBean.getMaxRequestTime() + ",");
            strBuilder.append("\"requestCount\":");
            strBuilder.append("" + monitorBean.getRequestCount() + ",");
            strBuilder.append("\"totalRequestTime\":");
            strBuilder.append("" + monitorBean.getTotalRequestTime() + "");
            strBuilder.append("}");
            if (i == 0) {
                jsonStr += strBuilder.toString();
            } else {
                jsonStr += "," + strBuilder.toString();
            }
        }
        jsonStr = jsonStr + "]";
        return jsonStr;
    }


}
