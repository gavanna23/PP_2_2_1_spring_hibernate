package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Anna", "Petrova", "anna@mail.ru"));
      userService.add(new User("Ivan", "Ivanov", "ivan@mail.ru"));
      userService.add(new User("Elena", "Smirnova", "elena@mail.ru"));
      userService.add(new User("Petr", "Petrov", "petr@mail.ru"));

       List<User> users = userService.listUsers();
//       for (User user : users) {
//           System.out.println("Id = " + user.getId());
//           System.out.println("First Name = " + user.getFirstName());
//           System.out.println("Last Name = " + user.getLastName());
//           System.out.println("Email = " + user.getEmail());
//           System.out.println();
//       }

      CarService carService =context.getBean(CarService.class);
      carService.add(new Car("mazda", 44444444, users.get(0)));
      carService.add(new Car("lada", 12135665, users.get(1)));
      carService.add(new Car("lada", 542312565, users.get(2)));
      carService.add(new Car("nissan", 4589545, users.get(3)));

           List<User> users1 = userService.listUsers();
           for (User user : users1) {
               System.out.println("Id = "+user.getId());
               System.out.println("First Name = "+user.getFirstName());
               System.out.println("Last Name = "+user.getLastName());
               System.out.println("Email = "+user.getEmail());
               System.out.println("Car =  "+user.getCar());
               System.out.println();
           }
       System.out.println(userService.findUserAndCar("mazda", 44444444));
       context.close();

      }

   }


