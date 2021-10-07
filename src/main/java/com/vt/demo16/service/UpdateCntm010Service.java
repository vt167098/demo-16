package com.vt.demo16.service;

import java.util.List;
import java.util.Optional;

import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.vt.demo16.dao.Cntm010DynamicSqlSupport;
import com.vt.demo16.dao.Cntm010Mapper;
import com.vt.demo16.model.Cntm010;

@Service
public class UpdateCntm010Service {

	@Autowired
	private Cntm010Mapper cntm010Mapper;

	/*
	 * private final PlatformTransactionManager transactionManager;
	 * 
	 * public UpdateCntm010Service(PlatformTransactionManager transactionManager) {
	 * this.transactionManager = transactionManager; }
	 */
	
	//@Transactional
	public void updateTx1(List<Cntm010> c1) {
		//TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

		c1.forEach(r -> {
			System.out.printf("post:%s-%d-%s-%s-%s\n", r.getItem(), r.getCtNo(), r.getReptName(), r.getBuldName(),
					r.getMemo1());
			/*
			 * Optional<Cntm010> c1 = cntm010Mapper .selectOne(c ->
			 * c.where(Cntm010DynamicSqlSupport.item, SqlBuilder.isEqualTo(r.getItem()))
			 * .and(Cntm010DynamicSqlSupport.ctNo, SqlBuilder.isEqualTo(r.getCtNo())));
			 * Cntm010 c2 = c1.get(); System.out.printf("old:%s-%d-%s-%s-%s\n",
			 * c2.getItem(), c2.getCtNo(), c2.getReptName(), c2.getBuldName(),
			 * c2.getMemo1()); c2.setReptName(r.getReptName());
			 * c2.setBuldName(r.getBuldName()); c2.setMemo1(r.getMemo1());
			 */
			try {
				UpdateStatementProvider updateStatement = SqlBuilder.update(Cntm010DynamicSqlSupport.cntm010)
						.set(Cntm010DynamicSqlSupport.buldName).equalTo(r.getBuldName())
						.set(Cntm010DynamicSqlSupport.reptName).equalTo(r.getReptName())
						.set(Cntm010DynamicSqlSupport.memo1).equalTo(r.getMemo1())
						.where(Cntm010DynamicSqlSupport.item, SqlBuilder.isEqualTo(r.getItem()))
						.and(Cntm010DynamicSqlSupport.ctNo, SqlBuilder.isEqualTo(r.getCtNo())).build()
						.render(RenderingStrategies.MYBATIS3);
				int rows = cntm010Mapper.update(updateStatement);
				System.out.printf("update: %s-%d-%s-%s-%s, row: %d\n", r.getItem(), r.getCtNo(), r.getBuldName(),
						r.getReptName(), r.getMemo1(), rows);
				if (r.getCtNo() == 104)
					throw new RuntimeException();
			} catch (Exception e) {
				//transactionManager.rollback(txStatus);
				throw e;
			}
		});
		//transactionManager.commit(txStatus);

	}
}
