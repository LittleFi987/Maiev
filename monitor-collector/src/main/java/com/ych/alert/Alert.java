package com.ych.alert;

import com.ych.core.common.AlertCommon;

/**
 * Created by chenhao.ye on 27/03/2018.
 */
public interface Alert {
    Boolean isAlert(AlertCommon common);

    void triggerAlert(AlertCommon common);
}
