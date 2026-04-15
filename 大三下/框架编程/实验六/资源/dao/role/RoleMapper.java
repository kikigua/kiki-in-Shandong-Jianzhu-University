package cn.smbms.dao.role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Role;

public interface RoleMapper {
	
	/**
	 * 根据角色名称查询角色信息列表（模糊查询）
	 * @param roleName
	 * @return
	 */
	public List<Role> getRoleListByRoleName(@Param("roleName") String roleName);
	
	/**
	 * 增加角色
	 * @param role
	 * @return
	 */
	public int add(Role role);
	/**
	 *根据角色id修改角色信息
	 * @param role
	 * @return
	 */
	public int modify(Role role);
	
	
	/**
	 * 根据角色id查询该角色下是否有用户信息
	 * @param roleId
	 * @return
	 */
	public int getUserCountByRoleId(@Param("userRole") Integer roleId);
	
	
	/**
	 * 根据角色ID删除角色信息
	 * @param roleId
	 * @return
	 */
	public int deleteRoleById(@Param("id") Integer roleId);
	
}
