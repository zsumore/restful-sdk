package org.restsql.core.impl.postgresql;

import org.restsql.core.DBDialect;
import org.restsql.core.SqlResourceMetaData;
import org.restsql.core.SqlVisitor;

public class PostgresqlDBDialect implements DBDialect {

	private SqlResourceMetaData sqlResourceMetaData;

	private SqlVisitor sqlVisitor;

	public PostgresqlDBDialect() {

		this.sqlResourceMetaData = new PostgreSqlSqlResourceMetaData();

		this.sqlVisitor = new PostgresqlFilterToSQL();

	}

	@Override
	public SqlResourceMetaData getSqlResourceMetaData() {

		return this.sqlResourceMetaData;
	}

	@Override
	public SqlVisitor getCustomVisitor() {

		return this.sqlVisitor;
	}

}
