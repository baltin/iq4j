package com.iq4j.jdbc.api;

import javax.sql.DataSource;

import com.iq4j.jdbc.impl.QueryRunner;


public abstract class SQLQuery extends AbstractQuery {

	public SQLQuery(DataSource dataSource, String sql, Object[] params) {
		super(new QueryRunner(dataSource), sql, params);
	}

	@Override
	public QueryRunner getSQLRunner() {
		return (QueryRunner) super.getSQLRunner();
	}

}
