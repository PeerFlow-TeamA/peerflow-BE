package com.peer.missionpeerflow.repository;

import com.peer.missionpeerflow.util.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.peer.missionpeerflow.entity.Question;
import org.springframework.data.domain.Pageable;


public interface MainRepository extends PagingAndSortingRepository<Question, Long> {
    Page<Question> findAllByCategory(Category category, Pageable pageable);

    Page<Question> findAllByTitleContaining(String title, Pageable pageable);
    Page<Question> findAll(Pageable pageable);
}