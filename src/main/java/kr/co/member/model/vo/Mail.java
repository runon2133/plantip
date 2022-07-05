package kr.co.member.model.vo;

import lombok.Data;

@Data
public class Mail {
	private String subject;
	private String content;
	private String from;
	private String fromName;
	private String to;
}
