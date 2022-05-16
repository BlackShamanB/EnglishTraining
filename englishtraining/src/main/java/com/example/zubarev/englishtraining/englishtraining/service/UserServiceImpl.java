// package com.example.zubarev.englishtraining.englishtraining.service;

// import java.util.Collections;
// import java.util.List;
// import java.util.Optional;

// import javax.persistence.EntityManager;
// import javax.persistence.PersistenceContext;

// import com.example.zubarev.englishtraining.englishtraining.model.Role;
// import com.example.zubarev.englishtraining.englishtraining.model.User;
// import com.example.zubarev.englishtraining.englishtraining.repos.RoleRepos;
// import com.example.zubarev.englishtraining.englishtraining.repos.UserRepos;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;

// @Service
// public class UserServiceImpl implements UserDetailsService {
//     @PersistenceContext
//     private EntityManager em;
//     @Autowired
//     UserRepos userRepos;
//     @Autowired
//     RoleRepos roleRepos;
//     @Autowired
//     BCryptPasswordEncoder bCryptPasswordEncoder;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         User user = userRepos.findUserByName(username);

//         if (user == null) {
//             throw new UsernameNotFoundException("User not found");
//         }

//         return user;
//     }

//     public User findUserById(Long userId) {
//         Optional<User> userFromDb = userRepos.findById(userId);
//         return userFromDb.orElse(new User());
//     }

//     public List<User> allUsers() {
//         return (List<User>) userRepos.findAll();
//     }

//     public boolean saveUser(User user) {
//         User userFromDB = userRepos.findUserByName(user.getUsername());

//         if (userFromDB != null) {
//             // return false;
//         }

//         user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
//         user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//         // User newUser = new User(Collections.singleton(new Role(1L, "ROLE_USER")), "123", bCryptPasswordEncoder.encode("123"),  bCryptPasswordEncoder.encode("123"), "123",
//         // "123", "123", 12, 3L, 3L,
//         // 3L);
//         userRepos.save(user);
//         return true;
//     }

//     public boolean deleteUser(Long userId) {
//         if (userRepos.findById(userId).isPresent()) {
//             userRepos.deleteById(userId);
//             return true;
//         }
//         return false;
//     }

//     public List<User> usergtList(Long idMin) {
//         return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
//                 .setParameter("paramId", idMin).getResultList();
//     }
// }