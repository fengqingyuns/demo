package com.example.demo.activity.controller;

import java.io.IOException;
import java.util.List;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
@RestController
@RequestMapping("/act")
public class ActivitiController {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
	private HistoryService historyService;
    @Autowired
	private RuntimeService runtimeService;
    /**
     *	部署流程实例
     */
    @RequestMapping("/start")
    public void TestStartProcess() {
        System.out.println("Start.........");
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> list = query.list();
        for (ProcessDefinition processDefinition : list) {
            System.out.println("流程启动成功，流程id:"+processDefinition);
        }
    }
    /**
     * 部署流程定义
     * @throws IOException
     */
    @RequestMapping("/cretDep")
    public void createDep( String id) throws IOException {
    	 // 获取模型
		Model model = repositoryService.getModel(id);
		System.err.println(model);
		ObjectNode objectNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(model.getId()));
		BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(objectNode);

		String processName = model.getName()+".bpmn20.xml";
		byte[] bytes = new BpmnXMLConverter().convertToXML(bpmnModel);
		// 部署流程
		Deployment deployment = repositoryService
				.createDeployment().name(model.getName())
				.addString(processName, new String(bytes,"UTF-8"))
				.deploy();
		
    }
    

	/**
	 * 根据实例编号查找下一个任务节点
	 * @param  procInstId ：实例编号
	 * @return
	 */
	public String nextTaskDefinition(String procInstId){
		//流程标示
		String processDefinitionId = historyService.createHistoricProcessInstanceQuery().processInstanceId(procInstId).singleResult().getProcessDefinitionId();

		ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(processDefinitionId);
		//执行实例
		ExecutionEntity execution = (ExecutionEntity) runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult();
		//当前实例的执行到哪个节点
		if(execution == null){
			TaskDefinition[] taskDefinitions = {};
			taskDefinitions = def.getTaskDefinitions().values().toArray(taskDefinitions);
			return taskDefinitions[taskDefinitions.length -1].getKey();
		}else{
			return  execution.getActivityId();
		}
	}
}
