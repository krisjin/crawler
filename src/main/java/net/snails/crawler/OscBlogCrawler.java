package net.snails.crawler;

import net.snails.pageprocessor.OscBlogPageProcessor;
import net.snails.pipeline.OSCBlogPipeline;
import us.codecraft.webmagic.Spider;

public class OscBlogCrawler {
	public static void main(String[] args) {
		Spider.create(new OscBlogPageProcessor()).addUrl("http://my.oschina.net").addPipeline(new OSCBlogPipeline())
				.thread(3).run();
	}
}
