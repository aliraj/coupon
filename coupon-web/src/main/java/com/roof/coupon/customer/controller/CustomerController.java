package com.roof.coupon.customer.controller;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;

import com.roof.coupon.GenderEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import com.roof.coupon.customer.entity.Customer;
import com.roof.coupon.customer.entity.CustomerVo;
import com.roof.coupon.customer.service.api.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "customer", description = "客户管理")
@Controller
@RequestMapping("coupon")
public class CustomerController {
	private ICustomerService customerService;

	@ApiOperation(value = "获得客户基础信息")
	@RequestMapping(value = "customer/base", method = {RequestMethod.GET})
	public @ResponseBody Result<Map<String,Object>> base(HttpServletRequest request) {
		Map<String,Object> map = Maps.newHashMap();
		GenderEnum[] genderEnums = GenderEnum.values();
		map.put("genders",genderEnums);
		return new Result(Result.SUCCESS, map);
	}

	@ApiOperation(value = "获得客户分页列表")
    @RequestMapping(value = "customer", method = {RequestMethod.GET})
    public @ResponseBody Result<Page> list(Customer customer, HttpServletRequest request) {
	    Page page = PageUtils.createPage(request);
	    page = customerService.page(page, customer);
	    return new Result(Result.SUCCESS, page);
	}


    @ApiOperation(value = "新增客户")
	@RequestMapping(value = "customer", method = {RequestMethod.POST})
	public @ResponseBody Result create(@RequestBody Customer customer) {
		if (customer != null) {
			customerService.save(customer);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID加载客户")
    @RequestMapping(value = "customer/{id}", method = {RequestMethod.GET})
    public @ResponseBody Result<CustomerVo> load(@PathVariable Long id) {
		CustomerVo customerVo = customerService.load(new Customer(id));
        return new Result(Result.SUCCESS,customerVo);
    }

	@ApiOperation(value = "根据ID更新客户")
	@RequestMapping(value = "customer/{id}", method = {RequestMethod.PUT})
	public @ResponseBody Result update(@PathVariable Long id ,@RequestBody Customer customer) {
		if (id != null && customer != null) {
			customer.setId(id);
			customerService.updateIgnoreNull(customer);
			return new Result("保存成功!");
		} else {
			return new Result(Result.FAIL,"数据传输失败!");
		}
	}

    @ApiOperation(value = "根据ID删除客户")
	@RequestMapping(value = "customer/{id}", method = {RequestMethod.DELETE})
	public @ResponseBody Result delete(@PathVariable Long id ) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		customerService.delete(new Customer(id));
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setCustomerService(
			@Qualifier("customerService") ICustomerService customerService) {
		this.customerService = customerService;
	}


}
