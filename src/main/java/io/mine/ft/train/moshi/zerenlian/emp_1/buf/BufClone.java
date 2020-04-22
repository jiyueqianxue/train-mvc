package io.mine.ft.train.moshi.zerenlian.emp_1.buf;

import com.rits.cloning.Cloner;

/**
 * buf深拷贝
 *
 */
public class BufClone {

	private final static Cloner cloner = new Cloner();

	public static Buf clone(Buf buf) {
		return cloner.deepClone(buf);
	}

}
