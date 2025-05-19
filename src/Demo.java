package src;

import java.time.LocalDate;
import java.util.Date;

public class Demo {
    public static void main(String[] args) {
          LinkedInSystem system = LinkedInSystem.getInstance();

          // create users
          User user1 = system.addUser("John");
          User user2 = system.addUser("David");
          User user3 = system.addUser("Joe");
          User user4 = system.addUser("Joy");
          User user5 = system.addUser("Richard");
          User user6 = system.addUser("Charlie");
          User user = new User("12212", "dummy");

          // add skills
          user1.addSkill("React");
          user1.addSkill("Java");
          user2.addSkill("C++");
          user3.addSkill("SQL");
          user.addSkill("dummy");

          // add experience
          user1.addExperience(new Experience("IBM", LocalDate.now().plusYears(2)));
          user1.addExperience(new Experience("Infosys", LocalDate.now()));
          user5.addExperience(new Experience("SAP", LocalDate.now().minusMonths(11)));
          user5.addExperience(new Experience("Google", LocalDate.now()));
          user6.addExperience(new Experience("HCL", LocalDate.now()));

          // add connections
          user1.addConnection(user2);
          user1.addConnection(user3);
          user4.addConnection(user6);
          user1.addConnection(user);

          // display data of users
          user1.displayUser();
          user2.displayUser();

          // add message
          Messsage messsage1 = user1.addMessage(user2, "Hi");
          Messsage messsage2 = user1.addMessage(user2, "Hey");
          Messsage messsage3 = user4.addMessage(user6, "Hello");


          // add post
          Post post1 = user1.addPost("Hey all");
          Post post2 = user1.addPost("WEclome");
          Post post3 = user3.addPost("Heya");
          user3.deletePost(post3.getId());
          user2.addReactOnPost(ReactType.LIKE, post1);
          user3.addReactOnPost(ReactType.HAHA, post1);

          // add comment
          Comment comment1 = user2.addComment(post1, "Yeahh");
          Comment comment2 = user3.addComment(post2, "Noooo");
          user1.addReactOnComment(ReactType.HAHA, comment1, post1);

          user1.displayUser();
          user.displayUser();
    }
}
