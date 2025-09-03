package com.example.demo.service;

public class CourseNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseNotFound(String message) {
		super(message);
	}

}
