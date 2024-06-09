package ra.user_validate_api.advice;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ra.user_validate_api.model.dto.response.DataError;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiValidateAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public DataError<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        for (int i = 0; i < ex.getAllErrors().size(); i++) {
            ObjectError error = ex.getAllErrors().get(i);
            errors.put("error"+i,error.getDefaultMessage());
        }
        return new DataError<>("error",errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public DataError<String> handleNoSuchElementException(NoSuchElementException ex){
        return new DataError<>("error","User not found",HttpStatus.NOT_FOUND);
    }
}
