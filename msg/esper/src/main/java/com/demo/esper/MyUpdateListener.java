package com.demo.esper;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

/**
 * @author lg
 *         Date: 4/13/17
 *         Time: 3:22 PM
 */
public class MyUpdateListener implements UpdateListener{

    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        if(newEvents != null && newEvents.length > 0){
            EventBean bean = newEvents[0];
            System.out.println(bean.getEventType());
            System.out.println(bean.getUnderlying());
            System.out.println(bean.get(""));
        }
    }
}
