package shine.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import shine.dao.exception.ShineException;

import com.shine.objects.ShineLocation;
import com.shine.objects.ShineObject;
import com.shine.objects.Player;

@Component
public class LocationObjectsHandler extends BaseHandler {

	@Autowired
	private ObjectManager objectManager;

	public List<ShineObject> initialReg(int userId, ShineLocation loc, ShineLocation userloc, double radius)
			throws ShineException {

		// if viewLoc is not set then send objects for userloc
		if (loc == null) {
			loc = userloc;
		}
			if (userId > 0) {
			registerUserLoc(userloc, userId);
			}
		List<ShineObject> objects = objectManager.getBoards(userloc, loc, radius);
			return objects;


	}

	private Player registerUserLoc(ShineLocation userloc, int userId) throws ShineException {
		return new PlayerManager().getUser(userId, userloc);
	}
}
