package shine.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import com.shine.boards.Advert;
import com.sp.entity.ad.AdvertEntity;

@Component
public class AdMapper {

	private static Mapper mapper = new DozerBeanMapper();

	public Advert entityToDto(AdvertEntity entity) {

		return mapper.map(entity, Advert.class);

	}

	public AdvertEntity dtoToEntity(Advert dto) {

		return mapper.map(dto, AdvertEntity.class);

	}

	public List<Advert> entityToDto(List<AdvertEntity> entities) {

		List<Advert> dtos = new ArrayList<Advert>();
		for (AdvertEntity entity : entities) {
			dtos.add(entityToDto(entity));
		}

		return dtos;

	}

	public List<AdvertEntity> dtoToEntity(List<Advert> dtos) {

		List<AdvertEntity> entities = new ArrayList<AdvertEntity>();
		for (Advert dto : dtos) {
			entities.add(dtoToEntity(dto));
		}

		return entities;

	}

}
