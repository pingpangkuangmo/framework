package com.demo.oozie.base;

import org.apache.oozie.command.wf.SubmitXCommand;
import org.apache.oozie.service.WorkflowAppService;
import org.apache.oozie.store.Store;
import org.apache.oozie.workflow.lite.DBLiteWorkflowLib;
import org.apache.oozie.workflow.lite.LiteWorkflowInstance;

public class Main {

	WorkflowAppService workflowAppService;
	SubmitXCommand submitXCommand;
	LiteWorkflowInstance liteWorkflowInstance;
	DBLiteWorkflowLib dbLiteWorkflowLib;
	Store store;
	org.apache.oozie.cli.OozieCLI cli;
}
