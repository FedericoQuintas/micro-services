package de.bonify.news.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidNewsCreationException extends RuntimeException {

	private static final long serialVersionUID = 3069596343326753297L;
	public InvalidNewsCreationException(String message) {
		super(message);
	}


}
