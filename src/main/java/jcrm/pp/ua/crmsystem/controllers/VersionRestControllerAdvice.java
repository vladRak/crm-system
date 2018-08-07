package jcrm.pp.ua.crmsystem.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.OptimisticLockException;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class VersionRestControllerAdvice {

//        @ExceptionHandler(OptimisticLockException.class)
//        public String handleOptLockException(OptimisticLockException ex, HttpServletResponse response) {
//            try {
//                response.sendError(460);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return ex.getMessage();
//        }
}
