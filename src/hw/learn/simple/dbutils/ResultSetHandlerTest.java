package hw.learn.simple.dbutils;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Before;
import org.junit.Test;

/**
 * ArrayHandler：把结果集中的第一行数据转成对象数组。
 * ArrayListHandler：把结果集中的每一行数据都转成一个数组，再存放到List中。
 * BeanHandler：将结果集中的第一行数据封装到一个对应的JavaBean实例中。
 * BeanListHandler：将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。
 * ColumnListHandler：将结果集中某一列的数据存放到List中。
 * KeyedHandler(name)：将结果集中的每一行数据都封装到一个Map里，再把这些map再存到一个map里，其key为指定的key。
 * MapHandler：将结果集中的第一行数据封装到一个Map里，key是列名，value就是对应的值。
 * MapListHandler：将结果集中的每一行数据都封装到一个Map里，然后再存放到List
 */
public class ResultSetHandlerTest {
	private QueryRunner qr;

	@Before
	public void init() {
		qr = new QueryRunner(DBCPUtil.getDataSource());
	}

	@Test
	// ArrayHandler：把结果集中的第一行数据转成对象数组。
	// ArrayHandler：查询所有
	public void testArrayHandler() throws SQLException {
		String sql = "select * from user_dbutils";
		Object result[] = (Object[]) qr.query(sql, new ArrayHandler());
		System.out.println(Arrays.asList(result)); // list toString()
	}

	// ArrayListHandler：把结果集中的每一行数据都转成一个数组，再存放到List中。
	// ArrayListHandler：查询所有
	@Test
	public void testArrayListHandler() throws SQLException {
		String sql = "select * from user_dbutils";
		List<Object[]> list = (List) qr.query(sql, new ArrayListHandler());
		for (Object[] o : list) {
			System.out.println(Arrays.asList(o));
		}
	}

	// ColumnListHandler：将结果集中某一列的数据存放到List中。
	// 查询某一
	@Test
	public void testColumnListHandler() throws SQLException {
		String sql = "select * from user_dbutils";
		List list = (List) qr.query(sql, new ColumnListHandler("id"));
		System.out.println(list);
	}

	// KeyedHandler(name)：将结果集中的每一行数据都封装到一个Map里，再把这些map再存到一个map里，其key为指定的key。
	@Test
	public void testKeyedHandler() throws Exception {
		String sql = "select * from user_dbutils";
		Map<Integer, Map> map = (Map) qr.query(sql, new KeyedHandler("id"));
		for (Map.Entry<Integer, Map> me : map.entrySet()) {
			int id = me.getKey();
			Map<String, Object> innermap = me.getValue();
			for (Map.Entry<String, Object> innerme : innermap.entrySet()) {
				String columnName = innerme.getKey();
				Object value = innerme.getValue();
				System.out.println(columnName + "=" + value);
			}
			System.out.println("----------------");
		}
	}

	// MapHandler：将结果集中的第一行数据封装到一个Map里，key是列名，value就是对应的值。
	@Test
	public void testMapHandler() throws SQLException {
		String sql = "select * from user_dbutils";
		Map<String, Object> map = (Map) qr.query(sql, new MapHandler());
		for (Map.Entry<String, Object> me : map.entrySet()) {
			System.out.println(me.getKey() + "=" + me.getValue());
		}
	}

	// MapListHandler：将结果集中的每一行数据都封装到一个Map里，然后再存放到List
	@Test
	public void testMapListHandler() throws SQLException {
		String sql = "select * from user_dbutils";
		List<Map> list = (List) qr.query(sql, new MapListHandler());
		for (Map<String, Object> map : list) {
			for (Map.Entry<String, Object> me : map.entrySet()) {
				System.out.println(me.getKey() + "=" + me.getValue());
			}
		}
	}

	@Test
	public void testScalarHandler() throws SQLException {
		String sql = "select count(*) from user_dbutils"; // [13] list[13]
		int count = ((Long) qr.query(sql, new ScalarHandler(1))).intValue();
		System.out.println(count);
	}
}