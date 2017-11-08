package com.tecsup.SpringMVC.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tecsup.SpringMVC.exception.DAOException;
import com.tecsup.SpringMVC.exception.LoginException;
import com.tecsup.SpringMVC.model.Employee;
import com.tecsup.SpringMVC.services.SecurityService;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private SecurityService securityService;
	
	//Que solo se llamado por un get
	@GetMapping("/login")
	//Se carga el modelo y la Vista
	public ModelAndView preLogin(){
		
		Employee emp = new Employee();
		
		//se coloca la variable "model"
		//Pero si se quiere mostrar la vista se llama entre el parentesis del modelandview("vista","model",value)
		//Pero para pruebas puedes poner el null
		//Luego de ello para las pruebas pones cuando llamas a EMployee
		ModelAndView model = new ModelAndView("login","command",emp);
		
		return model;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@PostMapping("/login")
	public ModelAndView login(@ModelAttribute("SpringWeb") Employee employee, ModelMap model) {

		
		logger.info("login()");
		logger.info(employee.toString());

		ModelAndView modelAndView = null;


		try {
			Employee emp = securityService.validate(employee.getLogin(), employee.getPassword());
			logger.info(emp.toString());
			modelAndView = new ModelAndView("redirect:/admin/menu", "command", emp);
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", "Usuario y/o clave incorrectos");
			modelAndView = new ModelAndView("login", "command", new Employee());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("login", "command", new Employee());
		}

		return modelAndView;
	}

	}
	
	

