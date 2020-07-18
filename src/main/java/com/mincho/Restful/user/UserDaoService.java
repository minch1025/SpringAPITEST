package com.mincho.Restful.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {
    //using memory data simply
    //buisness logic
    //Create list + create section on memory
    private static int UserCount=3;
    private static List<User> users = new ArrayList<>();
    //initializing
    static{
        users.add(new User(1,"minch",new Date()));
        users.add(new User(2,"sangsun",new Date()));
        users.add(new User(3,"sunny",new Date()));
    }

    //get all list
    public List<User> findAll(){
        return users;

    }
    //save user id
    public User save(User user){
        if(user.getId() == null){
             user.setId(++UserCount);
        }
        users.add(user);
        return user;
    }
    //get just one list
    public User findOne(int id){
        //users -> User user ( men to men finding)
        for(User user:users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }
    public User deleteById(int id){
        //Iterator means start process Iteratally.
        //Need to register this method on Class (User Controller)
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()){
            User user = iterator.next();

            if(user.getId()==id){
                iterator.remove();
                return user;
            }

        }
        return null;
    }

}
