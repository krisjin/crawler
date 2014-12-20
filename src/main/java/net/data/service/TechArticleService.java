package net.data.service;

import net.data.mappers.TechArticleMapper;
import net.data.model.TechArticle;
import net.data.utils.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author krisjin
 * @date 2014-7-9上午10:42:53
 */

public class TechArticleService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public void addTechArticle(TechArticle techArticle) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			TechArticleMapper newsMapper = sqlSession.getMapper(TechArticleMapper.class);
			newsMapper.addTechArticle(techArticle);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}


}
