/**
 * SQLException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package client.exceptionHandling.suchen;

import client.sucheEintrag.*;

public class SQLException extends java.lang.Exception {

    private static final long serialVersionUID = 1354706462837L;
    private SucheEintragStub.SQLExceptionE faultMessage;

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

    public void setFaultMessage(SucheEintragStub.SQLExceptionE msg) {
        faultMessage = msg;
    }

    public SucheEintragStub.SQLExceptionE getFaultMessage() {
        return faultMessage;
    }
}
    