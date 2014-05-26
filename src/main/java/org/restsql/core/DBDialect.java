package org.restsql.core;

public interface DBDialect {

	public SqlResourceMetaData getSqlResourceMetaData();

	public SqlVisitor getCustomVisitor();
}
