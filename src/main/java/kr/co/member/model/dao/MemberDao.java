package kr.co.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.member.model.vo.Member;

@Repository
public class MemberDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public Member selectOneMember(String memberId) {
		return sqlSession.selectOne("member.selectOneMember", memberId);
	}

	public int checkEmail(String memberEmail) {
		return sqlSession.selectOne("member.checkEmail", memberEmail);
	}

	public Member loginMember(Member member) {
		return sqlSession.selectOne("member.loginMember", member);
	}
	
	public int joinMember(Member member) {
		return sqlSession.insert("member.joinMember", member);
	}

	public String searchId(String memberEmail) {
		return sqlSession.selectOne("member.searchId", memberEmail);
	}

	public int checkIdEmail(Member member) {
		return sqlSession.selectOne("member.checkIdEmail", member);
	}

	public int searchPw(Member member) {
		return sqlSession.update("member.searchPw", member);
	}

	public int updateMember(Member member) {
		return sqlSession.update("member.updateMember", member);
	}

	public int updatePw(Member member) {
		return sqlSession.update("member.updatePw", member);
	}

	public int deleteMember(Member member) {
		return sqlSession.delete("member.deleteMember", member);
	}

}
