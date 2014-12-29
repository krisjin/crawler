package net.data.mappers;

import java.util.List;
import java.util.Map;

import net.data.model.TechArticle;

/**
 * @author krisjin
 * @date 2014年12月18日
 */
public interface TechArticleMapper {
	
	public void addTechArticle(TechArticle techArticle);

	public List selectTechArticleUrls(Map<String, Long> map);

	public long getTechArticleTotal();
	
}
