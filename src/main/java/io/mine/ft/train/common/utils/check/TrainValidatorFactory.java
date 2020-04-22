package io.mine.ft.train.common.utils.check;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 获取Validator，保证单例<br/>
 * <p>
 * You should usually not instantiate more than one factory; factory creation is
 * a costly process. Also, the factory also acts as a cache for the available
 * validation constraints.
 * 
 */
public enum TrainValidatorFactory {
	INSTANCE {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

		@Override
		public Validator getValidator() {
			return factory.getValidator();
		}
	};

	public abstract Validator getValidator();

}
