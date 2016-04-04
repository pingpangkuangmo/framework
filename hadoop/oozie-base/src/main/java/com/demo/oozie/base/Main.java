package com.demo.oozie.base;

import org.apache.oozie.WorkflowActionBean;
import org.apache.oozie.cli.OozieCLI;
import org.apache.oozie.command.wf.ActionCheckXCommand;
import org.apache.oozie.BundleJobBean;
import org.apache.oozie.CoordinatorJobBean;
import org.apache.oozie.DagEngine;
import org.apache.oozie.WorkflowJobBean;
import org.apache.oozie.action.ActionExecutor;
import org.apache.oozie.command.wf.ActionStartXCommand;
import org.apache.oozie.command.wf.SignalXCommand;
import org.apache.oozie.command.wf.SubmitXCommand;
import org.apache.oozie.local.LocalOozie;
import org.apache.oozie.service.CallableQueueService;
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
	
	CoordinatorJobBean coordinatorJobBean;
	WorkflowJobBean workflowJobBean;
	BundleJobBean bundleJobBean;
	
	org.apache.oozie.cli.OozieCLI cli;
	ActionCheckXCommand actionCheckXCommand;
	BaseJobServlet baseJobServlet;
	BaseJobsServlet baseJobsServlet;
	
	WorkflowActionBean workflowActionBean;
	SignalXCommand signalXCommand;
	ActionStartXCommand actionStartXCommand;
	DagEngine dagEngine;
	CallableQueueService callableQueueService;
	
	OozieCLI oozieCLI;
	
}
