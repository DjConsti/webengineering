/**
 * SQLException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package client.exceptionHandling.erstellen;

import client.erstelleEintrag.*;

public class SQLException extends java.lang.Exception {

    private static final long serialVersionUID = 1354706414666L;
    private ErstelleEintragStub.SQLExceptionE faultMessage;

    public SQLException() {
        super("SQLException");
    }

    public SQLException(java.lang.String s) {
        super(s);
    }

    public SQLException(java.lang.String s, java.lang.Throwable ex) {
        super(s, ex);
    }

    public SQLException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(ErstelleEintragStub.SQLExceptionE msg) {
        faultMessage = msg;
    }

    public ErstelleEintragStub.SQLExceptionE getFaultMessage() {
        return faultMessage;
    }
}
    