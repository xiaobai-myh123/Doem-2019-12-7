package cn.whcm.bean;
/**
 * 
 * @author Īҫ��
 *		�����ã���������ʵ����
 *		
 *	ʱ�䣺2019-12-8
 *		Orderlist_tb
		�û�  id    			 uid                int         
		�û���   		         uname           	varchar	       ����������uid
		��  id      			 bid   				int
		������   			     bname          	varchar	   
 */
public class Orderlist {
	private Integer uid;
	private String uname;
	private Integer bid;
	private String bname;
	public Orderlist() {
	}
	
	public Orderlist(Integer uid, String uname, Integer bid, String bname) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.bid = bid;
		this.bname = bname;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	@Override
	public String toString() {
		return "Orderlist [uid=" + uid + ", uname=" + uname + ", bid=" + bid + ", bname=" + bname + "]";
	}
}
