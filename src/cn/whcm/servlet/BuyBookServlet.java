package cn.whcm.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import cn.whcm.bean.User;
import cn.whcm.dao.Merchandisedao;
import cn.whcm.utils.JdbcUtils;

//����Ķ���servlet

public class BuyBookServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//�ж��û��Ƿ����
		HttpSession session = request.getSession();
		User quan_user = (User) session.getAttribute("quan_user");//session.setAttribute("quan_user",quan_user);//�û�ȫ��Ϣ
		System.out.println("BuyBookServlet="+quan_user);
		if(quan_user!=null) {
			//�û��Ѿ�����
			//��ȡ�û���Ϣ��ͼ��bid��bname
			Map<String,Object> map = (Map<String,Object>) session.getAttribute("mapUserBidBname");
			//��ʼ��bid��biname  �鿴��������ҳ�����ıȽ�  
			Integer bid = (Integer) map.get("bid");
			String bname = (String) map.get("bname");
			System.out.println(bid+bname);
			Connection conn = JdbcUtils.getCon();
			String sql="select number from merchandise_tb where id=? and bookname=?";
			PreparedStatement ps=null;
			Integer number_sql=null;
			ResultSet rs =null;
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, bid);
				ps.setString(2, bname);
				rs= ps.executeQuery();
				if(rs.next()) {
					number_sql=rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs==null)
						rs.close();
					if(ps==null)
						ps.close();
					if(conn==null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(number_sql==null) {
				System.out.println("bname+\"��Ʒ�����ѱ�ɾ��");
				session.setAttribute("msg_book_number", bname+"��Ʒ�����ѱ�ɾ��");
				response.sendRedirect("jsp/OrderInfo.jsp");
				return;
			} 
			Integer number = Integer.parseInt(request.getParameter("number"));//��ҳ�����鼮����
			System.out.println("number="+number+"number_sql="+number_sql);
			if(number_sql==0) {  //ͼ��û��ͼ��
				System.out.println(bname+"ͼ����Ϊ0");
				session.setAttribute("msg_book_number", bname+"ͼ����Ϊ0");
				response.sendRedirect("jsp/OrderInfo.jsp");
				return;
			}else if(number_sql>=number) {   //mysql ��ͼ���Ҫ���ͼ���
				//����ɹ�  ���ݿ��鼮����
				//---------------------------------------
				Merchandisedao mdao = new Merchandisedao();
				try {
					boolean delBookByIdName = mdao.delBookByIdName(bid, bname, number);
					if(delBookByIdName) {
						System.out.println("������"+bname+"ͼ��Ϊ����"+number);
						session.setAttribute("msg_book_number", "����ɹ�"+number+"��");
					}else {
						System.out.println("����ʧ��");
						session.setAttribute("msg_book_number", "����ʧ��"+number+"��");
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				//---------------------------------------
				response.sendRedirect("jsp/OrderInfo.jsp");
				return;
			}else {// ���С�ڹ���   ��������
				System.out.println("����"+bname+"����̫�࣬��治��number="+number);
				session.setAttribute("msg_book_number", "����"+bname+"���鼮���࣬"+"�洢����"+number+"��");
				response.sendRedirect("jsp/OrderInfo.jsp");
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
