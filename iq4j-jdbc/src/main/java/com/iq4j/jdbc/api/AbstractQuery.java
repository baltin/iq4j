package com.iq4j.jdbc.api;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;


import com.iq4j.jdbc.impl.QueryRunner;

/**
 * Wraps {@link QueryRunner} class.
 * 
 * @author Anatolian
 *
 */
public abstract class AbstractQuery implements Query {
	
	private SQLRunner sqlRunner;
	private String sql;
	private Object[] params;
	protected Logger logger = Logger.getLogger(this.getClass().getName());
	
	public AbstractQuery(SQLRunner sqlRunner, String sql, Object... params) {		
		this.sql = sql;
		this.params = params;
		this.sqlRunner = sqlRunner;
	}

	@Override
	public SQLRunner getSQLRunner() {
		return sqlRunner;
	}
	
	@Override
	public DataSource getDataSource() {
		return sqlRunner.getDataSource();
	}

	@Override
	public String getSql() {
		return sql;
	}

	@Override
	public Object[] getParams() {
		return params;
	}
	
	protected void warn(Exception exception){
		logger.log(Level.WARNING, exception.getMessage());
	}
	
	
}
