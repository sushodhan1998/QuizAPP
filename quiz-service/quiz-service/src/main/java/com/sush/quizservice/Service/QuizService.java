package com.sush.quizservice.Service;

import com.sush.quizservice.Dao.QuizDao;
import com.sush.quizservice.feign.QuizInterface;
import com.sush.quizservice.model.Answer;
import com.sush.quizservice.model.Quiz;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sush.quizservice.model.QuestionWrapper;

import java.util.List;
import java.util.Optional;


@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

    List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);

        quizDao.save(quiz);

        return  new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizkQuestion(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Integer> questionsIDs = quiz.get().getQuestionIds();
        List<QuestionWrapper> questionsForUser = quizInterface.getQuestionsFromId(questionsIDs).getBody();
        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> submitQuiz(List<Answer> answers, Integer id) {
        Optional<Quiz> quiz  = quizDao.findById(id);
        Integer score = quizInterface.getScore(answers).getBody();
        System.out.println("score--"+score);
        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}
