package com.ibank.backend.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import com.ibank.backend.entity.User;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    UserRepository repository;

    @Test
    public void findByName() {
        User tut1 = new User(0, "Alice", "Liu", "yxj@126.com");
        entityManager.persist(tut1);
        User tut2 = new User(0, "Bob","LI","ssdd@gmail.com");
        entityManager.persist(tut2);
        List<User> tutorials = repository.findByName("tony");
        assertThat(tutorials).hasSize(2).contains(tut1, tut2);
    }
}