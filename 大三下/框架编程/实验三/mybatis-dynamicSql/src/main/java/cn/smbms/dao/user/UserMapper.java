package cn.smbms.dao.user;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import cn.smbms.pojo.User;

public interface UserMapper {
	/**
	 * 查询用户表记录数
	 * @return
	 */
	public int count();
	
	/**
	 * 查询用户列表
	 * @param userName
	 * @param roleId
	 * @return
	 */
	public List<User> getUserList(@Param("userName")String userName,@Param("userRole")Integer roleId);
	
	/**
	 * 增加用户
	 * @param user
	 * @return
	 */
	public int add(User user);
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public int modify(User user);
	
	/**
	 * 修改当前用户密码
	 * @param id
	 * @param pwd
	 * @return
	 */
	public int updatePwd(@Param("id")Integer id, @Param("userPassword")String pwd);
	
	/**
	 * 根据userId删除用户信息
	 * @param delId
	 * @return
	 */
	public int deleteUserById(@Param("id")Integer delId);
	
	/**
	 * 根据用户角色列表，获取该角色列表下用户列表信息-foreach_array
	 * @param roleIds
	 * @return
	 */
	public List<User> getUserByRoleId_foreach_array(Integer[] roleIds);
	
	/**
	 * 根据用户角色列表，获取该角色列表下用户列表信息-foreach_list
	 * @param roleList
	 * @return
	 */
	public List<User> getUserByRoleId_foreach_list(List<Integer> roleList);
	
	/**
	 * 根据用户角色列表和性别(多参数)，获取该角色列表下指定性别的用户列表信息-foreach_map
	 * @param conditionMap
	 * @return
	 */
	public List<User> getUserByConditionMap_foreach_map(Map<String,Object> conditionMap);
	
	/**
	 * 根据用户角色列表，获取该角色列表下用户列表信息-foreach_map(单参数封装成map)
	 * @param roleMap
	 * @return
	 */
	public List<User> getUserByRoleId_foreach_map(Map<String,Object> roleMap);
	
	
	/**
	 * 查询用户列表(choose)
	 * @param userName
	 * @param roleId
	 * @param userCode
	 * @param creationDate
	 * @return
	 */
	public List<User> getUserList_choose(@Param("userName")String userName,
										 @Param("userRole")Integer roleId,
										 @Param("userCode")String userCode,
										 @Param("creationDate")Date creationDate);
	
	/**
	 * 根据角色ID删除用户信息
	 * @param roleId
	 * @return
	 */
	public int deleteUserByRoleId(@Param("userRole")Integer roleId);

}

