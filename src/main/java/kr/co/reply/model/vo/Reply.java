package kr.co.reply.model.vo;

import lombok.Data;

@Data
public class Reply {
	private int replyNo;
	private int replyLevel;
	private String replyWriter;
	private String replyContent;
	private String replyRegdate;
	private int counselNo;
	private int replyNoRef;
	private String replyTo;
	private int deleteChk;
	private String memberImg;
	
	public String getReplyContentBr() {
		return replyContent.replace("\r\n", "<br>");
	}
}
