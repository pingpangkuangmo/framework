package com.demo;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.collections.map.HashedMap;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.DataSourceResourceLoader;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.NamingManager;
import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lg
 *         Date: 4/10/17
 *         Time: 10:23 AM
 */
public class VelocityManager {

    public VelocityEngine velocityEngine;

    public VelocityManager() {
        try {
            // Create initial context
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.naming.java.javaURLContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES,
                    "org.apache.naming");
            InitialContext ic = new InitialContext();

            ic.createSubcontext("java:comp");
            ic.createSubcontext("java:comp/env");
            ic.createSubcontext("java:comp/env/jdbc");
            ic.bind("java:comp/env/jdbc/velocity", getDataSource());
        } catch (Exception e) {
            e.printStackTrace();
        }
        velocityEngine = new VelocityEngine();
        velocityEngine.init("/Users/lg/important/my/framework/velocity/src/main/resources/velocity.properties");
    }

    public DataSource getDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/test");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
        return druidDataSource;
    }

    public static void main(String[] args) throws Exception {
        VelocityManager manager = new VelocityManager();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(out, "UTF-8");

        Map<String, Object> params = new HashMap();
        Map<String, Object> newMessage = new HashedMap();
        newMessage.put("name", "lg");
        newMessage.put("email", "917312290@qq.com");
        newMessage.put("metadataLine", "lg metadataLine");
        newMessage.put("message", "hello email");
        params.put("newMessage", newMessage);
        VelocityContext velocityContext = new VelocityContext(params);
        manager.velocityEngine.mergeTemplate("id1", "UTF-8", velocityContext, writer);
        writer.flush();
        System.out.println(new String(out.toByteArray(), "UTF-8"));

    }

}
