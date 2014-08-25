package ru.spb.yarish.person_registry.osgi;

import com.squeakysand.osgi.framework.BasicBundleActivator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Activator extends BasicBundleActivator {

    private static final Logger LOG = LoggerFactory.getLogger(Activator.class);

    public Activator() {
        super(LOG);
    }

}
