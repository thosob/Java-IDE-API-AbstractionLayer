package de.uos.eclipse;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.eclipse.ui.IStartup;

public class Startable implements IStartup {
	
	@PostContextCreate
	void postContextCreate(final IEventBroker eventBroker, IApplicationContext context) {
   /*     final Shell shell = new Shell(SWT.SHELL_TRIM);

        // register for startup completed event and close the shell
        eventBroker.subscribe(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE,
                new EventHandler() {
                    @Override
                    public void handleEvent(Event event) {
                        shell.close();
                        shell.dispose();
                        eventBroker.unsubscribe(this);
                    }
                });
        // close static splash screen
        context.applicationRunning();
        shell.open(); */
    }
	
	@Override
	public void earlyStartup() {
		System.out.println("just a small test");
		
	}
	 

}
