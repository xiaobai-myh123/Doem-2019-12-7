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
/**
 * ��modify.jsp������д�뵽���ݿ���
 * @author Īҫ��
 *
 */

public class RmodifBookInfoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ͳһ�����ַ�����
		response.setHeader("text/html", "charset=uft-8");
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		Root root = (Root) session.getAttribute("root_mysql");//session.setAttribute("root_mysql",root_mysql);//root�û�ȫ��Ϣ
		if(root!=null) {
			//����
				//��ȡmodify.jsp������
			Integer bid = Integer.parseInt(request.getParameter("bid"));
			String bname = request.getParameter("bname");
			String presentation = request.getParameter("presentation");
			Double price = Double.parseDouble(request.getParameter("price"));
			Integer number = Integer.parseInt(request.getParameter("number"));
				//����ȡ������д�뵽���ݿ���
			Merchandise m = new Merchandise(bid, bname, null, price, presentation, number);
			Merchandisedao mdao= new Merchandisedao();
			boolean addBook;
			try {
				addBook = mdao.addMerchandise(m);
				if(addBook) {
					//�޸ĳɹ�
					System.out.println(bname+"�޸ĳɹ�,RmodifBookInfoServlet");
					session.setAttribute("succeed", bname+"��Ϣ�޸ĳɹ�");
					response.sendRedirect("jsp/Rmain.jsp");
					return;
				}else {
					//�޸�ʧ��  һ�㲻��ʧ��  ���������˵  �ȴ�����˵
					System.out.println(bname+"�޸�ʧ��,RmodifBookInfoServlet");
					session.setAttribute("succeed", bname+"�޸�ʧ��");
					response.sendRedirect("jsp/Rmain.jsp");
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
