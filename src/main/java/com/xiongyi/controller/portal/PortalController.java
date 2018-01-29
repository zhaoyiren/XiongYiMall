package com.xiongyi.controller.portal;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xiongyi.controller.base.BaseController;
import com.xiongyi.entity.Page;

@Controller
@RequestMapping(value="/portal")
public class PortalController extends BaseController{

	@RequestMapping(value="/index")
	public ModelAndView list(Page page) throws Exception{
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("potal/index");
		return mv;
	}
}
