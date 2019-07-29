package com.bosssoft.hr.train;

import com.bosssoft.hr.train.dao.tkmapper.TkMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;
/**
 * @author lujinshan
 * @date
 */
@SpringBootApplication(scanBasePackages = {"com.bosssoft.hr.train"})
@MapperScan(basePackages = "com.bosssoft.hr.train.dao" ,markerInterface = TkMapper.class)
public class BossBesBasedataControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BossBesBasedataControllerApplication.class, args);
    }

}
