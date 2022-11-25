package javacode.service;

import javacode.dao.RoleDao;
import javacode.dao.UserDao;
import javacode.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void add(User user) {
        user.setRoles(Collections.singleton(roleService.getRole("ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println(user);
        userDao.save(user);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id).get();
    }

    @Override
    @Cacheable(cacheNames="listUser")
    public List<User> listUser() {
        System.out.println("Find all users method");
        List<User> personList = new ArrayList<>();
        userDao.findAll().forEach(personList::add);
        return personList;
    }

    @Override
    public void delete(User person) {
        userDao.delete(person);
    }

    @Override
    public void edit(User personToUpdate) {
        Long id = personToUpdate.getId();
        Optional<User> user = userDao.findById(id);
        System.out.println(user);
        if (user.isPresent()) {
            User updatedPerson = user.get();
            updatedPerson.setLogin(personToUpdate.getLogin());
            updatedPerson.setPassword(personToUpdate.getPassword());
            updatedPerson.setAge(personToUpdate.getAge());
            userDao.save(updatedPerson);
            System.out.println("User with id:" + personToUpdate.getId() + " was updated: " + updatedPerson);
            return;
        }
        System.out.println("Smth went wrong with update");
    }

    @Override
    @Cacheable(cacheNames="listAges")
    public List<User> findAllKids(int age) {
        System.out.println("Find all kids method");
        return new ArrayList<>(userDao.findAllByAgeBefore(age));
    }

    @Override
    public void addWithRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println(user);
        userDao.save(user);
    }

    @Override
    @Cacheable(cacheNames="loadByName")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDao.findByLogin(username);
        System.out.println("Load user by name "+ username +"!");

        if (user.isEmpty()){
            throw new UsernameNotFoundException("User doesn't exists");
        }
        return user.get();
    }

    @Override
    public boolean userExists(String login) {
        return userDao.findByLogin(login).isPresent();
    }
}