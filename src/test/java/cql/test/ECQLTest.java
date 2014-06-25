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

		String filterStr = "to_char(datetime,'HH')='10'";

		MyFilterToSQL vistor = new MyFilterToSQL();

		PostgresqlFilterToSQL v = new PostgresqlFilterToSQL();

		FilterFactory ff = CommonFactoryFinder.getFilterFactory();

		Filter filter = ECQL.toFilter(filterStr, ff);

		String sql = v.encodeToString(filter);

		System.out.println(sql);

		String filterStr2 = "bb = 100 and name like '南海%'";

		Filter filter2 = ECQL.toFilter(filterStr2, ff);

		String sql2 = v.encodeToString(filter2);

		System.out.println(sql2);
		
		String vString="åæµ·";
		
		System.out.println(URLDecoder.decode(URLDecoder.decode(vString, "UTF-8"), "UTF-8"));

	}

}
