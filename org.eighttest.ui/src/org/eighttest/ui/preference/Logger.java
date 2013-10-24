/**
 *
 */
package org.eighttest.ui.preference;

import java.lang.reflect.Field;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;

/**
 * @author rpolley
 * 
 */
public class Logger {

	private Plugin plugin;
	private String id;

	public static Logger getLog(Plugin plugin, String pluginId) {
		return new Logger(plugin);
	}

	private Logger(Plugin plugin) {
		this.plugin = plugin;
		try {
			Field idField = plugin.getClass().getDeclaredField("PLUGIN_ID"); //$NON-NLS-1$
			id = idField.get(plugin).toString();
		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException e) {
			// not good, we just use a generic id in this case
			id = "org.eighttest.8Test"; //$NON-NLS-1$
		}
	}

	private ILog getLog() {
		return plugin.getLog();
	}

	public void logInfo(String message) {
		log(IStatus.INFO, IStatus.OK, message, null);
	}

	public void logError(Throwable exception) {
		logError("Unexpected Exception", exception); //$NON-NLS-1$
	}

	public void logError(String message, Throwable exception) {
		log(IStatus.ERROR, IStatus.OK, message, exception);
	}

	public void log(int severity, int code, String message, Throwable exception) {
		log(createStatus(severity, code, message, exception));
	}

	public IStatus createStatus(int severity, int code, String message,
			Throwable exception) {
		return new Status(severity, id, code, message, exception);
	}

	public void log(IStatus status) {
		getLog().log(status);
	}

}
