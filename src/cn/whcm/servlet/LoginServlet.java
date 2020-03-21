package cn.whcm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.whcm.bean.User;
import cn.whcm.dao.Userdao;
import cn.whcm.utils.StringIsiEmpty;
/**
 * 		������������жϵ�
 * 		�������   �����ɹ�ҳ��
 * 		���ʧ��   �򷵻ص���ҳ��
 * 
 * @author Īҫ��
 *
 */
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ͳһ�����ַ�����
		response.setHeader("text/html", "charset=uft-8");
		request.setCharacterEncoding("utf-8");
		//��ȡҳ���˻�������
		String login_jsp_user=null;
		login_jsp_user=request.getParameter("user");
		String login_jsp_password=null;
		login_jsp_password=request.getParameter("password");
		request.setAttribute("login_jsp_user", login_jsp_user);
		request.setAttribute("login_jsp_password", login_jsp_password);
		//�ж��˻��������Ƿ�Ϊ��
		if(StringIsiEmpty.isEmpty(login_jsp_password)||StringIsiEmpty.isEmpty(login_jsp_user)) {
			System.out.println("�˻�������Ϊ��");
			request.setAttribute("error", "�˻�������Ϊ��");
			System.err.println(request.getAttribute("error"));
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
			System.out.println(request.getContextPath()+"/jsp/login.jsp");
			return;
		}
		
		System.out.println(login_jsp_user+"->>>"+login_jsp_password);
		//��ȡ���ݿ��˻�������
		Userdao userdao=new Userdao();
		//����ҳ���user��passwrod��
		
		User user=new User(login_jsp_user, login_jsp_password);
		String username=user.getUser();
		try {
			boolean addUserBoolean = userdao.checkUser(user);//�ж��Ƿ����
			//�Ƚ��Ƿ���ȷ
			if(addUserBoolean) {
				//��ȷ������ȷҳ��
				
				HttpSession session = request.getSession();
				User quan_user=userdao.getQueryUser(user);
				//----------------------------------------------����ɹ�����session
				//����һ��ȫ��Ϣ�û�
				session.setAttribute("quan_user",quan_user);				//�û�ȫ��Ϣ
				session.setAttribute("addUserBoolean", addUserBoolean);     //�Ƿ����
				session.setAttribute("user", user);			 			    //�����û�
				//----------------------------------------------����ɹ�����session
//				session.removeAttribute(name);
				System.out.println("����ɹ�");
				request.setAttribute("user_name_password", user);
				response.sendRedirect("jsp/Main.jsp");
				return;
			
			}else {
				//������ת������ҳ��
				System.out.println("����ʧ��");
				request.setAttribute("error", "�˻��������������");
				request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
