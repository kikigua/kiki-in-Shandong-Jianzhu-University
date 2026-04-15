package com.example.util;

import android.content.Context;

import com.example.MySQLite.DatabaseUtil;
import com.example.activity.R;

/*
 * android 
 */
public class Constant {
	public static int ScreenWidth; // 屏幕分辨率
	public static int ScreenHeight;
	public static String NO_MESSAGE = "No Message";// 查询结果为空或条件没有限制
	public static String INIT_INFO = "<#INIT_INFO#>"; // 初始化信息
	public static String IS_USER = "<#IS_USER#>"; // 是否是用户
	public static String ADD_ATTENTION = "<#ADD_ATTENTION#>";// /增加关注
	public static String DELETE_ATTENTION = "<#DELETE_ATTENTION#>";// 取消关注
	public static String GET_UID_MESSAGE = "<#GET_UID_MESSAGE#>";// 获取个人的各项信息
	public static String REGIST = "<#REGIST#>";// 用户注册
	public static String GET_RECOMMEND = "<#Android_GET_RECOMEDN#>";// /获取主推荐
	public static String GET_RECOMMEND_MENU = "<#GET_RECOMMEND_MENU#>";// 获取菜谱推荐
	public static String GET_RECOMMEND_RANDOM = "<#GET_RECOMMEND_RANDOM#>";// 获取随拍推荐
	public static String SEAECH_MENU_YEARS = "<#SEARCH_MENU_YEARS#>";// 编号获取菜品
	public static String GET_PROCESS = "<#GET_PROCESS#>";// 获取菜品的制作步骤
	public static String LIKE_MENU = "<#LIKE_MENU#>"; // 喜欢菜谱
	public static String COLLECTION_MENU = "<#COLLECTION_MENU#>"; // 收藏菜品
	public static String GET_MENU_LIKE = "<#GET_MENU_LIKE#>";// 模糊搜索菜品
	public static String INSERT_PRO = "<#INSERT_PRO#>";// 插入菜谱制作过程
	public static String INSERT_MENU = "<#INSERT_MENU#>";// 添加菜品
	public static String MENU_COMENT = "<#MENU_COMMENT#>";// 评论菜谱
	public static String GET_COMMENT_M = "<#GET_COMMENT_M#>";// 获取菜谱评论
	public static String DELETE_MENUC = "<#DELETE_MENUC#>";// 取消菜谱收藏
	public static String GET_MENUC = "<#GET_MENUC#>"; // 获取菜谱收藏
	public static String GET_RANDOM_YEARS = "<#GET_RANDOM_YEARS#>";// 按编号查询随拍
	public static String LIKE_RANDOM = "<#LIKE_RANDOM#>"; // 喜欢随拍
	public static String COLL_RANDOM = "<#COLL_RANDOM#>"; // 收藏随拍
	public static String GET_RANDOM_LIKE = "<#GET_RANDOM_LIKE#>";// 模糊搜索随拍
	public static String INSERT_RANDOM = "<#INSERT_RANDOM>"; // 插入随拍
	public static String RANDOM_COMMENT = "<#RANDOM_COMMENT#>";// 评论随拍
	public static String GET_COMMENT_R = "<#GET_COMMENT_R#>";// 获取随拍评论
	public static String DELETE_RANDOMC = "<#DELETE_RANDOMC#>";// 取消随拍收藏
	public static String GET_RANDOMC = "<#GET_RANDOMC#>";// 获取随拍收藏
	public static String GET_IMAGE = "<#GET_image#>";
	public static String INSERT_PIC = "<#INSERT_PICTURE#>";// 插入图片
	public static String GET_THUMBNAIL = "<#GET_THUMBNAIL#>"; // 按名称获取缩略图
	public static int PROCESS_ADD = 6; // 添加过程描述
	public static int DELETE_PROCESS = 5; // 删除过程
	public static int PROCESS = 6; // 过程填写
	public static int PICTURE_SIMLE = 0; // 本地图片(选择主图)
	public static int CAMERA_SIMPLE = 1; // 拍照(主图)
	public static int CAMERA = 3; // 拍照(制作过程 ,随拍)
	public static int PICTURE = 2; // 本地图片(制作过程,随拍)
	public static int GALLERY_PREVIEWS = 6; // /图片
	/*
	 * 随拍查询开始时
	 */
	public final static int RANDOM_LIKE_NEW = 0;
	public final static int RADOM_LIKE_HOT = 1;
	public final static int RANDOM_LIKE_AT = 2;
	public final static int RANDOM_LIKE_CURE = 3;
	public final static int RANDOM_LIKE_SNACK = 4;
	public final static int RANDOM_LIKE_CHILD = 5;
	public final static int RANDOM_LIKE_SINGLE = 6;
	public final static int RANDOM_LIKE_TWO = 7;
	public final static int RANDOM_LIKE_HOME = 8;
	public final static int RANDOM_LIKE_FRIDEN = 9;
	public final static int RANDOM_LIKE_WEST = 10;
	public final static int RANDOM_LIKE_CHUANG = 11;
	public final static int RANDOM_LIKE_CHUAN = 12;
	public final static int RANDOM_LIKE_YUE = 13;
	public final static int RANDOM_lIKE_JAPAN = 14;
	public final static int RANDOM_LIKE_CHOCOLATE = 15;
	public final static int RANDOM_LIKE_BJL = 16;
	public final static int RANDOM_LIKE_BAKE = 17;
	public final static int RANDOM_LIKE_INTRODUCE = 18;
	public final static int RANDOM_BY_UID = 19; // 获取用户的随拍
	public final static int[] RANDOM_LIKE = new int[] { RANDOM_LIKE_NEW,
			RADOM_LIKE_HOT, RANDOM_LIKE_AT, RANDOM_LIKE_CURE,
			RANDOM_LIKE_SNACK, RANDOM_LIKE_CHILD, RANDOM_LIKE_SINGLE,
			RANDOM_LIKE_TWO, RANDOM_LIKE_HOME, RANDOM_LIKE_FRIDEN,
			RANDOM_LIKE_WEST, RANDOM_LIKE_CHUANG, RANDOM_LIKE_CHUAN,
			RANDOM_LIKE_YUE, RANDOM_lIKE_JAPAN, RANDOM_LIKE_CHOCOLATE,
			RANDOM_LIKE_BJL, RANDOM_LIKE_BAKE };
	// 随拍查询项图标ID
	public static int random_imgs[] = { R.drawable.pai_new, R.drawable.remen,
			R.drawable.renao, R.drawable.hongbei, R.drawable.xican,
			R.drawable.ertongcan, R.drawable.yirenshi, R.drawable.errenshijie,
			R.drawable.jiatingjvhui, R.drawable.pengyoujvhui, R.drawable.xican,
			R.drawable.chuangyicai, R.drawable.chuancai, R.drawable.yuecai,
			R.drawable.riliao, R.drawable.qiaokeli, R.drawable.bingjiling,
			R.drawable.shaokao };
	// 随拍查询项文字
	public static int random_text[] = { R.string.pai_new, R.string.remen,
			R.string.renao, R.string.hongbei, R.string.xiaochi,
			R.string.ertongcan, R.string.yirenshi, R.string.errenshijie,
			R.string.jiatingjvhui, R.string.pengyoujvhui, R.string.xican,
			R.string.chuangyicai, R.string.chuancai, R.string.yuecai,
			R.string.riliao, R.string.qiaokeli, R.string.bingjiling,
			R.string.hongkao };
	/*
	 * 随拍查询结束
	 */

	/*
	 * 菜品查询开始
	 */
	// 菜品查询项图片ID
	public static int menu_imgs[] = { R.drawable.recipe_lable_1_1,
			R.drawable.recipe_lable_1_2, R.drawable.recipe_lable_1_3,
			R.drawable.recipe_lable_2_1, R.drawable.recipe_lable_2_2,
			R.drawable.recipe_lable_2_3, R.drawable.recipe_lable_3_1,
			R.drawable.recipe_lable_3_2, };
	// 菜品查询项文字
	public static int menu_text[] = { R.string.zuixintuijian,
			R.string.remenpaihang, R.string.quanbucaipu,
			R.string.changjiancaishi, R.string.changjianzhushi,
			R.string.shicai, R.string.jiankangshipu, R.string.zhongshicaixi, };
	public final static int MENU_LIKE_NEW = 0; // 最新
	public final static int MENU_LIKE_HOT = 1;// 最热
	public final static int MENU_LIKE_STYLE = 2; // 菜系
	public final static int MENU_LIKE_FLAVOUR = 3; // 口味
	public final static int MENU_LIKE_TIME = 4; // 时间
	public final static int MENU_LIEK_COOK = 5; // 烹饪方式
	public final static int MENU_LIKE_DIFFICULT = 6; // 难度
	public final static int MENU_LIKE_TOOL = 7; // 工具
	public final static int MENU_LIKE_NAME = 8; // 菜名模糊搜索
	public final static int MENU_BY_UID = 9; // 用户名
	public final static int[] MENU_LIKE = new int[] { MENU_LIKE_NEW,
			MENU_LIKE_HOT, MENU_LIKE_STYLE, MENU_LIKE_FLAVOUR, MENU_LIKE_TIME,
			MENU_LIEK_COOK, MENU_LIKE_DIFFICULT, MENU_LIKE_TOOL, MENU_LIKE_NAME };
	// 菜品添加时用到的相关信息
	public static String[] difficulty;
	public static String[] flavour;
	public static String[] craft;
	public static String[] ctime;
	public static String[] tool;
	public static String[] style;
	public static String[][] MENU_ARGS;

	/*
	 * 菜品查询结束
	 */
	public static void init(Context context) {
		ScreenWidth = Utils.getScreenWidth(context);
		ScreenHeight = Utils.getScreenHeight(context);
		difficulty = FileUtils.loadFromSDFile(context, "difficulty.txt").split(",");
		flavour = FileUtils.loadFromSDFile(context, "flavour.txt").split(",");
		craft = FileUtils.loadFromSDFile(context, "craft.txt").split(",");
		ctime = FileUtils.loadFromSDFile(context, "ctime.txt").split(",");
		tool = FileUtils.loadFromSDFile(context, "tool.txt").split(",");
		style = FileUtils.loadFromSDFile(context, "style.txt").split(",");
		MENU_ARGS = new String[][] { new String[] { "最新" }, // 最新
				new String[] { "最热" }, // 最热
				style, flavour, ctime, craft, difficulty, tool };
		
		System.out.println("style[0].trim "+style[0].trim());
		System.out.println("style[0].length "+style[0].trim().length());
		String temp="常见菜式";
		System.out.println("常见菜式.length()"+temp.length());
		
		System.out.println("style[1].trim "+style[1].trim());
		System.out.println("style[1].length "+style[1].trim().length());
		temp="上海菜";
		System.out.println("上海菜.length()"+temp.length());
		
		System.out.println("style[style.length-1].trim "+style[style.length-1].trim());
		System.out.println("style[style.length-1].length "+style[style.length-1].trim().length());
		temp="其它";
		System.out.println("其它.length()"+temp.length());
		
	}
}
