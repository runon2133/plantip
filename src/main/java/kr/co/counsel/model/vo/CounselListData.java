package kr.co.counsel.model.vo;

import java.util.ArrayList;

import lombok.Data;

@Data
public class CounselListData {
	private ArrayList<Counsel> list;
	private String pageNavi;
}
