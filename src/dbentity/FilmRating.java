package dbentity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import entity.Rating;

@Entity
@Table(name="FILM_RATING", schema = "cineteca")
public class FilmRating implements Serializable {

	private static final long serialVersionUID = -4595314657033916254L;
	
	private FilmRatingId id;
	private String value;
	
	public FilmRating(FilmRatingId id, String value) {
		super();
		this.id = id;
		this.value = value;
	}
	
	public FilmRating() {
		super();
	}
	
	@EmbeddedId
	public FilmRatingId getId() {
		return id;
	}

	public void setId(FilmRatingId id) {
		this.id = id;
	}

	@Column(name = "VALUE", unique = true, nullable = false)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		FilmRating other = (FilmRating) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	//Transform
	public FilmRating(String imdbID, Rating rating) {
		super();
		this.id = new FilmRatingId();
		this.id.setImdbID(imdbID);
		this.id.setSource(rating.getSource());
		this.value = rating.getValue();
	}

	@Override
	public String toString() {
		return "FilmRating [id=" + id + ", value=" + value + "]";
	}
}
