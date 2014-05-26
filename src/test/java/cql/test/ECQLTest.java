package cql.test;

import mq.restful.util.MyFilterToSQL;

import org.geotools.data.jdbc.FilterToSQLException;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.filter.text.cql2.CQLException;
import org.geotools.filter.text.ecql.ECQL;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory;
import org.restsql.core.impl.postgresql.PostgresqlFilterToSQL;

public class ECQLTest {

	public static void main(String[] args) throws CQLException,
			FilterToSQLException {
		// TODO Auto-generated method stub

		String filterStr = "to_char(datetime,'HH')='10'";

		MyFilterToSQL vistor = new MyFilterToSQL();

		PostgresqlFilterToSQL v = new PostgresqlFilterToSQL();

		FilterFactory ff = CommonFactoryFinder.getFilterFactory();

		Filter filter = ECQL.toFilter(filterStr, ff);

		String sql = v.encodeToString(filter);

		System.out.println(sql);

		String filterStr2 = "date_part('HH',datetime)=10";

		Filter filter2 = ECQL.toFilter(filterStr2, ff);

		String sql2 = v.encodeToString(filter2);

		System.out.println(sql2);

	}

}
