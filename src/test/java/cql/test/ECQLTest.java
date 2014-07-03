package cql.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
			FilterToSQLException, UnsupportedEncodingException {
		// TODO Auto-generated method stub

		String filterStr = "'59288' = station_id";

		MyFilterToSQL vistor = new MyFilterToSQL();

		PostgresqlFilterToSQL v = new PostgresqlFilterToSQL();

		FilterFactory ff = CommonFactoryFinder.getFilterFactory();

		Filter filter = ECQL.toFilter(filterStr, ff);

		String sql = v.encodeToString(filter);

		System.out.println(sql);

		

	}

}
