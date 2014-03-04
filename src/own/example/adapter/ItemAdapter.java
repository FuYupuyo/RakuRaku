package own.example.adapter;

import java.util.List;

import own.example.model.RankingData;
import own.example.rakuraku.R;
import own.example.rakuraku.R.id;
import own.example.rakuraku.R.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<RankingData> {
	private LayoutInflater mInflater;

	public ItemAdapter(Context context, int textViewResourceId,
			List<RankingData> objects) {
		super(context, textViewResourceId, objects);
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.ranking_list_item, parent,
					false);
		}

		RankingData item = (RankingData) getItem(position);

		ImageView smallImageView;
		smallImageView = (ImageView) convertView.findViewById(R.id.small_image);
		smallImageView.setImageBitmap(item.getSmallImage());

		TextView itemRankTextView = (TextView) convertView
				.findViewById(R.id.item_rank_text);
		itemRankTextView.setText(String.format("%1$02d", position + 1) + "位");

		TextView itemNameTextView = (TextView) convertView
				.findViewById(R.id.item_name_text);
		itemNameTextView.setText(item.getItemName());

		return convertView;
	}
}
