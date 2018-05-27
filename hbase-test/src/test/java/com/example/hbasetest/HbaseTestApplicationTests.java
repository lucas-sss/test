package com.example.hbasetest;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NavigableMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HbaseTestApplicationTests {

	@Autowired
	private HbaseTemplate hbaseTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testHbaseClient() {

		Scan scan = new Scan();
//		scan.withStartRow(Bytes.toBytes("rowkeyone999999"));
//		scan.withStopRow(Bytes.toBytes("rowkeyone1"));
		scan.setStartRow(Bytes.toBytes("rowkeyone999999"));
		scan.setStopRow(Bytes.toBytes("rowkeyone"));
		scan.setReversed(true);
//		scan.setLimit(1);
		scan.setFilter(new PageFilter(1));

		List<String> strings = hbaseTemplate.find("key_list", scan, new RowMapper<String>() {

			@Override
			public String mapRow(Result result, int i) throws Exception {
				NavigableMap<byte[], byte[]> info = result.getFamilyMap(Bytes.toBytes("info"));
				if (info != null && info.size() > 0) {
					byte[] keys = info.get(Bytes.toBytes("key"));
					String s = Bytes.toString(keys);
					return s;
				}
				return null;
			}
		});

		if (strings != null && strings.size() > 0){

			for (int i = 0; i < strings.size(); i++) {
				System.out.println("第"+ i +"条查询结果：" + strings.get(i));
			}
			return;
		}
		System.out.println("无结果");
	}

}
