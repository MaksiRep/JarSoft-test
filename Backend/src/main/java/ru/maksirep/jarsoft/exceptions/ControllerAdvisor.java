package ru.maksirep.jarsoft.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(EntityDeletedException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse handleDeletedException(EntityDeletedException entityDeletedException) {
        return new ExceptionResponse(entityDeletedException.getMessage());
    }

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse handleInDBException(AlreadyExistException alreadyExistException) {
        return new ExceptionResponse(alreadyExistException.getMessage());
    }

    @ExceptionHandler(IncorrectInputException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse handleInputException(IncorrectInputException incorrectInputException) {
        return new ExceptionResponse(incorrectInputException.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handleNotFoundException(NotFoundException notFoundException) {
        return new ExceptionResponse(notFoundException.getMessage());
    }

    @ExceptionHandler(CategoryDeleteException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse handleVDeleteException(CategoryDeleteException categoryDeleteException) {
        return new ExceptionResponse(categoryDeleteException.getMessage());
    }

    @ExceptionHandler(EndBannersListException.class)
    @ResponseStatus(NO_CONTENT)
    public ExceptionResponse handleEndException(EndBannersListException endBannersListException) {
        return new ExceptionResponse(endBannersListException.getMessage());
    }
}
