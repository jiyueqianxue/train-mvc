package io.mine.ft.train.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
//import io.mine.ft.train.service.biz.ShopCategoryBiz;
//import lombok.extern.slf4j.Slf4j;
/**
  	TransactionDefinition.PROPAGATION_REQUIRED：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。这是默认值。
  	TransactionDefinition.PROPAGATION_REQUIRES_NEW：创建一个新的事务，如果当前存在事务，则把当前事务挂起。
  	TransactionDefinition.PROPAGATION_SUPPORTS：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
  	TransactionDefinition.PROPAGATION_NOT_SUPPORTED：以非事务方式运行，如果当前存在事务，则把当前事务挂起。
  	TransactionDefinition.PROPAGATION_NEVER：以非事务方式运行，如果当前存在事务，则抛出异常。
  	TransactionDefinition.PROPAGATION_MANDATORY：如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。
  	TransactionDefinition.PROPAGATION_NESTED：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；（子事务）
 	如果当前没有事务，则该取值等价于TransactionDefinition.PROPAGATION_REQUIRED。

  	value	String	可选的限定描述符，指定使用的事务管理器
	propagation	enum: Propagation	可选的事务传播行为设置
	isolation	enum: Isolation	可选的事务隔离级别设置
	readOnly	boolean	读写或只读事务，默认读写
	timeout	int (in seconds granularity)	事务超时时间设置
	rollbackFor	Class对象数组，必须继承自Throwable	导致事务回滚的异常类数组
	rollbackForClassName	类名数组，必须继承自Throwable	导致事务回滚的异常类名字数组
	noRollbackFor	Class对象数组，必须继承自Throwable	不会导致事务回滚的异常类数组
	noRollbackForClassName	类名数组，必须继承自Throwable	不会导致事务回滚的异常类名字数组
 *
 */
@Service
//@Slf4j
public class ShopSupport {
	
	//@Autowired
	//private ShopCategoryBiz shopCategoryBiz;
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update_1() throws Exception {
		
	
	}
}
