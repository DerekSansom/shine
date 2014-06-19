package com.shine.objects;

import java.util.List;

/**
 * Not very nice but since Player extending User confuses Hibernate for now use
 * this interface to interchange user/player
 * 
 * @author derek
 * 
 */
public interface Sp {

	public String getUsername();

	public int getScore();

	public int getId();

	public int getIntExtra();

	public String getStatus();

	public List<ShineObject> getListExtra();

	public boolean isHasIcon();

}
