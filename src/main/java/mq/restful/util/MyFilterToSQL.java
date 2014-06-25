package mq.restful.util;

import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.geotools.data.jdbc.FilterToSQL;
import org.geotools.filter.FilterCapabilities;
import org.geotools.util.Converters;
import org.opengis.filter.ExcludeFilter;
import org.opengis.filter.Id;
import org.opengis.filter.IncludeFilter;
import org.opengis.filter.PropertyIsBetween;
import org.opengis.filter.PropertyIsLike;
import org.opengis.filter.PropertyIsNull;
import org.opengis.filter.temporal.After;
import org.opengis.filter.temporal.Before;
import org.opengis.filter.temporal.Begins;
import org.opengis.filter.temporal.BegunBy;
import org.opengis.filter.temporal.During;
import org.opengis.filter.temporal.EndedBy;
import org.opengis.filter.temporal.Ends;
import org.opengis.filter.temporal.TContains;
import org.opengis.filter.temporal.TEquals;

@SuppressWarnings("deprecation")
public class MyFilterToSQL extends FilterToSQL {

	/**
	 * Default constructor
	 */
	public MyFilterToSQL() {
		super();

	}

	public MyFilterToSQL(Writer out) {
		super(out);
	}

	protected String dateFormatPattern = "yyyy-MM-dd HH:mm:ss";

	protected DateFormat dateFormat;

	public DateFormat getDateFormat() {
		if (null == dateFormat) {
			dateFormat = new SimpleDateFormat(dateFormatPattern);
		}
		return dateFormat;
	}

	@Override
	protected void writeLiteral(Object literal) throws IOException {
		// TODO Auto-generated method stub
		if (literal == null) {
			out.write("NULL");
		} else if (literal instanceof Number || literal instanceof Boolean) {
			out.write(String.valueOf(literal));
		} else if (literal instanceof Date) {
			String valueDate = getDateFormat().format(literal).replaceAll("'",
					"''");
			out.write("'" + valueDate + "'");
		}else {
			// we don't know the type...just convert back to a string
			String encoding = (String) Converters.convert(literal,
					String.class, null);
			if (encoding == null) {
				// could not convert back to string, use original l value
				encoding = literal.toString();
			}

			// sigle quotes must be escaped to have a valid sql string
			String escaped = encoding.replaceAll("'", "''");
			out.write("'" + escaped + "'");
		}
	}

	@Override
	protected FilterCapabilities createFilterCapabilities() {
		 FilterCapabilities capabilities = new FilterCapabilities();

	        capabilities.addAll(FilterCapabilities.LOGICAL_OPENGIS);
	        capabilities.addAll(FilterCapabilities.SIMPLE_COMPARISONS_OPENGIS);
	        capabilities.addType(PropertyIsNull.class);
	        capabilities.addType(PropertyIsBetween.class);
	        capabilities.addType(Id.class);
	        capabilities.addType(IncludeFilter.class);
	        capabilities.addType(ExcludeFilter.class);
	        capabilities.addType(PropertyIsLike.class);
	        
	        //temporal filters
	        capabilities.addType(After.class);
	        capabilities.addType(Before.class);
	        capabilities.addType(Begins.class);
	        capabilities.addType(BegunBy.class);
	        capabilities.addType(During.class);
	        capabilities.addType(Ends.class);
	        capabilities.addType(EndedBy.class);
	        capabilities.addType(TContains.class);
	        capabilities.addType(TEquals.class);

	        return capabilities;
	}
	
	

}
