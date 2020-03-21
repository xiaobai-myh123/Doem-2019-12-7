package cn.whcm.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.whcm.bean.Merchandise;
import cn.whcm.bean.Root;
import cn.whcm.dao.Merchandisedao;


public class RInsertBookServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ͳһ�����ַ�����
		response.setHeader("text/html", "charset=uft-8");
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		Root root = (Root) session.getAttribute("root_mysql");//session.setAttribute("root_mysql",root_mysql);//root�û�ȫ��Ϣ
		if(root!=null) {
			//����
			//��ȡ��ҳ����
			String bookname = request.getParameter("bookname");
			Double price = Double.parseDouble(request.getParameter("price"));
			String presentation = request.getParameter("presentation");
			Integer number = Integer.parseInt(request.getParameter("number"));
			//�������ݿ�
			Merchandise m = new Merchandise(null, bookname, null, price, presentation, number);
			Merchandisedao mdao = new Merchandisedao();
			try {
				boolean addBook = mdao.addBook(m);
				if(addBook) {
					//����ɹ�
					System.out.println("�����Ʒ�ɹ�");
					session.setAttribute("succeed", "�����Ʒ�ɹ�");
					response.sendRedirect("jsp/Raddbook.jsp");
					return;
					
				}else {
					//����ʧ�� һ�㲻��ʧ��  ���⴦��
					System.out.println("�����Ʒȫ����ϢΪ��     һ�㲻����Ϊ��");
					session.setAttribute("succeed", "�����Ʒȫ����ϢΪ��     һ�㲻����Ϊ��");
//					request.getRequestDispatcher("/jsp/Rmain.jsp").forward(request, response);
					response.sendRedirect("jsp/Rmain.jsp");
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//�ص�Raddbook.jsp
			
		}else {
			//û�е���
			request.setAttribute("error", "���ȵ���");
			request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
			return;
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
