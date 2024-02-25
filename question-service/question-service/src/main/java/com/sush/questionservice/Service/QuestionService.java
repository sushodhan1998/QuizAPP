package com.sush.questionservice.Service;
import com.sush.questionservice.Dao.QuestionDao;
import com.sush.questionservice.model.Answer;
import com.sush.questionservice.model.Question;
import com.sush.questionservice.model.QuestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions(){
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category){

        try{
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }


    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {

        List<Integer> quesIdList = questionDao.findRandomQuestionsByCategory(categoryName,numQuestions);

        return new ResponseEntity<>(quesIdList,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> questionWrapperList = new ArrayList<>();

        List<Question> questionList = new ArrayList<>();

        for(Integer id: questionIds){
            questionList.add(questionDao.findById(id).get());
        }
        for (Question question : questionList){
            QuestionWrapper questionWrapper = new QuestionWrapper
                    (question.getId(),question.getQuestionTitle(),question.getOption1(),
                            question.getOption2(),question.getOption3(),question.getOption4());
            questionWrapperList.add(questionWrapper);
        }

        return new ResponseEntity<>(questionWrapperList,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Answer> answers) {

            Integer rightAnswer = 0;
       for (Answer answer:answers){
           Question question = questionDao.findById(answer.getId()).get();
           if(answer.getRightAnswer().equals(question.getRightAnswer()))
               rightAnswer++;
       }
            return new ResponseEntity<>(rightAnswer,HttpStatus.OK);

    }
}
