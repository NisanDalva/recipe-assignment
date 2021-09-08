package com.recipeassignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RecipeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -3852861819213897449L;

	public RecipeNotFoundException() {
	}

	public RecipeNotFoundException(String message) {
		super(message);
	}

	public RecipeNotFoundException(Throwable cause) {
		super(cause);
	}

	public RecipeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
