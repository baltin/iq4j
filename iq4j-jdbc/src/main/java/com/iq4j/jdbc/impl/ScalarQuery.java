package com.iq4j.jdbc.impl;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.iq4j.jdbc.api.SQLQuery;

public class ScalarQuery<T> extends SQLQuery {
	
	public ScalarQuery(DataSource dataSource, String sql) {
		this(dataSource, sql, (Object[]) null);
	}
	
	public ScalarQuery(DataSource dataSource, String sql, Object... params) {
		super(dataSource, sql, params);
	}

	@Override
	public T run() {
		try {
			return getSQLRunner().query(getSql(), new ScalarHandler<T>(), getParams());			
		} catch (SQLException e) {
			warn(e);
			return null;
		}
	}

}
