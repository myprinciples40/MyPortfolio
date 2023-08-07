package com.fastcampus.boardproject.repository;

import com.fastcampus.boardproject.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource()
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}
