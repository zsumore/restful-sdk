package mq.restful.util;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class RestUtil {

	private static Map<String, String> contentTypeMap;

	private static Map<String, ObjectMapper> objectMapperMap;

	private static Map<String, ObjectMapper> prettyObjectMapperMap;

	public static String getContentType(String key, String charset) {
		if (null == contentTypeMap) {
			contentTypeMap = new HashMap<String, String>();
			contentTypeMap.put("json", "text/json;charset=" + charset.trim());
			contentTypeMap.put("xml", "text/xml;charset=" + charset.trim());
			contentTypeMap.put("csv", "text/csv;charset=" + charset.trim());
		}
		String ct = null;
		if (null != key) {
			ct = contentTypeMap.get(key.trim().toLowerCase());
		}
		if (null == ct) {
			ct = contentTypeMap.get("json");
		}

		return ct;
	}

	public static ObjectMapper getObjectMapper(String key) {
		if (null == objectMapperMap) {
			objectMapperMap = new HashMap<String, ObjectMapper>();

			final XmlMapper xmlMapper = new XmlMapper();
			final CsvMapper csvMapper = new CsvMapper();
			final ObjectMapper jsonMapper = new ObjectMapper();
			objectMapperMap.put("json", jsonMapper);
			objectMapperMap.put("csv", csvMapper);
			objectMapperMap.put("xml", xmlMapper);
		}
		ObjectMapper mapper = null;
		if (null != key) {
			mapper = objectMapperMap.get(key.trim().toLowerCase());
		}
		if (null == mapper)
			mapper = objectMapperMap.get("json");

		return mapper;
	}

	public static ObjectMapper getPrettyObjectMapper(String key) {

		if (null == prettyObjectMapperMap) {
			prettyObjectMapperMap = new HashMap<String, ObjectMapper>();

			final XmlMapper xmlMapper = new XmlMapper();
			xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
			final CsvMapper csvMapper = new CsvMapper();
			csvMapper.enable(SerializationFeature.INDENT_OUTPUT);
			final ObjectMapper jsonMapper = new ObjectMapper();
			jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
			prettyObjectMapperMap.put("json", jsonMapper);
			prettyObjectMapperMap.put("csv", csvMapper);
			prettyObjectMapperMap.put("xml", xmlMapper);
		}
		ObjectMapper mapper = null;
		if (null != key) {
			mapper = prettyObjectMapperMap.get(key.trim().toLowerCase());
		}
		if (null == mapper)
			mapper = prettyObjectMapperMap.get("json");

		return mapper;
	}

	public static Boolean stringNotNullOrEmpty(String s) {
		return (null != s && !s.trim().equals(""));
	}

}
