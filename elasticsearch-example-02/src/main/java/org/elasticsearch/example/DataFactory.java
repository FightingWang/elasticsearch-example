package org.elasticsearch.example;

import java.util.ArrayList;
import java.util.List;

public class DataFactory {
	
	public static DataFactory dataFactory = new DataFactory();

	private DataFactory() {
	}

	public DataFactory getInstance() {
		return dataFactory;
	}

	public static List<String> getInitJsonData() {
		List<String> list = new ArrayList<String>();
		String data1 = JsonUtil.obj2JsonData(//
				new Person(1,"老王","男","我是老王，我喜欢打篮球和乒乓球。"));
		String data2 = JsonUtil.obj2JsonData(//
				new Person(2,"小芳","女","我是小芳，我喜欢游泳和跳舞。"));
		String data3 = JsonUtil.obj2JsonData(//
				new Person(3,"小明","男","我是小明，我喜欢看书和画画。"));
		String data4 = JsonUtil.obj2JsonData(//
				new Person(4,"花花","女","我是花花，我喜欢跳舞、画画、吹箫。"));
		String data5 = JsonUtil.obj2JsonData(//
				new Person(5,"小强","男","我是小强，我喜欢挖墙脚和花花。"));
		list.add(data1);
		list.add(data2);
		list.add(data3);
		list.add(data4);
		list.add(data5);
		return list;
	}
}
