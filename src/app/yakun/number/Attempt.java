package app.yakun.number;

public class Attempt {

	private String id;
	private String number;
	private String hint;
	
	public Attempt(String id, String number, String hint) {
		super();
		this.id = id;
		this.number = number;
		this.hint = hint;
	}

	public String getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public String getHint() {
		return hint;
	}
	
}
