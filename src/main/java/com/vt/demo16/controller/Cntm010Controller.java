package com.vt.demo16.controller;

import java.util.List;
import java.util.Optional;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vt.demo16.dao.Cntm010DynamicSqlSupport;
import com.vt.demo16.dao.Cntm010Mapper;
import com.vt.demo16.dao.Cntm015DynamicSqlSupport;
import com.vt.demo16.dao.Cntm015Mapper;
import com.vt.demo16.dao.Join01Mapper;
import com.vt.demo16.model.Cntm010;
import com.vt.demo16.model.Join01;
import com.vt.demo16.service.UpdateCntm010Service;

@RestController
@RequestMapping("/cntm010")
public class Cntm010Controller {

	@Autowired
	Cntm010Mapper cntm010Mapper;

	@Autowired
	Cntm015Mapper cntm015Mapper;

	@Autowired
	Join01Mapper join01Mapper;

	@Autowired
	UpdateCntm010Service updateCntm010Service;

	@GetMapping("/sale/{saleNo}")
	public List<Join01> SaleRead(@PathVariable Integer saleNo) {

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
				.where(Cntm010DynamicSqlSupport.saleNo, SqlBuilder.isEqualTo(saleNo)).build()
				.render(RenderingStrategies.MYBATIS3);

		List<Join01> rows = join01Mapper.selectJoin01List(selectStatement);

		return rows;
	}

	@GetMapping("/get/{item}/{ctNo}")
	public Optional<Cntm010> Read(@PathVariable String item, @PathVariable Integer ctNo) {
		return cntm010Mapper.selectOne(c -> c.where(Cntm010DynamicSqlSupport.item, SqlBuilder.isEqualTo(item))
				.and(Cntm010DynamicSqlSupport.ctNo, SqlBuilder.isEqualTo(ctNo)));
	}

	@PostMapping("/update")
	public void Update(@RequestBody List<Cntm010> requestCntm010) {
			updateCntm010Service.updateTx1(requestCntm010);

		// return 0;
	}
}
