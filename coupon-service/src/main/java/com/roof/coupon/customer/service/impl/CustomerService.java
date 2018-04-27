package com.roof.coupon.customer.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.roof.coupon.DefaultUseableEnum;
import com.roof.coupon.area.entity.Area;
import com.roof.coupon.area.entity.AreaVo;
import com.roof.coupon.area.service.api.IAreaService;
import com.roof.coupon.customer.entity.CustomerTypeTransform;
import com.roof.coupon.wechat.service.api.IWeChatHander;
import com.roof.coupon.wechat.service.impl.WeChatDto;
import org.apache.commons.lang3.StringUtils;
import org.roof.roof.dataaccess.api.Page;
import com.roof.coupon.customer.dao.api.ICustomerDao;
import com.roof.coupon.customer.entity.Customer;
import com.roof.coupon.customer.entity.CustomerVo;
import com.roof.coupon.customer.service.api.ICustomerService;
import org.roof.web.user.service.api.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CustomerService implements ICustomerService {
	private ICustomerDao customerDao;

	private static Logger logger = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private IWeChatHander weChatHander;


	@Autowired
	private IUserService userService;

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


	public CustomerVo loadByOpenid(String openId) {
		Customer customer = new Customer();
		customer.setWeixinOpenId(openId);
		return (CustomerVo) customerDao.selectForObject("loadCustomerByOpenId", customer);
	}


	public WeChatDto saveOrUpdate(CustomerVo customerVo) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerVo, customer);
		WeChatDto weChatDto = null;
		try {
			weChatDto = weChatHander.getWeChatDto(customerVo.getCode());
			if (weChatDto != null && StringUtils.isEmpty(weChatDto.getErrcode())) {
				customer.setWeixinOpenId(weChatDto.getOpenid());
				customer.setUnionid(weChatDto.getUnionid());
			} else {
				logger.error("获取微信Openid出错:", weChatDto.getErrmsg());
			}
		} catch (IOException e) {
			logger.error("获取微信Openid出错:", e.getCause());
		}
		Assert.notNull(customer.getWeixinOpenId(), "openid不能为空");
		Assert.notNull(weChatDto, "WeChatDto不能为空");
		CustomerVo vo = loadByOpenid(customer.getWeixinOpenId());
		customer.setUseable(DefaultUseableEnum.usable.getCode());
		if (vo == null) {
			customer.setFollowTime(new Date());
			Long id = (Long) this.save(customer);
			weChatDto.setUserId(id);
		} else {
			customer.setId(vo.getId());
			updateIgnoreNull(customer);
			//增加用户状态
			if (vo.getBinaryType() != null) {
				weChatDto.setUserTags(CustomerTypeTransform.getAllUserTag(vo.getBinaryType()));
			}
			weChatDto.setUserId(vo.getId());
		}
		return weChatDto;
	}

	@Autowired
	public void setICustomerDao(
			@Qualifier("customerDao") ICustomerDao  customerDao) {
		this.customerDao = customerDao;
	}
	

}
