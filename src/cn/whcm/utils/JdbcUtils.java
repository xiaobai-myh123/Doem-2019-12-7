package cn.whcm.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/***
 * 		���ݿ⹤����
 * 		��ȡ���ݿ�    	 ����
 * 				           ����Դ����
 * 			 
 * @author Īҫ��
 *
 */
public class JdbcUtils {
	private static DataSource ds=null;
	
	public static DataSource getDataSourse() {
		ComboPooledDataSource cds = new ComboPooledDataSource();
		if(ds==null) {
			ds=cds;
		}
		return ds;
	}
	public static Connection getCon() {
		Connection con = null;
		if(ds==null) {
			ds=getDataSourse();
		}
		try {
			con=ds.getConnection();
			if(con==null) {
				System.out.println("���ݿ�����ʧ��");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
}
