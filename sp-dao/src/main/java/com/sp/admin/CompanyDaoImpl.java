package com.sp.admin;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.sp.entity.admin.CompanyEntity;

@Repository
public class CompanyDaoImpl implements CompanyDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public CompanyEntity findById(long id) {

		CompanyEntity board = entityManager.find(CompanyEntity.class, id);
		return board;
	}


}
