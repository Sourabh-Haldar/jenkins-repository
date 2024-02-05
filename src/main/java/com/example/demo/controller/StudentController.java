package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import com.example.demo.ui.ErrorResponse;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {
	private  StudentService studentService;
	@ExceptionHandler(StudentNotFoundException.class)
	public ErrorResponse handleException(StudentNotFoundException e) {

		ErrorResponse response = new ErrorResponse();
		response.setMessage(e.getMessage());
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setToOfError(System.currentTimeMillis());
		return response;
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());
		return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	private Map<String, List<String>> getErrorsMap(List<String> errors) {
		Map<String, List<String>> errorResponse = new HashMap<>();
		errorResponse.put("errors", errors);
		return errorResponse;
	}
	@PostMapping
	public ResponseEntity<?> createSchool(@RequestBody Student student) throws StudentNotFoundException{
		return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(student));
	}
	@GetMapping
	public ResponseEntity<?> listSchools(){
		return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudent());
	}
}
