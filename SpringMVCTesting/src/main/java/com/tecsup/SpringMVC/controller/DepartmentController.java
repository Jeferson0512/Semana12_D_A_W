package com.tecsup.SpringMVC.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tecsup.SpringMVC.exception.DAOException;
import com.tecsup.SpringMVC.model.Department;
import com.tecsup.SpringMVC.model.Employee;
import com.tecsup.SpringMVC.services.DepartmentService;

@Controller
public class DepartmentController {

	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/admin/dpto/list")
	public String list(@ModelAttribute("SpringWeb") Department department, ModelMap model){
		try{
			model.addAttribute("departments", departmentService.findAll());
		}catch (Exception e) {
			logger.info(e.getMessage());
			model.addAttribute("message", e.getMessage());
		}
		
		return "admin/dpto/list";
	}
	
	@GetMapping("/{department_id}")
	public ModelAndView home(@PathVariable int department_id, ModelMap model){
		
		ModelAndView modelAndView = null;
		Department dpt = new Department();
		try{
			dpt = departmentService.find(department_id);
			logger.info(dpt.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		modelAndView = new ModelAndView("home","command",dpt);
		
		return modelAndView;
	}
	
	@GetMapping("/admin/dpto/{action}/{department_id}")
	public ModelAndView form(@PathVariable String action, @PathVariable int department_id, ModelMap model){
		
		// action = {"editform","deleteform"}
		logger.info("action = " + action);
		ModelAndView modelAndView = null;
		
		try{
			Department dpt = departmentService.find(department_id);
			logger.info(dpt.toString());
			modelAndView = new ModelAndView("admin/dpto/" + action, "command", dpt);
		}catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("admin/dpto/" + action, "command", new Department());
		}
		
		return modelAndView;
	}
	
	@PostMapping("/admin/dpto/editsave")
	public ModelAndView editsave(@ModelAttribute("SpringWeb") Department dpt, ModelMap model) {
		
		logger.info("dpto = " + dpt);
		
		ModelAndView modelAndView = null;
		
		try{
			departmentService.update(dpt.getName(), dpt.getDescription(), dpt.getCity());
			
			modelAndView = new ModelAndView("redirect:/admin/dpto/list");
		}catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("redirect:/admin/dpto/list");
		}
		return modelAndView;
	}
	
	@PostMapping("/admin/dpto/delete")
	public ModelAndView delete(@ModelAttribute("SpringWeb") Department dpt, ModelMap model) {
		
		ModelAndView modelAndView = null;

		try {
			departmentService.delete(dpt.getName());
			modelAndView = new ModelAndView("redirect:/admin/dpto/list");
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("redirect:/admin/dpto/list");
		}

		return modelAndView;
	}
	
	@GetMapping("/admin/dpto/createform")
	public ModelAndView createform(){
		
		Department dpt = new Department();
		
		ModelAndView modelAndView = new ModelAndView("admin/dpto/createform", "command", dpt);
		
		return modelAndView;
	}
	
	@PostMapping("/admin/dpto/create")
	public ModelAndView create(@ModelAttribute("SpringWeb") Department dpt, ModelMap model) {

		
		ModelAndView modelAndView = null;
		
		try {
			departmentService.create(dpt.getName(), dpt.getDescription(), dpt.getCity());
			logger.info("new Departament Name = " + dpt.getName());	
			modelAndView = new ModelAndView("redirect:/admin/dpto/list");
		} catch (DAOException e) {
			logger.error(e.getMessage());
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("admin/dpto/createform","command", dpt);
		}

		return modelAndView;
	}
}
