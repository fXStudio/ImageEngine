package com.image.engine.services.impls;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.image.engine.mappers.AnalyticalMapper;
import com.image.engine.services.IMtcImageService;

/**
 * 核心服务
 * 
 * @author FXStudio.Ajaxfan
 */
@Service
public class MtcImageService implements IMtcImageService {
	/** 数据逻辑模型 */
	private @Autowired AnalyticalMapper ayalyticalMapper;
	/** 图片文件存放的根目录 */
	private @Value("${ImageEngine.mtc.image.directory}") String image_path;

	/**
	 * 查询图片信息
	 * 
	 * @param id
	 * @return
	 */
	@Cacheable(value = "caches")
	@Override
	public byte[] find(String date, String code, String name) {
		return loadImage(new File(getImagePath(date, code, name)));
	}

	/**
	 * 读取图片二进制流
	 * 
	 * @param file
	 * @return
	 */
	private byte[] loadImage(File file) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		InputStream in = null;

		if (file.exists()) {
			try {
				in = new FileInputStream(file);
				// 创建二进制缓存区
				byte[] buffer = new byte[2048];
				int i = 0;

				// 写入二进制流
				while ((i = in.read(buffer)) != -1) {
					out.write(buffer, 0, i);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						in = null;
					}
				}
			}
		}
		return out.toByteArray();
	}

	/**
	 * 组件图片路径
	 * 
	 * @param outdate
	 * @param carImage
	 * @return
	 */
	private String getImagePath(String date, String code, String name) {
		StringBuilder sb = new StringBuilder();
		sb.append(image_path);
		sb.append(File.separator);
		sb.append(date);
		sb.append(File.separator);
		sb.append(code);
		sb.append(File.separator);
		sb.append(name);
		sb.append(".jpg");

		return sb.toString();
	}
}
