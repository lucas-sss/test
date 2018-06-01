import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/5/10 0010
 */
public class NewTest {


    private static HbaseTemplate hbaseTemplate;


    public static void main(String[] args) {
        //创建scan对象，不再按照单一rowKey查找记录
        Scan scan = new Scan();
        scan.setStopRow("startRow".getBytes());
        scan.setStopRow("staopRow".getBytes());
        //进行反向扫描
        scan.setReversed(true);
        scan.addColumn("familyName".getBytes(), "qualifier".getBytes());
        scan.setMaxResultSize(1L);
        //有值表示查询多少条记录，没有默认最新
        scan.setMaxVersions();
        hbaseTemplate.find("tableName", scan, new RowMapper<Object>() {
            @Override
            public Object mapRow(Result result, int var2) throws Exception {

                List<Cell> columnCells = result.getColumnCells("familyName".getBytes(), "qualifier".getBytes());
                if (columnCells == null || columnCells.size() == 0){

                }
                byte[] valueArray = columnCells.get(0).getValueArray();



                return new Object();
            };

        });

        hbaseTemplate.get("tableName", "rowName", "familyName", "qualifier", new RowMapper<Object>() {

            @Override
            public Object mapRow(Result var1, int var2) throws Exception {

                return new Object();
            };

        });












        TreeMap<String, Object> map = new TreeMap<>();


        map.put("123:dfafaf", "xxx");

        map.put("125:fafdaf", "yyy");

        map.put("120:xb", "zzz");


        Map.Entry<String, Object> entry = map.pollFirstEntry();

        System.out.println(entry.getValue());





    }



}
