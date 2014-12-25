package net.snails.pageprocessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class GithubUserPageProcessor implements PageProcessor {
	  private Site site = Site.me();
	public void process(Page page) {
		page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/following)").all());
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+)").all());
		page.putField("author",  page.getRequest().getUrl());
		page.putField("company",page.getHtml().xpath("//li[@itemprop='worksFor']/text()"));
		
		String company=page.getResultItems().get("company").toString();
		if(company.equals("null")){
			page.setSkip(true);
		}
		
	}

	@Override
	public Site getSite() {
		return site;
	}

}
