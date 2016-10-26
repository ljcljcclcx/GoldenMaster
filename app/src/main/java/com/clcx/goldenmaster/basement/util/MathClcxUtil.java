package com.clcx.goldenmaster.basement.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MathClcxUtil {
	private Random random;

	private MathClcxUtil() {
	}

	private static MathClcxUtil instance = null;

	public static MathClcxUtil getInstance() {
		if (instance == null) {
			synchronized (MathClcxUtil.class) {
				if (instance == null) {
					instance = new MathClcxUtil();
				}

			}
		}
		return instance;
	}

	/**
	 * 从min到max只见的所有浮点型数据，三位小数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public float randomFloat(float min, float max) {
		random = new Random();
		int iMin = (int) (min * 1000);
		int iMax = (int) (max * 1000);

		int randInt = iMin + random.nextInt(iMax - iMin);

		random = null;
		return (float) randInt / 1000.0f;

	}

	/**
	 * 随机数 0-i
	 * 
	 * @return
	 */
	public int randomInt(int i) {
		random = new Random();
		return random.nextInt(i);
	}

	/**
	 * 金钱格式转换器
	 */
	public String moneyFormat(int money) {
		StringBuffer stb = new StringBuffer();
		List<String> split = new ArrayList<String>();
		int rest = money;
		while (rest > 0) {
			String restStr = "";
			int mod = rest % 1000;
			// 补0
			if (mod < 100 && mod >= 10) {
				restStr = ",0" + mod;
			} else if (mod < 10) {
				restStr = ",00" + mod;
			} else {
				restStr = "," + Integer.toString(mod);
			}

			rest /= 1000;
			if (rest == 0) {
				split.add(Integer.toString(mod));
			} else {
				split.add(restStr);
			}
		}

		for (int i = split.size() - 1; i >= 0; i--) {
			stb.append(split.get(i));
		}

		return stb.toString();

	}
}
