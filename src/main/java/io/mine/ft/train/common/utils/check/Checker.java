package io.mine.ft.train.common.utils.check;

import io.mine.ft.train.common.exception.ArgumentException;

/**
 * 切面检测
 */
public class Checker {

	/**
	 * 入参检测
	 *
	 * @param args
	 *            参数
	 */
	public static void checkFormData(Object[] args) {
		if (args == null || args.length == 0)
			return;

		for (Object param : args) {
			if (param != null) {
				String paramName = param.getClass().getSimpleName();
				try {
					if (paramName.contains("Request")) {
						continue;
					}
					if (paramName.contains("Req")) {
						JSR303Checker.check(param);
					}
				} catch (JSR303CheckException e) {
					throw new ArgumentException(e.getMessage());
				}
			}
		}

	}

	/**
	 * 入参检测
	 *
	 * @param args
	 *            参数
	 */
	public static void checkRequest(Object[] args) {
		if (args == null || args.length == 0)
			return;

		if (args.length > 1) {
			throw new ArgumentException("请求参数只能有1个");
		}
	}

	/**
	 * 实体检测
	 *
	 * @param param
	 *            接口请求参数
	 */
	public static void checkParamData(Object param) {
		if (param == null)
			return;

		Object data = param;

		try {
			JSR303Checker.check(data);
		} catch (JSR303CheckException e) {
			throw new ArgumentException(e.getMessage());
		}
	}
}
