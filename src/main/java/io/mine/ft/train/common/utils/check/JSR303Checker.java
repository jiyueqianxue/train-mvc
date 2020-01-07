package io.mine.ft.train.common.utils.check;

import java.util.Set;

import javax.validation.ConstraintViolation;

/**
 * @author mark
 * @date 2018-08-13.
 */
public class JSR303Checker {

	/**
	 * 通过jsr303规范的注解来校验参数
	 */
	public static void check(Object o) {
		Set<ConstraintViolation<Object>> constraintViolations = TrainValidatorFactory.INSTANCE.getValidator()
				.validate(o);
		validate(constraintViolations);
	}

	/**
	 * 通过jsr303规范的注解来校验参数
	 * 
	 * @param groups
	 *            校验groups
	 */
	public static void checkWithGroup(Object o, Class<?>... groups) {
		Set<ConstraintViolation<Object>> constraintViolations = TrainValidatorFactory.INSTANCE.getValidator()
				.validate(o, groups);
		validate(constraintViolations);
	}

	private static void validate(Set<ConstraintViolation<Object>> constraintViolations) {
		JSR303CheckException exception = null;
		if (constraintViolations != null && !constraintViolations.isEmpty()) {
			exception = new JSR303CheckException();
			for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
				exception.addError(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
			}
		}
		if (exception != null) {
			throw exception;
		}
	}
}
