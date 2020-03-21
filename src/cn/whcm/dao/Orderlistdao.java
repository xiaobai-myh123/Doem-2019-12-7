package cn.whcm.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.whcm.bean.Merchandise;
import cn.whcm.bean.Orderlist;
import cn.whcm.bean.User;
import cn.whcm.utils.JdbcUtils;

/***
 * 	
 * @author Īҫ��
 *	ʱ�䣺2019-12-8
 *	������ �����������ɾ�Ĳ�
 *	���ﳵ
 *	
 *	orderlist_tb              �Զ�������ɾ         
 *	
 *	���û�֧��Ǯ�����ﳵ�Ķ���ʱ   ��Ʒɾ�� 
 *	�µ�ʱ���� 
 *	���з���
 *	add ��del 
 */
public class Orderlistdao {
	
	//�¶���
	public boolean addBookById(User user,int id,String bname) throws Exception {
		//��ȡ�û���id   ˭����   ����һ����
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql = "insert into orderlist_tb(uid,uname,bid,bname) values(?,?,?,?)";
		int update = runner.update(sql, new Object[] {user.getId(),user.getUser(),id,bname});
		if(update>0) {
			System.out.println(user.getUser()+"�µ�"+update+"������");
			return true;
		}else {
			System.out.println("�µ�ʧ��");
		}
		return false;
	} 

	// ɾ������
	public boolean delBookById(User user, int id, String bname) throws Exception {
		// ��ȡ�û���id ˭���� ����һ����
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql = "delete from orderlist_tb where uid=? and uname=? and bid=? and bname=?";
		int update = runner.update(sql, new Object[] { user.getId(), user.getUser(), id, bname});
		if (update > 0) {
			System.out.println(user.getUser() + "ɾ��" + update + "������");
			return true;
		} else {
			System.out.println("ɾ��ʧ��");
		}
		return false;
	} 
	
	//��ѯ   ˭����ʲô����
	public List FindBookById(User user) throws Exception {
		// ��ȡ�û���id ˭���� ����һ����
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql="select * from orderlist_tb where uid=? and uname=? GROUP BY bid";
		System.err.println("FindBookById"+user);
		List<Orderlist> list = runner.query(sql,new BeanListHandler<Orderlist>(Orderlist.class),new Object[] {user.getId(),user.getUser()});
		if(list!=null) {
			System.out.println(list.size());
		}else {
			System.out.println("FindBookById��ѯ"+"ʧ��");
		}
		return list;
	} 
	
	
//	String sql = "select * from tb_book where name like ";
//	sql = sql + "\'%" + textFieldFindName.getText() + "%\'";
	
	
	
}
