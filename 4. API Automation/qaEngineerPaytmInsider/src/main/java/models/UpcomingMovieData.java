package models;

import java.util.ArrayList;
import java.util.Date;

public class UpcomingMovieData {

	private String provider_moviename;
	private String moviePosterUrl;
	private String movieTitle;
	private String movie_name;
	private String language;
	ArrayList<Object> genre = new ArrayList<Object>();
	private Date releaseDate;
	private float rank;
	private String paytmMovieCode;
	private int isContentAvailable;

	// Getter Methods 

	public String getProvider_moviename() {
		return provider_moviename;
	}

	public String getMoviePosterUrl() {
		return moviePosterUrl;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public String getMovie_name() {
		return movie_name;
	}

	public String getLanguage() {
		return language;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public float getRank() {
		return rank;
	}

	public String getPaytmMovieCode() {
		return paytmMovieCode;
	}

	public int getIsContentAvailable() {
		return isContentAvailable;
	}

	// Setter Methods 

	public void setProvider_moviename(String provider_moviename) {
		this.provider_moviename = provider_moviename;
	}

	public void setMoviePosterUrl(String moviePosterUrl) {
		this.moviePosterUrl = moviePosterUrl;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setRank(float rank) {
		this.rank = rank;
	}

	public void setPaytmMovieCode(String paytmMovieCode) {
		this.paytmMovieCode = paytmMovieCode;
	}

	public void setIsContentAvailable(int isContentAvailable) {
		this.isContentAvailable = isContentAvailable;
	}
}