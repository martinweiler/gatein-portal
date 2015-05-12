package com.redhat.support;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSecurityException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class PreferencesTestPortlet extends GenericPortlet {

    @Override
    protected void doView(RenderRequest request, RenderResponse response)
            throws PortletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        PortletPreferences prefs = request.getPreferences();
        String color = prefs.getValue("color", "black");
        writer.write("<div style='color:" + color + "'>Portlet Preference value: " + color + "</div>");
        writer.close();
    }

}
