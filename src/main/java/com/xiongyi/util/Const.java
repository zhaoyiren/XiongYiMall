package com.xiongyi.util;

import org.springframework.context.ApplicationContext;

/**
 * @author GUXIONG
 * ��Ŀ��س���
 */
public class Const {
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";//��֤��
	public static final String SESSION_USER = "sessionUser";			//session�õ��û�
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String sSESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String SESSION_menuList = "menuList";			//��ǰ�˵�
	public static final String SESSION_allmenuList = "allmenuList";		//ȫ���˵�
	public static final String SESSION_QX = "QX";
	public static final String SESSION_userpds = "userpds";			
	public static final String SESSION_USERROL = "USERROL";				//�û�����
	public static final String SESSION_USERNAME = "USERNAME";			//�û���
	public static final String TRUE = "T";
	public static final String FALSE = "F";
	public static final String LOGIN = "/login_toLogin.do";				//��¼��ַ
	public static final String SYSNAME = "admin/config/SYSNAME.txt";	//ϵͳ����·��
	public static final String PAGE	= "admin/config/PAGE.txt";			//��ҳ��������·��
	public static final String EMAIL = "admin/config/EMAIL.txt";		//�������������·��
	public static final String SMS1 = "admin/config/SMS1.txt";			//�����˻�����·��1
	public static final String SMS2 = "admin/config/SMS2.txt";			//�����˻�����·��2
	public static final String FWATERM = "admin/config/FWATERM.txt";	//����ˮӡ����·��
	public static final String IWATERM = "admin/config/IWATERM.txt";	//ͼƬˮӡ����·��
	public static final String WEIXIN	= "admin/config/WEIXIN.txt";	//΢������·��
	public static final String WEBSOCKET = "admin/config/WEBSOCKET.txt";//WEBSOCKET����·��
	public static final String FILEPATHIMG = "uploadFiles/uploadImgs/";	//ͼƬ�ϴ�·��
	public static final String FILEPATHFILE = "uploadFiles/file/";		//�ļ��ϴ�·��
	public static final String FILEPATHTWODIMENSIONCODE = "uploadFiles/twoDimensionCode/"; //��ά����·��
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)|(app)|(weixin)|(static)|(main)|(websocket)).*";	//����ƥ���ֵ�ķ���·�����أ�����
	public static ApplicationContext WEB_APP_CONTEXT = null; //��ֵ����web��������ʱ��WebAppContextListener��ʼ��
	
	/**
	 * APP Constants
	 */
	//appע��ӿ�_����Э�����)
	public static final String[] APP_REGISTERED_PARAM_ARRAY = new String[]{"countries","uname","passwd","title","full_name","company_name","countries_code","area_code","telephone","mobile"};
	public static final String[] APP_REGISTERED_VALUE_ARRAY = new String[]{"����","�����ʺ�","����","��ν","����","��˾����","���ұ��","����","�绰","�ֻ���"};
	
	//app�����û�����ȡ��Ա��Ϣ�ӿ�_����Э���еĲ���
	public static final String[] APP_GETAPPUSER_PARAM_ARRAY = new String[]{"USERNAME"};
	public static final String[] APP_GETAPPUSER_VALUE_ARRAY = new String[]{"�û���"};
	
	
}
