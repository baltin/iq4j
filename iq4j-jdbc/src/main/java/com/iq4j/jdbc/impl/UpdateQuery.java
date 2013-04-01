package com.iq4j.jdbc.impl;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.iq4j.jdbc.api.SQLQuery;

public class UpdateQuery extends SQLQuery {

	public UpdateQuery(DataSource dataSource, String sql) {
		this(dataSource, sql, (Object[]) null);
	}
	
	public UpdateQuery(DataSource dataSource, String sql, Object... params) {
		super(dataSource, sql, params);
	}

	@Override
	public Integer run() {
		try {
			return Integer.valueOf(getSQLRunner().update(getSql(), getParams()));
		} catch (SQLException e) {
			warn(e);
			return Integer.valueOf(-1);
		}
	}

}
