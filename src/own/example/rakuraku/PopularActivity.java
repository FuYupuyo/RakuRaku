package own.example.rakuraku;

import own.example.common.PageDispatcher;
import own.example.model.UrlParameter;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PopularActivity extends ActionBarActivity implements
		OnClickListener, OnItemSelectedListener {
	private Activity mActivity;
	private Spinner mSexSpinner;
	private String[] SexState;
	private Spinner mAgeSpinner;
	private String[] AgeState;
	private TextView mSearchButton;
	private UrlParameter mParam;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = this;
		mParam = new UrlParameter("", "");
		setView();
	}

	private void setView() {
		setContentView(R.layout.activity_popular);
		mSexSpinner = (Spinner) findViewById(R.id.spin_sex);
		SexState = getResources().getStringArray(R.array.spinSex);
		mSexSpinner.setOnItemSelectedListener(this);

		mAgeSpinner = (Spinner) findViewById(R.id.spin_age);
		AgeState = getResources().getStringArray(R.array.spinAge);
		mAgeSpinner.setOnItemSelectedListener(this);

		mSearchButton = (TextView) findViewById(R.id.search_button);
		mSearchButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		PageDispatcher.dispatchMainPage(mActivity, mParam);
	}

	@Override
	public void onItemSelected(AdapterView<?> adapter, View v, int arg2,
			long arg3) {
		int i = adapter.getSelectedItemPosition();
		if (adapter.getId() == mAgeSpinner.getId()) {
			if (i != 0) {
				mParam.setAge(i * 10 + "");
			}
			// Toast.makeText(this, AgeState[i], Toast.LENGTH_SHORT).show();
		} else if (adapter.getId() == mSexSpinner.getId()) {
			switch (i) {
			case 1:
				mParam.setSex("0");
				break;
			case 2:
				mParam.setSex("1");
				break;
			default:
				break;
			}
			// Toast.makeText(this, SexState[i], Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}
}
