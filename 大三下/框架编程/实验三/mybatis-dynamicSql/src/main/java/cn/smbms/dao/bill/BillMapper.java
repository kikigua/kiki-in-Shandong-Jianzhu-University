package cn.smbms.dao.bill;

import cn.smbms.pojo.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BillMapper {

    /**
     * 根据条件查询订单表(分页显示)
     * @param productName
     * @param providerId
     * @param isPayment
     * @param currentPageNo
     * @param pageSize
     * @return
     */
    public List<Bill> getBillList(@Param("productName")String productName,
                                  @Param("providerId")Integer providerId,
                                  @Param("isPayment")Integer isPayment,
                                  @Param("from")Integer currentPageNo,
                                  @Param("pageSize")Integer pageSize);

    /**
     * 根据供应商列表，获取该供应商列表下订单列表信息-foreach_array
     * @param proIds
     * @return
     */
    public List<Bill> getBillByProviderId_foreach_array(Integer[] proIds);

    /**
     * 根据供应商列表，获取该供应商列表下订单列表信息-foreach_list
     * @param proList
     * @return
     */
    public List<Bill> getBillByProviderId_foreach_list(List<Integer> proList);

    /**
     * 根据供应商列表和订单编码(多参数)，获取该供应商列表下指定订单编码的订单列表表信息-foreach_map
     * @param conditionMap
     * @return
     */
    public List<Bill>  getBillByConditionMap_foreach_map(Map<String,Object> conditionMap);

}
