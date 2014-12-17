package net.data.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import net.data.service.NewsService;

public class NewsUrls {
	
	public static void main(String[] args) throws IOException {
		FileOutputStream out =new FileOutputStream("e:/tech-news.txt");
		int perPageCount =10000;
		int totalPageNum=1;
		NewsService ns = new NewsService();
		
		long totalCounts =ns.getTechNewsTotal();
		System.out.println("共有："+totalCounts);
		totalPageNum = (int) (Math.ceil((double) totalCounts / (double)perPageCount));
		
		System.out.println("共有："+totalPageNum+"页");
		long offset =1;
		long rows=10000;
		for(int i=0;i<=totalPageNum;i++){
			offset=i*perPageCount;
			
			
			List urls = ns.getMediaUrls(offset, perPageCount);
			
			for(int j=0;j<urls.size()-1;j++){
				String url =(String)urls.get(j);
				out.write((url+"\n").getBytes());
			}
			System.out.println("插入："+offset);
		}
		
		out.close();
	}
}
