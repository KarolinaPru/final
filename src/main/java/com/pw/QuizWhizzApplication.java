package com.pw;

import com.pw.Logic.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizWhizzApplication {

    public static void main(String[] args) {
        QuestionService service = new QuestionServiceHardcoded();

        Player gameOwner = new PlayerImpl("Zenek Owner");
        Category testCategory = new Category("Testowa", "Testowa");
        Game game = new Game(gameOwner, testCategory, service.get10RandomQuestions(testCategory));

        Player playerTwo = new PlayerImpl("Romek Gracz");
        game.addPlayer(playerTwo);

        game.start();


        SpringApplication.run(QuizWhizzApplication.class, args);
    }
}
