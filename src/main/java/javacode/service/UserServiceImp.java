package javacode.service;

import javacode.api.RequestSender;
import javacode.dao.RoleDao;
import javacode.dao.UserDao;
import javacode.model.RolesEnum;
import javacode.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.serializer.NumberAnchorGenerator;

import java.util.*;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RequestSender requestSender;

    @Autowired
    private RoleService roleService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;




    @Override
    public User findById(Long id) {
        return userDao.findById(id).get();
    }

    @Override
    public List<User> listUser() {
        System.out.println("---->\n Find all users method");
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
        System.out.println("Edit service: " + user);
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
    @Cacheable(cacheNames = "listAges")
    public List<User> findAllKids(int age) {
        System.out.println("---->\nFind all kids method");
        return new ArrayList<>(userDao.findAllByAgeBefore(age));
    }

    @Override
    public void addWithRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setProfileImg(requestSender.sendImgRequest(user.getProfileId()));
        System.out.println(" AddWithRole: " + user);
        userDao.save(user);
    }

    @Override
    @CacheEvict("listUser")
    public void add(User user) {
        user.setRoles(Collections.singleton(roleService.getRole(RolesEnum.ROLE_USER)));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println(user);
        user.setProfileImg(requestSender.sendImgRequest(user.getProfileId()));
        userDao.save(user);
    }
    @Override
    @Cacheable(cacheNames = "loadByName")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDao.findByLogin(username);
        System.out.println("---->\nLoad user by name method ");

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User doesn't exists");
        }
        return user.get();
    }

    @Override
    public boolean userExists(String login) {
        return userDao.findByLogin(login).isPresent();
    }
}