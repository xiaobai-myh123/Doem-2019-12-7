package test;

import java.sql.SQLException;
import java.util.List;

import cn.whcm.bean.User;
import cn.whcm.dao.Userdao;

/**
 * 					������ �������ݿ����ɾ���Ƿ��д�
 * @author Īҫ��
 *
 */
public class TestUserDao {
	public static void main(String[] args) throws Exception {
		Userdao userdao=new Userdao();
		//����add
//		User user= new User("b","b");
//		boolean addUser = userdao.addUser(user);
//		if(addUser) {
//			System.out.println("add yes");
//		}else {
//			System.out.println("add no");
//		}
		//--------------------------------------
		//���Բ�ѯ����
//		List findAll = userdao.findAll();
//		for (Object object : findAll) {
//			System.out.println(object);
//		}
		//--------------------------------------
		//���Բ�ѯһ��
//		User findById = userdao.findById(1);
//		System.out.println(findById);
		//--------------------------------------
		//�޸�
//		User user = new User("c","c");
//		boolean updateByUser = userdao.updateByUser(user,1);
//		System.out.println(updateByUser);
		
		boolean deleteById = userdao.deleteById(4);
		System.out.println(deleteById);
	}
}
