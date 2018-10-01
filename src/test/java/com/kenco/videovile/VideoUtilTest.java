package com.kenco.videovile;

import com.kenco.videovile.videoinfo.VideoInfo;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class VideoUtilTest {

	@Test
	public void testGetVideoInfo() throws Exception {
		final File flameAvi = new File("src/test/resources/sample/flame.avi");

		final VideoInfo videoInfo = VideoUtil.getVideoInfo(flameAvi);
		Assert.assertEquals(240, videoInfo.getHeight());
		Assert.assertEquals(256, videoInfo.getWidth());
		Assert.assertEquals(0, videoInfo.getLength().getHours());
		Assert.assertEquals(0, videoInfo.getLength().getMinutes());
		Assert.assertEquals(3, videoInfo.getLength().getSeconds());
	}

}