package net.data.mappers;

import java.util.List;
import java.util.Map;

import net.data.model.TechNews;

public interface TechNewsMapper {
	
	public void addTechNews(TechNews news);
	
	public List selectTechNewsUrls(Map<String, Long> map);
	
	public long getTechNewsTotal();
}
