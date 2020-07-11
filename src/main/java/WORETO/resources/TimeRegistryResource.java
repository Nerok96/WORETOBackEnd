package WORETO.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TimeRegistryResource.TIME_REGISTRIES)
public class TimeRegistryResource {

    public static final String TIME_REGISTRIES = "/time-registries";

    @Autowired
    public TimeRegistryResource() {

    }

    @GetMapping()
    public String getHelloWorld() {
        return "Hello world";
    }
}
