package shine.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import com.shine.boards.NoticeBoard;
import com.sp.entity.NoticeBoardEntity;

@Component
public class BoardMapper {

	private static Mapper mapper = new DozerBeanMapper();

	public NoticeBoard entityToDto(NoticeBoardEntity entity) {

		NoticeBoard board = mapper.map(entity, NoticeBoard.class);
		if (entity.getPlayer() != null) {
			board.setCreator(entity.getPlayer().getUsername());
			board.setCreatorId(entity.getPlayer().getId());
		}

		return board;

	}

	public NoticeBoardEntity dtoToEntity(NoticeBoard dto) {

		return mapper.map(dto, NoticeBoardEntity.class);

	}

	public List<NoticeBoard> entityToDto(List<NoticeBoardEntity> entity) {

		List<NoticeBoard> dtos = new ArrayList<NoticeBoard>();
		for (NoticeBoardEntity noticeBoardEntity : entity) {
			dtos.add(entityToDto(noticeBoardEntity));
		}

		return dtos;

	}

	public List<NoticeBoardEntity> dtoToEntity(List<NoticeBoard> dto) {

		List<NoticeBoardEntity> entities = new ArrayList<NoticeBoardEntity>();
		for (NoticeBoard noticeBoard : dto) {
			entities.add(dtoToEntity(noticeBoard));
		}

		return entities;

	}

}
