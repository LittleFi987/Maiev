package com.ych.alert

import com.ych.core.common.AlertCommon
import com.ych.core.handler.AlarmHandler
import com.ych.core.model.MonitorSummary
import org.springframework.stereotype.Component
import javax.annotation.Resource

/**
 * Created by chenhao.ye on 22/03/2018.
 */
@Component
class SummaryAlert : Alert {

    @Resource
    private lateinit var alarmHandler: AlarmHandler

    override fun isAlert(common: AlertCommon): Boolean {
        if (common !is MonitorSummary) {
            return false
        }
        return true
    }

    override fun triggerAlert(common: AlertCommon) {
        val summary = common as MonitorSummary
        alarmHandler.alarm(summary)
    }
}