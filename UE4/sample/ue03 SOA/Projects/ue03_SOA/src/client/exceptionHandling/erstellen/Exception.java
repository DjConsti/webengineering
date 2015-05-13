/**
 * Exception.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package client.exceptionHandling.erstellen;

import client.erstelleEintrag.*;

public class Exception extends java.lang.Exception {

    private static final long serialVersionUID = 1354706414650L;
    private ErstelleEintragStub.ExceptionE faultMessage;

    public Exception() {
        super("Exception");
    }

    public Exception(java.lang.String s) {
        super(s);
    }

    public Exception(java.lang.String s, java.lang.Throwable ex) {
        super(s, ex);
    }

    public Exception(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(ErstelleEintragStub.ExceptionE msg) {
        faultMessage = msg;
    }

    public ErstelleEintragStub.ExceptionE getFaultMessage() {
        return faultMessage;
    }
}
    