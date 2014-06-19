package com.shine.boards;

import java.util.Date;

public class Report {

	
	private int id, reporterId;
	private Integer noticeId, replyId,adminId;
	private String reason, judgement, offendingEntry;
	private Date created, judgementDay;
	//accepted is an Object so it can be null;
	private Boolean accepted = Boolean.FALSE;
	
	Report(){
		
	}
	
	
	/** Basic constructor with required fields to report a post or reply.
	 * One of noticeId or replyId must be set.
	 * If both are set replyId is used.
	 * 
	 * @param Id
	 * @param reporterId
	 * @param noticeId
	 * @param replyId
	 * @param reason
	 */
	public Report(int id, int reporterId, Integer noticeId, Integer replyId, String reason) {
		this.id=id;
		this.reporterId = reporterId;
		this.noticeId = noticeId;
		this.replyId = replyId;
		this.reason = reason;
	}
	
	
	
	public int getReporterId() {
		return reporterId;
	}
	public void setReporterId(int reporterId) {
		this.reporterId = reporterId;
	}
	public Integer getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}
	public Integer getReplyId() {
		return replyId;
	}
	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getJudgement() {
		return judgement;
	}
	public void setJudgement(String judgement) {
		this.judgement = judgement;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getJudgementDay() {
		return judgementDay;
	}
	public void setJudgementDay(Date judgementDay) {
		this.judgementDay = judgementDay;
	}
	public Boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public Integer getAdminId() {
		return adminId;
	}


	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}


	@Override
	public boolean equals(Object o) {
		if(o != null && o instanceof Report){
			Report other = (Report) o;
			if(other.getId()==id &&
				other.getNoticeId()==noticeId &&
				other.getReplyId() == replyId){
				return true;
			}
			
		}
		return false;
	}


	@Override
	public int hashCode() {
		return id*(noticeId == null?1:noticeId+1)*(replyId == null?1:replyId+1);
	}

	public String getOffendingEntry() {
		return offendingEntry;
	}

	public void setOffendingEntry(String offendingEntry) {
		this.offendingEntry = offendingEntry;
	}
	
	
	
}
