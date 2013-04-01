package com.iq4j.jdbc.impl;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.iq4j.jdbc.api.SQLQuery;

public class BatchUpdateQuery extends SQLQuery {

	public BatchUpdateQuery(DataSource dataSource, String sql, Object[][] params) {
		super(dataSource, sql, (Object[])params);	
	}

	@Override
	public Object[][] getParams() {
		return (Object[][]) super.getParams();
	}	
	
	
	@Override
	public int[] run() {
		
		try {
			return getSQLRunner().batch(getSql(), getParams());
		} catch (SQLException e) {
			warn(e);
			return null;
		}
		
	}

}
