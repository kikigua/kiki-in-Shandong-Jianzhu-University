package dao.provider;

import static org.junit.Assert.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.smbms.dao.provider.ProviderMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.utils.MyBatisUtil;

public class ProviderMapperTest {


	@Test
	public void testCount() {
		SqlSession sqlSession = null;
		int count = 0;
		try {
			sqlSession = MyBatisUtil.createSqlSession();
			
			//第一种方式:调用selectOne方法执行查询操作
			//count = sqlSession.selectOne("cn.smbms.dao.provider.ProviderMapper.count");
			
			//第二种方式:调用getMapper(Mapper.class)执行dao接口方法来实现对数据库的查询操作
			count = sqlSession.getMapper(ProviderMapper.class).count();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}

		System.out.println("ProviderDaoTest testCount---> " + count);
	}
	
	@Test
	public void testGetProviderList(){
		SqlSession sqlSession = null;
		List<Provider> providerList = new ArrayList<Provider>();
		try {
			sqlSession = MyBatisUtil.createSqlSession();
			String proCode = "BJ";
			String proName = null;
			Integer pageSize = 5;
			Integer currentPageNo = 0;
			providerList = sqlSession.getMapper(ProviderMapper.class).getProviderList(proCode,proName,currentPageNo,pageSize);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		System.out.println("providerList size----> " + providerList.size());
		for(Provider provider: providerList){
			System.out.println("testGetProviderList id: " + provider.getId() +
					" and proCode: " + provider.getProCode() + 
					" and proName: " + provider.getProName()+
					" and proPhone: " + provider.getProPhone()+
					" and proContact: " + provider.getProContact()+
					" and proFax: " + provider.getProFax()+
					" and creationDate: " + new SimpleDateFormat("yyyy-MM-dd").format(provider.getCreationDate()));
		}
	}
	
	@Test
	public void testAdd(){
		System.out.println("testAdd !===================");
		SqlSession sqlSession = null;
		int count = 0;
		try {
			sqlSession = MyBatisUtil.createSqlSession();
			Provider provider = new Provider();
			provider.setProCode("BJ_GYS111");
			provider.setProName("供应商测试");
			provider.setProContact("张三");
			provider.setProAddress("供应商测试地址");
			provider.setProPhone("13566667777");
			provider.setCreatedBy(1);
			provider.setCreationDate(new Date());
			provider.setProFax("010-588876565");
			provider.setProDesc("供应商测试描述");
			count = sqlSession.getMapper(ProviderMapper.class).add(provider);
			//模拟异常，进行回滚
			//int i = 2/0;
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			sqlSession.rollback();
			count = 0;
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		System.out.println("testAdd count: " + count);
	}
	
	@Test
	public void testModify(){
		System.out.println("testModify !===================");
		SqlSession sqlSession = null;
		int count = 0;
		try {
			sqlSession = MyBatisUtil.createSqlSession();
			Provider provider = new Provider();
			provider.setId(16);
			//provider.setProCode("BJ_GYS123");
			//provider.setProName("供应商测试修改");
			provider.setProContact("张扬");
			provider.setProAddress("供应商测试地址修改");
			//provider.setProPhone("13500002222");
			provider.setModifyBy(1);
			provider.setModifyDate(new Date());
			//provider.setProFax("010-588876565");
			//provider.setProDesc("供应商测试描述修改");
			count = sqlSession.getMapper(ProviderMapper.class).modify(provider);
			//模拟异常，进行回滚
			//int i = 2/0;
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			sqlSession.rollback();
			count = 0;
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		System.out.println("testModify count: " + count);
	}
	
	@Test
	public void testDeleteProviderById() {
		System.out.println("testDeleteProviderById !===================");
		SqlSession sqlSession = null;
		Integer delId = 18;
		int count = 0;
		try {
			sqlSession = MyBatisUtil.createSqlSession();
			count = sqlSession.getMapper(ProviderMapper.class).deleteProviderById(delId);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			count = 0;
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		System.out.println("testDeleteProviderById count =================== " + count);
	}
	
	@Test
	public void testGetProviderList_choose(){
		SqlSession sqlSession = null;
		List<Provider> providerList = new ArrayList<Provider>();
		try {
			sqlSession = MyBatisUtil.createSqlSession();
			String proName = "";
			String proCode = "";
			String proContact = "";
			providerList = sqlSession.getMapper(ProviderMapper.class).getProviderList_choose(proCode,proName,proContact);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		System.out.println("providerList.size ----> " + providerList.size());
		for(Provider provider: providerList){
			System.out.println("testGetProviderList_choose=======> id: " + provider.getId() +
						" and proCode: " + provider.getProCode() + 
						" and proName: " + provider.getProName() + 
						" and proContact: " + provider.getProContact() +
						" and creationDate: " + new SimpleDateFormat("yyyy-MM-dd").format(provider.getCreationDate()));
		}
	}
}