package com.iq4j.jdbc.impl;

import javax.sql.DataSource;

import com.iq4j.jdbc.api.SQLRunner;


public class QueryRunner extends org.apache.commons.dbutils.QueryRunner implements SQLRunner {

	public QueryRunner(DataSource ds) {
		super(ds);
	}
	
}
