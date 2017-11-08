package com.tecsup.SpringMVC.dao.jdbc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tecsup.SpringMVC.dao.DepartmentDAO;
import com.tecsup.SpringMVC.exception.DAOException;
import com.tecsup.SpringMVC.exception.EmptyResultException;
import com.tecsup.SpringMVC.exception.LoginException;
import com.tecsup.SpringMVC.mapper.DepartmentMapper;
import com.tecsup.SpringMVC.mapper.EmployeeMapper;
import com.tecsup.SpringMVC.model.Department;
import com.tecsup.SpringMVC.model.Employee;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO{

	private static final Logger logger = LoggerFactory.getLogger(DepartmentDAOImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	//Busca depart

	@Override
	public Department findDepartment(int department_id) throws DAOException, EmptyResultException {

		String query = "SELECT department_id, name, description, city FROM departments WHERE department_id = ?";

		Object[] params = new Object[] { department_id };

		try {

			Department dpt = (Department) jdbcTemplate.queryForObject(query, params, new DepartmentMapper());
			//
			return dpt;

		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultException();
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}


	@Override
	public Department findDepartment(String name) throws DAOException, EmptyResultException {

		String query = "SELECT department_id, name, description, city FROM departments WHERE upper(name) like upper(?)";

		Object[] params = new Object[] { name };

		try {

			Department dpt = (Department) jdbcTemplate.queryForObject(query, params, new DepartmentMapper());
			//
			return dpt;

		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultException();
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void create(String name, String description, String city) throws DAOException {

		String query = "INSERT INTO departments (name, description, city)  VALUES ( ?,?,? )";

		Object[] params = new Object[] { name, description, city };

		Department dpt = null;
		
		try {
			// create
			jdbcTemplate.update(query, params);
			// search
			dpt = this.findDepartmentByName(name);

		} catch (EmptyResultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
		

	}

	@Override
	public void delete(String name) throws DAOException {

		String query = "DELETE FROM  departments WHERE name = ? ";

		Object[] params = new Object[] { name };

		try {
			jdbcTemplate.update(query, params);
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void update(String  name, String description, String city) throws DAOException {


		String query = "UPDATE departments SET description = ?, city =?,  WHERE name = ?";

		Object[] params = new Object[] { description, city, name };

		
		try {
			jdbcTemplate.update(query, params);
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}


	@Override
	public Department findDepartmentByName(String name) throws DAOException, EmptyResultException {

		String query = "SELECT department_id, name, description, city FROM departments WHERE name = ? ";

		Object[] params = new Object[] { name };

		try {

			Department department = jdbcTemplate.queryForObject(query, params, new DepartmentMapper());
			//
			return department;

		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultException();
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}
	
	@Override
	public List<Department> findAllDepartments() throws DAOException, EmptyResultException {

		String query = "SELECT department_id, name, description, city FROM departments ";

		try {

			List<Department> departments = jdbcTemplate.query(query, new DepartmentMapper());
			//
			return departments;

		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultException();
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<Department> findDepartmentsByName(String name) throws DAOException, EmptyResultException {

		String query = "SELECT department_id, name, description, city FROM departments WHERE upper(name) like upper(?) ";

		Object[] params = new Object[] { "%" + name + "%" };

		try {

			List<Department> departments = jdbcTemplate.query(query, params, new DepartmentMapper());
			//
			return departments;

		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultException();
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}
	
	@Override
	public List<Department> findDepartmentsByLastName(String name) throws DAOException, EmptyResultException {

		String query = "SELECT department_id, name, desc, city FROM departments WHERE upper(name) like upper(?) ";

		Object[] params = new Object[] { "%" + name};

		try {

			List<Department> departments = jdbcTemplate.query(query, params, new DepartmentMapper());
			//
			return departments;

		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultException();
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}
	
	public Department validate(String name, String desc) throws LoginException, DAOException {
		
		logger.info("validate(): login: " + name + ", clave: " + desc);
	
		if ("".equals(name) && "".equals(desc)) {
			throw new LoginException("name and desc incorrect");
		}
	
		String query = "SELECT name, desc, despartment_id, city  "
				+ " FROM departments WHERE name=? AND desc=?";
	
		Object[] params = new Object[] { name, desc };
	
		try {
	
			Department dtp = (Department) jdbcTemplate.queryForObject(query, params, new DepartmentMapper());
			//
			return dtp;
	
		} catch (EmptyResultDataAccessException e) {
			logger.info("Employee y/o clave incorrecto");
			throw new LoginException();
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}
	
}
