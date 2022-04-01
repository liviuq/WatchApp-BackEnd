package platform.webapplication.controller;

import platform.webapplication.model.DemoPerson;
import platform.webapplication.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DemoRestController {

    // inject service -> controller
    @Autowired
    private DemoService personService;

    @GetMapping("/personPage")
    public List<DemoPerson> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/personPage/{id}")
    public DemoPerson getPerson(@PathVariable int id) {

        return personService.getPerson(id);
    }
}
