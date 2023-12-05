package sg.edu.nus.iss.d17lecture.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.d17lecture.services.ProcessService;

@RestController
@RequestMapping(path="/process")
public class ProcessController {

    @Autowired
    ProcessService processSvc;
    
    @PostMapping(path="/searchbook", produces="application/json")
    public String processBookSearch(@RequestBody MultiValueMap<String, String> form) {

        String author = form.getFirst("searchName");
        System.out.println("Author: " + author);

        String result = processSvc.searchBook(author);

        return result;
    }

    @PostMapping(path="/searchCountry")
    public String processCountrySearch(@RequestBody MultiValueMap<String, String> form) {
        ResponseEntity<String> results = processSvc.filterCountries(form.getFirst("searchName"));
        return results.getBody();
    }
}
