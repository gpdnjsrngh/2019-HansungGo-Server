package kr.ac.hansung.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.hansung.model.Member;
import kr.ac.hansung.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/members")
	public String showOffers(Model model) {
		//service에서 members 정렬시켜서 저장
		//1. 코인 개수v  2. 시각 정렬은 그냥 우리가 판단합세
		List<Member> members = memberService.getCurrent();
		
		memberService.memberSort(members, members.size());
		model.addAttribute("members", members);
		
		return "members";
	}
	
	@RequestMapping("/create")
	public String createMember(Model model, String id, String name, String phone, String coinCount) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG, Locale.getDefault());
		
		String updateTime = dateFormat.format(date);
		
		Member newMember = new Member();
		newMember.setId(id);
		newMember.setName(name);
		newMember.setPhone(phone);
		newMember.setCoinCount(coinCount);
		newMember.setUpdateTime(updateTime);
		
		//insert하기 전에 같은 id의 멤버가 있는지 확인
		
		//있으면 update 없으면 insert
		memberService.insert(newMember);
		
		return showOffers(model);
	}
	
	@RequestMapping("/search")
	public String searchMember(Model model, String name, String phone) {
		System.out.println(name);
		Member searchedMember = new Member();
		
		searchedMember = memberService.search(name, phone);
		model.addAttribute("memberId", searchedMember.getId());
		return "members";
	}
	
	
	@RequestMapping(value="/unitysearch")
	@ResponseBody
	public String unitysearchMember(String name, String phone) {
		System.out.println(name);
		Member searchedMember = new Member();
		
		searchedMember = memberService.search(name, phone);
		return searchedMember.getId();
	}
}
