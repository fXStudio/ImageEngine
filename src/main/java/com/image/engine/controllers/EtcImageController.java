package com.image.engine.controllers;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.image.engine.services.IEtcImageService;

/**
 * 图片服务控制器
 * 
 * @author FXStudio.Ajaxfan
 */
@Controller
@RequestMapping(value = "etc", method = { RequestMethod.GET })
public class EtcImageController {
	/** 图片服务 */
	private @Autowired IEtcImageService etcImageService;

	/**
	 * 图片服务
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping(value = "{name:.+}")
	public synchronized void viewImage(@PathVariable("name") String name, HttpServletResponse response) {
		// 设置Http的Mime类型
		response.setContentType("image/png");
		// 查询图片对象
		byte[] image = etcImageService.find(name);
		
		if (image != null) {
			OutputStream out = null;

			try {
				out = response.getOutputStream();
				out.write(image);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						out = null;
					}
				}
			}
		}
	}
}
