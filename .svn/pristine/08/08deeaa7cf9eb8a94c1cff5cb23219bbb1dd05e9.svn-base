package shine.dao.msg;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import com.shine.mail.Mail;
import com.sp.entity.msg.MailEntity;

public class MailMapper {

	private static Mapper mapper = new DozerBeanMapper();

	public Mail entityToDto(MailEntity entity) {

		Mail mail = mapper.map(entity, Mail.class);
		return mail;

	}

	public MailEntity dtoToEntity(Mail dto) {

		return mapper.map(dto, MailEntity.class);

	}

	public List<Mail> entityToDto(List<MailEntity> entities) {

		List<Mail> dtos = new ArrayList<Mail>();
		for (MailEntity entity : entities) {
			dtos.add(entityToDto(entity));
		}

		return dtos;

	}

	public List<MailEntity> dtoToEntity(List<Mail> dtos) {

		List<MailEntity> entities = new ArrayList<MailEntity>();
		for (Mail dto : dtos) {
			entities.add(dtoToEntity(dto));
		}

		return entities;

	}

}
