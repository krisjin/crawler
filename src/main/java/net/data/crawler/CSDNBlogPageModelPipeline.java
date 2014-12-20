package net.data.crawler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import net.data.model.TechArticle;
import net.data.service.TechArticleService;
import net.data.utils.BloomFilter;
import net.data.utils.DateUtil;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import com.mysql.jdbc.StringUtils;

/**
 * @author krisjin
 * @date 2014-7-9上午11:31:23
 */

public class CSDNBlogPageModelPipeline implements PageModelPipeline {
	AtomicInteger count = new AtomicInteger(1);
	int capicity = 1000000;
	int initDataSize = 800000;
	private BloomFilter bloomfilter = new BloomFilter(capicity, initDataSize, 8);
	private TechArticleService articleService = new TechArticleService();

	public void process(Object obj, Task task) {
		FileWriter writer = null;

		bloomfilter.init("e:/tech-artilce.txt");
		if (obj instanceof CSDNBlogCrawler) {
			CSDNBlogCrawler qqt = (CSDNBlogCrawler) obj;
			String title = qqt.getTitle();
			String date = qqt.getDate();
			String content = qqt.getContent();

			TechArticle article = new TechArticle();
			article.setArticleSite("CSDN博客");
			article.setArticleUrl(qqt.getUrl());

			if (StringUtils.isNullOrEmpty(title)) {
				return;
			}

			if (bloomfilter.contains(qqt.getUrl())) {
				System.out.println(qqt.getUrl() + " have repeat..." + count.incrementAndGet());
				return;
			}

			if (date == null) {
				article.setArticlePostDate(new Date());
			} else {
				Date d = DateUtil.convertStringDateTimeToDate(date, "yyyy-MM-dd HH:mm");
				article.setArticlePostDate(d);
			}
			article.setArticleTitle(title);
			article.setArticleContent(content);
			articleService.addTechArticle(article);
			try {
				writer = new FileWriter("e:/tech-artilce.txt", true);
				writer.write((qqt.getUrl() + "\n"));
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("保存:" + article.getArticleTitle() + count.incrementAndGet());

		}
	}
}
