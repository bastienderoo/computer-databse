//package com.excilys.computerdatabase.persistence;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.excilys.computerdatabase.model.User;
//import com.excilys.computerdatabase.model.UserRole;
//import com.excilys.computerdatabase.persistence.implementation.UserDAOImp;
//
//public class UserDAOImpTest {
//  
//    private UserDAOImp userDAOImp;
//
//    @Before
//    public void setUp() {
//        userDAOImp = new UserDAOImp();
//    }
//
//    @After
//    public void tearDown() {
//    }
//    
//    @Test
//    public void addTest(){
//        User user = new User("UserTest","PasswordTest",UserRole.ROLE_ADMIN,true);
//        userDAOImp.add(user);
//        User userTest = userDAOImp.findByName("UserTest");
//        assertTrue(user.equals(userTest));
//    }
//    
//    @Test
//    public void deleteTest(){
//        User user = userDAOImp.findByName("JL");
//        userDAOImp.delete(user.getUsername());
//        User user2 = userDAOImp.findByName("JL");
//        assertFalse(user.equals(user2));
//    }
//}
