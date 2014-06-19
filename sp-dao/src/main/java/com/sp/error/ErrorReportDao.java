package com.sp.error;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.sp.entity.error.ErrorReportEntity;

@Repository
public class ErrorReportDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void saveReport(ErrorReportEntity report) {

		entityManager.persist(report);
	}

}
