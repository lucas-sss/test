import java.util.Map;
import java.util.TreeMap;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/5/10 0010
 */
public class NewTest {

    public static void main(String[] args) {

        TreeMap<String, Object> map = new TreeMap<>();
        map.put("123:dfafaf", "xxx");
        map.put("125:fafdaf", "yyy");
        map.put("120:xb", "zzz");

        Map.Entry<String, Object> entry = map.pollFirstEntry();
        System.out.println(entry.getValue());
    }

}
