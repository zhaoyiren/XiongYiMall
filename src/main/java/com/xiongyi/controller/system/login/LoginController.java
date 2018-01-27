package com.xiongyi.controller.system.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiongyi.controller.base.BaseController;
import com.xiongyi.entity.Menu;
import com.xiongyi.entity.Role;
import com.xiongyi.entity.User;
import com.xiongyi.service.appuser.AppuserManager;
import com.xiongyi.service.buttonrights.ButtonrightsManager;
import com.xiongyi.service.menu.MenuManager;
import com.xiongyi.service.role.RoleManager;
import com.xiongyi.service.user.UserManager;
import com.xiongyi.service.xybutton.XybuttonManager;
import com.xiongyi.util.AppUtil;
import com.xiongyi.util.Const;
import com.xiongyi.util.DateUtil;
import com.xiongyi.util.Jurisdiction;
import com.xiongyi.util.PageData;
import com.xiongyi.util.RightsHelper;
import com.xiongyi.util.Tools;




/**
 * @author GUXIONG
 * @description �����
 */
@Controller
public class LoginController extends BaseController {

	@Resource(name="userService")
	private UserManager userService;
	@Resource(name="menuService")
	private MenuManager menuService;
	@Resource(name="roleService")
	private RoleManager roleService;
	@Resource(name="buttonrightsService")
	private ButtonrightsManager buttonrightsService;
	@Resource(name="xybuttonService")
	private XybuttonManager xybuttonService;
	@Resource(name="appuserService")
	private AppuserManager appuserService;
	
	/**���ʵ�¼ҳ
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login_toLogin")
	public ModelAndView toLogin()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //��ȡϵͳ����
		mv.setViewName("system/index/login");
		mv.addObject("pd",pd);
		return mv;
	}
	
	/**�����¼����֤�û�
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login_login")
	@ResponseBody
	public Object login()throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "";
		String KEYDATA[] = pd.getString("KEYDATA").split(",xy,");
		if(null != KEYDATA && KEYDATA.length == 3){
			Session session = Jurisdiction.getSession();
			String sessionCode = (String)session.getAttribute(Const.SESSION_SECURITY_CODE);		//��ȡsession�е���֤��
			String code = KEYDATA[2];
			if(null == code || "".equals(code)){//�ж�Ч����
				errInfo = "nullcode"; 			//Ч����Ϊ��
			}else{
				String USERNAME = KEYDATA[0];	//��¼�������û���
				String PASSWORD  = KEYDATA[1];	//��¼����������
				pd.put("USERNAME", USERNAME);
				if(Tools.notEmpty(sessionCode) && sessionCode.equalsIgnoreCase(code)){		//�жϵ�¼��֤��
					String passwd = new SimpleHash("SHA-1", USERNAME, PASSWORD).toString();	//�������
					pd.put("PASSWORD", passwd);
					pd = userService.getUserByNameAndPwd(pd);	//�����û���������ȥ��ȡ�û���Ϣ
					if(pd != null){
						pd.put("LAST_LOGIN",DateUtil.getTime().toString());
						userService.updateLastLogin(pd);
						User user = new User();
						user.setUSER_ID(pd.getString("USER_ID"));
						user.setUSERNAME(pd.getString("USERNAME"));
						user.setPASSWORD(pd.getString("PASSWORD"));
						user.setNAME(pd.getString("NAME"));
						user.setRIGHTS(pd.getString("RIGHTS"));
						user.setROLE_ID(pd.getString("ROLE_ID"));
						user.setLAST_LOGIN(pd.getString("LAST_LOGIN"));
						user.setIP(pd.getString("IP"));
						user.setSTATUS(pd.getString("STATUS"));
						session.setAttribute(Const.SESSION_USER, user);			//���û���Ϣ��session��
						session.removeAttribute(Const.SESSION_SECURITY_CODE);	//�����¼��֤���session
						//shiro���������֤
						Subject subject = SecurityUtils.getSubject(); 
					    UsernamePasswordToken token = new UsernamePasswordToken(USERNAME, PASSWORD); 
					    try { 
					        subject.login(token); 
					    } catch (AuthenticationException e) { 
					    	errInfo = "�����֤ʧ�ܣ�";
					    }
					}else{
						errInfo = "usererror"; 				//�û�������������
						logBefore(logger, USERNAME+"��¼ϵͳ������û�������");
					}
				}else{
					errInfo = "codeerror";				 	//��֤����������
				}
				if(Tools.isEmpty(errInfo)){
					errInfo = "success";					//��֤�ɹ�
					logBefore(logger, USERNAME+"��¼ϵͳ");
				}
			}
		}else{
			errInfo = "error";	//ȱ�ٲ���
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**����ϵͳ��ҳ
	 * @param changeMenu���л��˵�����
	 * @return
	 */
	@RequestMapping(value="/main/{changeMenu}")
	@SuppressWarnings("unchecked")
	public ModelAndView login_index(@PathVariable("changeMenu") String changeMenu){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Session session = Jurisdiction.getSession();
			User user = (User)session.getAttribute(Const.SESSION_USER);				//��ȡsession�е��û���Ϣ(�����û���Ϣ)
			if (user != null) {
				User userr = (User)session.getAttribute(Const.SESSION_USERROL);		//��ȡsession�е��û���Ϣ(����ɫ��Ϣ)
				if(null == userr){
					user = userService.getUserAndRoleById(user.getUSER_ID());		//ͨ���û�ID��ȡ�û���Ϣ�ͽ�ɫ��Ϣ
					session.setAttribute(Const.SESSION_USERROL, user);				//����session	
				}else{
					user = userr;
				}
				String USERNAME = user.getUSERNAME();
				Role role = user.getRole();											//��ȡ�û���ɫ
				String roleRights = role!=null ? role.getRIGHTS() : "";				//��ɫȨ��(�˵�Ȩ��)
				session.setAttribute(USERNAME + Const.SESSION_ROLE_RIGHTS, roleRights); //����ɫȨ�޴���session
				session.setAttribute(Const.SESSION_USERNAME, USERNAME);				//�����û�����session
				List<Menu> allmenuList = new ArrayList<Menu>();
				if(null == session.getAttribute(USERNAME + Const.SESSION_allmenuList)){	
					allmenuList = menuService.listAllMenuQx("0");					//��ȡ���в˵�
					if(Tools.notEmpty(roleRights)){
						allmenuList = this.readMenu(allmenuList, roleRights);		//���ݽ�ɫȨ�޻�ȡ��Ȩ�޵Ĳ˵��б�
					}
					session.setAttribute(USERNAME + Const.SESSION_allmenuList, allmenuList);//�˵�Ȩ�޷���session��
				}else{
					allmenuList = (List<Menu>)session.getAttribute(USERNAME + Const.SESSION_allmenuList);
				}
				//�л��˵�����=====start
				List<Menu> menuList = new ArrayList<Menu>();
				if(null == session.getAttribute(USERNAME + Const.SESSION_menuList) || ("yes".equals(changeMenu))){
					List<Menu> menuList1 = new ArrayList<Menu>();
					List<Menu> menuList2 = new ArrayList<Menu>();
					//��ֲ˵�
					for(int i=0;i<allmenuList.size();i++){
						Menu menu = allmenuList.get(i);
						if("1".equals(menu.getMENU_TYPE())){
							menuList1.add(menu);
						}else{
							menuList2.add(menu);
						}
					}
					session.removeAttribute(USERNAME + Const.SESSION_menuList);
					if("2".equals(session.getAttribute("changeMenu"))){
						session.setAttribute(USERNAME + Const.SESSION_menuList, menuList1);
						session.removeAttribute("changeMenu");
						session.setAttribute("changeMenu", "1");
						menuList = menuList1;
					}else{
						session.setAttribute(USERNAME + Const.SESSION_menuList, menuList2);
						session.removeAttribute("changeMenu");
						session.setAttribute("changeMenu", "2");
						menuList = menuList2;
					}
				}else{
					menuList = (List<Menu>)session.getAttribute(USERNAME + Const.SESSION_menuList);
				}
				//�л��˵�����=====end
				if(null == session.getAttribute(USERNAME + Const.SESSION_QX)){
					session.setAttribute(USERNAME + Const.SESSION_QX, this.getUQX(USERNAME));	//��ťȨ�޷ŵ�session��
				}
				this.getRemortIP(USERNAME);	//���µ�¼IP
				mv.setViewName("system/index/main");
				mv.addObject("user", user);
				mv.addObject("menuList", menuList);
			}else {
				mv.setViewName("system/index/login");//sessionʧЧ����ת��¼ҳ��
			}
		} catch(Exception e){
			mv.setViewName("system/index/login");
			logger.error(e.getMessage(), e);
		}
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //��ȡϵͳ����
		mv.addObject("pd",pd);
		return mv;
	}
	
	/**���ݽ�ɫȨ�޻�ȡ��Ȩ�޵Ĳ˵��б�(�ݹ鴦��)
	 * @param menuList��������ܲ˵�
	 * @param roleRights�����ܵ�Ȩ���ַ���
	 * @return
	 */
	public List<Menu> readMenu(List<Menu> menuList,String roleRights){
		for(int i=0;i<menuList.size();i++){
			menuList.get(i).setHasMenu(RightsHelper.testRights(roleRights, menuList.get(i).getMENU_ID()));
			if(menuList.get(i).isHasMenu()){		//�ж��Ƿ��д˲˵�Ȩ��
				this.readMenu(menuList.get(i).getSubMenu(), roleRights);//�ǣ������Ų����Ӳ˵�
			}else{
				menuList.remove(i);
				i--;
			}
		}
		return menuList;
	}
	
	/**
	 * ����tab��ǩ
	 * @return
	 */
	@RequestMapping(value="/tab")
	public String tab(){
		return "system/index/tab";
	}
	
	/**
	 * ������ҳ���Ĭ��ҳ��
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/login_default")
	public ModelAndView defaultPage() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd.put("userCount", Integer.parseInt(userService.getUserCount("").get("userCount").toString())-1);				//ϵͳ�û���
		pd.put("appUserCount", Integer.parseInt(appuserService.getAppUserCount("").get("appUserCount").toString()));	//��Ա��
		mv.addObject("pd",pd);
		mv.setViewName("system/index/default");
		return mv;
	}
	
	/**
	 * �û�ע��
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logout")
	public ModelAndView logout(){
		String USERNAME = Jurisdiction.getUsername();	//��ǰ��¼���û���
		logBefore(logger, USERNAME+"�˳�ϵͳ");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		Session session = Jurisdiction.getSession();	//�������session����
		session.removeAttribute(Const.SESSION_USER);
		session.removeAttribute(USERNAME + Const.SESSION_ROLE_RIGHTS);
		session.removeAttribute(USERNAME + Const.SESSION_allmenuList);
		session.removeAttribute(USERNAME + Const.SESSION_menuList);
		session.removeAttribute(USERNAME + Const.SESSION_QX);
		session.removeAttribute(Const.SESSION_userpds);
		session.removeAttribute(Const.SESSION_USERNAME);
		session.removeAttribute(Const.SESSION_USERROL);
		session.removeAttribute("changeMenu");
		//shiro���ٵ�¼
		Subject subject = SecurityUtils.getSubject(); 
		subject.logout();
		pd = this.getPageData();
		pd.put("msg", pd.getString("msg"));
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //��ȡϵͳ����
		mv.setViewName("system/index/login");
		mv.addObject("pd",pd);
		return mv;
	}
	
	/**��ȡ�û�Ȩ��
	 * @param session
	 * @return
	 */
	public Map<String, String> getUQX(String USERNAME){
		PageData pd = new PageData();
		Map<String, String> map = new HashMap<String, String>();
		try {
			pd.put(Const.SESSION_USERNAME, USERNAME);
			pd.put("ROLE_ID", userService.findByUsername(pd).get("ROLE_ID").toString());//��ȡ��ɫID
			pd = roleService.findObjectById(pd);										//��ȡ��ɫ��Ϣ														
			map.put("adds", pd.getString("ADD_QX"));	//��
			map.put("dels", pd.getString("DEL_QX"));	//ɾ
			map.put("edits", pd.getString("EDIT_QX"));	//��
			map.put("chas", pd.getString("CHA_QX"));	//��
			List<PageData> buttonQXnamelist = new ArrayList<PageData>();
			if("admin".equals(USERNAME)){
				buttonQXnamelist = xybuttonService.listAll(pd);					//admin�û�ӵ�����а�ťȨ��
			}else{
				buttonQXnamelist = buttonrightsService.listAllBrAndQxname(pd);	//�˽�ɫӵ�еİ�ťȨ�ޱ�ʶ�б�
			}
			for(int i=0;i<buttonQXnamelist.size();i++){
				map.put(buttonQXnamelist.get(i).getString("QX_NAME"),"1");		//��ťȨ��
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}	
		return map;
	}
	
	/** ���µ�¼�û���IP
	 * @param USERNAME
	 * @throws Exception
	 */
	public void getRemortIP(String USERNAME) throws Exception {  
		PageData pd = new PageData();
		HttpServletRequest request = this.getRequest();
		String ip = "";
		if (request.getHeader("x-forwarded-for") == null) {  
			ip = request.getRemoteAddr();  
	    }else{
	    	ip = request.getHeader("x-forwarded-for");  
	    }
		pd.put("USERNAME", USERNAME);
		pd.put("IP", ip);
		userService.saveIP(pd);
	}  
	
}
