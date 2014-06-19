package com.sp.admin;

import com.sp.entity.NoticeBoardEntity;

public interface BoardsAdminDao {

	NoticeBoardEntity findById(int id);

	void saveBoard(NoticeBoardEntity board);

}
