package com.peer.missionpeerflow.exception;

import com.peer.missionpeerflow.exception.message.ErrorMessage;
import org.hibernate.QueryParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.of(e));
	}

	@ExceptionHandler(value = ForbiddenException.class)
	public ResponseEntity forbiddenException(ForbiddenException e) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorMessage.of(e));
	}

	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity notFoundException(NotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.of(e));
	}

	@ExceptionHandler(value = ConflictException.class)
	public ResponseEntity conflictException(ConflictException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorMessage.of(e));
	}

	@ExceptionHandler(value = QueryParameterException.class)
	public ResponseEntity queryParameterException(QueryParameterException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.of(e));
	}

	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity httpMessageNotReadableException(HttpMessageNotReadableException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.of(e));
	}

	@ExceptionHandler(value = DuplicatedAdoptionException.class)
	public ResponseEntity duplicatedAdoptionException(DuplicatedAdoptionException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.of(e));
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity exception(Exception e) {
//		e.printStackTrace(); 디버깅용 코드
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorMessage.of(e));
	}
}
