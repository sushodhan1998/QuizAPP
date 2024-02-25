package com.sush.quizservice.Controller;


import com.sush.quizservice.Service.QuizService;
import com.sush.quizservice.model.Answer;
import com.sush.quizservice.model.QuestionWrapper;
import com.sush.quizservice.model.QuizDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
@Tag(name = "QUIZ REST API's")
public class QuizController {

    @Autowired
    QuizService quizService;


    @Operation(
            summary = "Create a Quiz questions list for specified number of questions"
    )
    @PostMapping("create")
    public ResponseEntity<String> creatQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumQuestions(),quizDto.getTitle());
    }

    @Operation(
            summary = "Get the list for questions for specific quiz ID"
    )
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id){

        return quizService.getQuizkQuestion(id);
    }

    @Operation(
            summary = "Submit the answers that belongs to specific quiz ID"
    )
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@RequestBody List<Answer> answers, @PathVariable Integer id){
        return quizService.submitQuiz(answers,id);
    }
}
