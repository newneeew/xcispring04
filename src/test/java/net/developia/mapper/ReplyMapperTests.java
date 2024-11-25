package net.developia.mapper;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;
import net.developia.domain.Criteria;
import net.developia.domain.ReplyVO;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:**/root-context.xml")
@Log4j


public class ReplyMapperTests {

	private Long[] bnoArr = {
			1000420L,
			1000419L,
			1000418L,
			1000417L,
			1000416L,
	};
	
	@Autowired
	private ReplyMapper mapper;

	@Ignore
	@Test
	public void testCreate() {
		
		IntStream.rangeClosed(1,  10).forEach(i -> {
			ReplyVO vo = new ReplyVO();
			
			// 게시물의 번호
			vo.setBno(bnoArr[i%5]);
			vo.setReply("댓글 테스트 " + i);
			vo.setReplyer("replyer" + i);
			
			mapper.insert(vo);
		});
	}
	
	@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	@Test
	public void testRead() {
		Long targetRno = 1L; //있는 번호로 실행
		
		ReplyVO vo = mapper.read(targetRno);
		
		log.info(vo);
	}
	
	//@Transactional //다 dml인데 testcode에서 한 번만 필요하면 ignore, transactional은 test code 진행 후 rollback
	@Test
	public void testDelete() {
		Long targetRno = 1L;
		//mapper.delete(targetRno);
		assertEquals(1,  mapper.delete(targetRno));
	}
	
	@Test
	public void testUpdate() {
		
		Long targetRno = 10L;
		ReplyVO vo = mapper.read(targetRno);
		
		vo.setReply("Update Reply ");
		
		int count = mapper.update(vo);
		
		log.info("UPDATE COUNT: " + count);
		assertEquals(1, count);
	}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		
		List<ReplyVO> replies = mapper.getListWithPaging(cri,  bnoArr[0]); //bnoArr[0]이 있는 번호인지 확인할 것
		
		replies.forEach(reply -> log.info(reply));
	}
}
