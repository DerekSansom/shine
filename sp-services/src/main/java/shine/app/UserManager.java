package shine.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import shine.app.mapper.AvailableCreditsMapper;

import com.shine.AvailableCredits;
import com.sp.admin.CompanyDao;
import com.sp.credits.AvailableCreditsDao;
import com.sp.entity.AvailableCreditsEntity;
import com.sp.entity.UserEntity;
import com.sp.user.UserDao;
//import com.sp.entity.UserEntity;

@Component
public class UserManager extends BaseHandler {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AvailableCreditsDao availableCreditsDao;
	
	@Autowired
	private AvailableCreditsMapper availableCreditsMapper;

	public AvailableCredits getAvailableCreditsForUserId(int userId) {
		
		AvailableCreditsEntity availableCreditsEntity = availableCreditsDao.getAvailableCreditsByUserId(userId);
		AvailableCredits availableCredits = availableCreditsMapper.entityToDto(availableCreditsEntity);
		//currently hacked to this to test rest of use case
		return availableCredits;
	}
	
	public Integer getCompanyForUserId(int userId) {
		UserEntity userEntity = userDao.getUserById(userId);
		return userEntity.getCorp_id();
	}
}
