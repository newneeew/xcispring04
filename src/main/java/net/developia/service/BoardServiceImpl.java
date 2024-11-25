package net.developia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.developia.domain.BoardVO;
import net.developia.domain.Criteria;
import net.developia.mapper.BoardMapper;

@Log4j
@Service
@AllArgsConstructor
// 비즈니스 로직에만 집중
public class BoardServiceImpl implements BoardService {

	private BoardMapper mapper;

	public void register(BoardVO board) throws Exception {
		log.info("register..." + board);
		mapper.insertSelectKey(board);
	}

	public BoardVO get(Long bno) {
		log.info("get......" + bno);
		BoardVO board = mapper.read(bno);
		if (board == null)
			throw new RuntimeException(bno + "게시물이 없음"); // 사용자에러
		return board;
	}

	public boolean modify(BoardVO board) throws Exception {
		log.info("modify......" + board);
		if (mapper.update(board) == 0)
			throw new RuntimeException(board.getBno() + "빈 게시물이 수정되지 않음");
		return true;
	}


	@Override
	public boolean remove(Long bno) throws Exception {
		log.info("remove......" + bno);
		if (mapper.delete(bno) == 0) // 삭제되지 않으면-
			throw new RuntimeException(bno + "빈 게시물이 삭제되지 않음");
		return true;
	}

//	@Override
//	public List<BoardVO> getList() throws Exception {
//		return mapper.getList();
//	}
	
	public List<BoardVO> getList(Criteria cri) throws Exception {
		log.info("get List with criteria:" + cri);
		return mapper.getListWithPaging(cri);
	}
	
	@Override
	public int getTotal(Criteria cri) {
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}
}
