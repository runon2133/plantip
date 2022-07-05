package kr.co.counsel.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.counsel.model.vo.Counsel;
import kr.co.counsel.model.vo.CounselImg;
import kr.co.counsel.model.vo.CounselLike;
import kr.co.counsel.model.vo.CounselViewData;
import kr.co.reply.model.vo.Reply;

@Repository
public class CounselDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public int insertCounsel(Counsel counsel) {
		return sqlSession.insert("counsel.insertCounsel", counsel);
	}

	public int selectCounselNo() {
		return sqlSession.selectOne("counsel.selectCounselNo");
	}

	public int insertCounselImg(CounselImg ci) {
		return sqlSession.insert("counsel.insertCounselImg", ci);
	}

	public ArrayList<Counsel> selectCounselList(HashMap<String, Object> map) {
		List<Counsel> list = sqlSession.selectList("counsel.selectCounselList", map);
		return (ArrayList<Counsel>)list;
	}

	public int selectTotalCount() {
		return sqlSession.selectOne("counsel.selectTotalCount");
	}

	public int updateReadcount(int counselNo) {
		return sqlSession.update("counsel.updateReadcount", counselNo);
	}

	public CounselViewData selectCounsel(int counselNo) {
		return sqlSession.selectOne("counsel.selectCounsel", counselNo);
	}

	public int insertReply(Reply reply) {
		return sqlSession.insert("counsel.insertReply", reply);
	}

	public Reply selectOneReply(int replyNo) {
		return sqlSession.selectOne("counsel.selectOneReply", replyNo);
	}

	public int deleteReply(Reply reply) {
		return sqlSession.delete("counsel.deleteReply", reply);
	}

	public int updateReply(Reply reply) {
		return sqlSession.update("counsel.updateReply", reply);
	}

	public int countReply(int counselNo) {
		return sqlSession.selectOne("counsel.countReply", counselNo);
	}

	public int counselLikeView(CounselLike cl) {
		return sqlSession.selectOne("counsel.counselLikeView", cl);
	}

	public int countLike(int counselNo) {
		return sqlSession.selectOne("counsel.countLike", counselNo);
	}

	public int likeUp(CounselLike cl) {
		return sqlSession.insert("counsel.likeUp", cl);
	}

	public int likeDown(CounselLike cl) {
		return sqlSession.delete("counsel.likeDown", cl);
	}

	public int deleteCounsel(CounselLike cl) {
		return sqlSession.delete("counsel.deleteCounsel", cl);
	}

	public int updateCounsel(Counsel counsel) {
		return sqlSession.update("counsel.updateCounsel", counsel);
	}

	public int deleteCounselImg(int counselNo) {
		return sqlSession.delete("counsel.deleteCounselImg", counselNo);
	}

	public ArrayList<Counsel> searchCounselList(HashMap<String, Object> map) {
		List<Counsel> list = sqlSession.selectList("counsel.searchCounselList", map);
		return (ArrayList<Counsel>)list;
	}

	public int searchTotalCount(String keyword) {
		return sqlSession.selectOne("counsel.searchTotalCount", keyword);
	}

	public ArrayList<Counsel> likeCounselList(HashMap<String, Object> map) {
		List<Counsel> list = sqlSession.selectList("counsel.likeCounselList", map);
		return (ArrayList<Counsel>)list;
	}
	
	public int likeTotalCount(String memberId) {
		return sqlSession.selectOne("counsel.likeTotalCount", memberId);
	}

	public ArrayList<Counsel> writeCounselList(HashMap<String, Object> map) {
		List<Counsel> list = sqlSession.selectList("counsel.writeCounselList", map);
		return (ArrayList<Counsel>)list;
	}
	
	public int writeCounselTotalCount(String counselWriter) {
		return sqlSession.selectOne("counsel.writeCounselTotalCount", counselWriter);
	}
	
	public ArrayList<Reply> writeReplyList(HashMap<String, Object> map) {
		List<Reply> list = sqlSession.selectList("counsel.writeReplyList", map);
		return (ArrayList<Reply>)list;
	}
	
	public int writeReplyTotalCount(String replyWriter) {
		return sqlSession.selectOne("counsel.writeReplyTotalCount", replyWriter);
	}	
}
