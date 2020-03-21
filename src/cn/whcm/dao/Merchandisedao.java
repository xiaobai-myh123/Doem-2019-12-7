package cn.whcm.dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.whcm.bean.Merchandise;
import cn.whcm.bean.User;
import cn.whcm.utils.JdbcUtils;

/**
 * 
 * @author Īҫ��	
 * 			    Merchandise
 *				��Ʒ���	չʾ
 *						����
 *						ɾ��
 *						�޸�
 *	time��2019-12-9
 */

public class Merchandisedao {
	
	//����������Ʒ
	public List<Merchandise> showAllBook() throws Exception {
		//��ȡ�û���id   ˭����   ����һ����
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql="select * from merchandise_tb";
		ArrayList<Merchandise> list = (ArrayList<Merchandise>)runner.query(sql, new BeanListHandler<Merchandise>(Merchandise.class));
		System.out.println("��Ʒ�ĸ���Ϊ"+list.size());
		return list;
//		= runner.query(sql,new ScalarHandler("name"));
	} 
	
	//ɾ��ɾƷ
	public boolean delBookById(Integer id) throws SQLException {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql="delete from merchandise_tb where id=?";
		int update = runner.update(sql, new Object[] {id});
		if(update>0) {
			System.out.println("ɾ���ɹ�");
			return true;
		}
		System.out.println("ɾ��ʧ��");
		return false;
	}
	
	//������Ʒ
	public boolean delBookByIdName(Integer id,String bookname,Integer number) throws SQLException {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql ="update merchandise_tb set number=number-AND where id=? and bookname=?";
		sql=sql.replace("AND", number+"");
		int update = runner.update(sql,new Object[] {id,bookname});
		if(update>0) {
			System.out.println("ɾ���ɹ�");
			return true;
		}
		System.out.println("ɾ��ʧ��");
		return false;
	}
	
	//������Ʒ//insert into merchandise_tb(bookname,price,presentation,number) value("a",12.2,"����",12)
	public boolean addBook(Merchandise m) throws SQLException {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql = "insert into merchandise_tb(bookname,price,presentation,number) values(?,?,?,?)";
		int update = runner.update(sql,new Object[] {m.getBookname(),m.getPrice(),m.getPresentation(),m.getNumber()});
		if(update>0) {
			System.out.println("��ӳɹ�");
			return true;
		}
		System.out.println("���ʧ��");
		return false;
	}
	//����bid��bname��һ��ȫ����Ʒ��Ϣ    
	public Merchandise getMerchandiseByID(Integer bid,String bname) throws SQLException{
		Merchandise m = new Merchandise();
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql = "select * from merchandise_tb where id=? and bookname=?";
		m = (Merchandise)runner.query(sql, new BeanHandler<Merchandise>(Merchandise.class) , new Object[] {bid,bname});
		//Integer query = runner.query(sql, new ScalarHandler<Integer>());
		if(m==null) {
			System.out.println("Merchandise�����õ�������Ϊ��    �����ݴ����ݿ������Ĳ���������");
		}
		return m;
	}
	
	//�޸���Ʒ
	public boolean addMerchandise(Merchandise m) throws Exception {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql="update merchandise_tb set bookname=?,price=?,presentation=?,number=? where id=?";
		int update = runner.update(sql, new Object[] {m.getBookname(),m.getPrice(),m.getPresentation(),m.getNumber(),m.getId()});
		if(update>0) {
			System.out.println("addMerchandise��ӳɹ�");
			return true; 
		}
		System.out.println("addMerchandise���ʧ��");
		return false;
	}
	
	
}
