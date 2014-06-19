package shine.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import com.shine.boards.Notice;
import com.shine.boards.Reply;
import com.sp.entity.NoticeEntity;
import com.sp.entity.ReplyEntity;

@Component
public class NoticeMapper {

	private static Mapper mapper = new DozerBeanMapper();

	public Reply entityToDto(ReplyEntity entity) {

		Reply reply = mapper.map(entity, Reply.class);
		if (entity.getPlayer() != null) {
			reply.setAuthor(entity.getPlayer().getUsername());
			reply.setCreatorId(entity.getPlayer().getId());
		}

		return reply;

	}

	public ReplyEntity dtoToEntity(Reply dto) {

		return mapper.map(dto, ReplyEntity.class);

	}

	public List<Reply> replyEntitiesToDto(List<ReplyEntity> entity) {

		List<Reply> dtos = new ArrayList<Reply>();
		for (ReplyEntity replyEntity : entity) {
			dtos.add(entityToDto(replyEntity));
		}

		return dtos;

	}

	public List<ReplyEntity> replyDtosToEntity(List<Reply> dto) {

		List<ReplyEntity> entities = new ArrayList<ReplyEntity>();
		for (Reply reply : dto) {
			entities.add(dtoToEntity(reply));
		}

		return entities;

	}
	public Notice entityToDto(NoticeEntity entity) {

		Notice notice = mapper.map(entity, Notice.class);
		if (entity.getPlayer() != null) {
			notice.setAuthor(entity.getPlayer().getUsername());
			notice.setCreatorId(entity.getPlayer().getId());
		} else {
			notice.setAuthor("StreetPin");
			notice.setCreatorId(1);

		}

		return notice;

	}

	public NoticeEntity dtoToEntity(Notice dto) {

		return mapper.map(dto, NoticeEntity.class);

	}

	public List<Notice> entityToDto(List<NoticeEntity> entity) {

		List<Notice> dtos = new ArrayList<Notice>();
		for (NoticeEntity noticeEntity : entity) {
			Notice dto = entityToDto(noticeEntity);
			if (noticeEntity.getPlayer() != null) {
				dto.setCreatorId(noticeEntity.getPlayer().getId());
				dto.setAuthor(noticeEntity.getPlayer().getUsername());
			}
			dtos.add(dto);
		}

		return dtos;

	}

	public List<NoticeEntity> dtoToEntity(List<Notice> dto) {

		List<NoticeEntity> entities = new ArrayList<NoticeEntity>();
		for (Notice notice : dto) {
			entities.add(dtoToEntity(notice));
		}

		return entities;

	}
}
