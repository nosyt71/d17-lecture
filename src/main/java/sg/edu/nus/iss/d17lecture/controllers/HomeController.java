package sg.edu.nus.iss.d17lecture.controllers;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import sg.edu.nus.iss.d17lecture.models.Country;
import sg.edu.nus.iss.d17lecture.services.ProcessService;

@Controller
@RequestMapping(path="/home")
public class HomeController {

    @Autowired
    ProcessService processSvc;

    @GetMapping(path="/booksearch")
    public String bookSearchForm() {

        return "booksearch";

    }

    @GetMapping(path="/countries")
    public ResponseEntity<String> listCountry() {
        ResponseEntity<String> result = processSvc.getCountries();

        return result;
    }

    @GetMapping(path="countries/json")
    public String listCountry2(Model model) {
        ResponseEntity<String> result = processSvc.getCountries();

        String jsonString = result.getBody().toString();

        JsonReader jsonReader = Json.createReader(new StringReader(jsonString)); 
        JsonObject jsonObject = jsonReader.readObject();

        JsonObject jsonObjectData = jsonObject.getJsonObject("data");

        List<Country> countries = new ArrayList<Country>();
        Set<Entry<String, JsonValue>> entries = jsonObjectData.entrySet();
        for(Entry<String, JsonValue> entry: entries) {
            countries.add(new Country(entry.getKey(), entry.getValue().asJsonObject().getString("country")));
        } 

        model.addAttribute("countries", countries);
        return "countrylist";
    }

    @GetMapping(path="/countrysearch")
    public String countrySearchForm() {

        return "countrysearch";
    }
    
}
