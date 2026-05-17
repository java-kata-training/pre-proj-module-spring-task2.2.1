package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
       CarService carService = context.getBean(CarService.class);

       // create cars list
       List<Car> cars = new ArrayList<>();
       cars.add(new Car("Lamborghini", 570));
       cars.add(new Car("Bugatti", 110));
       cars.add(new Car("Mercedes-Maybach", 600));
       cars.add(new Car("Aurus", 5));

       // insert cars to db
       for (Car car : cars) {
           carService.add(car);
       }

       // create user list
       List<User> users = new ArrayList<>();
       users.add(new User("User1", "Lastname1", "user1@mail.ru"));
       users.add(new User("User2", "Lastname2", "user2@mail.ru"));
       users.add(new User("User3", "Lastname3", "user3@mail.ru"));
       users.add(new User("User4", "Lastname4", "user4@mail.ru"));

       // insert users ot db
       for (User user : users) {
           userService.add(user);
       }

       List<User> usersFromDb = userService.listUsers();
       List<Car> carsFromDg = carService.listCars();

       // assign a car to user
       for (int i = 0; i < usersFromDb.size() && i < carsFromDg.size(); i++) {

           User user = usersFromDb.get(i);
           Car car = carsFromDg.get(i);

           user.setCar(car);
           userService.update(user);
       }

       // list user to console
      for (User user : usersFromDb) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
          System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      context.close();
   }
}
