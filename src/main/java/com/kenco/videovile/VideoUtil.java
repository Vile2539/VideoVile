package com.kenco.videovile;

import com.kenco.videovile.videoinfo.Length;
import com.kenco.videovile.videoinfo.VideoInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class VideoUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(VideoUtil.class);

	private VideoUtil() {
	}

	public static VideoInfo getVideoInfo(File videoFile) {
		final VideoInfo videoInfo = new VideoInfo();
		videoInfo.setPath(videoFile.getAbsolutePath());

		// TODO : Abstract out video type
		// https://www.fastgraph.com/help/avi_header_format.html
		final byte[] aviHeader = new byte[56];
		try (InputStream is = new FileInputStream(videoFile)) {
			long skippedBytes = is.skip(32);
			if (skippedBytes != 32) {
				LOGGER.warn("Failed to skip first 32 bytes of file.");
			}
			final int bytesRead = is.read(aviHeader);
			if (bytesRead != aviHeader.length) {
				LOGGER.warn("Only read " + bytesRead + " bytes of the header instead of " + aviHeader.length + " bytes.");
			} else {
				final byte[] delayBetweenFrames = Arrays.copyOfRange(aviHeader, 0, 4);
				final byte[] numberOfFrames = Arrays.copyOfRange(aviHeader, 16, 20);
				final byte[] width = Arrays.copyOfRange(aviHeader, 32, 36);
				final byte[] height = Arrays.copyOfRange(aviHeader, 36, 40);
				final ByteBuffer wrappedWidth = ByteBuffer.wrap(width).order(ByteOrder.LITTLE_ENDIAN);
				videoInfo.setWidth(wrappedWidth.asIntBuffer().get());
				final ByteBuffer wrappedHeight = ByteBuffer.wrap(height).order(ByteOrder.LITTLE_ENDIAN);
				videoInfo.setHeight(wrappedHeight.asIntBuffer().get());

				final int frames = ByteBuffer.wrap(numberOfFrames).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer().get();
				final int delayInMicroseconds = ByteBuffer.wrap(delayBetweenFrames).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer().get();
				final int lengthInSeconds = (frames * delayInMicroseconds) / 1000000;
				videoInfo.setLength(new Length(lengthInSeconds));
			}
		} catch (IOException e) {
			LOGGER.error("Exception while reading video file.", e);
		}

		return videoInfo;
	}

}
