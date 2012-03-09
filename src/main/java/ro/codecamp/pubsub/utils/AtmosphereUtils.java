package ro.codecamp.pubsub.utils;

import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.Meteor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class AtmosphereUtils {


    public static final Logger LOG = LoggerFactory.getLogger(AtmosphereUtils.class);

    public static AtmosphereResource<HttpServletRequest, HttpServletResponse> getAtmosphereResource(HttpServletRequest request) {
        return getMeteor(request).getAtmosphereResource();
    }

    public static Meteor getMeteor(HttpServletRequest request) {
        return Meteor.build(request);
    }

    public static void suspend(final AtmosphereResource<HttpServletRequest, HttpServletResponse> resource) {
        resource.suspend();
    }
}
