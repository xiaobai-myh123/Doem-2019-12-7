package cn.whcm.servlet;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.whcm.bean.User;
import cn.whcm.utils.JdbcUtils;
/**
 * 	 �����ã�BuyBookNameServlet  
 * 		�ж���Ʒͼ���Ƿ��д�� 
 * 			���������
 * 			��֮����
 * @author Īҫ��
 *
 */
//@WebServlet("/ButBookNameServlet")
public class ButBookNameServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//�����ַ�
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			//�ж��û��Ƿ����
			HttpSession session = request.getSession();
			User quan_user = (User) session.getAttribute("quan_user");//session.setAttribute("quan_user",quan_user);//�û�ȫ��Ϣ
			System.out.println("ButBookNameServlet="+quan_user);
			if(quan_user!=null) {
				//�û��Ѿ�����
				//��ȡ�û���Ϣ��ͼ��bid��bname
				Integer bid = Integer.parseInt(request.getParameter("bid"));
				System.out.println("ButBookNameServlet-->bid="+bid);
				String bname = request.getParameter("bname");
				//����Map�����û���Ϣ���û������鼮
				if(quan_user==null||bid==null||bname==null) {
					System.out.println("��ȡ�û�����bid����bnameΪ��");
					return;
				}
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				String url = "jdbc:mysql://127.0.0.1:3306/Dome?useSSL=false";
				String user1 = "root";
				String password = "123456";
				Double price = null;
				String presentation= null;
				
				String sql ="SELECT * from merchandise_tb where id=?";
				ResultSet rs = null;
				PreparedStatement ps = null;
				Connection conn = null;
				//������������Ϣ
				try {
					conn = DriverManager.getConnection(url, user1, password);
					if(conn!=null) {
						ps = conn.prepareStatement(sql);
						ps.setInt(1, bid);
						rs = ps.executeQuery();
						if(rs.next()) {
							price = rs.getDouble("price");
							presentation = rs.getString("presentation");
							System.out.println(price+presentation);
						}
						
					}else {
						System.out.println("conn="+conn);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					try {
						if(rs!=null) {rs.close();}
						if(ps!=null) {ps.close();}
						if(conn!=null) {conn.close();}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				//___________________________________________________
				Map<String,Object> map = new HashMap();
				map.put("price",price);
				map.put("presentation",presentation);
				map.put("user",quan_user);
				map.put("bid",bid);
				map.put("bname", bname);
				System.out.println(map+"�ĳ���Ϊ"+map.size());
				session.setAttribute("mapUserBidBname", map);
				response.sendRedirect("jsp/OrderInfo.jsp");
				return;
				//___________________________________________________
			}else {
				//���û�е��� ���빺�ﳵ  �����뵽login.jsp
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
