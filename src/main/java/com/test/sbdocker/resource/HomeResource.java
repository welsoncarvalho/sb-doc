package com.test.sbdocker.resource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.test.sbdocker.model.Monster;
import com.test.sbdocker.repository.MonsterRepository;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/api/home")
public class HomeResource {

    @Autowired
    private MonsterRepository monsterRepository;

    @GetMapping
    public String getMessage() {
        return "Hello Docker!";
    }

    @GetMapping(value = "/{name}")
    public void getMessage(@PathVariable("name") String name, HttpServletResponse response) throws IOException {

        Optional<Monster> optional = monsterRepository.findById(name);

        byte[] monsterImage = null;

        if (optional.isPresent()) {
            System.out.println("Find on redis: " + name);
            monsterImage = optional.get().getImage();
        } else {
            System.out.println("Make the request: " + name);
            String url = "http://dnmonster:8080/monster/" + name + "?size=80";

            RestTemplate restTemplate = new RestTemplate();
            byte[] result = restTemplate.getForObject(url, byte[].class);

            Monster monster = new Monster();
            monster.setId(name);
            monster.setImage(result);

            monsterRepository.save(monster);
            monsterImage = monster.getImage();
        }

        InputStream in = new ByteArrayInputStream(monsterImage);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);

        IOUtils.copy(in, response.getOutputStream());
    }

}
