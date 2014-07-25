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

public class TencentTechPageModelPipeline implements PageModelPipeline {

	private NewsService newsService = new NewsService();

	public void process(Object obj, Task task) {

		if (obj instanceof TencentTechCrawler) {
			TencentTechCrawler qqt = (TencentTechCrawler) obj;
			String title = qqt.getTitle();
			String date = qqt.getDate();
			String content = qqt.getContent();

			News news = new News();
			news.setFolderId(2L);
			news.setStatus(1);
			news.setMedia("腾讯科技");
			news.setMediaUrl(qqt.getUrl());

			if (StringUtils.isNullOrEmpty(qqt.getImgUrl())) {
				news.setThumbnailsUrl("");
			} else {
				news.setThumbnailsUrl(qqt.getImgUrl());
			}
			if (StringUtils.isNullOrEmpty(qqt.getAuthor())) {
				news.setAuthor("");
			} else {
				news.setAuthor(qqt.getAuthor());
			}

			if (date == null) {
				news.setPostDate(new Date());
			} else {
				Date d = DateUtil.convertStringDateTimeToDate(date, "yyyy年MM月dd日HH:mm");
				news.setPostDate(d);
			}
			news.setTitle(title);
			news.setContent(content);
			newsService.addNews(news);

		}
	}
}
