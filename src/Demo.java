package src;

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

          // add skills
          user1.addSkill("React");
          user1.addSkill("Java");
          user2.addSkill("C++");
          user3.addSkill("SQL");

          // add experience
          user1.addExperience(new Experience("IBM", new Date()));
          user1.addExperience(new Experience("Infosys", new Date(500)));
          user5.addExperience(new Experience("SAP", new Date()));
          user5.addExperience(new Experience("Google", new Date(1000)));
          user6.addExperience(new Experience("HCL", new Date()));

          // add connections
          user1.addConnection(user2);
          user1.addConnection(user3);
          user4.addConnection(user6);

          // display data of users
          user1.displaySkills();
          user1.displayExperiences();
          user1.displayConnections();
          user2.displaySkills();
          user2.displayExperiences();
          user2.displayConnections();

          // add message
          Messsage messsage1 = user1.addMessage(user2, "Hi");
          Messsage messsage2 = user1.addMessage(user2, "Hey");
          Messsage messsage3 = user4.addMessage(user6, "Hello");
          user2.removeMessage(messsage1.getId());
          user1.removeMessage(messsage1.getId());

          system.displayMessages();

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
          user1.addReactOnComment(ReactType.HAHA, comment1);

          user1.displayPosts();
    }
}
