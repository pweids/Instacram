package com.instacram.dao;

import org.mybeans.dao.DAOException;

public interface InstacramDAO {
	
	public Object create(Object primaryKey) throws DAOException;
	public void delete(Object primaryKey) throws DAOException;
	public Object lookup(Object primaryKey) throws DAOException;
	
}
