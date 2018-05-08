package dbentity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import entity.Movie;
import entity.Rating;


@Entity
@Table(name = "FILM", schema = "cineteca")
public class Film implements Serializable {
	
	private static final long serialVersionUID = -7091875416407053157L;

	private String imdbID;
	private String title;
	private String year;
	private String rated;
	private String released;
	private String runtime;
	private String genre;
	private String director;
	private String writer;
	private String actors;
	private String plot;
	private String country;
	private String awards;
	private String poster;
	private String titleIta;
	private boolean seen;
	private Integer dvd;

	private List<FilmRating> filmRating = new ArrayList<FilmRating>();

	public Film() {
		super();
	}

	@Id
	@Column(name = "IMDBID", unique = true, nullable = false)
	public String getImdbID() {
		return imdbID;
	}
	
	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	@Column(name = "TITLE", nullable = false)
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "YEAR", nullable = true)
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "RATED", nullable = true)
	public String getRated() {
		return rated;
	}
	
	public void setRated(String rated) {
		this.rated = rated;
	}

	@Column(name = "RELEASED", nullable = true)
	public String getReleased() {
		return released;
	}
	
	public void setReleased(String released) {
		this.released = released;
	}

	@Column(name = "RUNTIME", nullable = true)
	public String getRuntime() {
		return runtime;
	}
	
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	@Column(name = "GENRE", nullable = true)
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Column(name = "DIRECTOR", nullable = true)
	public String getDirector() {
		return director;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}

	@Column(name = "WRITER", nullable = true)
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Column(name = "ACTORS", nullable = true)
	public String getActors() {
		return actors;
	}
	
	public void setActors(String actors) {
		this.actors = actors;
	}

	@Column(name = "PLOT", nullable = true)
	public String getPlot() {
		return plot;
	}
	
	public void setPlot(String plot) {
		this.plot = plot;
	}

	@Column(name = "COUNTRY", nullable = true)
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "AWARDS", nullable = true)
	public String getAwards() {
		return awards;
	}
	
	public void setAwards(String awards) {
		this.awards = awards;
	}

	@Column(name = "POSTER", nullable = true)
	public String getPoster() {
		return poster;
	}
	
	public void setPoster(String poster) {
		this.poster = poster;
	}

	@Column(name = "TITLE_ITA", nullable = false)
	public String getTitleIta() {
		return titleIta;
	}

	public void setTitleIta(String titleIta) {
		this.titleIta = titleIta;
	}

	@Transient
	public List<FilmRating> getFilmRating() {
		return filmRating;
	}

	public void setFilmRating(List<FilmRating> filmRating) {
		this.filmRating = filmRating;
	}
	
	public void addFilmRating(FilmRating filmRating) {
		if(this.filmRating==null) {
			this.filmRating = new ArrayList<FilmRating>();
		}
		this.filmRating.add(filmRating);
	}
	
	@Column(name = "DVD", nullable = false)
	public Integer getDvd() {
		return dvd;
	}

	public void setDvd(Integer dvd) {
		this.dvd = dvd;
	}
	
	@Column(name = "SEEN", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imdbID == null) ? 0 : imdbID.hashCode());
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
		Film other = (Film) obj;
		if (imdbID == null) {
			if (other.imdbID != null)
				return false;
		} else if (!imdbID.equals(other.imdbID))
			return false;
		return true;
	}

	//Transform
	public Film(Integer dvd, Movie movie){
		super();
		//Film info
		this.imdbID = movie.getImdbID();
		this.title = movie.getTitle();
		this.year = movie.getYear();
		this.rated = movie.getRated();
		this.released = movie.getReleased();
		this.runtime = movie.getRuntime();
		this.genre = movie.getGenre();
		this.director = movie.getDirector();
		this.writer = movie.getWriter();
		this.actors = movie.getActors();
		this.plot = movie.getPlot();
		this.country = movie.getCountry();
		this.awards = movie.getAwards();
		this.poster = movie.getPoster();
		this.titleIta = movie.getTitleIta();
		this.dvd = dvd;
		
		//Rating info
		if(movie.getRatings()!=null) {
			for(Rating rating : movie.getRatings()) {
				FilmRating filmRating = new FilmRating(this.imdbID, rating);
				this.addFilmRating(filmRating);
			}
		}
	}
}