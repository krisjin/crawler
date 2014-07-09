package net.data.crawler;

import java.util.Date;

import com.mysql.jdbc.StringUtils;

import net.data.model.News;
import net.data.service.NewsService;
import net.data.utils.DateUtil;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * @author krisjin
 * @date 2014-7-9上午11:31:23
 */

public class QQTechPageModelPipeline implements PageModelPipeline {

	private NewsService newsService= new NewsService();

	public void process(Object obj, Task task) {
		
		if(obj instanceof QQTechCrawler){
			QQTechCrawler qqt= (QQTechCrawler)obj;
			String title=qqt.getTitle();
			String date =qqt.getDate();
			String content=qqt.getContent();
			Date d=DateUtil.convertStringDateTimeToDate(date, "yyyy年MM月dd日HH:mm");
			
			
			News news =new News();
			news.setFolderId(1L);
			news.setStatus(1);
			if(StringUtils.isNullOrEmpty(qqt.getAuthor())){
				news.setAuthor("");
			}else{
				news.setAuthor(qqt.getAuthor());
			}
			
			news.setTitle(title);
			news.setContent(content);
			news.setPostDate(d);
			newsService.addNews(news);
		
		}
	}
}
