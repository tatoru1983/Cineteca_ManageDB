package entity;

import java.io.Serializable;

public class Rating implements Serializable {
	
	private static final long serialVersionUID = 5694174469622776359L;
	
	private String source;
	private String value;
	
	public Rating() {
		super();
	}
	public Rating(String source, String value) {
		super();
		this.source = source;
		this.value = value;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
