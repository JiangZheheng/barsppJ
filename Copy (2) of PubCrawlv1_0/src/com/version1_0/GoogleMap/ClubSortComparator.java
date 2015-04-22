package com.version1_0.GoogleMap;

import java.util.Comparator;

import android.location.Location;

import com.version1_0.ClubCrawl.Club;

public class ClubSortComparator implements Comparator<Club> {

	private Location myLocation;
	public ClubSortComparator(Location myLocation){
		this.setMyLocation(myLocation);
	}
	@Override
	public int compare(Club lhs, Club rhs) {
		float results1[]=new float[1];
		float results2[]=new float[1];
		Location.distanceBetween(myLocation.getLatitude(), myLocation.getLongitude(), lhs.getLatitude(), lhs.getLongitude(), results1);
		Location.distanceBetween(myLocation.getLatitude(), myLocation.getLongitude(), rhs.getLatitude(), rhs.getLongitude(), results2);
		return Double.compare(results1[0],results2[0]);
	}
	public Location getMyLocation() {
		return myLocation;
	}
	public void setMyLocation(Location myLocation) {
		this.myLocation = myLocation;
	}

}
