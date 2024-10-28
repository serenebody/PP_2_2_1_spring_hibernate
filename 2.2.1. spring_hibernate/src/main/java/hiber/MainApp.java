package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("user1", "user1", "user1@mail.ru");
      User user2 = new User("user2", "user2", "user2@mail.ru");
      User user3 = new User("user3", "user3", "user3@mail.ru");
      User user4 = new User("user4", "user4", "user4@mail.ru");

      Car BMW = new Car("BMW", 2020);
      Car HONDA = new Car("HONDA", 1999);
      Car AUDI = new Car("AUDI", 2024);
      Car LADA = new Car("LADA", 1988);

      user1.setCar(BMW);
      user2.setCar(HONDA);
      user3.setCar(AUDI);
      user4.setCar(LADA);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      userService.findBySeriesAndModel(2020,"BMW");

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Model = "+user.getCar().getModel());
         System.out.println("Series = "+user.getCar().getSeries());
         System.out.println();
      }

      context.close();
   }
}
