package com.example.demo.activity.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import com.google.common.collect.Lists;
//import com.jeeplus.modules.act.utils.ProcessDefCache;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.activity.common.AjaxJson;
import com.example.demo.activity.util.PageResult;
import com.example.demo.activity.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 流程定义相关Controller
 * @author Edwin
 * @version 2016-11-03
 */
@Service
@Transactional(readOnly = true)
public class ActProcessService {

	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;	
	@Autowired
	private HistoryService historyService;
	@Autowired
	private ActTaskService actTaskService;

	/**
	 * 流程定义列表
	 */
	public PageResult processList(int pageNum,int pageSize, String category) {
		PageResult r = new PageResult();
		PageHelper.startPage(pageNum, pageSize);
	    ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery()
	    		.latestVersion().orderByProcessDefinitionKey().asc();
	    
	    if (StringUtils.isNotEmpty(category)){
	    	processDefinitionQuery.processDefinitionCategory(category);
		}
		PageInfo<ProcessDefinition> pageInfo = new PageInfo<>(processDefinitionQuery.list());
	    
	    List<ProcessDefinition> processDefinitionList = pageInfo.getList();
	   List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	   r.setTotal(processDefinitionList.size());
	    for (ProcessDefinition processDefinition : processDefinitionList) {
	      String deploymentId = processDefinition.getDeploymentId();
	      Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
	      Map<String,Object> pMap = new HashMap<>();
	      pMap.put("id", processDefinition.getId());
	      pMap.put("category", processDefinition.getCategory());
	      pMap.put("key", processDefinition.getKey());
	      pMap.put("name", processDefinition.getName());
	      pMap.put("version","V:"+processDefinition.getVersion());
	      pMap.put("resourceName", processDefinition.getResourceName());
	      pMap.put("diagramResourceName", processDefinition.getDiagramResourceName());
	      pMap.put("deploymentId", processDefinition.getDeploymentId());
	      pMap.put("suspended", processDefinition.isSuspended());
			pMap.put("deploymentTime",deployment.getDeploymentTime());
			list.add(pMap);
	    }
	    r.setObject(list);
		return r;
	}

	/**
	 * 运行中的流程
	 */
	public PageResult runningList(int pageNum,int pageSize, String procInsId, String procDefKey) throws Exception{
		PageResult r = new PageResult();
		List<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
		PageHelper.startPage(pageNum, pageSize);
	    ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery().includeProcessVariables();

	    if (StringUtils.isNotBlank(procInsId)){
		    processInstanceQuery.processInstanceId(procInsId);
	    }
	    
	    if (StringUtils.isNotBlank(procDefKey)){
		    processInstanceQuery.processDefinitionKey(procDefKey);
	    }
	    
	 /*   page.setCount(processInstanceQuery.count());
        page.setTotal(processInstanceQuery.count());
		resultPage.setCount(processInstanceQuery.count());*/

		List<ProcessInstance> runningList = Lists.newArrayList();
	

		for (ProcessInstance p : runningList) {
			String state = actTaskService.queryProcessState(p.getId());
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("id", p.getId());
			map.put("processDefinitionName", p.getProcessDefinitionName());
			map.put("processInstanceId", p.getProcessInstanceId());
			map.put("processDefinitionId", p.getProcessDefinitionId());
			map.put("activityId", p.getActivityId());
			map.put("vars",p.getProcessVariables());
			map.put("state", state);
			result.add(map);
		}
		r.setTotal(runningList.size());
		r.setObject(result);
		return r;
	}
	

	/**
	 * 已结束的流程
	 */
	public PageResult historyList(int pageno,int pagesize, String procInsId, String procDefKey) {
		PageResult r = new PageResult();
		PageHelper.startPage(pageno,pagesize);
		HistoricProcessInstanceQuery query=historyService.createHistoricProcessInstanceQuery().includeProcessVariables().finished()
				.orderByProcessInstanceEndTime().desc();

		if (StringUtils.isNotBlank(procInsId)){
			query.processInstanceId(procInsId);
		}
		if (StringUtils.isNotBlank(procDefKey)){
			query.processDefinitionKey(procDefKey);
		}
		PageInfo<HistoricProcessInstance> pageInfo = new PageInfo<>(query.list());
		r.setTotal(pageInfo.getTotal());r.setObject(pageInfo.getList());
		return r;
	}

	/**
	 * 读取资源，通过部署ID
	 */
	public InputStream resourceRead(String procDefId, String proInsId, String resType) throws Exception {
		
		if (StringUtils.isBlank(procDefId)){
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(proInsId).singleResult();
			procDefId = processInstance.getProcessDefinitionId();
		}
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefId).singleResult();
		
		String resourceName = "";
		if (resType.equals("image")) {
			resourceName = processDefinition.getDiagramResourceName();
		} else if (resType.equals("xml")) {
			resourceName = processDefinition.getResourceName();
		}
		
		InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
		return resourceAsStream;
	}
	
	/**
	 * 部署流程 - 保存
	 * @param file
	 * @return
	 */
	@Transactional(readOnly = false)
	public AjaxJson deploy(String exportDir, String category, MultipartFile file) {

		AjaxJson j = new AjaxJson();
		String message = "";
		
		String fileName = file.getOriginalFilename();
		
		try {
			InputStream fileInputStream = file.getInputStream();
			Deployment deployment = null;
			String extension = FilenameUtils.getExtension(fileName);
			if (extension.equals("zip") || extension.equals("bar")) {
				ZipInputStream zip = new ZipInputStream(fileInputStream);
				deployment = repositoryService.createDeployment().addZipInputStream(zip).deploy();
			} else if (extension.equals("png")) {
				deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
			} else if (fileName.indexOf("bpmn20.xml") != -1) {
				deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
			} else if (extension.equals("bpmn")) { // bpmn扩展名特殊处理，转换为bpmn20.xml
				String baseName = FilenameUtils.getBaseName(fileName); 
				deployment = repositoryService.createDeployment().addInputStream(baseName + ".bpmn20.xml", fileInputStream).deploy();
			} else {
				j.setSuccess(false);
				j.setMsg("不支持的文件类型：" + extension);
				return j;
			}
			
			List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();

			// 设置流程分类
			for (ProcessDefinition processDefinition : list) {
//					ActUtils.exportDiagramToFile(repositoryService, processDefinition, exportDir);
				repositoryService.setProcessDefinitionCategory(processDefinition.getId(), category);
				message += "部署成功，流程ID=" + processDefinition.getId() + "<br/>";
			}
			
			if (list.size() == 0){
				j.setSuccess(false);
				j.setMsg( "部署失败，没有流程。");
				return j;
			}
			
		} catch (Exception e) {
			throw new ActivitiException("部署失败！", e);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 设置流程分类
	 */
	@Transactional(readOnly = false)
	public void updateCategory(String procDefId, String category) {
		repositoryService.setProcessDefinitionCategory(procDefId, category);
	}

	/**
	 * 挂起、激活流程实例
	 */
	@Transactional(readOnly = false)
	public String updateState(String state, String procDefId) {
		if (state.equals("active")) {
			repositoryService.activateProcessDefinitionById(procDefId, true, null);
			return "已激活ID为[" + procDefId + "]的流程定义。";
		} else if (state.equals("suspend")) {
			repositoryService.suspendProcessDefinitionById(procDefId, true, null);
			return "已挂起ID为[" + procDefId + "]的流程定义。";
		}
		return "无操作";
	}
	
	/**
	 * 将部署的流程转换为模型
	 * @param procDefId
	 * @throws UnsupportedEncodingException
	 * @throws XMLStreamException
	 */
	@Transactional(readOnly = false)
	public org.activiti.engine.repository.Model convertToModel(String procDefId) throws UnsupportedEncodingException, XMLStreamException {
		
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefId).singleResult();
		InputStream bpmnStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
		processDefinition.getResourceName());
		XMLInputFactory xif = XMLInputFactory.newInstance();
		InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
		XMLStreamReader xtr = xif.createXMLStreamReader(in);
		BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);
	
		BpmnJsonConverter converter = new BpmnJsonConverter();
		ObjectNode modelNode = converter.convertToJson(bpmnModel);
		org.activiti.engine.repository.Model modelData = repositoryService.newModel();
		modelData.setKey(processDefinition.getKey());
		modelData.setName(processDefinition.getResourceName());
		modelData.setCategory(processDefinition.getCategory());//.getDeploymentId());
		modelData.setDeploymentId(processDefinition.getDeploymentId());
		modelData.setVersion(Integer.parseInt(String.valueOf(repositoryService.createModelQuery().modelKey(modelData.getKey()).count()+1)));
	
		ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
		modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processDefinition.getName());
		modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, modelData.getVersion());
		modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, processDefinition.getDescription());
		modelData.setMetaInfo(modelObjectNode.toString());
	
		repositoryService.saveModel(modelData);
	
		repositoryService.addModelEditorSource(modelData.getId(), modelNode.toString().getBytes("utf-8"));
	
		return modelData;
	}
	
	/**
	 * 导出图片文件到硬盘
	 */
	public List<String> exportDiagrams(String exportDir) throws IOException {
		List<String> files = new ArrayList<String>();
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
		
		for (ProcessDefinition processDefinition : list) {
			String diagramResourceName = processDefinition.getDiagramResourceName();
			String key = processDefinition.getKey();
			int version = processDefinition.getVersion();
			String diagramPath = "";

			InputStream resourceAsStream = repositoryService.getResourceAsStream(
					processDefinition.getDeploymentId(), diagramResourceName);
			byte[] b = new byte[resourceAsStream.available()];

			@SuppressWarnings("unused")
			int len = -1;
			resourceAsStream.read(b, 0, b.length);

			// create file if not exist
			String diagramDir = exportDir + "/" + key + "/" + version;
			File diagramDirFile = new File(diagramDir);
			if (!diagramDirFile.exists()) {
				diagramDirFile.mkdirs();
			}
			diagramPath = diagramDir + "/" + diagramResourceName;
			File file = new File(diagramPath);

			// 文件存在退出
			if (file.exists()) {
				// 文件大小相同时直接返回否则重新创建文件(可能损坏)
			//	logger.debug("diagram exist, ignore... : {}", diagramPath);
				
				files.add(diagramPath);
			} else {
				file.createNewFile();
			//	logger.debug("export diagram to : {}", diagramPath);

				// wirte bytes to file
				FileUtils.writeByteArrayToFile(file, b, true);
				
				files.add(diagramPath);
			}
			
		}
		
		return files;
	}

	/**
	 * 删除部署的流程，级联删除流程实例
	 * @param deploymentId 流程部署ID
	 */
	@Transactional(readOnly = false)
	public void deleteDeployment(String deploymentId) {
		repositoryService.deleteDeployment(deploymentId, true);
	}
	
	/**
	 * 删除运行中的流程实例
	 * @param procInsId 流程实例ID
	 * @param deleteReason 删除原因，可为空
	 */
	@Transactional(readOnly = false)
	public void deleteProcIns(String procInsId, String deleteReason) {
		runtimeService.deleteProcessInstance(procInsId, deleteReason);
	}


	/**
	 * 删除历史流程
	 */
	@Transactional(readOnly = false)
	public void delHistoryProcInsById(String procInsId) {
		historyService.deleteHistoricProcessInstance(procInsId);
	}

	/**
	 * 获取流程定义
	 */
	public ProcessDefinition getProcessDefinition(String proDefId) {
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery()
				.latestVersion().orderByProcessDefinitionKey().asc();
		if (StringUtils.isNotBlank(proDefId)){
			processDefinitionQuery.processDefinitionId(proDefId);
		}
//		if (StringUtils.isNotBlank(procDefKey)){
//			processInstanceQuery.processDefinitionKey(procDefKey);
//		}
		ProcessDefinition p = processDefinitionQuery.singleResult();

//		act.setProcDefKey(procDefKey);
		return p;
	}




}
