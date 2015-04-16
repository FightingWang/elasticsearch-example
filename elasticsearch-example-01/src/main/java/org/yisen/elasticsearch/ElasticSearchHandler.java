package org.yisen.elasticsearch;

import java.util.List;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

public class ElasticSearchHandler {
	public Client client;

	public ElasticSearchHandler() {
		// 集群连接超时设置 ：将节点的名称（cluster.name）设为yisen
		Settings settings = //
				ImmutableSettings.settingsBuilder().put("cluster.name", "yisen").build();
		client = new TransportClient(settings).addTransportAddress(//
				  new InetSocketTransportAddress("localhost", 9300));
	}

	/**
	 * 建立索引
	 * 
	 * @param indexName
	 *            为索引库名，一个es集群中可以有多个索引库。 名称必须为小写
	 * @param indexType
	 *            Type为索引类型，是用来区分同索引库下不同类型的数据的， 一个索引库下可以有多个索引类型。
	 * @param jsondata
	 *            json格式的数据集合
	 * 
	 * @return
	 */
	public void createIndexResponse(String indexname, String type,//
			List<String> jsondata) {
		// 创建索引库 需要注意的是.setRefresh(true)这里一定要设置,否则第一次建立索引查找不到数据
		IndexRequestBuilder requestBuilder = client.prepareIndex(indexname, type).setRefresh(true);
		for (int i = 0; i < jsondata.size(); i++) {
			requestBuilder.setSource(jsondata.get(i)).execute().actionGet();
		}

	}

	/**
	 * 执行搜索
	 * 
	 * @param queryBuilder
	 * @param indexname
	 * @param type
	 * @return
	 */
	public void searcher(QueryBuilder queryBuilder, String indexname,
			String type) {
		SearchResponse searchResponse = client.prepareSearch(indexname)
				.setTypes(type).setQuery(queryBuilder).execute().actionGet();
		SearchHits hits = searchResponse.getHits();
		System.out.println("查询到记录数=" + hits.getTotalHits());
		SearchHit[] searchHists = hits.getHits();
		if (searchHists.length > 0) {
			for (SearchHit hit : searchHists) {
				Integer id = (Integer) hit.getSource().get("id");
				String name = (String) hit.getSource().get("name");
				String gender = (String) hit.getSource().get("gender");
				String introduction = (String) hit.getSource().get("introduction");
				System.out.println("id:"+id+", name:"+name+", gender:"+gender
						+", introduction:"+introduction);
			}
		}
	}

}
