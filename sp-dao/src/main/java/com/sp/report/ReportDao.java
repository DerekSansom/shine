package com.sp.report;

import java.util.List;

import org.springframework.stereotype.Repository;

import shine.dao.ads.ReportUser;

import com.sp.entity.PlayerEntity;
import com.sp.entity.ReportEntity;

@Repository
public interface ReportDao {

	public ReportEntity getReport(int id);

	public void saveReport(ReportEntity report) throws Exception;

	public void handleReport(int id, Boolean accepted, String judgement, int adminId);

	public List<ReportEntity> getPendingReports();

	public void handleUserReport(ReportUser ru, PlayerEntity user);

	public void save(ReportUser ru);

	public ReportUser getUserReport(int reportId);
}
