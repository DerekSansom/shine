package shine.app.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import com.shine.boards.CorpBrand;
import com.sp.entity.CorpBrandEntity;

@Component
public class BrandMapper {
	public CorpBrand entityToDto(CorpBrandEntity entity) {
		CorpBrand brandDto = new CorpBrand();
		brandDto.setId(entity.getId());
		brandDto.setCorporateId(entity.getCorporateId());
		brandDto.setBackgroundimg(entity.getBackgroundimg());
		brandDto.setUrl(entity.getUrl());
		brandDto.setName(entity.getName());
		brandDto.setLinkColour(entity.getLinkColour());
		brandDto.setTextColour(entity.getTextColour());
		brandDto.setLogo(entity.getLogo());
		brandDto.setBgColour(entity.getBgColour());
		return brandDto;
	}

	public CorpBrandEntity dtoToEntity(CorpBrand dto) {
		CorpBrandEntity entity = new CorpBrandEntity();
		entity.setId(dto.getId());
		entity.setCorporateId(dto.getCorporateId());
		entity.setBackgroundimg(dto.getBackgroundimg());
		entity.setUrl(dto.getUrl());
		entity.setName(dto.getName());
		entity.setLinkColour(dto.getLinkColour());
		entity.setTextColour(dto.getTextColour());
		entity.setLogo(dto.getLogo());
		entity.setBgColour(dto.getBgColour());
		return (entity);
	}

	public List<CorpBrand> entityToDto(List<CorpBrandEntity> entities) {

		List<CorpBrand> dtos = new ArrayList<CorpBrand>();
		for (CorpBrandEntity entity : entities) {
			dtos.add(entityToDto(entity));
		}
		return dtos;
	}
}
