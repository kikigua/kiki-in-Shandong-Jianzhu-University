package cn.smbms.dao.user;

import java.util.List;
import cn.smbms.pojo.User;

public interface UserMapper {
	/**
	 * 查询用户列表(参数：对象入参)
	 * @return
	 */
	public List<User> getUserList(User user);
	
	public int add(User user);
}
