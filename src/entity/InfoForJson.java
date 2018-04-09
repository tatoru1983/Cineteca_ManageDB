package entity;

import java.io.Serializable;

public class InfoForJson implements Serializable{
	private static final long serialVersionUID = -5275250084602198547L;
	
	private String idDvd;
	private String TitleIta;
	private String idImdb;
	
	public InfoForJson() {
		super();
	}
	public InfoForJson(String idDvd, String titleIta, String idImdb) {
		super();
		this.idDvd = idDvd;
		TitleIta = titleIta;
		this.idImdb = idImdb;
	}
	public String getIdDvd() {
		return idDvd;
	}
	public void setIdDvd(String idDvd) {
		this.idDvd = idDvd;
	}
	public String getTitleIta() {
		return TitleIta;
	}
	public void setTitleIta(String titleIta) {
		TitleIta = titleIta;
	}
	public String getIdImdb() {
		return idImdb;
	}
	public void setIdImdb(String idImdb) {
		this.idImdb = idImdb;
	}

}
