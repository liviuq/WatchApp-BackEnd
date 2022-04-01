package platform.webapplication.service;

import platform.webapplication.model.DemoPerson;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DemoService {
    private List<DemoPerson> persons = new ArrayList<>(
            Arrays.asList(
                    new DemoPerson(1, "John", "john@email.com", "000"),
                    new DemoPerson(2,"Tom", "tom@email.com", "111"),
                    new DemoPerson(3, "Pit", "Pit@email.com", "222")
            )
        );

    /**
     * business logic
     */
    public List<DemoPerson> getAllPersons() {
        return persons;
    }

    public DemoPerson getPerson(Integer id) {
        return persons.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(persons.get(0));
    }
}
