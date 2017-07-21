package com.image.engine.controllers;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.image.engine.services.IMtcImageService;

/**
 * 图片服务控制器
 * 
 * @author FXStudio.Ajaxfan
 */
@Controller
@RequestMapping(value = "mtc", method = { RequestMethod.GET })
public class MtcImageController {
	/** 图片服务 */
	private @Autowired IMtcImageService mtcImageService;
	
	/**
	 * 图片服务
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping(value = "/{date}/{code}/{name}")
	public synchronized void viewImage(@PathVariable("date") String date, @PathVariable("code") String code,
			@PathVariable("name") String name, HttpServletResponse response) {
		// 设置Http的Mime类型
		response.setContentType("image/png");
		// 查询图片对象
		byte[] image = mtcImageService.find(date, code, name);

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
