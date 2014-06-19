package shine.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sp.entity.loc.Location;
import com.sp.locations.LocationsDao;

@Component
public class LocationManager extends BaseHandler {

	@Autowired
	private LocationsDao locationsDao;

	public List<Location> getUnlocatedLocations() {

		List<Location> unlocs = locationsDao.getUnlocatedLocations();
		return unlocs;
	}

}
