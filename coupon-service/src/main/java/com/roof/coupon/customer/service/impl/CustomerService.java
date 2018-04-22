package com.roof.coupon.customer.service.impl;

import java.io.Serializable;
import java.util.List;

import com.roof.coupon.area.entity.Area;
import com.roof.coupon.area.entity.AreaVo;
import com.roof.coupon.area.service.api.IAreaService;
import org.apache.commons.lang3.StringUtils;
import org.roof.roof.dataaccess.api.Page;
import com.roof.coupon.customer.dao.api.ICustomerDao;
import com.roof.coupon.customer.entity.Customer;
import com.roof.coupon.customer.entity.CustomerVo;
import com.roof.coupon.customer.service.api.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
	private ICustomerDao customerDao;

	@Autowired
	private IAreaService areaService;

	public Serializable save(Customer customer){
		return customerDao.save(customer);
	}

	public void delete(Customer customer){
		customerDao.delete(customer);
	}
	
	public void deleteByExample(Customer customer){
		customerDao.deleteByExample(customer);
	}

	public void update(Customer customer){
		customerDao.update(customer);
	}
	
	public void updateIgnoreNull(Customer customer){
		customerDao.updateIgnoreNull(customer);
	}
		
	public void updateByExample(Customer customer){
		customerDao.update("updateByExampleCustomer", customer);
	}

	public CustomerVo load(Customer customer){
		return (CustomerVo)customerDao.reload(customer);
	}
	
	public CustomerVo selectForObject(Customer customer){
		return (CustomerVo)customerDao.selectForObject("selectCustomer",customer);
	}
	
	public List<CustomerVo> selectForList(Customer customer){
		return (List<CustomerVo>)customerDao.selectForList("selectCustomer",customer);
	}
	
	public Page page(Page page, Customer customer) {
		Page customerPage = customerDao.page(page, customer);
		List<CustomerVo> customerVoList = (List<CustomerVo>) customerPage.getDataList();
		for (CustomerVo customerVo : customerVoList){
			Area area = new Area();
			if(StringUtils.isNotEmpty(customerVo.getProvince())){
				area.setProvince(customerVo.getProvince());
				area.setCity(customerVo.getCity());
				AreaVo areaVo = areaService.selectForObject(area);
				if(areaVo != null){
					customerVo.setCity(areaVo.getCityCn());
					customerVo.setProvince(areaVo.getProvinceCn());
					customerVo.setCountry(areaVo.getNationCn());
				}
			}
		}
		customerPage.setDataList(customerVoList);
		return customerPage;
	}

	@Autowired
	public void setICustomerDao(
			@Qualifier("customerDao") ICustomerDao  customerDao) {
		this.customerDao = customerDao;
	}
	

}
