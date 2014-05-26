package org.restsql.core.impl.mysql;

import org.restsql.core.DBDialect;
import org.restsql.core.SqlResourceMetaData;
import org.restsql.core.SqlVisitor;

public class MysqlDBDialect implements DBDialect {

	private SqlResourceMetaData sqlResourceMetaData;

	private SqlVisitor sqlVisitor;

	public MysqlDBDialect() {

		this.sqlResourceMetaData = new MySqlSqlResourceMetaData();

		this.sqlVisitor = new MysqlFilterToSQL();

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
