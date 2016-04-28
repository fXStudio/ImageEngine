package com.image.engine.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.image.engine.mappers.KwImageMapper;
import com.image.engine.models.KwImage;
import com.image.engine.services.IKwImageService;

/**
 * 核心服务
 * 
 * @author FXStudio.Ajaxfan
 */
@Service
public class KwImageService implements IKwImageService {
	/** 数据逻辑模型 */
	private @Autowired KwImageMapper kwImageMapper;

	/**
	 * 查询图片信息
	 * 
	 * @param id
	 * @return
	 */
	@Cacheable(value = "caches", key = "#id")
	@Override
	public KwImage find(String id) {
		return kwImageMapper.selectByPrimaryKey(id);
	}
}
