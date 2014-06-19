package com.shine.objects;

public enum Avatar {

	ALIEN(1, "avatars_alien"),
	BUG(2, "avatars_bug"),
	DRAGON(3, "avatars_dragon"),
	ELF(4, "avatars_elf"),
	GIRL_ELF(5, "avatars_girl_elf"),
	GREEN_DRAGON(6, "avatars_green_dragon"),
	GREEN_GERM(7, "avatars_green_germ"),
	RED_GERM(8, "avatars_red_germ"),
	SPOTTY_ALIEN(9, "avatars_spotty_alien");

	int id;
	String img;

	private Avatar(int id, String img) {
		this.id = id;
		this.img = img;
	}

}
