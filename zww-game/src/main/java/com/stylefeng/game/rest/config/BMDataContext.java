package com.stylefeng.game.rest.config;

import org.springframework.context.ApplicationContext;


import java.util.*;

public class BMDataContext {
	public static final String USER_SESSION_NAME = "user";
	public static final String GUEST_USER = "guest";
	public static final String IM_USER_SESSION_NAME = "im_user";
	public static final String GUEST_USER_ID_CODE = "BEIMIGUESTUSEKEY" ;
	public static final String SERVICE_QUENE_NULL_STR = "service_quene_null" ;
	public static final String DEFAULT_TYPE = "default"	;		//默认分类代码
	
	public static final String ZWW_SYSTEM_DIC = "com.dic.system.template";
	public static final String ZWW_SYSTEM_GAME_TYPE_DIC = "com.dic.game.type";
	public static final String ZWW_SYSTEM_GAME_SCENE_DIC = "com.dic.scene.item";

	public static final String ZWW_SYSTEM_GAME_CARDTYPE_DIC = "com.dic.game.dizhu.cardtype";

	public static final String ZWW_SYSTEM_GAME_ROOMTITLE_DIC = "com.dic.game.room.title";
	
	public static final String ZWW_MESSAGE_EVENT = "command" ;
	public static final String ZWW_PLAYERS_EVENT = "players" ;
	
	public static final String ZWW_GAMESTATUS_EVENT = "gamestatus" ;
	
	public static final String ZWW_SEARCHROOM_EVENT = "searchroom" ;
	
	public static final String ZWW_GAME_PLAYWAY = "game_playway";
	
	public static final String ZWW_SYSTEM_AUTH_DIC = "com.dic.auth.resource";

	public static final String ZWW_SYSTEM_ROOM = "room" ;
	
	
	public static String SYSTEM_ORGI = "zhuawawa" ;
	
	private static int WebIMPort = 8081 ;
	
	private static boolean gameServerRunning = false ;			//游戏服务状态
	
	private static ApplicationContext applicationContext ;

	public static Map<String , Boolean> model = new HashMap<String,Boolean>();
	
	
	
	public static int getWebIMPort() {
		return WebIMPort;
	}

	public static void setWebIMPort(int webIMPort) {
		WebIMPort = webIMPort;
	}
	
	public static void setApplicationContext(ApplicationContext context){
		applicationContext = context ;
	}
	
	
	/**
	 * 根据ORGI找到对应 游戏配置
	 * @param orgi
	 * @return
	 */
	public static String getGameAccountConfig(String orgi){
		return BMDataContext.ConfigNames.ACCOUNTCONFIG.toString()+"_"+orgi ;
	}
	
	/**
	 * 根据ORGI找到对应 游戏配置
	 * @param orgi
	 * @return
	 */
	public static String getGameConfig(String orgi){
		return BMDataContext.ConfigNames.GAMECONFIG.toString()+"_"+orgi ;
	}
	
	/**
	 * 根据ORGI找到对应 游戏配置
	 * @param orgi
	 * @return
	 */
	public static String getGameAiConfig(String orgi){
		return BMDataContext.ConfigNames.AICONFIG.toString()+"_"+orgi ;
	}
	
	
	public static ApplicationContext getContext(){
		return applicationContext ;
	}
	
	/**
	 * 系统级的加密密码 ， 从CA获取
	 * @return
	 */
	public static String getSystemSecrityPassword(){
		return "BEIMI" ;
	}
	
	public enum NameSpaceEnum{
		
		SYSTEM("/bm/system") ,
		GAME("/zww/game");
		
		private String namespace ;
		
		public String getNamespace() {
			return namespace;
		}

		public void setNamespace(String namespace) {
			this.namespace = namespace;
		}

		NameSpaceEnum(String namespace){
			this.namespace = namespace ;
		}
		
		public String toString(){
			return super.toString().toLowerCase() ;
		}
	}
	
	public enum ModelType{
		ROOM,
		HALL;
		public String toString(){
			return super.toString().toLowerCase() ;
		}
	}
	
	public enum ConfigNames{
		GAMECONFIG,
		AICONFIG,
		ACCOUNTCONFIG,
		PLAYWAYCONFIG,
		PLAYWAYGROUP,
		PLAYWAYGROUPITEM;
		public String toString(){
			return super.toString().toLowerCase() ;
		}
	}
	
	public enum UserDataEventType{
		SAVE,UPDATE,DELETE;
		public String toString(){
			return super.toString().toLowerCase() ;
		}
	}
	
	public enum PlayerAction{
		GANG,
		PENG,
		HU,
		CHI,
		GUO;
		public String toString(){
			return super.toString().toLowerCase() ;
		}
	}

	public enum GameTypeEnum{
		MAJIANG,
		DIZHU,
		DEZHOU;
		public String toString(){
			return super.toString().toLowerCase() ;
		}
	}
	
	public enum PlayerTypeEnum{
		AI,			//AI
		NORMAL,		//普通玩家
		OFFLINE,	//托管玩家
		LEAVE;		//离开房间的玩家
		public String toString(){
			return super.toString().toLowerCase() ;
		}
	}
	
	public enum GameStatusEnum{
		READY,			//AI
		NOTREADY,		//普通玩家
		MANAGED,
		PLAYING,
		TIMEOUT;		//登录会话过期
		public String toString(){
			return super.toString().toLowerCase() ;
		}
	}
	
	public enum MessageTypeEnum{
		JOINROOM,
		MESSAGE,INSTRUCT, 
		END,
		TRANS, STATUS , AGENTSTATUS , SERVICE, WRITING;
		
		public String toString(){
			return super.toString().toLowerCase() ;
		}
	}
	
	public enum PVActionEnum{
		INCOME,	//
		CONSUME,
		EXCHANGE,
		VERIFY;
		
		public String toString(){
			return super.toString().toLowerCase() ;
		}
	}
	
	public enum PVAStatusEnum{
		OK,
		NOTENOUGH,
		FAILD,
		NOTEXIST,
		INVALID;
		public String toString(){
			return super.toString().toLowerCase() ;
		}
	}

	public static void setGameServerStatus(boolean running){
		gameServerRunning = running ;
	}
	public static boolean getGameServerStatus(){
		return gameServerRunning;
	}
	
}
