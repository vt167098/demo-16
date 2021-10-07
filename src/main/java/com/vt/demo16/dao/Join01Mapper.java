package com.vt.demo16.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

import com.vt.demo16.model.Join01;

public interface Join01Mapper {

	@SelectProvider(type=SqlProviderAdapter.class, method="select")
	@Results(id="Join01Result", value = {
	        @Result(column="item", property="item", jdbcType=JdbcType.CHAR, id=true),
	        @Result(column="ct_no", property="ctNo", jdbcType=JdbcType.DECIMAL, id=true),
	        @Result(column="pro_ctr", property="proCtr", jdbcType=JdbcType.CHAR),
	        @Result(column="sal_ctr", property="salCtr", jdbcType=JdbcType.CHAR),
	        @Result(column="cust_no", property="custNo", jdbcType=JdbcType.DECIMAL),
	        @Result(column="work_zip", property="workZip", jdbcType=JdbcType.CHAR),
	        @Result(column="ct_date", property="ctDate", jdbcType=JdbcType.DATE),
	        @Result(column="ct_amnt", property="ctAmnt", jdbcType=JdbcType.DECIMAL),
	        @Result(column="bs_item", property="bsItem", jdbcType=JdbcType.CHAR),
	        @Result(column="bs_no", property="bsNo", jdbcType=JdbcType.DECIMAL),
	        @Result(column="bs_cf_date", property="bsCfDate", jdbcType=JdbcType.DATE),
	        @Result(column="comp_1", property="comp1", jdbcType=JdbcType.CHAR),
	        @Result(column="comp_2", property="comp2", jdbcType=JdbcType.CHAR),
	        @Result(column="comp_3", property="comp3", jdbcType=JdbcType.CHAR),
	        @Result(column="a_no", property="aNo", jdbcType=JdbcType.CHAR),
	        @Result(column="remark", property="remark", jdbcType=JdbcType.CHAR),
	        @Result(column="sal_tntr", property="salTntr", jdbcType=JdbcType.CHAR),
	        @Result(column="srv_tntr", property="srvTntr", jdbcType=JdbcType.CHAR),
	        @Result(column="sal_beg_cost", property="salBegCost", jdbcType=JdbcType.DECIMAL),
	        @Result(column="rec_tntr", property="recTntr", jdbcType=JdbcType.CHAR)
	})
	List<Join01> selectJoin01List(SelectStatementProvider selectStatement);
}
