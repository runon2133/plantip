package kr.co.member.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import kr.co.common.MailSend;
import kr.co.common.TempKey;
import kr.co.counsel.model.service.CounselService;
import kr.co.counsel.model.vo.CounselListData;
import kr.co.member.model.service.MemberService;
import kr.co.member.model.vo.Mail;
import kr.co.member.model.vo.Member;
import kr.co.reply.model.vo.ReplyListData;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;
	
	@Autowired
	private CounselService counselService;

	@Autowired
	private MailSend sendMail;
	
	//로그인 페이지
	@RequestMapping(value="/loginFrm.do")
	public String loginFrm() {
		return "member/loginFrm";
	}
	
	//회원가입 페이지
	@RequestMapping(value="/joinFrm.do")
	public String joinFrm() {
		return "member/joinFrm";
	}
	
	//아이디 중복검사
	@ResponseBody
	@RequestMapping(value="/checkId.do")
	public String checkId(String memberId) {
		Member m = service.selectOneMember(memberId);
		if(m == null) {
			return "0";
		} else {
			return "1";
		}
	}
	
	//이메일 중복검사
	@ResponseBody
	@RequestMapping(value="/checkEmail.do")
	public String checkEmail(String memberEmail) {
		int emailCnt = service.checkEmail(memberEmail);
		if(emailCnt == 0) {
			return "0";
		} else {
			return "1";
		}
	}
	
	//회원가입
	@RequestMapping(value="/join.do")
	public String join(Member member, Model model) {
		int result = service.joinMemberEnc(member);
		if(result > 0) {
			return "member/joinComplete";
		} else {
			return "redirect:/";
		}
	}
	
	//아이디 찾기 페이지
	@RequestMapping(value="/searchIdFrm.do")
	public String searchIdFrm() {
		return "member/searchIdFrm";
	}

	//아이디 찾기
	@RequestMapping(value="/searchId.do")
	public String searchId(String memberEmail, Model model) throws Exception {
		int emailCnt = service.checkEmail(memberEmail);
		if(emailCnt == 0) {
			model.addAttribute("msg", "유효하지 않은 회원입니다.");
			model.addAttribute("loc", "/");
			return "common/msg";
		} else {
			Mail mail = service.searchId(memberEmail);
			sendMail.mailSend(mail);
			model.addAttribute("msg", "이메일로 아이디를 전송하였습니다. 다시 로그인해보세요.");
			model.addAttribute("loc", "/loginFrm.do");
			return "common/msg";
		}
	}
	
	//비밀번호 찾기 페이지
	@RequestMapping(value="/searchPwFrm.do")
	public String searchPwFrm() {
		return "member/searchPwFrm";
	}
	
	//비밀번호 찾기
	@RequestMapping(value="/searchPw.do")
	public String searchPw(Member member, Model model) throws Exception {
		int checkCnt = service.checkIdEmail(member);
		if(checkCnt == 0) {
			model.addAttribute("msg", "유효하지 않은 회원입니다.");
			model.addAttribute("loc", "/");
			return "common/msg";			
		} else {
			String tempKey = new TempKey().getKey(8, false);
			member.setMemberPw(tempKey);
			Mail mail = service.searchPwEnc(member, tempKey);
			sendMail.mailSend(mail);
			model.addAttribute("msg", "이메일로 임시 비밀번호를 전송하였습니다. 다시 로그인해보세요.");
			model.addAttribute("loc", "/loginFrm.do");
			return "common/msg";
		}
	}

	//로그인
	@RequestMapping(value="/login.do")
	public String login(String memberId, String memberPw, HttpSession session, Model model) {
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		Member m = service.loginEnc(member);
		if(m != null) {
			m.setMemberPw(memberPw);
			session.setAttribute("m", m);
			return "redirect:/";
		} else {
			model.addAttribute("msg", "아이디 또는 비밀번호를 확인하세요.");			
			model.addAttribute("loc", "/");
			return "common/msg";
		}
	}
	
	//로그아웃
	@RequestMapping(value="/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	//마이페이지
	@RequestMapping(value="/mypageView.do")
	public String mypageView() {
		return "member/mypageView";
	}
	
	//회원정보 수정
	@RequestMapping(value="/updateMember.do")
	public String join(Member member, MultipartFile[] profile, HttpSession session, HttpServletRequest request, Model model) {
		if (profile[0].isEmpty()) {
			member.setMemberImg(member.getOriginalImg());
		} else {
			//파일 업로드 경로 설정
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/member/");
			MultipartFile file = profile[0];
			//업로드할 파일명(확장자 포함)
			String filename = file.getOriginalFilename();
			//파일명
			String onlyFilename = filename.substring(0, filename.indexOf(".")); //test
			//.확장자
			String extention = filename.substring(filename.indexOf(".")); //.img
			//실제 업로드할 파일명
			String filepath = null;
			//파일명 중복 시 구분하기 위해 숫자를 붙임
			int count = 0;
			while(true) {
				if(count == 0) {
					filepath = onlyFilename + extention; //test.img
				} else {
					filepath = onlyFilename + "_" + count + extention; //test_1.img
				}
				File checkFile = new File(savePath + filepath); //java.io.File
				if(!checkFile.exists()) {
					break;
				}
				count++;
			}
			//파일 업로드
			try {
				//중복처리가 끝난 파일명(filepath)으로 파일을 업로드
				FileOutputStream fos = new FileOutputStream(new File(savePath + filepath));
				//업로드 속도 증가를 위한 보조스트림
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				//파일 업로드
				byte[] bytes = file.getBytes();
				bos.write(bytes);
				bos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			member.setMemberImg(filepath);
		}
		
		int result = service.updateMember(member);
		if(result > 0) {
			Member m = service.selectOneMember(member.getMemberId());
			m.setMemberPw(member.getMemberPw());
			session.setAttribute("m", m);
			model.addAttribute("msg", "회원정보 수정 완료");
		} else {
			model.addAttribute("msg", "회원정보 수정 실패");
		}
		model.addAttribute("loc", "/mypageView.do");
		return "common/msg";
	}
	
	//비밀번호 변경 페이지
	@RequestMapping(value="/changePwView.do")
	public String changePwView() {
		return "member/changePwView";
	}
	
	
	//현재 비밀번호 일치 확인
	@ResponseBody
	@RequestMapping(value="/checkPw.do")
	public String checkPw(@SessionAttribute Member m, String memberPw) {
		String originalPw = m.getMemberPw();
		if(originalPw.equals(memberPw)) {
			return "0";
		} else {
			return "1";
		}
	}
	
	//비밀번호 변경
	@RequestMapping(value="/updatePw.do")
	public String updatePw(@SessionAttribute Member m, HttpSession session, String newPw, Model model) {
		m.setMemberPw(newPw);
		int result = service.updatePwEnc(m);
		if(result > 0) {
			model.addAttribute("msg", "비밀번호가 변경되었습니다.");
			m.setMemberPw(newPw);
			session.setAttribute("m", m);
		} else {
			model.addAttribute("msg", "비밀번호 변경이 실패하였습니다.");
		}
		model.addAttribute("loc", "/changePwView.do");
		return "common/msg";
	}
	
	//회원 탈퇴
	@RequestMapping(value="/deleteMember.do")
	public String deleteMember(HttpSession session, Model model) {
		Member member = (Member)session.getAttribute("m");
		int result = service.deleteMember(member);
		if(result > 0) {
			session.invalidate();
			return "redirect:/";
		} else {
			model.addAttribute("msg", "회원 탈퇴에 실패하였습니다.");
			model.addAttribute("loc", "/mypageView.do");
			return "common/msg";
		}
	}
	
	//관심 글 페이지 이동
	@RequestMapping(value="/likeCounselList.do")
	public String likeCounselList(@SessionAttribute Member m, int reqPage, Model model) {
		String memberId = m.getMemberId();
		CounselListData cld = counselService.likeCounselList(memberId, reqPage);
		model.addAttribute("cld", cld);
		return "member/likeCounselList";
	}
	
	//작성 글 페이지 이동
	@RequestMapping(value="/writeCounselList.do")
	public String writeCounselList(@SessionAttribute Member m, int reqPage, Model model) {
		String counselWriter = m.getMemberId();
		CounselListData cld = counselService.writeCounselList(counselWriter, reqPage);
		model.addAttribute("cld", cld);
		return "member/writeCounselList";
	}
	
	//작성 댓글 페이지 이동
	@RequestMapping(value="/writeReplyList.do")
	public String writeReplyList(@SessionAttribute Member m, int reqPage, Model model) {
		String replyWriter = m.getMemberId();
		ReplyListData rld = counselService.writeReplyList(replyWriter, reqPage);
		model.addAttribute("rld", rld);
		return "member/writeReplyList";
	}

}
