package dbentity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FilmRatingId implements Serializable {

	private static final long serialVersionUID = -8509946146957907528L;
	
	private String imdbID;
	private String source;
	
	public FilmRatingId() {
		super();
	}
	
	public FilmRatingId(String imdbID, String source) {
		super();
		this.imdbID = imdbID;
		this.source = source;
	}

	@Column(name = "IMDBID", unique = true, nullable = false)
	public String getImdbID() {
		return imdbID;
	}
	
	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	@Column(name = "SOURCE", unique = true, nullable = false)
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imdbID == null) ? 0 : imdbID.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmRatingId other = (FilmRatingId) obj;
		if (imdbID == null) {
			if (other.imdbID != null)
				return false;
		} else if (!imdbID.equals(other.imdbID))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}

}
