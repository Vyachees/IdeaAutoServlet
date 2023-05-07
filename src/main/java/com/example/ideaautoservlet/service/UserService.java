package com.example.ideaautoservlet.service;


import com.example.ideaautoservlet.model.User;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserService {
    private final Map<Long, User> userStore = new ConcurrentHashMap<>();
    private final AtomicLong userIdSequence = new AtomicLong();

    public User getUserById(Long id) { return userStore.get(id); }

    public void createNewUser(User user){
        User newUser = new User();
        newUser.setId(userIdSequence.getAndIncrement());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setBirthDay(user.getBirthDay());
        newUser.setCompany(user.getCompany());

        userStore.put(newUser.getId(), newUser);

        System.out.println("My id from new user is "+newUser.getId());

    }

    public String  deleteUser(Long id){
        if(userStore.containsKey(id)) {
            userStore.remove(id);
            return "User "+id +" successfully deleted";
        }
        else
            return  "User "+id+" not possible to delete, no such user";
    }


}
