package com.beuteu.serivce;

import java.util.List;
import java.util.Map;

import com.beuteu.domain.BoardVO;
import com.beuteu.domain.Search;

public interface BoardService {
	
	public List<Map> boardList(Search search) throws Exception;
	
	public List<Map> boardList2(BoardVO vo) throws Exception;

	public void boardRegist(BoardVO vo) throws Exception;

	public BoardVO boardView(BoardVO vo) throws Exception;

	public void boardDelete(int bno) throws Exception;

	public int pageCount(Search search) throws Exception;

	public void viewPoint(BoardVO vo) throws Exception;





}
