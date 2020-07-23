package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;


@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;
	
	//방명록 리스트 가져오기
	public List<GuestbookVo> list(){
		System.out.println("guestbookService/getList");
		
		return guestbookDao.select();
	}

	//방명록 글 저장
	public int write(GuestbookVo guestbookVo){
		System.out.println("guestbookService/write");
		
		return guestbookDao.insert(guestbookVo);
	}
	
	//방명록 글 삭제
	public int remove(GuestbookVo guestbookVo){
		System.out.println("guestbookService/remove");
		
		return guestbookDao.delete(guestbookVo);
	}
	
	//방명록 글 저장(ajax)
	public GuestbookVo addGuest(GuestbookVo guestbookVo) {
		
		//저장
		guestbookDao.insertSelectKey(guestbookVo);
		
		int no = guestbookVo.getNo(); //연구해볼것
		System.out.println("selectkey로 받은 no값:" + no );
		
		//저장한 데이터 가져오기
		return guestbookDao.selectByNo(no);
	}
	
	
	
	
	
}
