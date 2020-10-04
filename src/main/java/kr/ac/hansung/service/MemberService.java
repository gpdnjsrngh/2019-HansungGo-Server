package kr.ac.hansung.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.MemberDAO;
import kr.ac.hansung.model.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	public List<Member> getCurrent() {
		return memberDAO.getMembers();
	}
	
	public void insert(Member member) {
		if(isExist(member.getId(), member.getName(), member.getPhone())) {
			System.out.println("존재한다.");
			memberDAO.update(member);
		}
		else
			memberDAO.insert(member);
			
	}
	
	public Member search(String id, String phone) {
		return memberDAO.getMember(id, phone);
	}
	
	public boolean isExist(String id, String name, String phone) {
		if(memberDAO.getRowCount(id, name, phone)>=1) {
			return true;
		}
		
		else {
			return false;
		}
	
	}
	
	public void memberSort(List<Member> members, int n)
	{
		MemberComparatorByCoinCount comp = new MemberComparatorByCoinCount();
		Collections.sort(members, comp);
	}
		
}

class MemberComparatorByCoinCount implements Comparator<Member> {
	@Override
	public int compare(Member o1, Member o2) {
		int firstValue = Integer.parseInt(o1.getCoinCount());
		int secondValue = Integer.parseInt(o2.getCoinCount());
		
		//Order by descending
		if(firstValue>secondValue) {
			return -1;
		}
		else if(firstValue<secondValue) {
			return 1;
		}
		else {
			return 0;
		}
			
	}
}
