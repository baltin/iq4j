package com.iq4j.jdbc.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.iq4j.jdbc.api.SQLQuery;

public class SelectQuery<T> extends SQLQuery {

	private ResultSetHandler<List<T>> resultSetHandler;
		
	public SelectQuery(DataSource dataSource,  Class<T> resultBeanClass, String sql) {	
		this(dataSource,  resultBeanClass, sql, (Object[]) null);
	}

	public SelectQuery(DataSource dataSource,  Class<T> resultBeanClass, String sql, Object... params) {	
		super(dataSource, sql, params);
		this.resultSetHandler = new BeanListHandler<T>(resultBeanClass);
	}
	
	public SelectQuery(DataSource dataSource, String sql, ResultSetHandler<List<T>> resultSetHandler) {
		this(dataSource, sql, resultSetHandler, (Object[]) null);
	}

	public SelectQuery(DataSource dataSource, String sql, ResultSetHandler<List<T>> resultSetHandler, Object... params) {
		super(dataSource, sql, params);
		this.resultSetHandler = resultSetHandler;
	}
	
	
	@Override
	public List<T> run() {
		try {
			return getSQLRunner().query(this.getSql(), this.resultSetHandler, getParams());
		} catch (SQLException e) {
			warn(e);
		}
		return new ArrayList<T>(0);
	}

}
