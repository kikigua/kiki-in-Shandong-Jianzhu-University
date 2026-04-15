package com.bn.util;
/*
 * pc
 */
public class Constant {
	
	public static String manager;
	public static String[] craft;

	public static String[] ctime;// 时间
	public static String[] difficulty;// 难度
	public static String[] flavour;
	public static String[] label;// = new String[] { "所有", "一人食", "二人世界"// 标签};
	public static String[] state=new String[]{"所有","通过","禁止"};
	public static String[] city = new String[] { "所有", "唐山市" };
	public static String[] style;
	public static String[] tool;// 厨具
	//其他
	public static String NO_MESSAGE = "No Message";		//没有查询的信息或该条件没有限制
	public static String IS_MANAGER = "<#IS_manager#>";// 判断是否为管理员

	//用户
	public static String GET_MENUBY_UID = "<#GET_MENUBY_UID#>"; // 获取用户所有菜品
	public static String GET_UID_MESSAGE = "<#GET_UID_MESSAGE#>";// 获取个人的各项信息
	public static String GET_ATTENTION = "<#GET_ATTENTION#>";// 获取关注信息
	public static String DELETE_ATTENTION = "<#DELETE_ATTENTION#>";// 取消关注
	public static String GET_FANS = "<#GET_FANS#>";// 获取粉丝
	public static String GET_MENUC = "<#GET_MENUC#>";// 获取菜谱收藏
    public static String GET_RANDOM_RECOMMENT="<#GET_RANDOM_RECOMMENT#>";//获取所有随拍收藏 pc
	public static String DELETE_MENUC = "<#DELETE_MENUC#>";// 取消菜谱收藏
	public static String DELETE_RANDOMC = "<#DELETE_RANDOMC#>";// 取消随拍收藏
	public static String GET_USER_SELECT = "<#GET_USER_SELECT#>";// 条件筛选用户

	//菜品
	public static String SEAECH_MENU = "<#SEARCH_MENU#>";// 编号获取菜谱  
	public static String INSERT_PRO = "<#INSERT_PRO#>";// 插入菜谱制作过程
	public static String GET_MSG_SELECT = "<#GET_MSG_SELECT#>";// 条件筛选获取菜谱信息 	pc
	public static String GET_PROCESS = "<#GET_PROCESS#>";// 获取菜品的制作步骤 android pc
	public static String DELETR_MENU = "<#DELETE_MENU#>"; // 按编号删除菜品 pc
	public static String INSERT_MENU = "<#INSERT_MENU#>";// 添加菜品 android pc
    public static String FORBID_MENU="<#FORBID_MENU#>";//禁止菜品通过
    public static String PERMIT_MENU="<#PERMIT_MENU#>";//允许菜品通过
	public static String IS_EXCELLENT_MENU="<#IS_EXCELLENT_MENU#>";	//是否是精品菜谱
	public static String GET_MENU_COMMENT="<#GET_MENU_COMMENT#>";	//获取用户所有菜品收藏

	//随拍
	public static String GET_RANDOMBY_UID = "<#GET_RANDOMBY_UID#>"; // 获取用户所有随拍	
	public static String GET_RANDOMBY_CODITION = "<#GET_RANDOMBY_CODITION#>"; // 根据标签、城市、状态获取随拍
	public static String GET_RANDOM = "<#GET_RANDOM#>";// 按编号查询随拍
	public static String FORBID_RANDOM="<#FORBID_RANDOM#>";//禁止随拍通过;
    public static String PERMINT_RANDOM="<#PERMINT_RANDOM#>";//允许随拍通过
	public static String IS_EXCELLENT_RANDOM="<#IS_EXCELLENT_RANDOM#>";	//是否是精品菜谱

    //图片
	public static String GET_IMAGE = "<#GET_image#>";// 按照图片名称获取图片
	public static String INSERT_PIC = "<#INSERT_PICTURE#>";// 插入图片
	//主推荐
	public static String GET_RECOMMEND = "<#Android_GET_RECOMEDN#>";// /获取主推荐内容
	public static String UPLOAD_PRIMARY_RECOMMEND="<#UPLOAD_PRIMARY_RECOMMEND#>";//添加主推荐																// 端首页的推荐随拍
	public static String DELETE_PRIMARY_RECOMMEND="<#DELETE_PRIMARY_RECOMMEND#>";//删除主推荐
	//菜品推荐
	public static String GET_RECOMMEND_MENU_PC = "<#GET_RECOMMEND_MENU_PC#>";// 菜谱推荐
	public static String ADD_RECOMMEND_MENU="<#ADD_RECOMMEND_MENU#>";		//添加精品菜谱
	public static String DELETE_RECOMMEND_MENU="<#DELETE_RECOMMEND_MENU#>";//删除精品菜谱
    public static String GET_MENU_LEFT="<#GET_MENU_LEFT#>";		//获取可以推荐的菜谱
    //随拍推荐
	public static String ADD_RECOMMEND_RANDOM="<#ADD_RECOMMEND_RANDOM#>";	//添加精品随拍
	public static String DELETE_RECOMMEND_RANDOM="<#DELETE_RECOMMEND_RANDOM#>";//删除精品随拍
	public static String GET_RECOMMEND_RANDOM_PC = "<#GET_RECOMMEND_RANDOM_PC#>";// 获取android
    public static String GET_RANDOM_LEFT="<#GET_RANDOM_LEFT#>";	//查询可以推荐的随拍
    //评论
    public static String GET_MENU_PINLUN="<#GET_MENU_PINLUN#>";//条件获取菜品评论
    public static String FORBIT_MENU_PINLUN="<#FORBIT_MENU_PINLUN#>";//禁止菜品评论
    public static String PERMIT_MENU_PINGLUN="<#PERMIT_MENU_PINGLUN#>";//通过随拍评论
    public static String GET_RANDOM_PINLUN="<#GET_RANDOM_PINLUN#>";//条件获取随拍评论
    public static String FORBIT_RANDOM_PINLUN="<#FORBIT_RANDOM_PINLUN#>";//禁止随拍评论
    public static String PERMIT_RANDOM_PINGLUN="<#PERMIT_RANDOM_PINGLUN#>";//通过随拍评论
    public static String INIT_INFO="<#INIT_INFO#>";	//初始化信息
}	
