package com.koseksi.app.exceptions;

public class CustomFileStorageException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomFileStorageException(String message) {
        super(message);
    }

    public CustomFileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
