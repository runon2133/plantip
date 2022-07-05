package kr.co.reply.model.vo;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ReplyListData {
	private ArrayList<Reply> list;
	private String pageNavi;
}
