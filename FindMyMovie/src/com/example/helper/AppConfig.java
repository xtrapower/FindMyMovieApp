package com.example.helper;

public class AppConfig {

	public class Server {
		public static final String URL_GET_DETAIL = "https://api.themoviedb.org/3/movie/";
		public static final String URL_GET_SEARCH = "https://api.themoviedb.org/3/search/movie?query=";
		public static final String URL_GET_STATIC_SEARCH = "https://api.themoviedb.org/3/movie/";
		public static final String URL_GET_KEYWORD_ID = "https://api.themoviedb.org/3/search/keyword?query=";
		public static final String URL_GET_KEYWORD_SEARCH = "https://api.themoviedb.org/3/keyword/";
		public static final String URL_GET_PERSON_ID = "https://api.themoviedb.org/3/search/person?query=";
		public static final String URL_GET_PERSON_SEARCH = "http://api.themoviedb.org/3/person/";
		public static final String URL_GET_GENRE_SEARCH = "http://api.themoviedb.org/3/discover/movie?";
	
		public static final String URL_GET_SEARCH_EXTRA = "&primary_release_year=";
		public static final String URL_GET_GENRE_EXTRA = "&with_genres=";
		public static final String URL_GET_RATING_EXTRA = "&vote_average.gte=";
		public static final String URL_GET_POPULAR_EXTRA = "&vote_count.gte=";

		public static final String API_KEY = "api_key=f4abf758a9edc14dedcad5f120ea63ab";
		public static final String URL_GET_IMAGE = "https://image.tmdb.org/t/p/w150";

	}

	public class Data {
		public static final int DATABASE_VERSION = 1;
		public static final String DATABASE_KEY = "moviesearch";
		public static final String TABLE_KEY = "movies";
		public static final String ID_KEY = "id";
		public static final String TITLE_KEY = "title";
		public static final String RELEASE_KEY = "release_date";
		public static final String RATING_KEY = "rating";
		public static final String POPULAR_KEY = "popular";
		public static final String IMAGE_KEY = "image";
		
	}

}
