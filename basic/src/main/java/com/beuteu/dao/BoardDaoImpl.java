/*
 *------------------------------------------------------------------------------
 * AccCdServiceImpl.java
 * DESC : AccCdService 구현 ?��?��?��
 *
 * PROJ :  ?��?��?���?
 * Copyright 2012 LG CNS All rights reserved
 *------------------------------------------------------------------------------
 *                  �?         �?         ?��         ?��
 *------------------------------------------------------------------------------
 *   DATE       AUTHOR                  DESCRIPTION
 * -----------    -----------  -------------------------------------------------
 * 2015. 08. 18.   고인?��     최초 ?��로그?�� ?��?��
 *------------------------------------------------------------------------------
 */
package com.beuteu.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.beuteu.domain.BoardVO;
import com.beuteu.domain.Search;
import com.beuteu.serivce.BoardService;


@Service("boardDao")
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static final String NAME_SPACE = "com.spring.web.mapper.Mapper.";

	@Override
	public List<Map> boardList(Search search) throws Exception {
		return sqlSession.selectList(NAME_SPACE + "boardList",search);
	}
	
	@Override
	public List<Map> boardList2(BoardVO vo) throws Exception {
		return sqlSession.selectList(NAME_SPACE + "boardList2",vo);
	}

	@Override
	public void boardRegist(BoardVO vo) throws Exception {
		sqlSession.insert(NAME_SPACE + "boardRegist",vo);
	}

	@Override
	public BoardVO boardView(BoardVO vo) throws Exception {
		return sqlSession.selectOne(NAME_SPACE + "boardView",vo);

	}

	@Override
	public void boardDelete(int bno) throws Exception {
		sqlSession.delete(NAME_SPACE + "boardDelete",bno);
	}

	@Override
	public int pageCount(Search search) throws Exception {
		return sqlSession.selectOne(NAME_SPACE + "pageCount",search);
	}

	@Override
	public void viewPoint(BoardVO vo) throws Exception {
		sqlSession.update(NAME_SPACE + "viewPoint", vo);
		
	}
		

}
