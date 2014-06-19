package com.sp.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import shine.app.PlayerManager;

import com.sp.brand.BrandDao;
import com.sp.entity.CorpBrandEntity;
import com.sp.entity.NoticeBoardEntity;
import com.sp.entity.admin.CompanyEntity;

@Component
public class BoardsAdminManager {

	@Autowired
	private BoardsAdminDao boardsAdminDao;

	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private PlayerManager playerManager;

	@Autowired
	private BrandDao brandDao;

	@Transactional
	public void updateBoardOwnership(int boardId, int ownerId, Integer brandId) {

		NoticeBoardEntity board = boardsAdminDao.findById(boardId);
		if (board != null) {
			board.setCreatorId(ownerId);
			board.setPlayer(null);
			if (brandId != null && brandId > 0) {
				CorpBrandEntity brand = brandDao.getBrand(brandId);
				board.setBrand(brand);
			} else {
				board.setBrand(null);
			}

			boardsAdminDao.saveBoard(board);
		}
	}

	public void populateRequest(BoardsAdminOwnershipRequest request) {

		List<String> errors = new ArrayList<>();

		NoticeBoardEntity board = boardsAdminDao.findById(request.getBoardId());
		if (board == null) {
			errors.add(String.format("Board with id: %s not found", request.getBoardId()));
		} else {

			request.setBoardName(board.getName());

			if (board.getCreatorId() != null) {
				CompanyEntity previousOwner = companyDao.findById(board.getCreatorId());
				if (previousOwner != null) {
					request.setPreviousOwnerName("Company: " + previousOwner.getName());
				} else {
					request.setPreviousOwnerName("Previous owner id=" + board.getCreatorId() + " not found");
				}

			} else if (board.getPlayer() != null) {
				request.setPreviousOwnerName("Player: " + board.getPlayer().getUsername());
			}
		}
		CompanyEntity newOwner = companyDao.findById(request.getNewOwnerId());
		if (newOwner == null) {
			errors.add(String.format("Company with id: %s not found", request.getNewOwnerId()));
		} else {
			request.setNewOwnerName(newOwner.getName());

			List<CorpBrandEntity> brands = brandDao.getBrandsByCompany(request.getNewOwnerId());
			if (!CollectionUtils.isEmpty(brands)) {
			// errors.add(String.format("No brands found for company %s",
			// newOwner.getName()));
			// } else {
				// Map<Integer, String> brandMap = new HashMap<>();
				// for (CorpBrandEntity corpBrandEntity : brands) {
				// brandMap.put(corpBrandEntity.getId(),
				// corpBrandEntity.getName());
				// }
				request.setNewOwnerBrands(brands);
			}

		}
		if (!errors.isEmpty()) {
			throw new SpValidationException(errors);
		}

	}

}
