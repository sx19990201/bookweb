package sx.book.web;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
@MapperScan("sx.book.web.mapper")       //@MapperScan("sx.book.web")
@EnableScheduling
public class SxWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SxWebApplication.class, args);
    }

}
