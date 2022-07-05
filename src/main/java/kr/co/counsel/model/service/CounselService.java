package kr.co.counsel.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.counsel.model.dao.CounselDao;
import kr.co.counsel.model.vo.Counsel;
import kr.co.counsel.model.vo.CounselImg;
import kr.co.counsel.model.vo.CounselLike;
import kr.co.counsel.model.vo.CounselListData;
import kr.co.counsel.model.vo.CounselViewData;
import kr.co.reply.model.vo.Reply;
import kr.co.reply.model.vo.ReplyListData;

@Service
public class CounselService {

	@Autowired
	private CounselDao dao;

	@Transactional
	public int insertCounsel(Counsel counsel) {
		return dao.insertCounsel(counsel);
	}
	
	@Transactional
	public int insertCounsel(Counsel counsel, ArrayList<CounselImg> list) {
		int result1 = dao.insertCounsel(counsel);
		int result = 0;
		if(result1 > 0) {
			int counselNo = dao.selectCounselNo();
			for(CounselImg ci : list) {
				ci.setCounselNo(counselNo);
				result += dao.insertCounselImg(ci);
			}
		} else {
			return -1;
		}
		return result;
	}

	public CounselListData selectCounselList(int reqPage) {
		//한 페이지당 게시물 수
		int numPerPage = 10; 
		int end = reqPage * numPerPage;            	
		int start = end - numPerPage + 1;		   									
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		ArrayList<Counsel> list = dao.selectCounselList(map);

		//전체 게시물 수
		int totalCount = dao.selectTotalCount();  
		//전체 페이지 수
		int totalPage = 0;
		if(totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}

		//페이지 네비 길이
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1; //네비 시작번호
		String pageNavi = "<ul class='pagination justify-content-center'>";
		
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/counselList.do?reqPage=1'>";
			pageNavi += "<span class='fs-7 align-self-center'>FIRST</span></a></li>";
			pageNavi += "<li class='page-item'><a class='page-link' href='/counselList.do?reqPage=" + (pageNo - 1) + "'>";
			pageNavi += "<span>&laquo;</span></a></li>";
		}

		//페이지 숫자
		for(int i = 0; i < pageNaviSize; i++) {  //0,1,2,3,4
			if(pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/counselList.do?reqPage=" + pageNo + "'>";
				pageNavi += pageNo + "</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/counselList.do?reqPage=" + pageNo + "'>";
				pageNavi += pageNo + "</a></li>";
			}
			pageNo++;
			if(pageNo > totalPage) {  //최종 페이지보다 네비게이션 시작번호가 더 클 경우
				break;				  //break
			}
		}
		// 다음버튼
		if (pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/counselList.do?reqPage=" + pageNo + "'>";  // 페이지가 이미 1이 증가된 상태로 들어오기때문에 pageNo로 작성
			pageNavi += "<span>&raquo;</span></a></li>";
			pageNavi += "<li class='page-item'><a class='page-link' href='/counselList.do?reqPage=" + totalPage + "'>";
			pageNavi += "<span class='fs-7 align-self-center'>LAST</span></a></li>";
		}
		pageNavi += "</ul>";
		
		CounselListData cld = new CounselListData();
		cld.setList(list);
		cld.setPageNavi(pageNavi);
		return cld;
	}

	@Transactional
	public int updateReadCount(int counselNo) {
		return dao.updateReadcount(counselNo);
	}
	
	@Transactional
	public CounselViewData counselView(int counselNo) {
		CounselViewData cvd = dao.selectCounsel(counselNo);
		return cvd;
	}

	@Transactional
	public int insertReply(Reply reply) {
		return dao.insertReply(reply);
	}

	public Reply selectOneReply(int replyNo) {
		return dao.selectOneReply(replyNo);
	}

	@Transactional
	public int deleteReply(Reply reply) {
		return dao.deleteReply(reply);
	}

	@Transactional
	public int updateReply(Reply reply) {
		return dao.updateReply(reply);
	}

	public int countReply(int counselNo) {
		return dao.countReply(counselNo);
	}

	public int counselLikeView(CounselLike cl) {
		return dao.counselLikeView(cl);
	}

	public int countLike(int counselNo) {
		return dao.countLike(counselNo);
	}

	@Transactional
	public int likeUp(CounselLike cl) {
		return dao.likeUp(cl);
	}

	@Transactional
	public int likeDown(CounselLike cl) {
		return dao.likeDown(cl);
	}

	@Transactional
	public int deleteCounsel(CounselLike cl) {
		return dao.deleteCounsel(cl);
	}

	public CounselViewData selectOneCounsel(int counselNo) {
		return dao.selectCounsel(counselNo);
	}

	@Transactional
	public int updateCounsel(Counsel counsel) {
		return dao.updateCounsel(counsel);
	}

	@Transactional
	public int updateCounsel(Counsel counsel, ArrayList<CounselImg> list) {
		int result1 = dao.updateCounsel(counsel);
		int result = 0;
		if(result1 > 0) {
			dao.deleteCounselImg(counsel.getCounselNo());
			for(CounselImg ci : list) {
				ci.setCounselNo(counsel.getCounselNo());
				result += dao.insertCounselImg(ci);
			}
		} else {
			return -1;
		}
		return result;
	}

	@Transactional
	public int updateCounselNoImg(Counsel counsel) {
		int result = dao.updateCounsel(counsel);
		if(result > 0) {
			result += dao.deleteCounselImg(counsel.getCounselNo());
		}
		return result;
	}

	public CounselListData searchCounsel(int reqPage, String keyword) {
		//한 페이지당 게시물 수
		int numPerPage = 10; 
		int end = reqPage * numPerPage;            	
		int start = end - numPerPage + 1;		   									
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("keyword", keyword);
		ArrayList<Counsel> list = dao.searchCounselList(map);

		//전체 게시물 수
		int totalCount = dao.searchTotalCount(keyword);
		//전체 페이지 수
		int totalPage = 0;
		if(totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}

		//페이지 네비 길이
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1; //네비 시작번호
		String pageNavi = "<ul class='pagination justify-content-center'>";
		
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/searchCounsel.do?reqPage=1&keyword=" + keyword + "'>";
			pageNavi += "<span class='fs-7 align-self-center'>FIRST</span></a></li>";
			pageNavi += "<li class='page-item'><a class='page-link' href='/searchCounsel.do?reqPage=" + (pageNo - 1) + "&keyword=" + keyword + "'>";
			pageNavi += "<span>&laquo;</span></a></li>";
		}

		//페이지 숫자
		for(int i = 0; i < pageNaviSize; i++) {  //0,1,2,3,4
			if(pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/searchCounsel.do?reqPage=" + pageNo + "&keyword=" + keyword + "'>";
				pageNavi += pageNo + "</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/searchCounsel.do?reqPage=" + pageNo + "&keyword=" + keyword + "'>";
				pageNavi += pageNo + "</a></li>";
			}
			pageNo++;
			if(pageNo > totalPage) {  //최종 페이지보다 네비게이션 시작번호가 더 클 경우
				break;				  //break
			}
		}
		// 다음버튼
		if (pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/searchCounsel.do?reqPage=" + pageNo + "&keyword=" + keyword + "'>";  // 페이지가 이미 1이 증가된 상태로 들어오기때문에 pageNo로 작성
			pageNavi += "<span>&raquo;</span></a></li>";
			pageNavi += "<li class='page-item'><a class='page-link' href='/searchCounsel.do?reqPage=" + totalPage + "&keyword=" + keyword + "'>";
			pageNavi += "<span class='fs-7 align-self-center'>LAST</span></a></li>";
		}
		pageNavi += "</ul>";
		
		CounselListData cld = new CounselListData();
		cld.setList(list);
		cld.setPageNavi(pageNavi);
		return cld;
	}
	
	public CounselListData likeCounselList(String memberId, int reqPage) {
		//한 페이지당 게시물 수
		int numPerPage = 10;
		int end = reqPage * numPerPage;            	
		int start = end - numPerPage + 1;	   									
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("memberId", memberId);
		ArrayList<Counsel> list = dao.likeCounselList(map);
	
		//전체 게시물 수
		int totalCount = dao.likeTotalCount(memberId);  
		//전체 페이지 수
		int totalPage = 0;
		if(totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}
	
		//페이지 네비 길이
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1; //네비 시작번호
		String pageNavi = "<ul class='pagination justify-content-center'>";
		
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/likeCounselList.do?reqPage=1'>";
			pageNavi += "<span class='fs-7 align-self-center'>FIRST</span></a></li>";
			pageNavi += "<li class='page-item'><a class='page-link' href='/likeCounselList.do?reqPage=" + (pageNo - 1) + "'>";
			pageNavi += "<span>&laquo;</span></a></li>";
		}
	
		//페이지 숫자
		for(int i = 0; i < pageNaviSize; i++) {  //0,1,2,3,4
			if(pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/likeCounselList.do?reqPage=" + pageNo + "'>";
				pageNavi += pageNo + "</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/likeCounselList.do?reqPage=" + pageNo + "'>";
				pageNavi += pageNo + "</a></li>";
			}
			pageNo++;
			if(pageNo > totalPage) {  //최종 페이지보다 네비게이션 시작번호가 더 클 경우
				break;				  //break
			}
		}
		// 다음버튼
		if (pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/likeCounselList.do?reqPage=" + pageNo + "'>";  // 페이지가 이미 1이 증가된 상태로 들어오기때문에 pageNo로 작성
			pageNavi += "<span>&raquo;</span></a></li>";
			pageNavi += "<li class='page-item'><a class='page-link' href='/likeCounselList.do?reqPage=" + totalPage + "'>";
			pageNavi += "<span class='fs-7 align-self-center'>LAST</span></a></li>";
		}
		pageNavi += "</ul>";
		
		CounselListData cld = new CounselListData();
		cld.setList(list);
		cld.setPageNavi(pageNavi);
		return cld;
	}
	
	public CounselListData writeCounselList(String counselWriter, int reqPage) {
		//한 페이지당 게시물 수
		int numPerPage = 10;
		int end = reqPage * numPerPage;            	
		int start = end - numPerPage + 1;		   									
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("counselWriter", counselWriter);
		ArrayList<Counsel> list = dao.writeCounselList(map);
	
		//전체 게시물 수
		int totalCount = dao.writeCounselTotalCount(counselWriter);  
		//전체 페이지 수
		int totalPage = 0;
		if(totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}
	
		//페이지 네비 길이
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1; //네비 시작번호
		String pageNavi = "<ul class='pagination justify-content-center'>";
		
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/writeCounselList.do?reqPage=1'>";
			pageNavi += "<span class='fs-7 align-self-center'>FIRST</span></a></li>";
			pageNavi += "<li class='page-item'><a class='page-link' href='/writeCounselList.do?reqPage=" + (pageNo - 1) + "'>";
			pageNavi += "<span>&laquo;</span></a></li>";
		}
	
		//페이지 숫자
		for(int i = 0; i < pageNaviSize; i++) {  //0,1,2,3,4
			if(pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/writeCounselList.do?reqPage=" + pageNo + "'>";
				pageNavi += pageNo + "</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/writeCounselList.do?reqPage=" + pageNo + "'>";
				pageNavi += pageNo + "</a></li>";
			}
			pageNo++;
			if(pageNo > totalPage) {  //최종 페이지보다 네비게이션 시작번호가 더 클 경우
				break;				  //break
			}
		}
		// 다음버튼
		if (pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/writeCounselList.do?reqPage=" + pageNo + "'>";  // 페이지가 이미 1이 증가된 상태로 들어오기때문에 pageNo로 작성
			pageNavi += "<span>&raquo;</span></a></li>";
			pageNavi += "<li class='page-item'><a class='page-link' href='/writeCounselList.do?reqPage=" + totalPage + "'>";
			pageNavi += "<span class='fs-7 align-self-center'>LAST</span></a></li>";
		}
		pageNavi += "</ul>";
		
		CounselListData cld = new CounselListData();
		cld.setList(list);
		cld.setPageNavi(pageNavi);
		return cld;
	}

	public ReplyListData writeReplyList(String replyWriter, int reqPage) {
		//한 페이지당 게시물 수
		int numPerPage = 10;
		int end = reqPage * numPerPage;            	
		int start = end - numPerPage + 1;		   									
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("replyWriter", replyWriter);
		ArrayList<Reply> list = dao.writeReplyList(map);
	
		//전체 게시물 수
		int totalCount = dao.writeReplyTotalCount(replyWriter);  
		//전체 페이지 수
		int totalPage = 0;
		if(totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}
	
		//페이지 네비 길이
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1; //네비 시작번호
		String pageNavi = "<ul class='pagination justify-content-center'>";
		
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/writeReplyList.do?reqPage=1'>";
			pageNavi += "<span class='fs-7 align-self-center'>FIRST</span></a></li>";
			pageNavi += "<li class='page-item'><a class='page-link' href='/writeReplyList.do?reqPage=" + (pageNo - 1) + "'>";
			pageNavi += "<span>&laquo;</span></a></li>";
		}
	
		//페이지 숫자
		for(int i = 0; i < pageNaviSize; i++) {  //0,1,2,3,4
			if(pageNo == reqPage) {
				pageNavi += "<li class='page-item active'>";
				pageNavi += "<a class='page-link' href='/writeReplyList.do?reqPage=" + pageNo + "'>";
				pageNavi += pageNo + "</a></li>";
			} else {
				pageNavi += "<li class='page-item'>";
				pageNavi += "<a class='page-link' href='/writeReplyList.do?reqPage=" + pageNo + "'>";
				pageNavi += pageNo + "</a></li>";
			}
			pageNo++;
			if(pageNo > totalPage) {  //최종 페이지보다 네비게이션 시작번호가 더 클 경우
				break;				  //break
			}
		}
		// 다음버튼
		if (pageNo <= totalPage) {
			pageNavi += "<li class='page-item'>";
			pageNavi += "<a class='page-link' href='/writeReplyList.do?reqPage=" + pageNo + "'>";  // 페이지가 이미 1이 증가된 상태로 들어오기때문에 pageNo로 작성
			pageNavi += "<span>&raquo;</span></a></li>";
			pageNavi += "<li class='page-item'><a class='page-link' href='/writeReplyList.do?reqPage=" + totalPage + "'>";
			pageNavi += "<span class='fs-7 align-self-center'>LAST</span></a></li>";
		}
		pageNavi += "</ul>";
		
		ReplyListData rld = new ReplyListData();
		rld.setList(list);
		rld.setPageNavi(pageNavi);
		return rld;
	}

}
