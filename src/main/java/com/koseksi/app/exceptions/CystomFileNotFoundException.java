package com.koseksi.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CystomFileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public CystomFileNotFoundException(String message) {
        super(message);
    }

    public CystomFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
