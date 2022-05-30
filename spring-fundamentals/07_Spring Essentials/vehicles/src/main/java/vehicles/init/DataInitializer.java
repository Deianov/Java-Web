package vehicles.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import vehicles.model.Brand;
import vehicles.model.enums.Role;
import vehicles.model.User;
import vehicles.service.BrandService;
import vehicles.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static vehicles.constant.Initialization.*;
import static vehicles.model.enums.Role.ADMIN;
import static vehicles.model.enums.Role.SELLER;

@Component
//@Slf4j
public class DataInitializer implements CommandLineRunner {

    private static final Logger LOGGER = LogManager.getLogger(DataInitializer.class.getName());

    private static final List<User> SAMPLE_USERS = List.of(
            new User("Default", "Admin", "admin", "admin", Set.of(ADMIN)),
            new User("Ivan", "Pertov", "ivan", "ivan", Set.of(SELLER)),
            new User("Dimitar", "Georgiev", "dimitar", "dimitar", Set.of(Role.BUYER))
    );

    private final BrandService brandService;
    private final UserService userService;

    @Autowired
    public DataInitializer(BrandService brandService, UserService userService) {
        this.brandService = brandService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        if(userService.getUsersCount() == 0) {

            SAMPLE_USERS.forEach(userService::createUser);
//            log.info("Created Users: {}", userService.getUsers());
            LOGGER.info("Created Users: {}", userService.getUsers());


            SAMPLE_BRANDS.forEach((brand, models) -> {
                Brand newBrand = Brand.create(brand, models);
                brandService.createBrand(newBrand);
            });
//            log.info("Created Brands: {}", brandService.getBrands());
            LOGGER.info("Created Brands: {}", brandService.getBrands());
        }
    }
}
