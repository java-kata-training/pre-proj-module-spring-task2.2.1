package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
       CarService carService = context.getBean(CarService.class);


//       Car car1 = new Car(1L, "Lamborghini", 570);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru",
//              new Car(1L, "Lamborghini", 570)));
//
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru",
//              new Car(2L, "Bugatti", 110)));
//
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru",
//              new Car(3L, "Mercedes-Maybach", 600)));
//
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru",
//              new Car(4L, "Ferrari", 90)));
//
//      userService.add(new User("User5", "Lastname4", "user4@mail.ru",
//              new Car(5L, "Aurus", 5)));

       userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Lamborghini", 570)));
       userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Bugatti", 110)));
       userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Mercedes-Maybach", 600)));
       userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Aurus", 5)));

      List<User> users = userService.listUsers();
      for (User user : users) {
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
