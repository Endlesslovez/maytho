package vn.isofh.may.tho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"vn.isofh"})
@EnableScheduling
public class MayThoApplication {

  public static void main(String[] args) {
    SpringApplication.run(MayThoApplication.class, args);
  }

}
