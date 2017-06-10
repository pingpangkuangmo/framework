package com.demo;

import com.demo.esper.EsperManager;
import com.demo.event.DALEvent;
import com.demo.event.Header;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EsperManager esperManager = new EsperManager();
        esperManager.init();
        long count = 0;
        while(true){
            count++;
            DALEvent dalEvent = new DALEvent();
            dalEvent.setGroup("group_" + count%20);
            dalEvent.setSql("sql_" + count%30);
            dalEvent.setClientApp("client_app_" + count%40);
            dalEvent.setClientIp("client_ip_" + count%50);
            dalEvent.setErrorType("error_type_" + count%60);
            dalEvent.setErrorCode("error_code_" + count%70);
            dalEvent.setReqLen(count%80);
            dalEvent.setRespLen(count%90);
            dalEvent.setWait(count%100);
            dalEvent.setExec(count%110);
            dalEvent.setTable("table_" + count%30);
            dalEvent.setOperation("operation_" + count%45);
            dalEvent.setDuration(count%37);

            Header header = new Header();
            header.setEntry("endtry_" + count%28);
            header.setAppId("app_id_" + count%48);
            header.setHostIp("host_ip_" + count%69);
            header.setHostName("host_name_" + count%39);
            header.setId(count + "");
            header.setMsg("msg_" + count%82);
            header.setCluster("cluster_" + count%30);
            header.setEzone("ezone_" + count%43);
            header.setIdc("idc_" + count%29);
            header.setRequestId(count + "");
            header.setAddition("addition_" + count%13);
            header.setRpcLevel((int)count%20);

            dalEvent.setHeader(header);
            dalEvent.setId(count);

            Map<String, String> tags = new HashMap();
            tags.put("key_1", count%20 + "");
            tags.put("key_2", count%30 + "");
            tags.put("key_3", count%40 + "");

            dalEvent.setTags(tags);
            dalEvent.setType("type_" + count%37);
            dalEvent.setName("name_" + count%58);
            dalEvent.setStatus("status_" + count%20);
            dalEvent.setTimestamp(System.currentTimeMillis());
            dalEvent.setData("data_" + count%22);

            esperManager.sendEvent(dalEvent);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
