package com.bootcampmanagement.server.repositories;

import com.bootcampmanagement.server.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    public Optional<User> findByUsername(String username);
    
    @Modifying
    @Query(
        value = "insert into user "
                + "(id,username,password) "
                + "VALUES"
                + "(:id,:username,:password)", nativeQuery = true)
    public void addUser(
        @Param("id") Integer id, 
        @Param("username") String username, 
        @Param("password") String password
    );
    
    @Modifying
    @Query("update User SET "
            + "username = :username"
            + " WHERE id = :id")
    public void editUsername(
        @Param("username") String username,
        @Param("id") Integer id
    );
    
    //Total user
    @Query(value="SELECT count(*) FROM user",nativeQuery = true)
    int totalUser();
}
