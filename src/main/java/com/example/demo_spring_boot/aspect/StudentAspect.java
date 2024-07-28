package com.example.demo_spring_boot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
@Aspect
public class StudentAspect {
private final String FILE_LOGS = "src/main/java/com/example/demo_spring_boot/aspect/logs.data";
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @After("execution(* com.example.demo_spring_boot.controllers.StudentController.*(..))")
//    public void logAfterVisitStudentController(JoinPoint joinPoint) {
//        logger.info("Người dùng đã vào chức năng " + joinPoint.getSignature().getName()
//                + " vào lúc " + LocalDateTime.now());
//    }
@After("execution(* com.example.demo_spring_boot.controllers.StudentController.*(..))")
public void logAfterVisitStudentController(JoinPoint joinPoint) {
    String logMessage = "Người dùng đã vào chức năng " + joinPoint.getSignature().getName()
            + " vào lúc " + LocalDateTime.now() + "\n";
    writeLogToFile(logMessage);
}

    private void writeLogToFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_LOGS, true))) {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
