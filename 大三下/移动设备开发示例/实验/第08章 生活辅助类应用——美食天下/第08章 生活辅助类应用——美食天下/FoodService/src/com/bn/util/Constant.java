package com.bn.util;
/*
 * service
 */
public class Constant {
	public static String NO_MESSAGE = "No Message";	//查询内容为空或条件没有限制
	public static String IS_MANAGER = "<#IS_manager#>";// 判断是否为管理员
	public static String INIT_INFO="<#INIT_INFO#>";	//初始化信息
    public static String REGIST="<#REGIST#>";//注册
	public static String IS_USER = "<#IS_USER#>"; // 是否是用户
	public static String GET_UID_MESSAGE = "<#GET_UID_MESSAGE#>";// 获取个人的各项信息 android pc
	public static String GET_ATTENTION = "<#GET_ATTENTION#>";// 获取关注信息 pc
	public static String DELETE_ATTENTION = "<#DELETE_ATTENTION#>";// 取消关注 android pc	
	public static String GET_FANS = "<#GET_FANS#>";// 获取粉丝
	public static String GET_USER_SELECT = "<#GET_USER_SELECT#>";//条件筛选用户
	public static String ADD_ATTENTION = "<#ADD_ATTENTION#>";// /增加关注 android
	public static String GET_MENUBY_UID="<#GET_MENUBY_UID#>"; //获取用户所有菜谱 pc
	public static String GET_RANDOMBY_UID="<#GET_RANDOMBY_UID#>"; //获取用户所有随拍
	//主推荐
	public static String GET_RECOMMEND = "<#Android_GET_RECOMEDN#>";// 	android、pc端获取主推荐内容
	public static String UPLOAD_PRIMARY_RECOMMEND="<#UPLOAD_PRIMARY_RECOMMEND#>";//添加主推荐	
	public static String DELETE_PRIMARY_RECOMMEND="<#DELETE_PRIMARY_RECOMMEND#>";//删除主推荐	
	//菜谱推荐
	public static String GET_RECOMMEND_MENU="<#GET_RECOMMEND_MENU#>";//android 端获取菜谱推荐 
	public static String GET_RECOMMEND_MENU_PC="<#GET_RECOMMEND_MENU_PC#>";//pc端获取菜谱推荐 
	public static String ADD_RECOMMEND_MENU="<#ADD_RECOMMEND_MENU#>";		//添加精品菜谱
	public static String DELETE_RECOMMEND_MENU="<#DELETE_RECOMMEND_MENU#>";//删除精品菜谱
    public static String GET_MENU_LEFT="<#GET_MENU_LEFT#>";	//查询可以推荐的菜谱

	//随拍推荐
	public static String GET_RECOMMEND_RANDOM="<#GET_RECOMMEND_RANDOM#>";//android端获取随拍推荐
	public static String GET_RECOMMEND_RANDOM_PC="<#GET_RECOMMEND_RANDOM_PC#>";//pc端获取随拍推荐
	public static String ADD_RECOMMEND_RANDOM="<#ADD_RECOMMEND_RANDOM#>";	//添加精品随拍
	public static String DELETE_RECOMMEND_RANDOM="<#DELETE_RECOMMEND_RANDOM#>";//删除精品随拍
    public static String GET_RANDOM_LEFT="<#GET_RANDOM_LEFT#>";	//查询可以推荐的随拍
	public static String IS_EXCELLENT_RANDOM="<#IS_EXCELLENT_RANDOM#>";	//是否是精品菜谱

    //图片
	public static String GET_IMAGE = "<#GET_image#>";// 按照图片名称获取图片 android pc
	public static String INSERT_PIC = "<#INSERT_PICTURE#>";// 插入图片
    public static String GET_THUMBNAIL="<#GET_THUMBNAIL#>"; //按名称获取缩略图 pc

	//菜品
	public static String SEAECH_MENU = "<#SEARCH_MENU#>";// 编号获取菜谱  pc 不论状态
	public static String SEAECH_MENU_YEARS = "<#SEARCH_MENU_YEARS#>";// android编号获取菜品YEARS
	public static String GET_MSG_SELECT = "<#GET_MSG_SELECT#>";//pc条件获取菜谱 
	public static String GET_MENU_LIKE = "<#GET_MENU_LIKE#>";// android条件获取菜品
	public static String GET_PROCESS = "<#GET_PROCESS#>";// android、pc获取菜品的制作步骤
	public static String INSERT_MENU = "<#INSERT_MENU#>";// android、pc添加菜品
	public static String INSERT_PRO = "<#INSERT_PRO#>";	//android、pc添加菜谱制作过程
	public static String DELETR_MENU = "<#DELETE_MENU#>"; // pc端按编号删除菜品
	public static String GET_MENUC = "<#GET_MENUC#>";// android、pc获取菜谱收藏
	public static String DELETE_MENUC = "<#DELETE_MENUC#>";//android、pc 取消菜谱收藏
    public static String FORBID_MENU="<#FORBID_MENU#>";//禁止菜品通过
    public static String PERMIT_MENU="<#PERMIT_MENU#>";//允许菜品通过
	public static String GET_COMMENT_M = "<#GET_COMMENT_M#>";// 获取菜谱评论
	public static String LIKE_MENU = "<#LIKE_MENU#>"; // 喜欢菜品
	public static String COLLECTION_MENU = "<#COLLECTION_MENU#>"; // 收藏菜品
	public static String MENU_COMENT = "<#MENU_COMMENT#>";// 菜谱评论
	public static String IS_EXCELLENT_MENU="<#IS_EXCELLENT_MENU#>";	//是否是精品菜谱
	public static String GET_MENU_COMMENT="<#GET_MENU_COMMENT#>";	//获取用户所有菜品收藏
	//评论
    public static String GET_MENU_PINLUN="<#GET_MENU_PINLUN#>";//条件获取菜品评论 pc
    public static String FORBIT_MENU_PINLUN="<#FORBIT_MENU_PINLUN#>";//禁止菜品评论
    public static String PERMIT_MENU_PINGLUN="<#PERMIT_MENU_PINGLUN#>";//通过菜品评论
    public static String GET_RANDOM_PINLUN="<#GET_RANDOM_PINLUN#>";//条件获取随拍评论
    public static String FORBIT_RANDOM_PINLUN="<#FORBIT_RANDOM_PINLUN#>";//禁止随拍评论
    public static String PERMIT_RANDOM_PINGLUN="<#PERMIT_RANDOM_PINGLUN#>";//通过随拍评论
    /**
	 * android条件查询菜谱
	 */
	public final static int MENU_LIKE_NEW = 0;	//最新
	public final static int MENU_LIKE_HOT = 1;	//最热
	public final static int MENU_LIKE_STYLE = 2;	//菜系
	public final static int MENU_LIKE_FLAVOUR = 3;	//口味
	public final static int MENU_LIKE_TIME = 4;		//时间
	public final static int MENU_LIEK_COOK = 5;		//烹饪方式
	public final static int MENU_LIKE_DIFFICULT = 6;	//难度
	public final static int MENU_LIKE_TOOL = 7;			//工具
	public final static int MENU_LIKE_NAME = 8;			//菜名 搜索
	public final static int MENU_BY_UID=9;				//用户名
	// 菜谱查询方式
	public static String getSql(String type, String args) {
		String sql = null;
		switch (Integer.valueOf(type)) {
		case MENU_LIKE_NEW: // 最新
			sql = " order by uploadTime  desc limit 10";
			break;
		case MENU_LIKE_HOT: // 最热
			sql = " order  by slike desc,uploadTime  desc limit 10 ";
			break;
		case MENU_LIKE_STYLE: // 菜系
			sql = " and style='" + args
					+ "' order by uploadTime  desc limit 10 ";
			break;
		case MENU_LIKE_FLAVOUR: // 口味
			sql = " and flavour='" + args
					+ "' order by uploadTime  desc limit 10 ";
			break;
		case MENU_LIKE_TIME: // 时间
			sql = " and ctime='" + args
					+ "' order by uploadTime  desc limit 10 ";
			break;
		case MENU_LIEK_COOK: // 烹饪方式
			sql = " and craft='" + args
					+ "' order by uploadTime  desc limit 10 ";
			break;
		case MENU_LIKE_DIFFICULT: // 难度
			sql = " and difficulty='" + args
					+ "' order by uploadTime  desc limit 10 ";
			break;
		case MENU_LIKE_TOOL: // 工具
			sql = " and difficulty like '%" + args
					+ "%' order by uploadTime  desc limit 10 ";
			break;
		case MENU_LIKE_NAME: // %名称%
			sql = " and cname like '%" + args + "%' order by uploadTime  desc limit 10";
			break;
		case MENU_BY_UID:
			sql=" and uid='"+args+"' order by uploadTime  desc limit 10";
		}
		System.out.println("sql " + sql);
		return sql;
	}
	/**
	 * android 条件菜谱查询 结束
	 */

    //随拍
	public static String GET_RANDOM = "<#GET_RANDOM#>";// 按编号查询随拍 pc
	public static String GET_RANDOM_YEARS = "<#GET_RANDOM_YEARS#>";// 按编号查询随拍
	public static String GET_RANDOMBY_CODITION="<#GET_RANDOMBY_CODITION#>"; //pc 根据条件标签或城市获取随拍
	public static String GET_RANDOM_LIKE = "<#GET_RANDOM_LIKE#>";// android 条件搜索随拍
	public static String GET_RANDOMC = "<#GET_RANDOMC#>";// 获取随拍收藏
	public static String GET_COMMENT_R = "<#GET_COMMENT_R#>";// 获取随拍评论
	public static String INSERT_RANDOM = "<#INSERT_RANDOM>";	//插入随拍
	public static String LIKE_RANDOM = "<#LIKE_RANDOM#>"; // 喜欢随拍
	public static String COLL_RANDOM = "<#COLL_RANDOM#>"; // 收藏随拍
	public static String DELETE_RANDOMC = "<#DELETE_RANDOMC#>";// 取消随拍收藏 android pc
	public static String RANDOM_COMMENT = "<#RANDOM_COMMENT#>";// 评论随拍
    public static String FORBID_RANDOM="<#FORBID_RANDOM#>";//禁止随拍通过;
    public static String PERMINT_RANDOM="<#PERMINT_RANDOM#>";//允许随拍通过
    public static String GET_RANDOM_RECOMMENT="<#GET_RANDOM_RECOMMENT#>";//获取所有随拍收藏 pc
    /**
	 * android 条件查询随拍
	 */
	public final static int RANDOM_LIKE_NEW = 0;	//最新
	public final static int RADOM_LIKE_HOT = 1;		//热门
	public final static int RANDOM_LIKE_AT = 2;		//热闹
	public final static int RANDOM_LIKE_CURE = 3;	//烘焙
	public final static int RANDOM_LIKE_SNACK = 4;	//小吃
	public final static int RANDOM_LIKE_CHILD = 5;	//儿童餐
	public final static int RANDOM_LIKE_SINGLE = 6;	//一人食
	public final static int RANDOM_LIKE_TWO = 7;	//二人世界
	public final static int RANDOM_LIKE_HOME = 8;	//家庭聚餐
	public final static int RANDOM_LIKE_FRIDEN = 9;	//朋友聚餐
	public final static int RANDOM_LIKE_WEST = 10;	//西餐
	public final static int RANDOM_LIKE_CHUANG = 11;	//创意菜
	public final static int RANDOM_LIKE_CHUAN = 12;		//川菜
	public final static int RANDOM_LIKE_YUE = 13;		//粤菜
	public final static int RANDOM_lIKE_JAPAN = 14;		//日料	
	public final static int RANDOM_LIKE_CHOCOLATE = 15;	//巧克力
	public final static int RANDOM_LIKE_BJL = 16;		//冰激凌
	public final static int RANDOM_LIKE_BAKE = 17;		//烘烤
	public final static int RANDOM_LIKE_INTRODUCE = 18;	//介绍 用于搜索
	public final static int RANDOM_BY_UID=19;			//获取用户的随拍
	public final static int[] RANDOM_LIKE = new int[] { RANDOM_LIKE_NEW,
			RADOM_LIKE_HOT, RANDOM_LIKE_AT, RANDOM_LIKE_CURE,
			RANDOM_LIKE_SNACK, RANDOM_LIKE_CHILD, RANDOM_LIKE_SINGLE,
			RANDOM_LIKE_TWO, RANDOM_LIKE_HOME, RANDOM_LIKE_FRIDEN,
			RANDOM_LIKE_WEST, RANDOM_LIKE_CHUANG, RANDOM_LIKE_CHUAN,
			RANDOM_LIKE_YUE, RANDOM_lIKE_JAPAN, RANDOM_LIKE_CHOCOLATE,
			RANDOM_LIKE_BJL, RANDOM_LIKE_BAKE };

	public static String[] Random_Args = {
			" order by uploadTime  desc limit 10  ",// 最新
			" order  by slike desc,uploadTime  desc limit 10 ",// 热门
			" order by collection desc,uploadTime  desc limit 10 ", // 热闹
			" and label like '%烘焙%' order by uploadTime  desc limit 10",
			" and label like '%小吃%' order by uploadTime  desc limit 10",
			" and label like '%儿童餐%' order by uploadTime  desc limit 10",
			" and label like '%一人食%' order by uploadTime  desc limit 10",
			" and label like '%二人世界%' order by uploadTime  desc limit 10",
			" and label like '%家庭聚餐%' order by uploadTime  desc limit 10",
			" and label like '%朋友聚会%' order by uploadTime  desc limit 10",
			" and label like '%西餐%' order by uploadTime  desc limit 10",
			" and label like '%创意菜%' order by uploadTime  desc limit 10",
			" and label like '%川菜%' order by uploadTime  desc limit 10",
			" and label like '%粤菜%' order by uploadTime  desc limit 10",
			" and label like '%日料%' order by uploadTime  desc limit 10",
			" and label like '%巧克力%' order by uploadTime  desc limit 10",
			" and label like '%冰激凌%' order by uploadTime  desc limit 10",
			" and label like '%烘烤%' order by uploadTime  desc limit 10", };

	public static String getRandomSql(int type, String arg) {
		String sql = null;
		switch (type) 
		{
		case RANDOM_BY_UID:
			sql=" and uid='"+arg+"' order by uploadTime  desc limit 10";
			break;
		case RANDOM_LIKE_INTRODUCE:
			sql = " and introduce like '%" + arg + "%' order by uploadTime  desc limit 10";
			break;
		default:
			sql = Random_Args[type];
			break;
		}
		return sql;
	}
	/**
	 * android 条件查询随拍结束
	 */

}