package com.spring.agendalive.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.agendalive.entity.Live;


public interface LiveRepository extends JpaRepository<Live, Long> {

    Page<Live> findByLiveDateAfterOrderByLiveDateAsc(LocalDateTime date, Pageable pageable);
    Page<Live> findByLiveDateBeforeOrderByLiveDateDesc(LocalDateTime date, Pageable pageable);
    
}