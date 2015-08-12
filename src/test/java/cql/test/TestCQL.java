package cql.test;

import static org.junit.Assert.*;
import mq.restful.util.MyFilterToSQL;

import org.geotools.data.jdbc.FilterToSQLException;
import org.geotools.filter.IsEqualsToImpl;
import org.geotools.filter.LiteralExpressionImpl;
import org.geotools.filter.temporal.DuringImpl;
import org.geotools.filter.temporal.TEqualsImpl;
import org.geotools.filter.text.cql2.CQLException;
import org.geotools.filter.text.ecql.ECQL;
import org.geotools.filter.text.ecql.SqlECQL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.opengis.filter.Filter;
import org.restsql.core.DBDialect;
import org.restsql.core.impl.BaseFilterToSQL;
import org.restsql.core.impl.SqlUtils;
import org.restsql.core.impl.postgresql.PostgresqlDBDialect;

public class TestCQL {

	DBDialect dbDialect;

	@Before
	public void setUp() throws Exception {
		dbDialect = new PostgresqlDBDialect();
	}

	@After
	public void tearDown() throws Exception {
		dbDialect = null;
	}

	@Test
	public void test() throws CQLException, FilterToSQLException {

		for (int i = 0; i < 1000; i++) {
			String filterStr = "(datetime between '2015-02-11+23:55:00' and '2015-02-12+23:59:59') and in('59288')";

			// String filterStr = "datetime=2014-03-21T14:00:00Z";

			// String filterClause = filterStr.replaceAll("\\+", " ");

			// System.out.println(filterClause);
			Filter filter = SqlECQL.toFilter(filterStr, SqlUtils.ff);
			System.out.println(filter);

			/*
			 * System.out.println(filter.getClass());
			 * System.out.println(filter.toString());
			 * System.out.println(filter.getExpression1().getClass());
			 * 
			 * System.out.println(filter.getExpression1());
			 * 
			 * System.out.println(filter.getExpression2().getClass());
			 * 
			 * System.out.println(filter.getExpression2());
			 * 
			 * LiteralExpressionImpl l=(LiteralExpressionImpl)
			 * filter.getExpression2();
			 * 
			 * System.out.println(l.getType());
			 * System.out.println(l.getValue().toString());
			 */

			String filterClause = this.dbDialect.getCustomVisitor()
					.encodeToString(filter);

			System.out.println(filterClause);

			/*
			 * String b="station_id LIKE 'G22%'"; Filter filter2=
			 * ECQL.toFilter(b);
			 * 
			 * 
			 * 
			 * System.out.println(filter2.toString());
			 * 
			 * System.out.println(vistor.encodeToString(filter2));
			 * 
			 * String c="dateFormat('yyyy',datetime)"; Filter filter3=
			 * ECQL.toFilter(c); System.out.println(filter3.toString());
			 */

			// fail("Not yet implemented");
		}

	}

}
