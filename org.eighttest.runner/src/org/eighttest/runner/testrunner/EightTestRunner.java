/**
 *
 */
package org.eighttest.runner.testrunner;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eighttest.runner.IEightTestRunner;

/**
 * The Class EightTestRunner.
 * 
 * @author rpolley
 */
public class EightTestRunner implements IEightTestRunner,
		IResourceChangeListener {

	/** The workspace. */
	private IWorkspace workspace;

	/**
	 * Instantiates a new eight test runner.
	 */
	public EightTestRunner() {
	}

	/**
	 * Activates the services.
	 */
	public void activate() {
		workspace = ResourcesPlugin.getWorkspace();
		addListener();
	}

	/**
	 * Deactivates the services.
	 */
	public void deactivate() {
		if (workspace != null) {
			workspace.removeResourceChangeListener(this);
		}
	}

	/**
	 * Adds this class as a {@link IResourceChangeListener} to the workspace.
	 */
	private void addListener() {
		workspace.addResourceChangeListener(this,
				IResourceChangeEvent.POST_CHANGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org
	 * .eclipse.core.resources.IResourceChangeEvent)
	 */
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		Job job = new Job("Fetch all Tests") { //$NON-NLS-1$

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					IJavaProject[] projects = JavaCore.create(
							workspace.getRoot()).getJavaProjects();
					for (IJavaProject iJavaProject : projects) {
					}
				} catch (JavaModelException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
}
