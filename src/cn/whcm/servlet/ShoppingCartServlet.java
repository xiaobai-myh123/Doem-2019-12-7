package cn.whcm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.whcm.bean.User;
import cn.whcm.dao.Orderlistdao;


/**
 * 
 * @author Īҫ��
 *	���ﳵ��servelt   ��Ƶ�������        Orderlistdao_td
 */
public class ShoppingCartServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//����ַ�������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//�ж��û��Ƿ����  
		HttpSession session = request.getSession();
		//��ȡ�û���id������ �����ֺ���id
		User user = (User)session.getAttribute("quan_user");
		
		//���жϵ��û�true��false
		Boolean addUserBoolean=(Boolean)session.getAttribute("addUserBoolean");
		if(addUserBoolean!=null&&addUserBoolean!=false&&user!=null) {
			//1.����ɹ�
					/**
					 * ��ȡ���id ���������
					 */
			System.out.println("ShoppingCartServlet--->"+user);
			System.out.println("ShoppingCartServlet--->"+addUserBoolean);
			String bname=request.getParameter("bname");
			System.out.println("bname="+bname);
			String bid=request.getParameter("bid");
			System.out.println("bid="+bid);
			//��������   Orderlistdao_td
			Orderlistdao odao = new Orderlistdao();
			try {
				boolean addBookById = odao.addBookById(user, Integer.parseInt(bid), bname);
				if(addBookById) {
					session.setAttribute("succeed", bname+"����ӹ��ﳵ");
					//request.getRequestDispatcher("jsp/Main.jsp").forward(request, response);
					response.sendRedirect("jsp/Main.jsp");
					return;
				}else {
					session.setAttribute("succeed", bname+"��ӹ��ﳵʧ��");
					//request.getRequestDispatcher("jsp/Main.jsp").forward(request, response);	
					response.sendRedirect("jsp/Main.jsp");
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//
		}else {
			//2.����ʧ��  
			request.setAttribute("error", "���ȵ���");
			request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
			return;
			//��ת���������
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
