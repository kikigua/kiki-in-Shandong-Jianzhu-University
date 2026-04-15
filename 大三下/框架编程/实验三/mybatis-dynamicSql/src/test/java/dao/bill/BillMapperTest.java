package dao.bill;

import static org.junit.Assert.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.smbms.dao.bill.BillMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import cn.smbms.pojo.Bill;
import cn.smbms.utils.MyBatisUtil;

public class BillMapperTest {

	@Test
	public void testGetBillList() {
		SqlSession sqlSession = null;
		List<Bill> billList = new ArrayList<Bill>();
		try {
			sqlSession = MyBatisUtil.createSqlSession();
			String productName = null;
			Integer providerId = null;
			Integer isPayment = 2;
			Integer pageSize = 5;
			Integer currentPageNo = 0;
			billList = sqlSession.getMapper(BillMapper.class).getBillList(productName,providerId,isPayment,currentPageNo,pageSize);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		System.out.println("billList.size ---> " + billList.size());
		for(Bill bill: billList){
			System.out.println("testGetBillList id: " + bill.getId() +
						" and BillCode: " + bill.getBillCode() + 
						" and ProductName: " + bill.getProductName() + 
						" and totalPrice: " + bill.getTotalPrice() + 
						" and isPayment: " + bill.getIsPayment() + 
						" and providerId : " + bill.getProviderId() +
						" and providerName: " + bill.getProviderName() +
						" and creationDate: " + new SimpleDateFormat("yyyy-MM-dd").format(bill.getCreationDate()));
		}
	}
	
	@Test
	public void testGetBillByProviderId_foreach_array(){
		SqlSession sqlSession = null;
		List<Bill> billList = new ArrayList<Bill>();
		Integer[] proIds = {1,14};
		try {
			sqlSession = MyBatisUtil.createSqlSession();
			billList = sqlSession.getMapper(BillMapper.class).getBillByProviderId_foreach_array(proIds);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		System.out.println("billList.size ----> " + billList.size());
		for(Bill bill : billList){
			System.out.println("bill ===========> id: " + bill.getId()+
						", billCode: " + bill.getBillCode() + 
						", productName: " + bill.getProductName() +
						", totalPrice: " + bill.getTotalPrice() +
						", providerId: " + bill.getProviderId());
		}
	}
	@Test
	public void testGetBillByProviderId_foreach_list(){
		SqlSession sqlSession = null;
		List<Bill> billList = new ArrayList<Bill>();
		List<Integer> proList = new ArrayList<Integer>();
		proList.add(1);
		proList.add(14);
		try {
			sqlSession = MyBatisUtil.createSqlSession();
			billList = sqlSession.getMapper(BillMapper.class).getBillByProviderId_foreach_list(proList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		System.out.println("billList.size ----> " + billList.size());
		for(Bill bill : billList){
			System.out.println("bill ===========> id: " + bill.getId()+
					", billCode: " + bill.getBillCode() + 
					", productName: " + bill.getProductName() +
					", totalPrice: " + bill.getTotalPrice() +
					", providerId: " + bill.getProviderId());
		}
	}
	
	@Test
	public void testGetBillByConditionMap_foreach_map(){
		SqlSession sqlSession = null;
		List<Bill> billList = new ArrayList<Bill>();
		Map<String, Object> conditionMap = new HashMap<String,Object>();
		List<Integer> proList = new ArrayList<Integer>();
		proList.add(1);
		proList.add(14);
		conditionMap.put("billCode", "BILL2016");
		conditionMap.put("providerIds",proList);
		try {
			sqlSession = MyBatisUtil.createSqlSession();
			billList = sqlSession.getMapper(BillMapper.class).getBillByConditionMap_foreach_map(conditionMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		System.out.println("billList.size ----> " + billList.size());
		for(Bill bill : billList){
			System.out.println("bill ===========> id: " + bill.getId()+
					", billCode: " + bill.getBillCode() + 
					", productName: " + bill.getProductName() +
					", totalPrice: " + bill.getTotalPrice() +
					", providerId: " + bill.getProviderId());
		}
	}

}
