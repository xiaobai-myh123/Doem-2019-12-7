package cn.whcm.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.catalina.valves.rewrite.RewriteValve;

import cn.whcm.bean.User;
import cn.whcm.dao.Userdao;
import cn.whcm.utils.StringIsiEmpty;

public class RegisteredServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("text/html", "charset=uft-8");
		request.setCharacterEncoding("utf-8");
		String uname = request.getParameter("uname");
		String upassword = request.getParameter("upassword");
		System.out.println(uname+upassword);
		//�жϴ�ҳ������������
		if(StringIsiEmpty.isEmpty(uname)||StringIsiEmpty.isEmpty(upassword)) {
			response.sendRedirect("jsp/registered.jsp");
			System.out.println("��ע��ҳ������������Ϊ��");
			return;
		}
		User user=new User(uname, upassword);
		Userdao uerdao = new Userdao();
		try {
			boolean addUser = uerdao.addUser(user);
			if(addUser) {
				//ע��ɹ�
				request.setAttribute("cg", "ע��ɹ�");
				System.out.println(user.getUser()+"--"+user.getPassword()+"ע��ɹ�");
				request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
			}else {
				//ע��ʧ��
				System.out.println(user.getUser()+"--"+user.getPassword()+"ע��ʧ��");
				response.setHeader("refresh", "0;");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
