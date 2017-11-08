package com.tecsup.SpringMVC.services;

import com.tecsup.SpringMVC.exception.DAOException;
import com.tecsup.SpringMVC.exception.LoginException;
import com.tecsup.SpringMVC.model.Employee;

public interface SecurityService {

	Employee validate(String idEmployee, String clave) throws LoginException, DAOException;

}

