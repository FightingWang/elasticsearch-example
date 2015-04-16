package org.elasticsearch.example;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;

public class ElasticSearchUtilsTest {
	private static final String INDEX = "person_index";
	private static final String TYPE = "person_type";
	@Test
	public void testGetClient() throws Exception {
		ElasticSearchUtils.getClient();
	}
	
	@Test
	public void testCreateIndexNull() throws Exception {
		ElasticSearchUtils.createIndexNull(INDEX);
	}
	
	@Test
	public void testCreateMapping() throws Exception {
		ElasticSearchUtils.createMapping(INDEX, TYPE, ElasticSearchUtils.setMapping());
	}
	
	@Test
	public void testCreateIndex() throws Exception {
		List<String> docList = DataFactory.getInitJsonData();
		for (String doc : docList) {
			ElasticSearchUtils.getClient().prepareIndex(INDEX,TYPE).setSource(doc).execute().actionGet();
		}
	}
	
	@Test
	public void testDeleteIndexById() throws Exception {
		ElasticSearchUtils.getClient().prepareDelete(INDEX, TYPE, "Rf743fRoQBmMCs_8_HIvRg").execute().actionGet(); 
	}
	
	@Test
	public void testDeleteIndexByQuery() throws Exception {
		QueryBuilder query = QueryBuilders.inQuery("title", "query");  
		ElasticSearchUtils.getClient().prepareDeleteByQuery("productIndex").setQuery(query).execute().actionGet(); 
	}
	
}
