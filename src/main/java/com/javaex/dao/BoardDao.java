package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	//글전체 가져오기
	public List<BoardVo> select(String keyword) {
		System.out.println("boardDao/select");
		
		return sqlSession.selectList("board.select", keyword);
	}

	//글 1개 가져오기
	public BoardVo selectBoard(int no) {
		System.out.println("boardDao/selectBoard");
		
		return sqlSession.selectOne("board.selcetBoard", no);
	}

	//조회수 업데이트
	public int updateHit(int no) {
		System.out.println("boardDao/updateHit");
		
		return sqlSession.update("board.updateHit", no);
	}

	//글저장
	public int insert(BoardVo boardVo) {
		System.out.println("boardDao/insert");
		
		return sqlSession.insert("board.insert", boardVo);
	}
	
	//글삭제
	public int delete(BoardVo boardVo) {
		System.out.println("boardDao/delete");
		
		return sqlSession.delete("board.delete", boardVo);
	}
	
	//글수정
	public int update(BoardVo boardVo) {
		System.out.println("boardDao/update");
		
		return sqlSession.update("board.update", boardVo);
	}
	
	
	//글전체 가져오기 페이징
	public List<BoardVo> select2(int startRnum, int endRnum) {
		System.out.println("boardDao/select2");
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		
		return sqlSession.selectList("board.select2", map);
	}
	
	//토탈카운트
	public int totalCount() {
		System.out.println("boardDao/totalCount");
		return sqlSession.selectOne("board.totalCount");
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
