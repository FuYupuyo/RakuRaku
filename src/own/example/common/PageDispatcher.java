package own.example.common;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.toolbox.JsonObjectRequest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import own.example.common.Const.ScreenType;
import own.example.model.UrlParameter;
import own.example.rakuraku.ItemActivity;
import own.example.rakuraku.MainActivity;
import own.example.rakuraku.MemoActivity;
import own.example.rakuraku.PopularActivity;

public class PageDispatcher {
	private PageDispatcher() {
		// ユーティリティクラスなのでインスタンス化禁止
	}

	public static void dispatchMainPage(Context context, UrlParameter obj) {
		dispatchPage(context, ScreenType.SCREEN_TYPE_ACTIVITY_MAIN, obj);
	}

	public static void dispatchMemoPage(Context context) {
		dispatchPage(context, ScreenType.SCREEN_TYPE_MEMO_ACTIVITY, null);
	}

	public static void dispatchPopularPage(Context context) {
		dispatchPage(context, ScreenType.SCREEN_TYPE_POPULAR_ACTIVITY, null);
	}

	public static void dispatchItemPage(Context context, JSONObject obj) {
		dispatchPage(context, ScreenType.SCREEN_TYPE_ITEM_ACTIVITY, obj);
	}

	private static void dispatchPage(Context ctx, ScreenType type, Object obj) {
		if (ctx == null || type == null) {
			return;
		}

		Log.d("Dispatch Screen:", type.toString());

		Intent mIntent;
		switch (type) {
		case SCREEN_TYPE_ACTIVITY_MAIN:
			mIntent = new Intent(ctx, MainActivity.class);
			if (obj == null) {
				ctx.startActivity(mIntent);
			}
			mIntent.putExtra("parameter", obj.toString());
			ctx.startActivity(mIntent);
			break;

		case SCREEN_TYPE_MEMO_ACTIVITY:
			mIntent = new Intent(ctx, MemoActivity.class);
			ctx.startActivity(mIntent);
			break;

		case SCREEN_TYPE_POPULAR_ACTIVITY:
			mIntent = new Intent(ctx, PopularActivity.class);
			ctx.startActivity(mIntent);
			break;

		case SCREEN_TYPE_ITEM_ACTIVITY:
			mIntent = new Intent(ctx, ItemActivity.class);
			String url = null;
			String name = null;
			String price = null;
			String caption = null;
			try {
				JSONObject item = (JSONObject) obj;
				url = item.getJSONArray("mediumImageUrls").getJSONObject(0)
						.getString("imageUrl");
				name = item.getString("itemName");
				price = item.getString("itemPrice");
				price = price + "円";
				caption = item.getString("itemCaption");
				url = ((JSONObject) obj).getJSONArray("mediumImageUrls")
						.getJSONObject(0).getString("imageUrl");
				int index = url.indexOf("?");
				url = url.substring(0, index);
			} catch (JSONException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			mIntent.putExtra("url", url);
			mIntent.putExtra("name", name);
			mIntent.putExtra("price", price);
			mIntent.putExtra("caption", caption);
			ctx.startActivity(mIntent);
			break;

		default:
			break;
		}
	}

}
