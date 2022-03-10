package com.epam.esm.web;

import com.epam.esm.database.dao.GiftDao;
import com.epam.esm.database.dao.OrderDao;
import com.epam.esm.database.dao.TagDao;
import com.epam.esm.database.dao.UserDao;
import com.epam.esm.util.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ComponentScan(basePackages = {"com"})
@SpringBootApplication
@EntityScan(basePackages = {"com"})
public class GiftApplication extends SpringBootServletInitializer {
    @Value("${spring.messages.basename}")
    private String baseName;

    @Bean(name = "texts")
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
        rs.setBasename(baseName);
        return rs;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public static void main(String[] args) {
        SpringApplication.run(GiftApplication.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(GiftApplication.class);
    }

    @Bean
    public CommandLineRunner run(UserDao userDao,
                                 GiftDao giftDao,
                                 TagDao tagDao,
                                 OrderDao orderDao,
                                 UserService service,
                                 PasswordEncoder passwordEncoder) {
        return args -> {
//            new Thread(() -> {
//                Gift gift;
//                for (int i = 0; i < 9995; i++) {
//                    gift = new Gift();
//                    gift.setName("gift name" + i);
//                    gift.setDescription("description" + i);
//                    double price = 100 + Math.random() * 1000;
//                    gift.setPrice(BigDecimal.valueOf(price));
//                    gift.setStartDate(LocalDate.of(2022, 10, 10));
//                    gift.setLastUpdateDate(LocalDate.of(2022, 11, 10));
//                    gift.setDuration(30);
//                    giftDao.addNewGift(gift);
//                }
//            }).start();
//
//            new Thread(() -> {
//                CustomTag tag;
//                for (int i = 0; i < 995; i++) {
//                    tag = new CustomTag();
//                    tag.setName("tag name " + i);
//                    tagDao.addNewTag(tag);
//                }
//            }).start();
//
//            new Thread(() -> {
//                User user;
//                for (int i = 0; i < 998; i++) {
//                    user = new User();
//                    user.setRole(new Role(2, "ROLE_USER"));
//                    user.setUserName("Username " + i);
//                    user.setPassword(passwordEncoder.encode("123") + i);
//                    user.setEmail("user" + i + "@gmail.com");
//                    user.setActive(true);
//                    userDao.addNewUser(user);
//                }
//            }).start();

//            new Thread(() -> {
//                Gift gift;
//                CustomTag tag;
//                User user;
//               Order order;
//                for (int i = 0; i < 100; i++) {
//                    order = new Order();
//                    gift = new Gift();
//                    tag = new CustomTag();
//                    order.setDate(LocalDate.now());
//                    order.setPrice(BigDecimal.valueOf(100 + Math.random() * 100));
//                    gift.setId((int) (Math.random() * 100));
//                    order.setGift(gift);
//                    tag.setId((int) (Math.random() * 100));
//                    order.setTag(tag);
//                    user = new User();
//                    user.setId((int) (Math.random() * 100));
//                    order.setUser(user);
//                    orderDao.createNewOrder(order);
//                }
//            }).start();
        };





    }
}
