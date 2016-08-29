package app.yakun.number;

import java.io.Serializable;

public class Rank implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String steps;

	public Rank(String name, String steps) {
		super();
		this.name = name;
		this.steps = steps;
	}

	public String getName() {
		return name;
	}

	public String getSteps() {
		return steps;
	}

	@Override
	public boolean equals(Object o) {
		
		Rank rank  = (Rank)o;
		return name.equals(rank.getName()) && steps.equals(rank.getSteps());
		
	}
	
}
