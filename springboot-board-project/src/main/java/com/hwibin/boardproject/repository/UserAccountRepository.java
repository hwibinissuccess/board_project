package com.hwibin.boardproject.repository;

import com.hwibin.boardproject.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(excerptProjection = UserAccountProjection.class)
@RepositoryRestResource
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}