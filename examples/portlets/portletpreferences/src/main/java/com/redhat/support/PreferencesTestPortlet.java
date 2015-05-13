package com.redhat.support;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
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

    @Override
    protected void doEdit(RenderRequest request, RenderResponse response)
            throws PortletException, IOException {
        response.setContentType("text/html");
        PortletPreferences prefs = request.getPreferences();
        String currentColor = prefs.getValue("color", "");
        String[] possibleColors = { "black", "blue", "yellow", "green", "red", "orange"};
        PrintWriter writer = response.getWriter();
        writer.write("<div>Portlet Preferences</div>");
        writer.write("<form action='" + response.createActionURL() + "' method='POST'>");
        writer.write("Select a color: ");
        writer.write("<select name='color'>");
        for(String c : possibleColors) {
            writer.write("<option" + (c.equals(currentColor)?" selected":"") + ">" + c + "</option>");
        }
        writer.write("</select>");
        writer.write("<input type='submit' value='Save'/>");
        writer.write("</form>");
        writer.close();
    }

    @Override
    public void processAction(ActionRequest request, ActionResponse response)
            throws PortletException, IOException {
        String selectedColor = request.getParameter("color");
        PortletPreferences prefs = request.getPreferences();
        prefs.setValue("color", selectedColor);
        prefs.store();
    }
}
