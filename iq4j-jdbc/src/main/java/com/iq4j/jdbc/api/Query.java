package com.iq4j.jdbc.api;

import javax.sql.DataSource;

public interface Query {
	
	public SQLRunner getSQLRunner();
	
	public DataSource getDataSource();
	public String getSql();
	public Object[] getParams();
	
	public Object run();
	
}
