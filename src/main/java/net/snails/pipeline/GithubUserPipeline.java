package net.snails.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class GithubUserPipeline implements Pipeline{

	@Override
	public void process(ResultItems resultItems, Task task) {
		System.out.println("author:"+resultItems.get("author"));
		System.out.println("company:"+resultItems.get("company")+"\n ................................................");
	}

}
