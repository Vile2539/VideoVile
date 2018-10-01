package com.kenco.videovile.videoinfo;

public class Length {

	private final int hours;

	private final int minutes;

	private final int seconds;

	public Length(long seconds) {
		this.hours = (int) seconds / 3600;
		int remainder = (int) seconds - this.hours * 3600;
		this.minutes = remainder / 60;
		remainder = remainder - this.minutes * 60;
		this.seconds = remainder;
	}

	public int getHours() {
		return hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	@Override
	public String toString() {
		return "Length{" +
				"hours=" + hours +
				", minutes=" + minutes +
				", seconds=" + seconds +
				'}';
	}

}
