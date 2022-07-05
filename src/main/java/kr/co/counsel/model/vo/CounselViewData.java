package kr.co.counsel.model.vo;

import java.util.ArrayList;

import kr.co.reply.model.vo.Reply;
import lombok.Data;

@Data
public class CounselViewData {
	private Counsel counsel;
	private ArrayList<CounselImg> counselImgList;
	private ArrayList<Reply> replyList;
}
