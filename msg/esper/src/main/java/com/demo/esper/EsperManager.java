package com.demo.esper;

import com.demo.event.DALEvent;
import com.espertech.esper.client.*;

/**
 * @author lg
 *         Date: 4/13/17
 *         Time: 3:14 PM
 */
public class EsperManager {

    private EPServiceProvider engine;
    private EPAdministrator epAdministrator;

    ConfigurationPlugInSingleRowFunction configurationPlugInSingleRowFunction;

    public EsperManager(){
        engine = EPServiceProviderManager.getDefaultProvider();
        epAdministrator = engine.getEPAdministrator();
    }

    public void init(){
        epAdministrator.getConfiguration().addAnnotationImport("com.demo.esper.Timer");
        epAdministrator.createEPL("create context context_name_1minute start @now end after 1minute");
        epAdministrator.getConfiguration().addEventType("dal", DALEvent.class);
        String epl = "@Timer(name=\"{group}.dal\",tags={\"status\",\"sql\",\"ezone\"},maxVal='max',sumVal='sum',countVal='count',msg='msg') context context_name_1minute select `group`,status,`sql`,header.ezone as ezone,timeMinutes as timestamp,max(duration) as max,sum(duration) as sum,count(1) as count,max_sampling(duration,header.msg) as msg from dal group by `group`,`sql`,status,header.ezone,timeMinutes output snapshot when terminated";
        EPStatement statement = epAdministrator.createEPL(epl);
        statement.addListener(new MyUpdateListener());
    }

    public void sendEvent(DALEvent dalEvent){
        engine.getEPRuntime().sendEvent(dalEvent);
    }


}
