package shine.dao.ads;

import java.util.Date;

public class ReportUser {

	private int id, userId;
	private Integer adminId;
	private String reason, judgement;
	private Date created, judgementDay;
	//accepted is an Object so it can be null;
	private Boolean accepted = Boolean.FALSE;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
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

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

}
