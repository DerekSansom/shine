package com.sp.board;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.sp.entity.NoticeBoardEntity;

@Repository
public class BoardCreationDaoImpl implements BoardCreationDao {


	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void createNoticeBoard(NoticeBoardEntity board) {

		entityManager.persist(board);
	}

}
