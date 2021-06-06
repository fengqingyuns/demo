package com.example.demo.activity.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.activity.common.AjaxJson;
import com.example.demo.activity.service.ActModelService;
import com.example.demo.shiro.controller.AbstractController;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author 李涛
 *
 */
@Controller
@RequestMapping(value = "/act/model")
public class ActModelController extends AbstractController {

	@Autowired
	private ActModelService actModelService;

	/**
	 * 流程模型列表
	 */
	@ResponseBody
	@RequestMapping(value = "data")
	public Map<String, Object> data(int pageno,int pagesize,String category) {
		PageInfo<org.activiti.engine.repository.Model> page = actModelService.modelList(
				pageno,pagesize,category);
		Map<String,Object> map = new HashMap<>();
		map.put("rows", page.getList());map.put("size", page.getSize());
		System.out.println(page.getList());
		System.out.println(page.getSize());
		return map;
	}


	/**
	 * 流程模型列表
	 */
	@RequiresPermissions("act:model:list")
	@RequestMapping(value = { "list", "" })
	public String modelList(String category, HttpServletRequest request, HttpServletResponse response, Model model) {

		return "modules/bpm/model/modelList";
	}

	/**
	 * 创建模型
	 */
	@RequiresPermissions("act:model:create")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		return "modules/bpm/model/modelCreate";
	}
	
	/**
	 * 创建模型
	 */
	@ResponseBody
	@RequiresPermissions("act:model:create")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public AjaxJson create(String name, String key, String description, String category,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
			actModelService.create(name, key, description, category);
			j.setMsg("添加模型成功");

		} catch (Exception e) {
			j.setMsg("添加模型失败");
		//	logger.error("创建模型失败：", e);
		}
		return j;
	}

	/**
	 * 根据Model部署流程
	 */
	@ResponseBody
	@RequiresPermissions("act:model:deploy")
	@RequestMapping(value = "deploy")
	public AjaxJson deploy(String id) {
		AjaxJson j = new AjaxJson();
		String message = actModelService.deploy(id);
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 导出model的xml文件
	 */
	@RequiresPermissions("act:model:export")
	@RequestMapping(value = "export")
	public void export(String id, HttpServletResponse response) {
		actModelService.export(id, response);
	}

	/**
	 * 更新Model分类
	 */
	@ResponseBody
	@RequiresPermissions("act:model:edit")
	@RequestMapping(value = "updateCategory")
	public AjaxJson updateCategory(String id, String category) {
		AjaxJson j = new AjaxJson();
		actModelService.updateCategory(id, category);
		j.setMsg("设置成功，模块ID=" + id);
		return j;
	}
	
	/**
	 * 删除Model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("act:model:del")
	@RequestMapping(value = "delete")
	public AjaxJson delete(String id) {
		AjaxJson j = new AjaxJson();
		actModelService.delete(id);
		j.setMsg("删除成功，模型ID=" + id);
		return j;
	}
	
	/**
	 * 删除Model
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("act:model:del")
	@RequestMapping(value = "deleteAll")
	public AjaxJson deleteAll(String ids) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			actModelService.delete(id);
		}
		AjaxJson j = new AjaxJson();
		j.setMsg("删除成功" );
		return j;
	}
}
