package kr.co.counsel.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import kr.co.counsel.model.service.CounselService;
import kr.co.counsel.model.vo.Counsel;
import kr.co.counsel.model.vo.CounselImg;
import kr.co.counsel.model.vo.CounselLike;
import kr.co.counsel.model.vo.CounselListData;
import kr.co.counsel.model.vo.CounselViewData;
import kr.co.member.model.service.MemberService;
import kr.co.member.model.vo.Member;
import kr.co.reply.model.vo.Reply;

@Controller
public class CounselController {

	@Autowired
	private CounselService service;
	
	@Autowired
	private MemberService memberService;
	
	//상담글 리스트
	@RequestMapping(value="/counselList.do")
	public String counselList(int reqPage, Model model) {
		CounselListData cld = service.selectCounselList(reqPage);
		model.addAttribute("cld", cld);
		return "counsel/counselList";
	}
	
	//상담글 작성 페이지 이동
	@RequestMapping(value="/counselFrm.do")
	public String counselFrm() {
		return "counsel/counselFrm";
	}
	
	//상담글 작성
	@RequestMapping(value="/insertCounsel.do")
	public String insertCounsel(Counsel counsel, MultipartFile[] addImgs, HttpSession session, HttpServletRequest request, Model model) {
		//파일목록을 저장할 List
		ArrayList<CounselImg> list = new ArrayList<CounselImg>();
		//상담글 등록 여부를 위한 값
		int result = 0;
		//첨부파일이 없는 경우
		if(addImgs[0].isEmpty()) {
			result = service.insertCounsel(counsel);
		} else {
			//파일 업로드 경로 설정
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/counsel/");
			
			for(MultipartFile file : addImgs) {
				//업로드할 파일명(확장자 포함)
				String filename = file.getOriginalFilename();
				//파일명
				String onlyFilename = filename.substring(0, filename.indexOf(".")); //test
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
					// 중복처리가 끝난 파일명(filepath)으로 파일을 업로드
					FileOutputStream fos = new FileOutputStream(new File(savePath + filepath));
					// 업로드 속도 증가를 위한 보조스트림
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					// 파일 업로드
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
				CounselImg ci = new CounselImg();
				ci.setFilename(filename);
				ci.setFilepath(savePath);
				list.add(ci);
			}
			result = service.insertCounsel(counsel, list);
		}
		
		if(result > 0) {
			model.addAttribute("msg", "상담이 등록되었습니다.");
		} else {
			model.addAttribute("msg", "상담이 등록되지 않았습니다.");
		}
		model.addAttribute("loc", "/counselList.do?reqPage=1");
		return "common/msg";
	}
	
	//상담글 수정
	@RequestMapping(value="/updateCounsel.do")
	public String updateCounsel(int oldImgChk, Counsel counsel, MultipartFile[] addImgs, HttpSession session, HttpServletRequest request, Model model) {
		if(oldImgChk == 0) {
			int result = service.updateCounsel(counsel);
			if(result > 0) {
				return "redirect:/counselView.do?memberId=" + counsel.getCounselWriter() + "&counselNo=" + counsel.getCounselNo();
			} else {
				return "redirect:/";
			}
		} else {
			//파일목록을 저장할 List
			ArrayList<CounselImg> list = new ArrayList<CounselImg>();
			//상담글 등록 여부를 위한 값
			int result = 0;
			//첨부파일이 없는 경우
			if(addImgs[0].isEmpty()) {
				result = service.updateCounselNoImg(counsel);
			} else {
				//파일 업로드 경로 설정
				String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/counsel/");
				
				for(MultipartFile file : addImgs) {
					//업로드할 파일명(확장자 포함)
					String filename = file.getOriginalFilename();
					//파일명
					String onlyFilename = filename.substring(0, filename.indexOf(".")); //test
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
						// 중복처리가 끝난 파일명(filepath)으로 파일을 업로드
						FileOutputStream fos = new FileOutputStream(new File(savePath + filepath));
						// 업로드 속도 증가를 위한 보조스트림
						BufferedOutputStream bos = new BufferedOutputStream(fos);
						// 파일 업로드
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
					CounselImg ci = new CounselImg();
					ci.setFilename(filename);
					ci.setFilepath(savePath);
					list.add(ci);
				}
				result = service.updateCounsel(counsel, list);
			}
			
			if(result > 0) {
				model.addAttribute("msg", "상담글이 수정되었습니다.");
			} else {
				model.addAttribute("msg", "상담글이 수정되지 않았습니다.");
			}
			model.addAttribute("loc", "/counselView.do?memberId=" + counsel.getCounselWriter() + "&counselNo=" + counsel.getCounselNo());
			return "common/msg";
		}
	}
	
	//조회수 증가, 상담글 상세보기로 이동
	@RequestMapping(value="/updateReadCount.do")
	public String updateReadCount(String memberId, int counselNo) {
		int result = service.updateReadCount(counselNo);
		if(result > 0) {
			return "redirect:/counselView.do?memberId=" + memberId + "&counselNo=" + counselNo;
		} else {
			return "redirect:/";			
		}
	}
	
	//상담글 상세보기
	@RequestMapping(value="/counselView.do")
	public String counselView(String memberId, int counselNo, Model model) {
		CounselViewData cvd = null;
		int counselLike = 0;
		CounselLike cl = new CounselLike();
		cl.setMemberId(memberId);
		cl.setCounselNo(counselNo);
		if(memberId != null) {
			cvd = service.counselView(counselNo);
			counselLike = service.counselLikeView(cl);
		} else {
			cvd = service.counselView(counselNo);
		}
		if(cvd != null) {
			Member member = memberService.selectOneMember(cvd.getCounsel().getCounselWriter());
			int replyCnt = service.countReply(counselNo);
			int likeCnt = service.countLike(counselNo);
			model.addAttribute("cvd", cvd);
			model.addAttribute("m", member);
			model.addAttribute("replyCnt", replyCnt);
			model.addAttribute("likeCnt", likeCnt);
			if(counselLike != -1) {
				model.addAttribute("counselLike", counselLike);
			}
			return "counsel/counselView";
		} else {
			model.addAttribute("msg", "삭제된 글입니다.");
			model.addAttribute("loc", "/counselList.do?reqPage=1");
			return "common/msg";
		}
	}
	
	//상담글 삭제
	@RequestMapping(value="/deleteCounsel.do")
	public String deleteCounsel(@SessionAttribute Member m, int counselNo) {
		CounselLike cl = new CounselLike();
		cl.setMemberId(m.getMemberId());
		cl.setCounselNo(counselNo);
		int result = service.deleteCounsel(cl);
		if(result > 0) {
			return "redirect:/counselList.do?reqPage=1";
		} else {
			return "redirect:/";
		}
	}
	
	//상담글 수정 페이지 이동
	@RequestMapping(value="/updateCounselFrm.do")
	public String updateCounselFrm(int counselNo, Model model) {
		CounselViewData cvd = service.selectOneCounsel(counselNo);
		model.addAttribute("cvd", cvd);
		return "counsel/updateCounselFrm";
	}
	
	//댓글 등록
	@RequestMapping(value="/insertReply.do")
	public String insertReply(@SessionAttribute Member m, Reply reply, Model model) {
		reply.setReplyWriter(m.getMemberId());
		int result = service.insertReply(reply);
		if(result > 0) {
			int replyCnt = service.countReply(reply.getCounselNo());
			model.addAttribute("replyCnt", replyCnt);			
			return "redirect:/counselView.do?counselNo=" + reply.getCounselNo();
		} else {
			return "redirect:/";
		}
	}
	
	//댓글 수정
	@RequestMapping(value="/updateReply.do")
	public String updateReply(@SessionAttribute Member m, Reply reply) {
		reply.setReplyWriter(m.getMemberId());
		int result = service.updateReply(reply);
		if(result > 0) {
			return "redirect:/counselView.do?counselNo=" + reply.getCounselNo();			
		} else {
			return "redirect:/";
		}
	}
	
	//댓글 삭제
	@RequestMapping(value="/deleteReply.do")
	public String deleteReply(@SessionAttribute Member m, Reply reply) {
		reply.setReplyWriter(m.getMemberId());
		int result = service.deleteReply(reply);
		if(result > 0) {
			return "redirect:/counselView.do?counselNo=" + reply.getCounselNo();			
		} else {
			return "redirect:/";
		}
	}
	
	//공감하기
	@ResponseBody
	@RequestMapping(value="/likeUp.do")
	public HashMap<String, Object> likeUp(CounselLike cl, Model model) {
		int result = service.likeUp(cl);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("loc", "/counselView.do?counselNo=" + cl.getCounselNo() + "&memberId=" + cl.getMemberId());
		if(result > 0) {
			map.put("msg", "해당 글을 공감합니다.");
			return map;
		} else {
			map.put("msg", "조회 오류");
			return map;
		}
	}
	
	//공감취소
	@ResponseBody
	@RequestMapping(value="/likeDown.do")
	public HashMap<String, Object> likeDown(CounselLike cl, Model model) {
		int result = service.likeDown(cl);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("loc", "/counselView.do?counselNo=" + cl.getCounselNo() + "&memberId=" + cl.getMemberId());
		if(result > 0) {
			map.put("msg", "해당 글을 공감하지 않습니다.");
			return map;
		} else {
			map.put("msg", "조회 오류");
			return map;
		}
	}
	
	//상담글 검색
	@RequestMapping(value="/searchCounsel.do")
	public String searchCounsel(int reqPage, String keyword, Model model) {
		CounselListData cld = service.searchCounsel(reqPage, keyword);
		model.addAttribute("cld", cld);
		return "counsel/counselList";
	}

}
