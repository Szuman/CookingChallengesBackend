package com.cookingchallenges.exception;

import com.cookingchallenges.domain.comment.exception.CommentNotFoundException;
import com.cookingchallenges.domain.content.exception.ContentNotFoundException;
import com.cookingchallenges.domain.user.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        String defaultMessage = ex.getBindingResult().getAllErrors().stream().findFirst().get().getDefaultMessage();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex, defaultMessage);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<Object> handleBookingNotFoundException(UserNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ContentNotFoundException.class)
    ResponseEntity<Object> handleScreeningNotFoundException(ContentNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    ResponseEntity<Object> handleTicketNotFoundException(CommentNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex);
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
