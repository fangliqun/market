package cqupt.repository;

import cqupt.domain.User;

public interface UserEhcacheRepository {

    /**
     * 增加用户
     * @param user 用户
     * @return 增加后的用户
     */
    User save(User user);

    /**
     * 查询用户
     * @param id 主键
     * @return 用户
     */
    User selectById(Integer id);
    
    /**
     * 查询用户
     * @param username 主键
     * @return 用户
     */
    User selectByUsername(String username,String password);
    
    User selectUserByUsername(String username);

    /**
     * 更新用户
     * @param user 更新的用户
     * @return 用户
     */
    User updateById(User user);

    /**
     * 删除用户
     * @param id 主键
     * @return 删除状态
     */
    String deleteById(Integer id);
}
