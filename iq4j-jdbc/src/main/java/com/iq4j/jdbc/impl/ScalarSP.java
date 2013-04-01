package com.iq4j.jdbc.impl;

import javax.sql.DataSource;

import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.iq4j.jdbc.api.SPQuery;

public class ScalarSP<T> extends SPQuery {

	public ScalarSP(DataSource dataSource, String storedProcedure, Object[] params) {
		super(dataSource, storedProcedure, params);
	}

	@Override
	public T run() {
		try {
			return getSQLRunner().query(getSql(), new ScalarHandler<T>());
		} catch (Exception e) {
			warn(e);
		}
		return null;
	}

}
