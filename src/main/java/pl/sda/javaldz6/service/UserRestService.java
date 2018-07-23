package pl.sda.javaldz6.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.sda.javaldz6.model.User;
import sun.rmi.runtime.Log;

@Service
public class UserRestService {

    private static Logger log = Logger.getLogger(UserRestService.class);

    @Autowired
    private RestTemplate restTemplate;

    public User getRestUser() {
        return restTemplate.getForObject("http://localhost:8085/user/1",User.class);

    }
}
