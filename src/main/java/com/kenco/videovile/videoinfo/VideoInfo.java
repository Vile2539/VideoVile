package com.kenco.videovile.videoinfo;

public class VideoInfo {

	private Length length;
	private String path;
	private int width;
	private int height;

	public Length getLength() {
		return length;
	}

	public void setLength(Length length) {
		this.length = length;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "VideoInfo{" +
				"length=" + length +
				", path='" + path + '\'' +
				", width=" + width +
				", height=" + height +
				'}';
	}

}
