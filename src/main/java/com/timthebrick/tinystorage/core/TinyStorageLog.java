package com.timthebrick.tinystorage.core;

import org.apache.logging.log4j.Level;

import com.timthebrick.tinystorage.reference.References;

import cpw.mods.fml.common.FMLLog;

public class TinyStorageLog {

	//Object based logging
	
	public static void log(Level logLevel, Object object) {
		FMLLog.log(References.MOD_NAME, logLevel, String.valueOf(object));
	}

	public static void all(Object object) {
		log(Level.ALL, object);
	}

	public static void debug(Object object) {
		log(Level.DEBUG, object);
	}

	public static void error(Object object) {
		log(Level.ERROR, object);
	}

	public static void fatal(Object object) {
		log(Level.FATAL, object);
	}

	public static void info(Object object) {
		log(Level.INFO, object);
	}

	public static void off(Object object) {
		log(Level.OFF, object);
	}

	public static void trace(Object object) {
		log(Level.TRACE, object);
	}

	public static void warn(Object object) {
		log(Level.WARN, object);
	}
	
	//String based logging
	
	public static void log(Level logLevel, String message) {
		FMLLog.log(References.MOD_NAME, logLevel, message);
	}
	
	public static void all(String object) {
		log(Level.ALL, object);
	}

	public static void debug(String object) {
		log(Level.DEBUG, object);
	}

	public static void error(String object) {
		log(Level.ERROR, object);
	}

	public static void fatal(String object) {
		log(Level.FATAL, object);
	}

	public static void info(String object) {
		log(Level.INFO, object);
	}

	public static void off(String object) {
		log(Level.OFF, object);
	}

	public static void trace(String object) {
		log(Level.TRACE, object);
	}

	public static void warn(String object) {
		log(Level.WARN, object);
	}

}
