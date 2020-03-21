package test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestPicture")
/**
 * test��Ƭ
 * @author Īҫ��
 *
 */
public class TestPicture {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		������Ƭ�Ĵ�С
		int w=100;
		int h=50;
//		������Ƭ
		BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		//��ʼ������Ƭ
		Graphics2D g = img.createGraphics();
		//���û��
		g.setColor(Color.red);
		g.fillRect(0, 0, w, h);
		ImageIO.write(img, "jpg", response.getOutputStream());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
