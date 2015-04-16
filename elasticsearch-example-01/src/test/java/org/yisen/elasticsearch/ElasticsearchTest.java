package org.yisen.elasticsearch;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Before;
import org.junit.Test;


public class ElasticsearchTest {
	
	public ElasticSearchHandler esHandler;
	
	@Before
	public void  startUp() throws Exception {
		esHandler = new ElasticSearchHandler();
	}
	
	/**
	 *创建索引
	 *
	 */
	@Test
	public void testCreateIndex() throws Exception {
		esHandler.createIndexResponse("index_person", "type_person", DataFactory.getInitJsonData());
	}
	
	@Test
	public void testSearcher() throws Exception {
		QueryBuilder queryBuilder = QueryBuilders//
				.rangeQuery("id").from(1).to(6).includeLower(true).includeUpper(false);
		esHandler.searcher(queryBuilder, "index_person", "type_person");
	}
}
