package com.tecsup.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tecsup.SpringMVC.model.Department;

public class DepartmentMapper implements RowMapper<Department>{

	public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
		Department dpt = new Department();
		dpt.setDepartmentId(rs.getInt("department_id"));
		dpt.setName(rs.getString("name"));
		dpt.setDescription(rs.getString("description"));
		dpt.setCity(rs.getString("city"));
		return dpt;
	}
}
