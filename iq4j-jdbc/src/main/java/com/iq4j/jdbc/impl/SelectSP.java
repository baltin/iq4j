package com.iq4j.jdbc.impl;

import java.util.List;

import javax.sql.DataSource;
 
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.iq4j.jdbc.api.SPQuery;

public class SelectSP<T> extends SPQuery {

	ResultSetHandler<List<T>> rsh;
	
	public SelectSP(DataSource dataSource, String storedProcedure, Class<T> resultBeanClass) {
		super(dataSource, storedProcedure, (Object[]) null);
	}

	public SelectSP(DataSource dataSource, String storedProcedure, Class<T> resultBeanClass, Object... params) {
		super(dataSource, storedProcedure, params);
		this.rsh = new BeanListHandler<T>(resultBeanClass);
	}

	@Override
	public List<T> run() {
		
		try {
			return getSQLRunner().query(getSql(), rsh, getParams());
		} catch (Exception e) {
			warn(e);
			return null;
		}
		
	}

}
