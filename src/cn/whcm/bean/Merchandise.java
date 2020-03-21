package cn.whcm.bean;
/**
 * @author Īҫ��
 *	ʱ�䣺2019-12-8
 *	�๦��������������Ʒ��ʵ����
 *
 *	Merchandise_tb
	���� ���� ���                  �۸�    ����
		 
		Id              ����         int               ��Ϊ��             ����       
		bookname        ����        varchar		          ��Ϊ��
		serial          ���        varchar			����Ϊ��	               �����λ�ַ�������������    
		price            doubl�۸�       e 			��Ϊnull
		presentation    ����        varchar            ����Ϊ��          
		number          ����      int                  ��Ϊ��           
		��ʹ�û���������������Ϊ���ݿ� ��Щ���ݿ�Ϊ�� ����
 */
public class Merchandise {
	private Integer id;
	private String bookname;
	private String serial;
	private Double price;
	private String presentation;
	private Integer number;
	public Merchandise() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}



	@Override
	public String toString() {
		return "Merchandise [id=" + id + ", bookname=" + bookname + ", serial=" + serial + ", price=" + price
				+ ", presentation=" + presentation + ", number=" + number + "]";
	}

	public Merchandise(Integer id, String bookname, String serial, Double price, String presentation, Integer number) {
		super();
		this.id = id;
		this.bookname = bookname;
		this.serial = serial;
		this.price = price;
		this.presentation = presentation;
		this.number = number;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
