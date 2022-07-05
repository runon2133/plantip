package kr.co.counsel.model.vo;

import lombok.Data;

@Data
public class Counsel {
	private int counselNo;
	private String counselTitle;
	private String counselContent;
	private String counselWriter;
	private String counselRegdate;
	private int counselReadcount;
	private int counselLike;
	private int replyCount;
	
	public String getCounselContentBr() {
		return counselContent.replace("\r\n", "<br>");
	}
}
