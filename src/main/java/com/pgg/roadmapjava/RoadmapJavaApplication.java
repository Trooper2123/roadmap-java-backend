package com.pgg.roadmapjava;

import com.pgg.roadmapjava.crud.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RoadmapJavaApplication
implements ApplicationRunner{

    @Autowired
   private Menu menu;

    public static void main(String[] args) {

        SpringApplication.run(RoadmapJavaApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
       menu.initCrud();
    }
}
