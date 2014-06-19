package shine.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import shine.app.mapper.BoardMapper;

import com.shine.boards.NoticeBoard;
import com.shine.objects.ShineLocation;
import com.sp.board.BoardDao;
import com.sp.entity.NoticeBoardEntity;

@Component
public class BoardSearchManager extends BaseHandler {

	@Autowired
	private BoardDao bdao;

	@Autowired
	private BoardMapper boardMapper;

	public List<NoticeBoard> getBoardSearch(ShineLocation loc, String s, int t, int resultsToFind) {

		List<NoticeBoardEntity> entities = new ArrayList<NoticeBoardEntity>();
		entities.addAll(bdao.getBoardsSearch(s));

		return boardMapper.entityToDto(entities);
	}

}
