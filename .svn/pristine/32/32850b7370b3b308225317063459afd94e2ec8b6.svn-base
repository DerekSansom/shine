package shine.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import com.shine.boards.Report;
import com.sp.entity.ReportEntity;

@Component
public class ReportMapper {

	private static Mapper mapper = new DozerBeanMapper();

	public Report entityToDto(ReportEntity entity) {

		Report board = mapper.map(entity, Report.class);
		return board;

	}

	public ReportEntity dtoToEntity(Report dto) {

		return mapper.map(dto, ReportEntity.class);

	}

	public List<Report> entityToDto(List<ReportEntity> entities) {

		List<Report> dtos = new ArrayList<Report>();
		for (ReportEntity entity : entities) {
			dtos.add(entityToDto(entity));
		}
		return dtos;
	}


}
