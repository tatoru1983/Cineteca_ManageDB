package dbentity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import entity.Movie;
import entity.Rating;


@Entity
@Table(name = "FILM")
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
	
	private List<FilmRating> filmRating;

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

	@Column(name = "TITLE", unique = true, nullable = false)
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "YEAR", unique = true, nullable = true)
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "RATED", unique = true, nullable = true)
	public String getRated() {
		return rated;
	}
	
	public void setRated(String rated) {
		this.rated = rated;
	}

	@Column(name = "RELEASED", unique = true, nullable = true)
	public String getReleased() {
		return released;
	}
	
	public void setReleased(String released) {
		this.released = released;
	}

	@Column(name = "RUNTIME", unique = true, nullable = true)
	public String getRuntime() {
		return runtime;
	}
	
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	@Column(name = "GENRE", unique = true, nullable = true)
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Column(name = "DIRECTOR", unique = true, nullable = true)
	public String getDirector() {
		return director;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}

	@Column(name = "WRITER", unique = true, nullable = true)
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Column(name = "ACTORS", unique = true, nullable = true)
	public String getActors() {
		return actors;
	}
	
	public void setActors(String actors) {
		this.actors = actors;
	}

	@Column(name = "PLOT", unique = true, nullable = true)
	public String getPlot() {
		return plot;
	}
	
	public void setPlot(String plot) {
		this.plot = plot;
	}

	@Column(name = "COUNTRY", unique = true, nullable = true)
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "AWARDS", unique = true, nullable = true)
	public String getAwards() {
		return awards;
	}
	
	public void setAwards(String awards) {
		this.awards = awards;
	}

	@Column(name = "POSTER", unique = true, nullable = true)
	public String getPoster() {
		return poster;
	}
	
	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getTitleIta() {
		return titleIta;
	}

	@Column(name = "TITLE_ITA", unique = true, nullable = false)
	public void setTitleIta(String titleIta) {
		this.titleIta = titleIta;
	}

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
	public Film(Movie movie){
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
		
		//Rating info
		for(Rating rating : movie.getRatings()) {
			FilmRating filmRating = new FilmRating(this.imdbID, rating);
			this.addFilmRating(filmRating);
		}
	}
}