package com.demo.oozie.base;

import org.apache.oozie.BundleJobBean;
import org.apache.oozie.CoordinatorJobBean;
import org.apache.oozie.WorkflowJobBean;
import org.apache.oozie.action.ActionExecutor;
import org.apache.oozie.command.wf.SubmitXCommand;
import org.apache.oozie.local.LocalOozie;
import org.apache.oozie.service.Service;
import org.apache.oozie.service.Services;
import org.apache.oozie.service.WorkflowAppService;
import org.apache.oozie.servlet.BaseJobServlet;
import org.apache.oozie.servlet.BaseJobsServlet;
import org.apache.oozie.store.Store;
import org.apache.oozie.workflow.WorkflowApp;
import org.apache.oozie.workflow.WorkflowInstance;
import org.apache.oozie.workflow.lite.DBLiteWorkflowLib;
import org.apache.oozie.workflow.lite.LiteWorkflowInstance;

public class Main {

	WorkflowAppService workflowAppService;
	SubmitXCommand submitXCommand;
	LiteWorkflowInstance liteWorkflowInstance;
	DBLiteWorkflowLib dbLiteWorkflowLib;
	Store store;
	Services services;
	Service service;
	WorkflowApp workflowApp;
	WorkflowInstance workflowInstance;
	ActionExecutor actionExecutor;
	LocalOozie localOozie;
	BaseJobServlet baseJobServlet;
	BaseJobsServlet baseJobsServlet;
	
	CoordinatorJobBean coordinatorJobBean;
	WorkflowJobBean workflowJobBean;
	BundleJobBean bundleJobBean;
	
}
