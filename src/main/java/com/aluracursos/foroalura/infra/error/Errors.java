package com.aluracursos.foroalura.infra.error;

import com.aluracursos.foroalura.domain.ValidacionException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.TypeMismatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class Errors {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity errorFields(MethodArgumentNotValidException e){
        List<DatosErrorValidacion> errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity errorValidation(ValidacionException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity errorIntegrity(SQLIntegrityConstraintViolationException e){
        //TODO: Change mssg to less information (constraints) in message
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity errorDataType(MethodArgumentTypeMismatchException e){

        return ResponseEntity.badRequest().body(new DataErrorDataType(e));
    }

    public record DatosErrorValidacion(String campo, String error){

        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

    public record DataErrorDataType(String valor, String typeSended, String expectedType){
        public DataErrorDataType (MethodArgumentTypeMismatchException e){
            this(
                    e.getValue().toString(),
                    e.getValue().getClass().getSimpleName(),
                    e.getRequiredType().getSimpleName()
            );
        }
    }
}
