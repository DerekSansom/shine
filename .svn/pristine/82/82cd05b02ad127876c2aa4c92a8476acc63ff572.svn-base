package com.sp.brand;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sp.entity.CorpBrandEntity;

@Repository
public class BrandDaoImpl implements BrandDao {

	private static final Logger logger = LoggerFactory.getLogger(BrandDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public CorpBrandEntity getBrand(int id) {

		CorpBrandEntity brand = entityManager.find(CorpBrandEntity.class, id);
		return brand;
	}

	@Override
	public List<CorpBrandEntity> getBrandsByCompany(int companyId) {

		TypedQuery<CorpBrandEntity> query = entityManager.createQuery(
				"from CorpBrandEntity cb where cb.corporateId = :companyId", CorpBrandEntity.class);
		query.setParameter("companyId", companyId);

		List<CorpBrandEntity> list = query.getResultList();
		return list;
	}

	@Override
	public List<CorpBrandEntity> getAllBrands(int start, int count) {
		String queryStr = "from CorpBrandEntity";

		TypedQuery<CorpBrandEntity> query = entityManager.createQuery(queryStr, CorpBrandEntity.class);
		query.setMaxResults(count);
		query.setFirstResult(start);

		List<CorpBrandEntity> list = query.getResultList();
		return list;
	}
	
	@Override
	@Transactional
	public int saveOrUpdateBrand(CorpBrandEntity brand) {
		try {
			entityManager.persist(brand);
		}
		catch (Exception e) {
		    logger.error("saveOrUpdateBrand Exception - " + e.getMessage() + System.lineSeparator() + brand.toString());
		}
		return brand.getId();
	}
}
