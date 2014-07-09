package net.data.service;

import net.data.mappers.NewsMapper;
import net.data.model.News;
import net.data.utils.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author krisjin
 * @date 2014-7-9上午10:42:53
 */

public class NewsService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public void addNews(News news) {
		SqlSession sqlSession =  MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			NewsMapper newsMapper =sqlSession.getMapper(NewsMapper.class);
			newsMapper.addNews(news);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

}
