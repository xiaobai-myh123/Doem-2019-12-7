package cn.whcm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import cn.whcm.bean.Merchandise;
import cn.whcm.dao.Merchandisedao;

/**
 * 	չʾ(���ﳵ)��servlet
 *  @author Īҫ��
 *
 */


public class ShowBookServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡ���ݿ���ͼ��
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		Merchandisedao merchandisedao = new Merchandisedao();
		try {
			List<Merchandise> showAllBook = merchandisedao.showAllBook();
			//����ͼ��
			HttpSession session = request.getSession();
			session.setAttribute("showAllBook_session", showAllBook);
			request.setAttribute("showAllBook", showAllBook);
			System.out.println("ShowBookServlet");
//			request.getRequestDispatcher("jsp/Main.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/jsp/Main.jsp");
			
			System.out.println(request.getContextPath()+"/jsp/Main.jsp");
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
