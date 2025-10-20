package org.example.springjdbc.friendapp.repository;

import org.example.springjdbc.friendapp.domain.Friend;
import org.springframework.data.repository.CrudRepository;

public interface FriendRepository extends CrudRepository<Friend, Long> {
}
