package com.sp.admin;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.helpers.MarkerIgnoringBase;

public class WebLogger extends MarkerIgnoringBase {

	List<String> logMessages = new ArrayList<String>();

	@Override
	public boolean isTraceEnabled() {
		return true;
	}

	@Override
	public void trace(String msg) {
		logMessages.add(msg);
	}

	@Override
	public void trace(String format, Object arg) {
		log(format, arg);
	}


	@Override
	public void trace(String format, Object arg1, Object arg2) {
		log(format, arg1, arg2);
	}

	@Override
	public void trace(String format, Object[] argArray) {
		log(format, argArray);
	}

	@Override
	public void trace(String msg, Throwable t) {
		logMessages.add(msg);
	}

	@Override
	public boolean isDebugEnabled() {
		return true;
	}

	@Override
	public void debug(String msg) {
		logMessages.add(msg);
	}

	@Override
	public void debug(String format, Object arg) {
		log(format, arg);
	}

	@Override
	public void debug(String format, Object arg1, Object arg2) {
		log(format, arg1, arg2);
	}

	@Override
	public void debug(String format, Object[] argArray) {
		log(format, argArray);
	}

	@Override
	public void debug(String msg, Throwable t) {
		logMessages.add(msg);
	}

	@Override
	public boolean isInfoEnabled() {
		return true;
	}

	@Override
	public void info(String msg) {
		logMessages.add(msg);
	}

	@Override
	public void info(String format, Object arg) {
		log(format, arg);
	}

	@Override
	public void info(String format, Object arg1, Object arg2) {
		log(format, arg1, arg2);
	}

	@Override
	public void info(String format, Object[] argArray) {
		log(format, argArray);
	}

	@Override
	public void info(String msg, Throwable t) {
		logMessages.add(msg);
	}

	@Override
	public boolean isWarnEnabled() {
		return true;
	}

	@Override
	public void warn(String msg) {
		logMessages.add(msg);
	}

	@Override
	public void warn(String format, Object arg) {
		log(format, arg);
	}

	@Override
	public void warn(String format, Object[] argArray) {
		log(format, argArray);
	}

	@Override
	public void warn(String format, Object arg1, Object arg2) {
		log(format, arg1, arg2);
	}

	@Override
	public void warn(String msg, Throwable t) {
		logMessages.add(msg);
	}

	@Override
	public boolean isErrorEnabled() {
		return true;
	}

	@Override
	public void error(String msg) {
		logMessages.add(msg);
	}

	@Override
	public void error(String format, Object arg) {
		log(format, arg);
	}

	@Override
	public void error(String format, Object arg1, Object arg2) {
		log(format, arg1, arg2);
	}

	@Override
	public void error(String format, Object[] argArray) {
		log(format, argArray);
	}

	@Override
	public void error(String msg, Throwable t) {
		logMessages.add(msg);
	}

	private void log(String format, Object arg) {
		logMessages.add(String.format(format, arg));
	}

	private void log(String format, Object[] argArray) {
		logMessages.add(String.format(format, argArray));
	}

	private void log(String format, Object arg1, Object arg2) {

		logMessages.add(String.format(format, arg1, arg2));
	}

	public List<String> getLogs() {
		return logMessages;
	}

}
