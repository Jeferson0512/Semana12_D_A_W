package com.tecsup.SpringMVC.services;

import java.util.List;

import com.tecsup.SpringMVC.exception.DAOException;
import com.tecsup.SpringMVC.exception.EmptyResultException;
import com.tecsup.SpringMVC.model.Department;

public interface DepartmentService {

	Department find(int department_id) throws DAOException, EmptyResultException;
	
	List<Department> findAll() throws DAOException, EmptyResultException;
	
	void update(String name, String description, String city) throws DAOException;
	
	void delete(String name) throws DAOException;
	
	void create(String name, String description,String city) throws DAOException;
		
}
