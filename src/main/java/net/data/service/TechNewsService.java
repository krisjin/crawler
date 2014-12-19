package net.data.service;

import net.data.mappers.TechNewsMapper;
import net.data.model.TechNews;
import net.data.utils.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;

/**
 * @author krisjin
 */
public class TechNewsService {

	public void addTechNews(TechNews techNews) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			TechNewsMapper techNewsMapper = sqlSession.getMapper(TechNewsMapper.class);
			techNewsMapper.addTechNews(techNews);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
}
