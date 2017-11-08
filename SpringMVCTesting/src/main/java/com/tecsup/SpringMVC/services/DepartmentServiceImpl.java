package com.tecsup.SpringMVC.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsup.SpringMVC.dao.DepartmentDAO;
import com.tecsup.SpringMVC.dao.EmployeeDAO;
import com.tecsup.SpringMVC.exception.DAOException;
import com.tecsup.SpringMVC.exception.EmptyResultException;
import com.tecsup.SpringMVC.model.Department;
import com.tecsup.SpringMVC.model.Employee;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentDAO departmentDAO;

	@Override
	public Department find(int department_id) throws DAOException, EmptyResultException {
		
		Department dpt = departmentDAO.findDepartment(department_id);

		return dpt;
	}
	
	@Override
	public List<Department> findAll()
			throws DAOException, EmptyResultException {
		
		List<Department> dpts = departmentDAO.findAllDepartments();
	
		return dpts;
	}

	@Override
	public void update(String name, String description, String city)
			throws DAOException {
	
		departmentDAO.update(name, description, city);
	}

	@Override
	public void delete(String name) throws DAOException {
		 
		departmentDAO.delete(name);
	
	}

	@Override
	public void create(String name, String description, String city)
			throws DAOException {
	
		departmentDAO.create(name, description, city);

	}

}
