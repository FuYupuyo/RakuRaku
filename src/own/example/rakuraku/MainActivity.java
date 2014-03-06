package own.example.rakuraku;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import own.example.adapter.ItemAdapter;
import own.example.common.PageDispatcher;
import own.example.model.RankingData;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ActionBarActivity implements
		OnMenuItemClickListener, OnClickListener {
	private Activity mActivity;
	private ListView mListView;
	private TextView mSettingButton;
	private TextView mMemoButton;
	private RequestQueue mQueue;
	private JSONArray mItemsArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = this;
		setView();
	}

	private void setView() {
		setContentView(R.layout.activity_main);

		mListView = (ListView) findViewById(R.id.ListView);
		mSettingButton = (TextView) findViewById(R.id.favorite_button);
		mSettingButton.setOnClickListener(this);
		mMemoButton = (TextView) findViewById(R.id.memo_button);
		mMemoButton.setOnClickListener(this);

		String url = "https://app.rakuten.co.jp/services/api/IchibaItem/Ranking/20120927?"
				+ "format=json&applicationId=1093443291235859235";
		Intent intent = getIntent();
		if (intent.getStringExtra("parameter") != null) {
			url = url + intent.getStringExtra("parameter");
		}

		mQueue = Volley.newRequestQueue(mActivity);

		JsonObjectRequest request = new JsonObjectRequest(Method.GET, url,
				null, new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						// JSONObjectのパース、List、Viewへの追加等
						try {
							mItemsArray = response.getJSONArray("Items");
							ArrayList<RankingData> rankingList = new ArrayList<RankingData>();
							Bitmap defaultBitmap = BitmapFactory
									.decodeResource(getResources(),
											R.drawable.ic_launcher);
							Toast.makeText(mActivity, response.getString("title"), Toast.LENGTH_SHORT).show();
							for (Integer i = 0; i < mItemsArray.length(); i++) {
								JSONObject Item;
								Item = mItemsArray.getJSONObject(i)
										.getJSONObject("Item");
								String url = Item
										.getJSONArray("smallImageUrls")
										.getJSONObject(0).getString("imageUrl");
								RankingData data = new RankingData(
										defaultBitmap, Item
												.getString("itemName"), Item);
								rankingList.add(data);
							}
							ItemAdapter adapter = new ItemAdapter(mActivity, 0,
									rankingList);
							mListView.setAdapter(adapter);
							mListView
									.setOnItemClickListener(new OnItemClickListener() {
										@Override
										public void onItemClick(
												AdapterView<?> parent,
												View view, int position, long id) {
											ListView listView = (ListView) parent;
											RankingData item = (RankingData) listView
													.getItemAtPosition(position);
											Toast.makeText(mActivity,
													item.getItemName(),
													Toast.LENGTH_SHORT).show();
											PageDispatcher.dispatchItemPage(
													mActivity, item.getItem());
										}
									});
						} catch (JSONException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Toast.makeText(mActivity, "通信エラーです", Toast.LENGTH_SHORT).show();
					}
				});
		mQueue.add(request);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		/** ボタン */
		MenuItem favoriteActionItem = menu.findItem(R.id.action_settings);
		favoriteActionItem.setOnMenuItemClickListener(this);
		MenuItem settingsActionItem = menu.findItem(R.id.action_memo);
		settingsActionItem.setOnMenuItemClickListener(this);
		return true;
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		if (item == null)
			return false;

		switch (item.getItemId()) {
		case R.id.action_settings:
			PageDispatcher.dispatchPopularPage(this);

			break;

		case R.id.action_memo:
			PageDispatcher.dispatchMemoPage(this);
			break;

		default:
			break;
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.favorite_button:
			PageDispatcher.dispatchPopularPage(this);
			break;

		case R.id.memo_button:
			PageDispatcher.dispatchMemoPage(this);
			break;

		default:
			break;
		}
	}

}
