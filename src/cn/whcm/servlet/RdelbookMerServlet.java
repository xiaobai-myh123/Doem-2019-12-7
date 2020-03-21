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
import cn.whcm.bean.User;
import cn.whcm.dao.Merchandisedao;

/**
 * 	������
 * 	ɾ��ͼ�� ��merchandise_tb����
 * 
 * @author Īҫ��
 *
 */

public class RdelbookMerServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�����ַ�
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//�ж��û��Ƿ����
		HttpSession session = request.getSession();
		Root root = (Root) session.getAttribute("root_mysql");//session.setAttribute("root_mysql",root_mysql);//root�û�ȫ��Ϣ
		if(root!=null) {
			//����ɹ�
			//ɾ��ͼ��   ����ͼ��id����
			Integer bid = Integer.parseInt(request.getParameter("bid"));
			String bname = request.getParameter("bname");
			System.out.println("RdelbookMerServlet-->bid="+bid+" "+bname);
			//�����ݿ�ȡ�鼮
			Merchandisedao mdao = new Merchandisedao();
			try {
				boolean delBookById = mdao.delBookById(bid);
				if(delBookById) {
					//ɾ���ɹ�
					System.out.println(bid+bname+"ɾ���ɹ�");
					session.setAttribute("succeed", bname+"ɾ���ɹ�");
					response.sendRedirect("jsp/Rmain.jsp");
					return;
				}else {
					//ɾ�����ɹ�
					System.out.println(bid+bname+"ɾ��ʧ��");
					session.setAttribute("succeed", bname+"ɾ��ʧ��");
					response.sendRedirect("jsp/Rmain.jsp");
					return;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}else {
			//û�е���
			request.setAttribute("error", "���ȵ���");
			request.getRequestDispatcher("jsp/Rlogin.jsp").forward(request, response);
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
