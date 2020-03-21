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
 *   �����ݿ���    �鼮��servlet�������޸ĵģ�
 * @author Īҫ��
 *
 */

public class RmodifyBookServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ͳһ�����ַ�����
		response.setHeader("text/html", "charset=uft-8");
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		Root root = (Root) session.getAttribute("root_mysql");//session.setAttribute("root_mysql",root_mysql);//root�û�ȫ��Ϣ
		if(root!=null) {
			//�Ѿ�����
			//��ȡ�鼮id���鼮����
			Integer bid = Integer.parseInt(request.getParameter("bid"));
			String bname = request.getParameter("bname");
			//�����ݿ���ȫ����Ϣ
			Merchandisedao m = new Merchandisedao();
			try {
				Merchandise merchandiseByID = m.getMerchandiseByID(bid, bname);//��������Ʒȫ����Ϣ
				if(merchandiseByID==null) {
					System.out.println("��������Ʒȫ����ϢΪ��     һ�㲻����Ϊ��");
					session.setAttribute("succeed", "��������Ʒȫ����ϢΪ��     һ�㲻����Ϊ��");
//					request.getRequestDispatcher("/jsp/Rmain.jsp").forward(request, response);
					response.sendRedirect("/jsp/Rmain.jsp");
					return;
				}else {
					//����Ʒ��Ϣ���ó�session
					session.setAttribute("merchandiseByID", merchandiseByID);//һ����Ʒ��ȫ��Ϣ
//					Root root = (Root) session.getAttribute("root_mysql");
					//Ȼ�����޸ĵ�modif.jsp���޸�
					response.sendRedirect("jsp/modify.jsp");
					return;
					//�޸ĳɹ�   ����ʾ�޸ĳɹ�  ������ʾ�޸�ʧ��   �޸�ʧ��ԭ��   ����û����дjs�ж�
				}
			} catch (SQLException e) {
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
