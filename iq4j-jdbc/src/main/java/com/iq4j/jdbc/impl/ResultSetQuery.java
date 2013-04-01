package com.iq4j.jdbc.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.ResultSetHandler;

import com.iq4j.jdbc.api.SQLQuery;

public class ResultSetQuery extends SQLQuery {
 
	// ---------------------- fields ------------------------------
	private ResultSetHandler<ResultSet> rsh = new InnerResultSetHandler();
	

	// ---------------------- constructors ------------------------------
	public ResultSetQuery(DataSource dataSource, String sql) {
		super(dataSource, sql, (Object[]) null);
	}

	public ResultSetQuery(DataSource dataSource, String sql, Object... params) {
		super(dataSource, sql, params);
	}

	// ---------------------- Query implementation ------------------------------
	@Override
	public ResultSet run() {
		
		try {
			getSQLRunner().query(getSql(), rsh, getParams());
		} catch (Exception e) {
			warn(e);
		}
		
		return null;
	}

	// ---------------------- Inner ResultSetHandler Implementation ------------------------------	
	private class InnerResultSetHandler implements ResultSetHandler<ResultSet> {

		@Override
		public ResultSet handle(ResultSet rs) throws SQLException {
			return rs;
		}
		
	}
	
}
