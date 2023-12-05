package sg.edu.nus.iss.d17lecture.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProcessService {

    String url_booksearch= "https://openlibrary.org/search.json?q=";
    String url_countries = "https://api.first.org/data/v1/countries";
    RestTemplate template = new RestTemplate();

    public String searchBook(String author) {

        url_booksearch +=author;
        String result = template.getForObject(url_booksearch, String.class);

        return result;
    }

    public ResponseEntity<String> getCountries() {
        ResponseEntity<String> result = template.getForEntity(url_countries, String.class);

        return result;
    }

    public ResponseEntity<String> filterCountries(String name) {
        String urlCall = url_countries;
        urlCall += "?q=" + name;
        ResponseEntity<String> result = template.getForEntity(urlCall, String.class);

        return result;
    }
}
