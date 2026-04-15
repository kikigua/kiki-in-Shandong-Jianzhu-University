package cn.smbms.dao.provider;

import cn.smbms.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderMapper {

    /**
     * 查询供应商表记录数
     * @return
     */
    public int count();
    /**
     * 查询供应商列表(分页显示)
     * @param proCode
     * @param proName
     * @param currentPageNo
     * @param pageSize
     * @return
     */
    public List<Provider> getProviderList(@Param("proCode")String proCode,
                                          @Param("proName")String proName,
                                          @Param("from")Integer currentPageNo,
                                          @Param("pageSize")Integer pageSize);
    /**
     * 增加供应商
     * @param provider
     * @return
     */
    public int add(Provider provider);
    /**
     * 修改供应商信息
     * @param provider
     * @return
     */
    public int modify(Provider provider);
    /**
     * 根据供应商ID删除供应商信息
     * @param delId
     * @return
     */
    public int deleteProviderById(@Param("id")Integer delId);

    /**
     * 查询供应商列表(choose)
     * @param proCode
     * @param proName
     * @param proContact
     * @return
     */
    public List<Provider> getProviderList_choose(@Param("proCode")String proCode,
                                                 @Param("proName")String proName,
                                                 @Param("proContact")String proContact);



}
