package com.vt.demo16;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.vt.demo16.dao.Cntm010DynamicSqlSupport;
import com.vt.demo16.dao.Cntm010Mapper;
import com.vt.demo16.dao.Cntm015DynamicSqlSupport;
import com.vt.demo16.dao.Cntm015Mapper;
import com.vt.demo16.dao.Join01Mapper;
import com.vt.demo16.model.Cntm010;
import com.vt.demo16.model.Cntm015;
import com.vt.demo16.model.Join01;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class Demo16ApplicationTests {

	@Autowired
	Cntm010Mapper cntm010Mapper;

	@Autowired
	Cntm015Mapper cntm015Mapper;

	@Autowired
	Join01Mapper join01Mapper;

	@Test
	public void selectAll1() {
		List<Cntm010> cntm010List = cntm010Mapper.select(SelectDSLCompleter.allRows());
		for (Cntm010 cntm010 : cntm010List) {
			System.out.println(cntm010.getSaleNo());
		}
	}

	@Test
	public void selectAll2() {
		List<Cntm010> cntm010List = cntm010Mapper.select(c -> c);
		for (Cntm010 cntm010 : cntm010List) {
			System.out.println(cntm010.getBuldName());
		}
	}

	@Test
	public void selectWhere() {
		List<Cntm010> cntm010List = cntm010Mapper
				.select(c -> c.where(Cntm010DynamicSqlSupport.item, SqlBuilder.isEqualTo("GF")));
		for (Cntm010 cntm010 : cntm010List) {
			System.out.println(cntm010.getWorkAddr());
		}
	}

	@Test
	public void selectOne() {
		Optional<Cntm010> cntm010 = cntm010Mapper
				.selectOne(c -> c.where(Cntm010DynamicSqlSupport.saleNo, SqlBuilder.isEqualTo(814))
						.and(Cntm010DynamicSqlSupport.ctNo, SqlBuilder.isEqualTo(4121)));
		if (cntm010.isPresent()) {
			System.out.println(cntm010.get().getCtDate());
		}
	}

	@Test
	public void selectOne2() {
		SelectStatementProvider selectStatementProvider = SqlBuilder
				.select(Cntm015DynamicSqlSupport.bsNo, Cntm015DynamicSqlSupport.comp2,
						Cntm015DynamicSqlSupport.salBegCost)
				.from(Cntm015DynamicSqlSupport.cntm015)
				.where(Cntm015DynamicSqlSupport.bsItem, SqlBuilder.isEqualTo("BS"))
				.and(Cntm015DynamicSqlSupport.bsNo, SqlBuilder.isGreaterThan(new BigDecimal(20119))).build()
				.render(RenderingStrategies.MYBATIS3);
		List<Cntm015> cntm015List = cntm015Mapper.selectMany(selectStatementProvider);
		for (Cntm015 cntm015 : cntm015List) {
			System.out.println(cntm015.getBsNo());
		}
	}

	@Test
	public void testSelect() {

		SelectStatementProvider selectStatement = SqlBuilder
				.select(Cntm010DynamicSqlSupport.item, Cntm010DynamicSqlSupport.ctNo, Cntm010DynamicSqlSupport.proCtr,
						Cntm010DynamicSqlSupport.salCtr, Cntm010DynamicSqlSupport.custNo,
						Cntm010DynamicSqlSupport.workZip, Cntm010DynamicSqlSupport.ctDate,
						Cntm010DynamicSqlSupport.ctAmnt, Cntm015DynamicSqlSupport.bsItem, Cntm015DynamicSqlSupport.bsNo,
						Cntm015DynamicSqlSupport.bsCfDate, Cntm015DynamicSqlSupport.comp2,
						Cntm015DynamicSqlSupport.remark, Cntm015DynamicSqlSupport.salTntr,
						Cntm015DynamicSqlSupport.srvTntr, Cntm015DynamicSqlSupport.salBegCost,
						Cntm015DynamicSqlSupport.recTntr)
				.from(Cntm010DynamicSqlSupport.cntm010).join(Cntm015DynamicSqlSupport.cntm015)
				.on(Cntm010DynamicSqlSupport.item, SqlBuilder.equalTo(Cntm015DynamicSqlSupport.item))
				.and(Cntm010DynamicSqlSupport.ctNo, SqlBuilder.equalTo(Cntm015DynamicSqlSupport.ctNo))
				.where(Cntm010DynamicSqlSupport.item, SqlBuilder.isEqualTo("GF"))
				.and(Cntm010DynamicSqlSupport.saleNo, SqlBuilder.isEqualTo(814)).build()
				.render(RenderingStrategies.MYBATIS3);

		List<Join01> rows = join01Mapper.selectJoin01List(selectStatement);
		rows.stream().forEach(c -> {
			System.out.printf("%s-%d-%s-%.0f\n", c.getItem(), c.getCtNo(), c.getBsItem(), c.getBsNo());
		});
		assertThat(rows).hasSize(3);
	}

}
