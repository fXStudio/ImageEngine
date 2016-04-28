package com.image.engine.mappers;

import com.image.engine.models.KwImage;

/**
 * 数据逻辑模型
 * 
 * @author FXStudio.Ajaxfan
 */
public interface KwImageMapper {
	public KwImage selectByPrimaryKey(String id);
}
