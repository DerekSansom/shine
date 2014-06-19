package shine.app;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import shine.app.mapper.BrandMapper;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.boards.CorpBrand;
import com.sp.brand.BrandDao;
import com.sp.entity.CorpBrandEntity;
import com.sp.entity.UserEntity;
import com.sp.user.UserDao;

@Component
public class BrandManager {
	
	private static final Logger logger = LoggerFactory.getLogger(BrandManager.class);

	@Autowired
	private BrandMapper mapper;
	
	@Autowired
	private BrandDao brandDao;
	
	@Autowired
	private UserDao userDao;
	
	public List<CorpBrand> getBrandsForUserId(Integer userId) {
		List<CorpBrand> retBrands = null;
		Integer companyId = null;
		if(userId!=null) {
			UserEntity userEntity = userDao.getUserById(userId);
			companyId = userEntity.getCorp_id();
			retBrands = getBrandsForCompanyId(companyId);
		}
		return retBrands;
	}
	
	public List<CorpBrand> getBrandsForCompanyId(Integer companyId) {
		List<CorpBrand> brands = new ArrayList<CorpBrand>(); 
		if(companyId != null){
			try{
				brands = mapper.entityToDto(brandDao.getBrandsByCompany(companyId));
			}catch(Exception e){
				//log.error("Failed to retrieve brands for user", e);
				throw new RuntimeException("Could not retrieve brands for company", e);
			}
		}
		return  brands;
	}
	
	public List<CorpBrand> getAllBrandsPaginated(int start, int count) throws ShineException {
		List<CorpBrandEntity> brandEntities = brandDao.getAllBrands(start, count);
		List<CorpBrand> brands = mapper.entityToDto(brandEntities);
		return brands;
	}
	
	public int saveOrUpdateBrand(CorpBrand brandDto) throws ShineException {
		CorpBrandEntity brandEntity = mapper.dtoToEntity(brandDto);
		brandDao.saveOrUpdateBrand(brandEntity);
		int brandId = brandEntity.getId();
		if (brandId > 0) {
			return brandId;
		} else {
			throw new ShineException(GeneralError.SYSTEM_ERROR,
					"Failed to create brand");
		}
	}
}

