package com.image.engine.controllers;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.image.engine.models.KwImage;
import com.image.engine.services.IKwImageService;

/**
 * 图片服务控制器
 * 
 * @author FXStudio.Ajaxfan
 */
@Controller
@RequestMapping(value = "cdn", method = { RequestMethod.GET })
public class ImageController {
	/** 图片服务 */
	private @Autowired IKwImageService kwImageService;

	/**
	 * 图片服务
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping(value = "{id}")
	public void viewImage(@PathVariable("id") String id, HttpServletResponse response) {
		// 设置Http的Mime类型
		response.setContentType("image/png");
		// 查询图片对象
		KwImage image = kwImageService.find(id);

		if (image != null) {
			OutputStream out = null;

			try {
				out = response.getOutputStream();
				out.write(image.getImage());
				out.close();
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
