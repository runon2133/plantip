package kr.co.member.model.vo;

import lombok.Data;

@Data
public class Member {
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberEmail;
	private String memberRegdate;
	private int memberLevel;
	private String memberImg;
	private String originalImg;
}
