package cn.whcm.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.whcm.bean.Root;
import cn.whcm.utils.JdbcUtils;

/**
 *	root �û��ĵ���
 * @author Īҫ��
 *
 */

public class Rootdao {

	//����root  ��������ж�root�Ƿ����
	public Root getRoot(Root r) throws Exception {
		Root root=null;
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql="select * from root where name=? and password=?";
		root = (Root)runner.query(sql, new BeanHandler<Root>(Root.class), new Object[] {r.getName(),r.getPassword()});
		System.out.println("Rootdao="+root);
		return root;
	}
}
