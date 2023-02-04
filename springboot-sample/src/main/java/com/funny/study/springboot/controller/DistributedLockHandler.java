package com.funny.study.springboot.controller;
 
import org.springframework.stereotype.Service;
 
import redis.clients.jedis.Jedis;

/**
 * 基于redis setnx get set 的分布式锁
 *  1. setnx(lockkey, 当前时间+过期超时时间) ，如果返回1，则获取锁成功；如果返回0则没有获取到锁，转向2。
 *  2. get(lockkey)获取值oldExpireTime ，并将这个value值与当前的系统时间进行比较，如果小于当前系统时间，则认为这个锁已经超时，可以允许别的请求重新获取，转向3。
 *  3. 计算newExpireTime=当前时间+过期超时时间，然后getset(lockkey, newExpireTime) 会返回当前lockkey的值currentExpireTime。
 *  4. 判断currentExpireTime与oldExpireTime 是否相等，如果相等，说明当前getset设置成功，获取到了锁。如果不相等，说明这个锁又被别的请求获取走了，那么当前请求可以直接返回失败，或者继续重试。
 *  5. 在获取到锁之后，当前线程可以开始自己的业务处理，当处理完毕后，比较自己的处理时间和对于锁设置的超时时间，如果小于锁设置的超时时间，则直接执行delete释放锁；如果大于锁设置的超时时间，则不需要再锁进行处理。
 * ————————————————
 * 版权声明：本文为CSDN博主「缘灭由你」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/abbc7758521/article/details/77990048/
 */
@Service("distributedLockHandler")
public class DistributedLockHandler {
 
	private static final Integer Lock_Timeout = 3;
 
	private Jedis jedis;
 
	/**
	 * 外部调用加锁的方法
	 * @param lockKey 锁的名字
	 * @param timeout 超时时间（放置时间长度，如：5L）
	 * @return
	 */
	public boolean tryLock(String lockKey, Long timeout) {
		try {
			Long currentTime = System.currentTimeMillis();//开始加锁的时间
			boolean result = false;
			
			while (true) {
				if ((System.currentTimeMillis() - currentTime) / 1000 > timeout) {//当前时间超过了设定的超时时间
					System.out.println("Execute DistributedLockHandler.tryLock method, Time out.");
					break;
				} else {
					result = innerTryLock(lockKey);
					if (result) {
						break;
					} else {
						System.out.println("Try to get the Lock,and wait 100 millisecond....");
						Thread.sleep(100);
					}
				}
			}
			return result;
		} catch (Exception e) {
			System.out.println("Failed to run DistributedLockHandler.getLock method."+ e);
			return false;
		}
	}
	
	/**
	 * 释放锁
	 * @param lockKey 锁的名字
	 */
	public void realseLock(String lockKey) {
		if(!checkIfLockTimeout(System.currentTimeMillis(), lockKey)){
			jedis.del(lockKey);
		}
	}
	
	/**
	 * 内部获取锁的实现方法
	 * @param lockKey 锁的名字
	 * @return
	 */
	private boolean innerTryLock(String lockKey) {
		
		long currentTime = System.currentTimeMillis();//当前时间
		String lockTimeDuration = String.valueOf(currentTime + Lock_Timeout + 1);//锁的持续时间
		Long result = jedis.setnx(lockKey, lockTimeDuration);
		
		if (result == 1) {
			return true;
		} else {
			if (checkIfLockTimeout(currentTime, lockKey)) {
				String preLockTimeDuration = jedis.getSet(lockKey, lockTimeDuration);
				if (currentTime > Long.valueOf(preLockTimeDuration)) {
					return true;
				}
			}
			return false;
		}
		
	}
 
	/**
	 * 判断加锁是否超时
	 * @param currentTime 当前时间
	 * @param lockKey 锁的名字
	 * @return
	 */
	private boolean checkIfLockTimeout(Long currentTime, String lockKey) {
		if (currentTime > Long.valueOf(jedis.get(lockKey))) {//当前时间超过锁的持续时间
			return true;
		} else {
			return false;
		}
	}
 
	public DistributedLockHandler setJedis(Jedis jedis) {
		this.jedis = jedis;
		return this;
	}
 
}