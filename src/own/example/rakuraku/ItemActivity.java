package own.example.rakuraku;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageCache;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.TextView;

public class ItemActivity extends ActionBarActivity {
	// Volleyでリクエスト時に設定するタグ名。キャンセル時に利用する。
	private static final Object TAG_REQUEST_QUEUE = new Object();
	// RequestQueueのインスタンス用
	private RequestQueue mRequestQueue;
	// ImageLoader
	private ImageLoader mImageLoader;
	// ImageLoaderのキャッシュ
	private ImageCache mCache;
	private TextView mName;
	private TextView mPrice;
	private TextView mCaption;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);

		// キャッシュの情報を取得する
		mCache = getCacheNone();
		// RequestQueueのインスタンス用を取得
		mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		mImageLoader = new ImageLoader(mRequestQueue, mCache);

		// NetworkImageViewを取得する
		NetworkImageView image = (NetworkImageView) findViewById(R.id.item_image);
		// 画像の読み込み
		String url = null;
		String name = null;
		String price = null;
		String caption = null;

		Intent intent = getIntent();
		if (intent != null) {
			url = intent.getStringExtra("url");
			name = intent.getStringExtra("name");
			price = intent.getStringExtra("price");
			caption = intent.getStringExtra("caption");
		}
		image.setImageUrl(url, mImageLoader);

		mName = (TextView) findViewById(R.id.item_name);
		mName.setText(name);
		mPrice = (TextView) findViewById(R.id.item_price);
		mPrice.setText(price);
		mCaption = (TextView) findViewById(R.id.item_caption);
		mCaption.setText(caption);
	}

	/*
	 * onCreateの後に呼び出される。
	 */
	@Override
	public void onStart() {
		super.onStart();
	}

	/*
	 * アプリが見えなくなったときに呼び出される。 （他のアプリを立ち上げたりした時など）
	 */
	@Override
	public void onStop() {
		super.onStop();
		mRequestQueue.cancelAll(TAG_REQUEST_QUEUE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/*
	 * キャッシュを利用しないためnullにする
	 */
	private ImageCache getCacheNone() {

		return new ImageCache() {
			@Override
			public Bitmap getBitmap(String url) {
				return null;
			}

			@Override
			public void putBitmap(String url, Bitmap bitmap) {
			}
		};

	}

}
