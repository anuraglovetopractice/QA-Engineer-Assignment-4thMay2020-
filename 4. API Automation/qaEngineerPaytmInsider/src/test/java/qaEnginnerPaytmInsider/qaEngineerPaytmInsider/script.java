package qaEnginnerPaytmInsider.qaEngineerPaytmInsider;

import org.testng.annotations.Test;
import httpRequests.GetRequest;
import junit.framework.Assert;
import models.UpcomingMovies;
import utilities.Utilities;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;

public class script extends TestBase {

	@Test
	public void validatingMovieData() {

		// Reading the upcomingMovies.properties file
		Properties prop = Utilities.readPropertiesFile("upcomingMovies");

		// Getting Today's Date
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dateToday = new Date();
		String currentDate = df.format(dateToday);
		try {
			dateToday = df.parse(currentDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// hitting the get request and storing the response in a variable
		GetRequest gr = new GetRequest();
		gr.response = gr.httpGetRequest(prop.getProperty("upcomingMovies"));

		// Asserting the status code
		int actualStatusCode = gr.response.getStatusCode();
		int expectedStatusCode = Integer.valueOf(prop.getProperty("twoHundred"));
		Assert.assertEquals(
				"Status code is not matched, expected was " + expectedStatusCode + " but found " + actualStatusCode,
				actualStatusCode, expectedStatusCode);

		// Storing the movies data into lists
		ArrayList<Date> moviesReleaseDate = new ArrayList<Date>();
		ArrayList<String> moviesPosterURL = new ArrayList<String>();
		ArrayList<String> paytmMovieCode = new ArrayList<String>();
		ArrayList<String> movieLanguage = new ArrayList<String>();

		UpcomingMovies upcomingMovies = (UpcomingMovies) Utilities.jsonToObject(gr.response.asString(), UpcomingMovies.class);
		int movieDataSize = upcomingMovies.getUpcomingMovieData().size();
		for (int movieData = 0; movieData < movieDataSize; movieData++) {
			moviesReleaseDate.add(upcomingMovies.getUpcomingMovieData().get(movieData).getReleaseDate());
			moviesPosterURL.add(upcomingMovies.getUpcomingMovieData().get(movieData).getMoviePosterUrl());
			paytmMovieCode.add(upcomingMovies.getUpcomingMovieData().get(movieData).getPaytmMovieCode());
			movieLanguage.add(upcomingMovies.getUpcomingMovieData().get(movieData).getLanguage());
		}

		// Asserting paytm movie code is unique.
		Set<String> uniquePaytmMovieCode = new HashSet<String>();
		uniquePaytmMovieCode.addAll(paytmMovieCode);
		Assert.assertTrue("paytm movie code is not unique",
				CollectionUtils.isEqualCollection(paytmMovieCode, uniquePaytmMovieCode));

		for (int date = 0; date < moviesReleaseDate.size(); date++) {
			// Made this conditions because it might be possible that upcoming movies may
			// not have release date yet
			if (moviesReleaseDate.get(date) != null) {
				// Asserting movies release date should not be before today’s date
				Assert.assertTrue(dateToday.before(moviesReleaseDate.get(date)));
			}

			// Asserting movie Poster URL​ have only ​.jpg​ format
			Assert.assertTrue("Movie poster URL is not in jpg format", moviesPosterURL.get(date).endsWith(".jpg"));

			// Asserting No movie code should have more than 1 language format
			String language = movieLanguage.get(date);
			boolean oneLanguage = true;
			if (language.equals("Hindi")) {
				oneLanguage = true;
			} else if (language.equals("Kannada")) {
				oneLanguage = true;
			} else if (language.equals("English")) {
				oneLanguage = true;
			} else {
				oneLanguage = false;
			}
			Assert.assertTrue("Movie code is in more than 1 language format", oneLanguage);

		}
	}

	@Test
	public void isMovieContentAvailable() {

		// Reading the upcomingMovies.properties file
		Properties prop = Utilities.readPropertiesFile("upcomingMovies");

		// hitting the get request and storing the response in a variable
		GetRequest gr = new GetRequest();
		gr.response = gr.httpGetRequest(prop.getProperty("upcomingMovies"));

		// Asserting the status code
		int actualStatusCode = gr.response.getStatusCode();
		int expectedStatusCode = Integer.valueOf(prop.getProperty("twoHundred"));
		Assert.assertEquals(
				"Status code is not matched, expected was " + expectedStatusCode + " but found " + actualStatusCode,
				actualStatusCode, expectedStatusCode);

		ArrayList<String> isContentAvailable = new ArrayList<String>();
		ArrayList<String> movie_name = new ArrayList<String>();

		UpcomingMovies upcomingMovies = (UpcomingMovies) Utilities.jsonToObject(gr.response.asString(),
				UpcomingMovies.class);
		int movieDataSize = upcomingMovies.getUpcomingMovieData().size();
		for (int movieData = 0; movieData < movieDataSize; movieData++) {
			if (upcomingMovies.getUpcomingMovieData().get(movieData).getIsContentAvailable() == 0) {
				movie_name.add(upcomingMovies.getUpcomingMovieData().get(movieData).getMovie_name());
			}
		}
		
		try {
			Utilities.writingIntoExcel(movie_name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}