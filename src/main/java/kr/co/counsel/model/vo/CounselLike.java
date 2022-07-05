package kr.co.counsel.model.vo;

import lombok.Data;

@Data
public class CounselLike {
	private int counselLikeNo;
	private int counselNo;
	private String memberId;
	private int counselLikeCheck;
}
