package com.shine.objects;

public class Emerald extends Stone {

	private String question, answerStr, successMsg, failMsg;
	private int correctAnswer;
	private Integer creatorId;
	private String[] answers;

	public Emerald(int id) {
		super(id);
	}

	public Emerald() {
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer("E-Id=");
		buf.append(id).append(",")
				.append(", lat=").append(getLat()).append(", lng=")
				.append(getLng())
				.append(", points=").append(getPoints());
		return buf.toString();

	}

	public String[] getAnswers() {
		return answers;
	}

	public String getAnswerStr() {
		return answerStr;
	}

	public void setAnswerStr(String answers) {
		this.answerStr = answers;
		if (answers != null) {
			this.answers = answers.split("#");
		} else {
			this.answers = null;
		}

	}

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public void addAnswer(String chararacters, int answerId) {

	}

	// @Override
	public String toXml() {
		StringBuilder xml = getStartXml()
				.append(" points=\"")
				.append(points)
				.append("\"")
				.append(formatIntAttribute("creator", creatorId))
				.append(">")
				.append("<question>")
				.append(question)
				.append("</question>")
				.append("<successmsg>")
				.append(successMsg)
				.append("</successmsg>")
				.append("<failmsg>")
				.append(failMsg)
				.append("</failmsg>");
		int i = 1;
		for (String answer : answers) {
			xml.append("<answer id=\"" + i++ + "\">");
			xml.append(answer);
			xml.append("</answer>");
		}
		xml.append("</object>");

		return xml.toString();
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}

	@Override
	public int hashCode() {
		final int prime = 63;
		return prime * id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Emerald))
			return false;
		Emerald other = (Emerald) obj;
		return id == other.id;
	}

	public String getSuccessMsg() {
		return successMsg;
	}

	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	public String getFailMsg() {
		return failMsg;
	}

	public void setFailMsg(String failMsg) {
		this.failMsg = failMsg;
	}

}
