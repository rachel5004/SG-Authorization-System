package com.auth.user.repository;

import com.auth.user.model.Salt;
import org.springframework.data.repository.CrudRepository;

public interface SaltRepository extends CrudRepository<Salt,Long> {
}