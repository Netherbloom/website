package com.ru.util;


/**
 * 公共数组 - 用单例模式
 * @author PyePye
 * 2014.12.18
 */
public class CommArray {
	/** 
	 * 私有化构造方法 
	 */
	private CommArray() {} 
	/** 
     * 静态初始化器，线程安全 
     */ 
	public static final CommArray instance = new CommArray();
	
	public static CommArray getInstance(){
		return instance;  
	}

	//登录方式
	public String[] arr_LoginType = {"普通"};
	//删除状态
	public String[] arr_Delflag = {"正常","删除"};
	//启用状态
	public String[] arr_Isuse = {"停用","启用"};
	//状态
	public String[] arr_YesNo = {"否", "是" };
	

	/**
	 * 服务台取书时间
	 * 单位：分钟 默认30分钟
	 */
	public int fetchTime=30;

	public int getFetchTime() {
		return fetchTime;
	}

	public void setFetchTime(int fetchTime) {
		this.fetchTime = fetchTime;
	}


	public String[] getArr_LoginType() {
		return arr_LoginType;
	}

	public void setArr_LoginType(String[] arr_LoginType) {
		this.arr_LoginType = arr_LoginType;
	}

	public String[] getArr_Delflag() {
		return arr_Delflag;
	}

	public void setArr_Delflag(String[] arr_Delflag) {
		this.arr_Delflag = arr_Delflag;
	}

	public String[] getArr_Isuse() {
		return arr_Isuse;
	}

	public void setArr_Isuse(String[] arr_Isuse) {
		this.arr_Isuse = arr_Isuse;
	}


	public String[] getArr_YesNo() {
		return arr_YesNo;
	}

	public void setArr_YesNo(String[] arr_YesNo) {
		this.arr_YesNo = arr_YesNo;
	}

	
	/**
	 * 错误状态
	 * @author
	 *
	 */
	public static enum ReturnStatus {
		 _101("类型错误"),
		 _102("非法客户端访问"),
		 _103("参数错误"),
		 _104("保存失败"),
		 _105("删除失败"),
		 _106("请求频繁,请稍后再试"),
		 _200("操作成功"),
		 _201("操作失败");
		 
		 public final String msg;
		ReturnStatus(String msg) {
			this.msg = msg;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}
		public String getMsg() {
			return this.msg;
		}
	}


	/**
	 * 注册来源
	 */
	public static enum RegisterSource{
		_后台添加("admin"),
		_手机注册("phone"),
		_邮箱注册("email");
		public final String value;
		RegisterSource(String value) {
			this.value = value;
		}
		
		public String getName() {
			return super.toString().replace("_", "").toString();
		}
		public String getValue() {
			return this.value;
		}
	}

	/**
	 * 用户类型
	 */
	public static enum UserType {
		_超级管理员(1),
		_管理员(2),
		_普通用户(3);

		public final int name;
		UserType(int name) {
			this.name = name;
		}

		public int getValue() {
			return this.name;
		}
		public String getName() {
			return super.toString().replace("_", "").toString();
		}
	}

	/**
	 * 用户日志
	 * @author debbie
	 *
	 */
	public static enum UserLogType{
		_登录("login"),
		_登出("layout");

		public final String value;
		UserLogType(String value) {
			this.value = value;
		}
		
		public String getName() {
			return super.toString().replace("_", "").toString();
		}
		public String getValue() {
			return this.value;
		}
	}
	
	/**
	 * 积分规则类型
	 */
	public static enum requestType {
		_天气("weather"),
		_机器问答("robot");

		public final String value;
		requestType(String value) {
			this.value = value;
		}
		
		public String getName() {
			return super.toString().replace("_", "").toString();
		}
		public String getValue() {
			return this.value;
		}
	}


	
	
}
