package cqupt.repository.imp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import cqupt.domain.User;
import cqupt.repository.UserDao;
import cqupt.repository.UserEhcacheRepository;
@CacheConfig(cacheNames= {"userCache"})
@Repository
public class UserEhcacheRepositoryImp implements UserEhcacheRepository {
	private static final Logger logger = Logger.getLogger(UserEhcacheRepositoryImp.class);
	
	@Autowired
	private UserDao userDao;
	
	@CachePut(key = "#p0")
	@Override
	public User save(User user) {
		User savedUser =userDao.save(user);
		logger.info("新增功能，同步到缓存，直接写入数据库，ID为：" + savedUser.getUserid());
		return savedUser ;
	}

	@Cacheable(key = "#p0")
    @Override
    public User selectById(Integer id) {
        logger.info("查询功能，缓存未找到，直接读取数据库，ID为：" + id);
        return userDao.findOne(id);
    }

	@CachePut(key = "#p0")
    @Override
    public User updateById(User user) {
        logger.info("更新功能，更新缓存，直接更新数据库，ID为：" + user.getUserid());
        return userDao.save(user);
    }

    @CacheEvict(key = "#p0")
    @Override
    public String deleteById(Integer id) {
        logger.info("删除功能，删除缓存，直接删除数据库数据，ID为：" + id);
        userDao.delete(id);
        return "删除成功";
    }
    
    @Cacheable(key = "#p0")
	@Override
	public User selectByUsername(String username,String password) {
    	System.out.println("aaaaaaaaaaaaaaaaaaaaa"+username);
        logger.info("查询功能，缓存未找到，直接读取数据库，username为：" + username);
        return userDao.login(username,password);
	}

}
