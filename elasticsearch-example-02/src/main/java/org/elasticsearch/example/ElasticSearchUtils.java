package org.elasticsearch.example;

import java.io.IOException;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

public class ElasticSearchUtils {

	public static Client getClient() {
		Settings settings = ImmutableSettings.settingsBuilder()
				.put("cluster.name", "yisen")//
				.build();
		Client client = new TransportClient(settings).addTransportAddress(//
				new InetSocketTransportAddress("localhost", 9300));
		return client;
	}
	
	public static void createIndexNull(String index){
		getClient().admin().indices().prepareCreate(index).execute().actionGet();
	}
	
	public static void createMapping(String index,String type,XContentBuilder mapping){
		PutMappingRequest mappingRequest = Requests.putMappingRequest(index).type(index).source(mapping);
		getClient().admin().indices().putMapping(mappingRequest);
	}
	
	public static XContentBuilder setMapping() throws IOException{
		XContentBuilder mapping = XContentFactory.jsonBuilder()
				.startObject()
	               .startObject("productindex")
	               .startObject("properties")       
	                 .startObject("title").field("type", "string").field("store", "yes").endObject()  
	                 .startObject("description").field("type", "string").field("index", "analyzer").endObject()
	                 .startObject("price").field("type", "double").endObject()
	                 .startObject("onSale").field("type", "boolean").endObject()
	                 .startObject("type").field("type", "integer").endObject()
	                 .startObject("createDate").field("type", "date").endObject()               
	               .endObject()
	              .endObject()
	            .endObject();
		return mapping;
	}
	
}
