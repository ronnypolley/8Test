/**
 *
 */
package org.eighttest.runner.testrunner;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eighttest.runner.IEightTestRunner;

/**
 * @author rpolley
 * 
 */
public class EightTestRunner implements IEightTestRunner,
		IResourceChangeListener {

	/**
	 *
	 */
	public EightTestRunner() {
		// TODO Auto-generated constructor stub
	}

	public void activate() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		workspace.addResourceChangeListener(this,
				IResourceChangeEvent.POST_CHANGE);
	}

	public void deactivate() {

	}

	@Override
	public void resourceChanged(IResourceChangeEvent event) {

	}

}
