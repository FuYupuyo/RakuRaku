package own.example.model;

public class UrlParameter {
	private String age;
	private String sex;
	private String mNull = "";

	public UrlParameter(String age, String sex) {
		super();
		this.age = age;
		this.sex = sex;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		if (age.equals(mNull)) {
			return "";
		}
		return "&age=" + age;
	}

	public String getSex() {
		if (sex.equals(mNull)) {
			return "";
		}
		return "&sex=" + sex;
	}

	@Override
	public String toString() {
		return getAge() + getSex();
	}
}
