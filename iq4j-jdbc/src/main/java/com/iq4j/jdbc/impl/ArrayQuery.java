package com.iq4j.jdbc.impl;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.handlers.ArrayHandler;

import com.iq4j.jdbc.api.SQLQuery;

public class ArrayQuery extends SQLQuery {

	public ArrayQuery(DataSource dataSource, String sql) {
		this(dataSource, sql, (Object[]) null);
	}

	public ArrayQuery(DataSource dataSource, String sql, Object... params) {
		super(dataSource, sql, params);
	}
	
	@Override
	public Object[] run() {
		try {
			return getSQLRunner().query(getSql(), new ArrayHandler());
		} catch (SQLException e) {
			warn(e);
			return new Object[]{};
		}
	}
	
}
