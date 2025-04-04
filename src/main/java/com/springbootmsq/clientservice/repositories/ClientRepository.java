package com.springbootmsq.clientservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springbootmsq.clientservice.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    
    @Query("""
    SELECT COUNT(c) > 0
    FROM Client c
    WHERE LOWER(c.firstName) = LOWER(:firstName)
      AND LOWER(c.lastName) = LOWER(:lastName)
      AND c.age = :age
""")
boolean existsByNameAndAge(@Param("firstName") String firstName,
                           @Param("lastName") String lastName,
                           @Param("age") Integer age);

}
