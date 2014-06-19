package com.sp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "reports")
public class ReportEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "reporterid")
	private int reporterId;

	@Column(name = "noticeid")
	private Integer noticeId;

	@Column(name = "replyid")
	private Integer replyId;

	@Column(name = "adminid")
	private Integer adminId;

	@Column(name = "reason")
	private String reason;

	@Column(name = "judgement")
	private String judgement;

	@Column(name = "created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Column(name = "judgement_day", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date judgementDay;

	@Column(name = "accepted")
	private Boolean accepted = Boolean.FALSE;

	ReportEntity(){
		
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
	public ReportEntity(int id, int reporterId, Integer noticeId, Integer replyId, String reason) {
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
		if(o != null && o instanceof ReportEntity){
			ReportEntity other = (ReportEntity) o;
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
	
	
	
}
