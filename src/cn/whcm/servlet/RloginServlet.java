package cn.whcm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.whcm.bean.Root;
import cn.whcm.dao.Rootdao;
import cn.whcm.utils.StringIsiEmpty;

/**
 * ����������ж�root�û���
 *  ������Ʒ����ɾ�Ĳ�
 * @author Īҫ��
 *
 */
public class RloginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ͳһ�����ַ�����
//		response.setHeader("text/html", "charset=uft-8");
		response.setContentType("text/html;charset=utf-8");
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
			request.getRequestDispatcher("/jsp/Rlogin.jsp").forward(request, response);
			return;
		}
		//��root���ݿ�����������
		//-----------------------------------
		Root root = new Root(login_jsp_user,login_jsp_password);
		Rootdao rootdao= new Rootdao();
		try {
			Root root_mysql = rootdao.getRoot(root);
			if(root_mysql!=null) {
				//����ɹ�
				//����ɹ� ��ס�˺�
				HttpSession session = request.getSession();
				session.setAttribute("root_mysql", root_mysql);
				response.sendRedirect("jsp/Rmain.jsp");
				return;
			}else {
				//����ʧ��
				System.out.println("����ʧ��");
				request.setAttribute("error", "�˻��������������");
				request.getRequestDispatcher("/jsp/Rlogin.jsp").forward(request, response);
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//-----------------------------------
		//��root���ݿ�����������
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
