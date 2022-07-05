package kr.co.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.member.model.dao.MemberDao;
import kr.co.member.model.vo.Mail;
import kr.co.member.model.vo.Member;

@Service
public class MemberService {

	@Autowired
	private MemberDao dao;

	public Member selectOneMember(String memberId) {
		Member m = dao.selectOneMember(memberId);
		return m;
	}

	public int checkEmail(String memberEmail) {
		return dao.checkEmail(memberEmail);
	}

	public Member loginEnc(Member member) {
		Member m = dao.loginMember(member);
		return m;
	}

	@Transactional
	public int joinMemberEnc(Member member) {
		return dao.joinMember(member);
	}

	public Mail searchId(String memberEmail) {
		String memberId = dao.searchId(memberEmail);
		Mail mail = new Mail();
		mail.setSubject("아이디 찾기 결과입니다.");
		mail.setContent(
						"<h3>아이디 찾기 결과</h3>" +
						memberId
				);
		mail.setFrom("plantipco@naver.com");
		mail.setFromName("플랜팁");
		mail.setTo(memberEmail);
		return mail;
	}

	public int checkIdEmail(Member member) {
		return dao.checkIdEmail(member);
	}
	
	public Mail searchPwEnc(Member member, String tempKey) {
		dao.searchPw(member);
		Mail mail = new Mail();
		mail.setSubject("비밀번호 찾기 결과입니다.");
		mail.setContent(
						"<h3>임시 비밀번호 발급</h3>" +
						tempKey
				);
		mail.setFrom("plantipco@naver.com");
		mail.setFromName("플랜팁");
		mail.setTo(member.getMemberEmail());
		return mail;
	}

	@Transactional
	public int updateMember(Member member) {
		return dao.updateMember(member);
	}

	@Transactional
	public int updatePwEnc(Member member) {
		return dao.updatePw(member);
	}

	@Transactional
	public int deleteMember(Member member) {
		return dao.deleteMember(member);
	}

}
