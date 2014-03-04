package own.example.common;

public class Const {
	private Const(){
		// ユーティリティクラスなのでインスタンス化禁止
	}
	
	public static enum ScreenType {
		/** Activity */
		SCREEN_TYPE_ACTIVITY_MAIN,
		SCREEN_TYPE_MEMO_ACTIVITY,
		SCREEN_TYPE_POPULAR_ACTIVITY,
		SCREEN_TYPE_ITEM_ACTIVITY,
	}
	
}
