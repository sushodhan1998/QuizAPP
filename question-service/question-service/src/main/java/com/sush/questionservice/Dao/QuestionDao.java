package com.sush.questionservice.Dao;


import com.sush.questionservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {



    public List<Question> findByCategory(String category);

    @Query(value = "SELECT id FROM Question q WHERE q.category = ?1 ORDER BY RANDOM() LIMIT ?2")
    List<Integer> findRandomQuestionsByCategory(String category, int numQ);
}
