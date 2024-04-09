Учебный проект на Java. Простой экзаменационный сервис.</br>
В этом проетке реализован DI, OOP. </br>
Стек технологий: </br>
Java 17
Spring framework
Способ запуска:</br>
Скопировать репозиторий, запустить его в IDE, перейти по адресу http://localhost:8080/exam.</br>
Для хранения всех вопросов использовался Set. При вызове метода /exam/get/{amount} формируется список случайных вопрос.</br>
-/get/{amount} где {amount} это колличество вопросов.</br>
-/java/add?question={question}&answer={answer} добавить вопрос, где {question} - вопрос, {answer} - ответ.</br>
-/java/remove?question={question}&answer={answer} удить вопрос, где {question} - вопрос, {answer} - ответ.</br>
-/java - получить все вопросы.</br>
-java/math/add?question={question}&answer={answer} добавить вопрос, где {question} - вопрос, {answer} - ответ.</br>
-java/math/remove?question={question}&answer={answer} удить вопрос, где {question} - вопрос, {answer} - ответ.</br>
-java/math/getAll получить все вопросы.</br>
