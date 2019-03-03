package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class InfoForJson implements Serializable{
	private static final long serialVersionUID = -5275250084602198547L;
	
	private String idDvd;
	private String titleIta;
	private String idImdb;
	
	public InfoForJson() {
		super();
	}
	public InfoForJson(String idDvd, String titleIta, String idImdb) {
		super();
		this.idDvd = idDvd;
		this.titleIta = titleIta;
		this.idImdb = idImdb;
	}
	public String getIdDvd() {
		return idDvd;
	}
	public void setIdDvd(String idDvd) {
		this.idDvd = idDvd;
	}
	public String getTitleIta() {
		return titleIta;
	}
	public void setTitleIta(String titleIta) {
		this.titleIta = titleIta;
	}
	public String getIdImdb() {
		return idImdb;
	}
	public void setIdImdb(String idImdb) {
		this.idImdb = idImdb;
	}

	public InfoForJson(List row){
		super();
		this.idDvd = row.get(0).toString();
		if(row.size()>1)
			this.titleIta = row.get(1).toString();
		if(row.size()>2)
			this.idImdb = row.get(2).toString();
	}
	
	private BigDecimal getBGValue(Object obj) {
		if(obj==null)
			return new BigDecimal(0);
		try {
			String valueString = obj.toString().replaceAll(",", ".");
			BigDecimal bdValue = new BigDecimal(valueString);
			return bdValue;
		}catch(Exception ex) {
			return new BigDecimal(0);
		}
	}
}
