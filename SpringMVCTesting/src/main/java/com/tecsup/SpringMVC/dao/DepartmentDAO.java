package com.tecsup.SpringMVC.dao;

import java.util.List;

import com.tecsup.SpringMVC.exception.DAOException;
import com.tecsup.SpringMVC.exception.EmptyResultException;
import com.tecsup.SpringMVC.exception.LoginException;
import com.tecsup.SpringMVC.model.Department;

public interface DepartmentDAO {

	Department findDepartment(int department_id) throws DAOException, EmptyResultException;
	
	Department findDepartment(String name) throws DAOException, EmptyResultException;

	void create(String name, String desc, String city) throws DAOException;

	void delete(String name) throws DAOException;

	void update(String name, String description, String city) throws DAOException;

	Department findDepartmentByName(String name) throws DAOException, EmptyResultException;

	List<Department> findAllDepartments() throws DAOException, EmptyResultException;

	List<Department> findDepartmentsByName(String name) throws DAOException, EmptyResultException;
	
	List<Department> findDepartmentsByLastName(String name) throws DAOException, EmptyResultException;


}
