package com.sp.admin;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.sp.entity.NoticeBoardEntity;

@Repository
public class BoardsAdminDaoImpl implements BoardsAdminDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public NoticeBoardEntity findById(int id) {

		NoticeBoardEntity board = entityManager.find(NoticeBoardEntity.class, id);
		return board;
	}


	@Override
	public void saveBoard(NoticeBoardEntity board) {
		// TODO Auto-generated method stub

	}
}
