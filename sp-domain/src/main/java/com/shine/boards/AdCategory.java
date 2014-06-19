package com.shine.boards;

public enum AdCategory {

	GENERAL(1, "General", 'g'),
	FOOD_DRINK(2, "Food & drink", 'f'),
	SERVICES(3, "Services", 's'),
	EVENTS(4, "Events", 'e'),
	LEISURE(5, "Leisure", 'l'),
	TRAVEL(6, "Travel", 't'),
	SHOPPING(7, "Shopping", 'u'),
	JOBS(8, "Jobs", 'j'),
	HOME_GARDEN(9, "Home & garden", 'h');

	private final int id;
	private String category;
	private char code;
	public static final String allOffers = "gfseltujh";

	private AdCategory(int id, String category, char code) {
		this.id = id;
		this.category = category;
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public String getCategory() {
		return category;
	}

	public char getCode() {
		return code;
	}

	public static AdCategory getCat(char code) {
		if (code > 0) {
			for (AdCategory cat : AdCategory.values()) {
				if (code == cat.code) {
					return cat;
				}

			}
		}
		return GENERAL;
	}

	public static AdCategory getCategory(int id) {
		for (AdCategory cat : AdCategory.values()) {
			if (id == cat.id) {
				return cat;
			}
		}
		return null;
	}

}
