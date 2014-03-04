package own.example.model;

import org.json.JSONObject;

import android.graphics.Bitmap;

public class RankingData {
	private Bitmap smallImage;
	private String itemName;
	private JSONObject item;

	public RankingData(Bitmap smallImage, String itemName, JSONObject item) {
		super();
		this.smallImage = smallImage;
		this.itemName = itemName;
		this.item = item;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setSmallImage(Bitmap smallImage) {
		this.smallImage = smallImage;
	}

	public String getItemName() {
		return itemName;
	}

	public Bitmap getSmallImage() {
		return smallImage;
	}

	public JSONObject getItem() {
		return item;
	}

	public void setItem(JSONObject item) {
		this.item = item;
	}
}
