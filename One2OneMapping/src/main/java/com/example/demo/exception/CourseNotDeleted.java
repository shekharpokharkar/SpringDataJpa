package com.example.demo.exception;

public class CourseNotDeleted extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseNotDeleted(String message) {
		super(message);
	}

}
