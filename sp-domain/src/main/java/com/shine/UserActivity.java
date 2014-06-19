package com.shine;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Indicates a ShineObject that represents an activity by a user. Has created
 * date and creatorId.
 * 
 */
@JsonIgnoreProperties({ "activityTitle", "activitySummary", "action" })
public interface UserActivity {

	Date getCreated();

	Integer getCreatorId();

	String getActivityTitle();

	String getActivitySummary();

	Action getAction();

	class Action {
		public Action(int id, String action) {
			this.id = id;
			this.action = action;
		}

		int id;
		String action;
	}

}
