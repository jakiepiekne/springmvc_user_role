//package com.springmvc.learning.dao.service;
//
//import com.springmvc.learning.configuration.HibernateConfig;
//import com.springmvc.learning.configuration.WebSecurity;
//import com.springmvc.learning.models.RoleEntity;
//import com.springmvc.learning.models.UserEntity;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.stream.Stream;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {HibernateConfig.class, WebSecurity.class})
//@TestPropertySource("classpath:application-test.properties")
//@Transactional
//public class UserServiceTest {
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    RoleService roleService;
//
//    @Before
//    public void init() {
//        RoleEntity roleToCreate = new RoleEntity("test");
//        roleService.create(roleToCreate);
//        RoleEntity role = roleService.findByName("test").get();
//        UserEntity user1 = new UserEntity(1L, "test1", "test1", "test1", "test1@test.t.com",
//                "password", "password", LocalDate.of(2001, Month.JULY, 9), role);
//        UserEntity user2 = new UserEntity(2L, "test2", "test2", "test2", "test2@test.t.com",
//                "password", "password", LocalDate.of(2002, Month.JULY, 10), role);
//        UserEntity user3 = new UserEntity(3L, "test3", "test3", "test3", "test3@test.t.com",
//                "password", "password", LocalDate.of(2003, Month.JULY, 11), role);
//        Stream.of(user1, user2, user3).forEach(user -> userService.create(user));
//
//    }
//
//
//    @Test
//    public void testCreate() {
//        UserEntity user = new UserEntity("testCreate", "testCreate", "testCreate", "testCreate@test.t.com",
//                "password", LocalDate.of(2001, Month.JULY, 9), roleService.findByName("test").get());
//        userService.create(user);
//        Assert.assertEquals(4, userService.findAll().size());
//
//    }
//
//    @Test
//    public void testUpdate() {
//        UserEntity user = userService.findByEmail("test1@test.t.com").get();
//        user.setEmail("testUPDATE@test.t.com");
//        Assert.assertTrue(userService.findByEmail("testUPDATE@test.t.com").isPresent());
//
//    }
//
//    @Test
//    public void testDelete() {
//        UserEntity user = userService.findByEmail("test1@test.t.com").get();
//        userService.delete(user);
//        Assert.assertTrue(userService.findByEmail("test1@test.t.com").isEmpty());
//    }
//
//    @Test
//    public void testDeleteById() {
//        userService.deleteById(2L);
//        Assert.assertTrue(userService.findById(2L).isEmpty());
//    }
//
//    @Test
//    public void testFindById() {
//        UserEntity userExpected = userService.findByEmail("test1@test.t.com").get();
//        Long id = userExpected.getId();
//        Assert.assertEquals("test1@test.t.com", userService.findById(id).get().getEmail());
//    }
//
//    @Test
//    public void testFindAll() {
//        Assert.assertEquals(3, userService.findAll().size());
//    }
//
//    @Test
//    public void testFindByEmail() {
//        Assert.assertTrue(userService.findByEmail("test1@test.t.com").isPresent());
//    }
//
//    @Test
//    public void testFindByUsername() {
//        Assert.assertTrue(userService.findByUsername("test1").isPresent());
//    }
//
//    @Test
//    public void testFindByEmailOrUsername() {
//        Assert.assertTrue(userService.findByEmailOrUsername("test1@test.t.com").isPresent());
//        Assert.assertTrue(userService.findByEmailOrUsername("test1").isPresent());
//    }
//}
