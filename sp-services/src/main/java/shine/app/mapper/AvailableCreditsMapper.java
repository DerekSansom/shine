package shine.app.mapper;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import com.shine.AvailableCredits;
import com.sp.entity.AvailableCreditsEntity;

@Component
public class AvailableCreditsMapper {
	
	private static Mapper mapper = new DozerBeanMapper();

	public AvailableCredits entityToDto(AvailableCreditsEntity entity) {
		if (entity == null) {
			return null;
		}
		return new AvailableCredits(entity.getId(), entity.getAdvert(), entity.getBoard());
	}

	public AvailableCreditsEntity dtoToEntity(AvailableCredits dto) {
		return mapper.map(dto, AvailableCreditsEntity.class);
	}
}
