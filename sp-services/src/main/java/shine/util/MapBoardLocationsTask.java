package shine.util;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sp.locations.BoardLocationsMapper;
import com.sp.spring.SpApplicationContext;

@Component
public class MapBoardLocationsTask implements Job {

	private static Logger log = LoggerFactory.getLogger(MapBoardLocationsTask.class);


	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		BoardLocationsMapper boardLocationsMapper = SpApplicationContext.getContext().getBean(BoardLocationsMapper.class);

		log.info("####################### MapBoardLocationsTask STARTING #########################");

		try {
			boardLocationsMapper.populateBoardLocations();
			boardLocationsMapper.getLocationsLocations();
			boardLocationsMapper.populateCountryOnlyBoards();
		} catch (Exception e) {
			log.warn("processing boardid ", e);
			e.printStackTrace();
		}
		log.info("####################### MapBoardLocationsTask FINISHED #########################");

	}

}
