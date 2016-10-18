package de.claudiopoll.poll.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidPollCreationException extends RuntimeException {

	private static final long serialVersionUID = 3069596343326753297L;
	public InvalidPollCreationException(String message) {
		super(message);
	}


}
