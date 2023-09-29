package com.test.palindromesearch.repository;

import com.test.palindromesearch.model.Palindrome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalindromeRepository extends JpaRepository<Palindrome, Long > {
}
