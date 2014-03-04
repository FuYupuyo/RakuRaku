package own.example.rakuraku;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class PopularActivity extends ActionBarActivity {
	private Activity mActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = this;
		setView();
	}

	private void setView() {
		setContentView(R.layout.activity_popular);

	}
}
