package com.smithsmodding.tinystorage.util.common;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigurationHelper {
	
	/**
	 * 
	 * @param configuration
	 * @param name
	 * @param category
	 * @param defaultValue
	 * @param comment
	 * @param validValues
	 * @param langKey
	 * @return
	 */
	public static String getString(Configuration configuration, String name, String category, String defaultValue, String comment, String[] validValues, String langKey) {
		Property property = configuration.get(category, name, defaultValue);
		property.setValidValues(validValues);
		property.setLanguageKey(langKey);
		property.comment = comment + " [default: " + defaultValue + "]";
		String value = property.getString();

		for (String validValue : validValues) {
			if (value.equalsIgnoreCase(validValue)) {
				return validValue;
			}
		}

		return defaultValue;
	}
}
