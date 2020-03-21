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
 * �����ã�ɾ����Ҫ�Ķ���
 * 
 * @author Īҫ�� ���û�ɾ��ʱ ɾ�����ݿ� orderlist_tb������
 */
public class DelBookOrdellsitServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����ַ�
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//�ж��û��Ƿ����
		HttpSession session = request.getSession();
		User quan_user = (User) session.getAttribute("quan_user");//session.setAttribute("quan_user",quan_user);//�û�ȫ��Ϣ
		System.out.println("DelBookOrdellsitServlet="+quan_user);
		if(quan_user!=null) {
			//�Ѿ�����  ��ȡ��������bidֵ  Ȼ��ɾ��orderlist_tb�е�����
			Integer bid = Integer.parseInt(request.getParameter("bid"));
			System.out.println("DelBookOrdellsitServlet-->bid="+bid);
			String bname = request.getParameter("bname");
			Orderlistdao orderlistdao = new Orderlistdao();
			try {
				boolean delBookById = orderlistdao.delBookById(quan_user, bid, bname);
				if(delBookById) {
					System.out.println(bname+"ɾ���ɹ�");
					session.setAttribute("delbook", bname+"ɾ���ɹ�");
					response.sendRedirect("jsp/shoppingcart.jsp");
					return;
				}else {
					System.out.println(bname+"ɾ��ʧ��");
					session.setAttribute("delbook", bname+"ɾ��ʧ��");
					response.sendRedirect("jsp/shoppingcart.jsp");
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else {
			//���û�е��� ���빺�ﳵ  �����뵽login.jsp
			request.setAttribute("error", "���ȵ���");
			request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
