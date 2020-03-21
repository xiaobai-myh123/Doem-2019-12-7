package cn.whcm.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import cn.whcm.bean.User;
import cn.whcm.utils.JdbcUtils;

/*
 * 
 * 	�û�dao   ���û��Ĳ���
 * 			//�����û�
 * 			//ɾ���û�
 * 			//��ѯ�����û�
 * 			//��ѯ����
 * 			//�޸��û�
 * 			//�ж��û��Ƿ����
 */
public class Userdao {
	
	//�����û�
	public boolean addUser(User user) throws SQLException {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql= "insert into user(user,password) values(?,?)";
		int update = runner.update(sql, new Object[] {user.getUser(),user.getPassword()});
		if(update>0) {
			return true;
		}
		return false;
	}
	//ɾ���û�
	public boolean deleteById(int id) throws SQLException {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql = "delete from user where id=?";
		int update = runner.update(sql, id);
		if(update>0) {
			return true;
		}
		return false;
	}
	//��ѯ�����û�
	public List findAll() throws Exception{
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql = "select * from user";
		ArrayList<User> list = (ArrayList<User>)runner.query(sql, new BeanListHandler<User>(User.class));
	
//		= runner.query(sql,new ScalarHandler("name"));
		return list;
	} 
	
	//��ѯ����
	public User findById(int id) throws Exception {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql = "select * from user where id=?";
		User user = null;
		user = runner.query(sql, new BeanHandler<User>(User.class), id);
		return user;
	}
	//�޸��û�
	public boolean updateByUser(User user,int id) throws Exception {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql = "update user set user=?,password=? where id=?";
		int update = runner.update(sql, new Object[] {user.getUser(),user.getPassword(),id});
		if(update>0) {															
			return true;
		}
		return false;
	}
	//�ж��û��Ƿ����
	public boolean checkUser(User user) throws Exception {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql = "select * from user where user=? and password = ?";
		User usertmp = null;
		usertmp = runner.query(sql, new BeanHandler<User>(User.class), new Object[] {user.getUser(),user.getPassword()});
//		System.out.println(usertmp);
		if(usertmp!=null) {
			return true;
		}
		return false;
	}
	//���û� ���ֺ�����  ��user
	public User getQueryUser(User user) throws Exception {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSourse());
		String sql = "select * from user where user=? and password = ?";
		User usertmp  = runner.query(sql, new BeanHandler<User>(User.class), new Object[] {user.getUser(),user.getPassword()});
//		System.out.println(usertmp);
		if(usertmp!=null) {
			System.out.println(usertmp);
			return usertmp;
		}
		System.out.println("ShoppingCartServlet---getkUser(User user)--��"+usertmp);
		return null;
	}
}
