package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	//@Autowired
	private final BoardMapper boardMapper;
	
	//private final LogAdvice advice;
	
	@Override
	public void register(BoardVO board) {
		
		boardMapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(Long bno) {

		return boardMapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {

		return boardMapper.update(board)  == 1;
	}

	@Override
	public boolean remove(Long bno) {
		
		return boardMapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		
		return boardMapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		return boardMapper.getTotalCount(cri);
	}

}
