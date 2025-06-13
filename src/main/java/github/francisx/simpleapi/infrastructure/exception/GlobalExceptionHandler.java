package github.francisx.simpleapi.infrastructure.exception;

import github.francisx.simpleapi.infrastructure.exception.error.RoleNotAllowedEx;
import github.francisx.simpleapi.infrastructure.exception.error.SQLNotFoundEx;
import github.francisx.simpleapi.infrastructure.exception.error.UnauthorizedEx;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnauthorizedEx.class)
    public ResponseEntity<ExceptionResponse> rejectedUnauthorizedRequests(UnauthorizedEx ex){
        return ResponseEntity.status(401).body(new ExceptionResponse(ex.getMessage()));
    }

    @ExceptionHandler(RoleNotAllowedEx.class)
    public ResponseEntity<ExceptionResponse> roleNotAuthorized(RoleNotAllowedEx ex){
        return ResponseEntity.status(401).body(new ExceptionResponse(ex.getMessage()));
    }

    @ExceptionHandler(SQLNotFoundEx.class)
    public ResponseEntity<ExceptionResponse> sqlExceptions(SQLNotFoundEx ex){
        return ResponseEntity.status(404).body(new ExceptionResponse(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<FieldErrors>> validationErrors(MethodArgumentNotValidException ex) {
        var listOfErrors = ex.getFieldErrors().stream()
                .map(e -> new FieldErrors(e.getField(), e.getDefaultMessage()))
                .toList();
        return new ResponseEntity<>(listOfErrors, HttpStatus.BAD_REQUEST);
    }

    public record ExceptionResponse(String message){}

    public record FieldErrors(String key, String message){}
}
