package com.shine.boards;

public enum NoticeCategory {

	HERE_AND_NOW("Here & now", 1),
	ANNOUNCEMENTS("Announcements", 2),
	MEETING_UP("Meeting up", 3),
	INTERESTS("Interests", 4),
	WANTS("Wants", 5),
	RANT("Rant", 6);

	private final int id;
	private final String category;
	public static int ALL = 0;

	private NoticeCategory(String category, int id) {
		this.id = id;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public String getCategory() {
		return category;
	}

	@Override
	public String toString() {
		return category;
	}

	public static NoticeCategory getCat(String category) {
		if (category != null) {
			for (NoticeCategory cat : NoticeCategory.values()) {
				if (category.equals(cat.getCategory())) {
					return cat;
				}

			}
		}
		return HERE_AND_NOW;
	}

	public static NoticeCategory getCat(int id) {
		for (NoticeCategory cat : NoticeCategory.values()) {
			if (id == cat.getId()) {
				return cat;
			}

		}
		return HERE_AND_NOW;
	}

}
