package com.version1_0.ClubCrawl;

public enum Club {
	CUCKOO("Cuckoo Belfast", 54.583472, -5.945005),
	KREMLIN_NIGHT("Kremlin Night",54.603860, -5.930733),
	CLIFTON_HOT("Clifton Hot",54.615789, -5.942810);

	private final String name;
	private final double longitude;
	private final double latitude;

	 private Club(String name, double latitude, double longitude) {
		 this.name=name;
		 this.latitude=latitude;
		 this.longitude=longitude;
	}

	public String getName() {
		return name;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}
}
