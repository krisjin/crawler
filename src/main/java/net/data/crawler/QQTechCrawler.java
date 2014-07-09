package net.data.crawler;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @author krisjin
 * @date 2014-7-9下午2:28:54
 */

@TargetUrl("http://tech.qq.com/a/\\d+/\\d+.htm")
public class QQTechCrawler {

	@ExtractBy(value = "//div[@class='hd']/h1/text()")
	private String title;

	@ExtractBy(value = "//div[@id='Cnt-Main-Article-QQ']/outerHtml()")
	private String content;

	@ExtractBy(value = "//span[@class='pubTime']/text()")
	private String date;
	
	@ExtractBy(value="//span[@class='auth']/text()")
	private String author;
	public static void main(String[] args) {
		OOSpider.create(Site.me(), new QQTechPageModelPipeline(), QQTechCrawler.class).thread(5).addUrl("http://tech.qq.com").run();
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getDate() {
		return date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	
}
