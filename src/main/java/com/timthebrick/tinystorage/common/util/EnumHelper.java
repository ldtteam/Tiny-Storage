package com.timthebrick.tinystorage.common.util;

import java.util.EnumSet;

public class EnumHelper {

	public static <T extends Enum> T rotateEnum(T ce, boolean backwards, EnumSet ValidOptions) {
		do {
			if (backwards) {
				ce = prevEnum(ce);
			} else {
				ce = nextEnum(ce);
			}
		} while (!ValidOptions.contains(ce));

		return ce;
	}

	public static <T extends Enum> T prevEnum(T ce) {
		EnumSet valList = EnumSet.allOf(ce.getClass());

		int pLoc = ce.ordinal() - 1;
		if (pLoc < 0) {
			pLoc = valList.size() - 1;
		}

		if (pLoc < 0 || pLoc >= valList.size()) {
			pLoc = 0;
		}

		int pos = 0;
		for (Object g : valList) {
			if (pos == pLoc) {
				return (T) g;
			}
			pos++;
		}

		return null;
	}

	public static <T extends Enum> T nextEnum(T ce) {
		EnumSet valList = EnumSet.allOf(ce.getClass());

		int pLoc = ce.ordinal() + 1;
		if (pLoc >= valList.size()) {
			pLoc = 0;
		}

		if (pLoc < 0 || pLoc >= valList.size()) {
			pLoc = 0;
		}

		int pos = 0;
		for (Object g : valList) {
			if (pos == pLoc) {
				return (T) g;
			}
			pos++;
		}

		return null;
	}
}
