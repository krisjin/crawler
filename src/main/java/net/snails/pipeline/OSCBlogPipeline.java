package net.snails.pipeline;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import net.data.model.TechArticle;
import net.data.service.TechArticleService;
import net.data.utils.BloomFilter;
import net.data.utils.DateUtil;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class OSCBlogPipeline implements Pipeline {
	
	
	AtomicInteger count = new AtomicInteger(1);
	int capicity = 8000000;
	int initDataSize = 5000000;
	private BloomFilter bloomfilter = new BloomFilter(capicity, initDataSize, 8);
	private TechArticleService articleService = new TechArticleService();
	
	
	public void process(ResultItems result, Task task) {
		FileWriter writer = null;
		String title = result.get("title");
		String date = result.get("date");
		String content = result.get("content");
		String url = result.get("url");
		Date d =DateUtil.convertStringDateTimeToDate(DateUtil.parseOscBlogPostDate(date),"yyyy-MM-dd HH:mm");
		
		bloomfilter.init("e:/tech-article.txt");
		if(bloomfilter.contains(url)){
			return;
		}
		
		TechArticle art=new TechArticle();
		art.setArticleTitle(title.trim());
		art.setArticleUrl(url);
		art.setArticleContent(content);
		art.setArticlePostDate(d);
		art.setArticleSite("OSCBlog");
		
		articleService.addTechArticle(art);
		
		try {
			writer = new FileWriter("e:/tech-article.txt", true);
			writer.write((art.getArticleUrl() + "\n"));
			writer.close();
			System.out.println("save "+art.getArticleUrl());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
