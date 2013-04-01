package com.iq4j.jdbc.api;

import javax.sql.DataSource;

import com.iq4j.jdbc.impl.SPRunner;


public abstract class SPQuery extends AbstractQuery {

	private String sp;
	
	public SPQuery(DataSource dataSource, String storedProcedure, Object... params) {
		
		super(new SPRunner(dataSource), generateCallStatement(storedProcedure, params) , params);
		this.sp = storedProcedure;
	}

	private static String generateCallStatement(String sp, Object... params) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("{call ")
		  .append(sp)
		  .append("(");
		
		if(params != null) {
			
			for (int i = 0; i < params.length; i++) {
				if(i > 0) {
					sb.append(", ");
				}
				sb.append("?");
			}
			
		}
		
		sb.append(") }");
		
		return sb.toString();
	}
	
	@Override
	public SPRunner getSQLRunner() {
		return (SPRunner)super.getSQLRunner();
	}
	
	public String getStoredProcedure() {
		return sp;
	}

	
}
