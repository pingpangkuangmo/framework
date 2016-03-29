package com.demo.oozie.base;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.OozieClientException;
import org.apache.oozie.client.WorkflowJob;
import org.apache.oozie.client.WorkflowJob.Status;

public class WorkflowClient {

	private static String OOZIE_URL = "http://sachidn002.hq.navteq.com:11000/oozie/";
    private static String JOB_PATH = "hdfs://sachicn001:8020/user/blublins/workflows/IPSIngestion";
    private static String JOB_Tracker = "sachicn003:2010";
    private static String NAMENode = "hdfs://sachicn001:8020";

    OozieClient wc = null;

    public WorkflowClient(String url){
        wc = new OozieClient(url);
    }

    public String startJob(String wfDefinition, List<WorkflowParameter> wfParameters)
        throws OozieClientException{

        // create a workflow job configuration and set the workflow application path
        Properties conf = wc.createConfiguration();
        conf.setProperty(OozieClient.APP_PATH, wfDefinition);

        // setting workflow parameters
        conf.setProperty("jobTracker", JOB_Tracker);
        conf.setProperty("nameNode", NAMENode);
        if((wfParameters != null) && (wfParameters.size() > 0)){
            for(WorkflowParameter parameter : wfParameters)
                conf.setProperty(parameter.getName(), parameter.getValue());
        }
        // submit and start the workflow job
        return wc.run(conf);
    }

    public Status getJobStatus(String jobID) throws OozieClientException{
        WorkflowJob job = wc.getJobInfo(jobID);
        return job.getStatus();
    }

    public static void main(String[] args) throws OozieClientException, InterruptedException{

        // Create client
        WorkflowClient client = new WorkflowClient(OOZIE_URL);
        // Create parameters
        List<WorkflowParameter> wfParameters = new LinkedList<WorkflowParameter>();
        WorkflowParameter drive = new WorkflowParameter("driveID","729-pp00004-2010-09-01-09-46");
        WorkflowParameter lidar = new WorkflowParameter("lidarChunk","4");
        WorkflowParameter signage = new WorkflowParameter("signageChunk","4");
        wfParameters.add(drive);
        wfParameters.add(lidar);
        wfParameters.add(signage);
        // Start Oozing
        String jobId = client.startJob(JOB_PATH, wfParameters);
        Status status = client.getJobStatus(jobId);
        if(status == Status.RUNNING)
             System.out.println("Workflow job running");
        else
             System.out.println("Problem starting Workflow job");
    }

}
