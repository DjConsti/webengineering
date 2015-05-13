/**
 * ErstelleEintragStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package client.erstelleEintrag;

/*
 *  ErstelleEintragStub java implementation
 */
public class ErstelleEintragStub extends org.apache.axis2.client.Stub {

    protected org.apache.axis2.description.AxisOperation[] _operations;
    //hashmaps to keep the fault mapping
    private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
    private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
    private java.util.HashMap faultMessageMap = new java.util.HashMap();
    private static int counter = 0;

    private static synchronized java.lang.String getUniqueSuffix() {
        // reset the counter if it is greater than 99999
        if (counter > 99999) {
            counter = 0;
        }
        counter = counter + 1;
        return java.lang.Long.toString(java.lang.System.currentTimeMillis()) + "_" + counter;
    }

    private void populateAxisService() throws org.apache.axis2.AxisFault {

        //creating the Service with a unique name
        _service = new org.apache.axis2.description.AxisService("ErstelleEintrag" + getUniqueSuffix());
        addAnonymousOperations();

        //creating the operations
        org.apache.axis2.description.AxisOperation __operation;

        _operations = new org.apache.axis2.description.AxisOperation[1];

        __operation = new org.apache.axis2.description.OutInAxisOperation();


        __operation.setName(new javax.xml.namespace.QName("http://soa/", "erstellEintrag"));
        _service.addOperation(__operation);




        _operations[0] = __operation;


    }

    //populates the faults
    private void populateFaults() {

        faultExceptionNameMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://soa/xsd", "Exception"), "erstellEintrag"), "client.exceptionHandling.erstellen.Exception");
        faultExceptionClassNameMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://soa/xsd", "Exception"), "erstellEintrag"), "client.exceptionHandling.erstellen.Exception");
        faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://soa/xsd", "Exception"), "erstellEintrag"), "ErstelleEintragStub$ExceptionE");

        faultExceptionNameMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://soa/xsd", "SQLException"), "erstellEintrag"), "client.exceptionHandling.erstellen.SQLException");
        faultExceptionClassNameMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://soa/xsd", "SQLException"), "erstellEintrag"), "client.exceptionHandling.erstellen.SQLException");
        faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName("http://soa/xsd", "SQLException"), "erstellEintrag"), "ErstelleEintragStub$SQLExceptionE");



    }

    /**
     *Constructor that takes in a configContext
     */
    public ErstelleEintragStub(org.apache.axis2.context.ConfigurationContext configurationContext,
            java.lang.String targetEndpoint)
            throws org.apache.axis2.AxisFault {
        this(configurationContext, targetEndpoint, false);
    }

    /**
     * Constructor that takes in a configContext  and useseperate listner
     */
    public ErstelleEintragStub(org.apache.axis2.context.ConfigurationContext configurationContext,
            java.lang.String targetEndpoint, boolean useSeparateListener)
            throws org.apache.axis2.AxisFault {
        //To populate AxisService
        populateAxisService();
        populateFaults();

        _serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext, _service);


        _serviceClient.getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
                targetEndpoint));
        _serviceClient.getOptions().setUseSeparateListener(useSeparateListener);

        //Set the soap version
        _serviceClient.getOptions().setSoapVersionURI(org.apache.axiom.soap.SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);


    }

    /**
     * Default Constructor
     */
    public ErstelleEintragStub(org.apache.axis2.context.ConfigurationContext configurationContext,String host,int port) throws org.apache.axis2.AxisFault {

        this(configurationContext, "http://"+host+":"+port+"/axis2/services/erstelleEintrag");

    }

    /**
     * Default Constructor
     */
    public ErstelleEintragStub(String host, int port) throws org.apache.axis2.AxisFault {
        this("http://"+host+":"+port+"/axis2/services/erstelleEintrag");
    }

    /**
     * Constructor taking the target endpoint
     */
    public ErstelleEintragStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
        this(null, targetEndpoint);
    }

    /**
     * Auto generated method signature
     *
     * @see soa.ErstelleEintrag#erstellEintrag
     * @param erstellEintrag0

     * @throws soa.Exception :
     * @throws soa.SQLException :
     */
    public ErstelleEintragStub.ErstellEintragResponse erstellEintrag(
            ErstelleEintragStub.ErstellEintrag erstellEintrag0)
            throws java.rmi.RemoteException, client.exceptionHandling.erstellen.Exception, client.exceptionHandling.erstellen.SQLException {
        org.apache.axis2.context.MessageContext _messageContext = null;
        try {
            org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
            _operationClient.getOptions().setAction("urn:erstellEintrag");
            _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



            addPropertyToOperationClient(_operationClient, org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");


            // create a message context
            _messageContext = new org.apache.axis2.context.MessageContext();



            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;


            env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                    erstellEintrag0,
                    optimizeContent(new javax.xml.namespace.QName("http://soa/",
                    "erstellEintrag")), new javax.xml.namespace.QName("http://soa/",
                    "erstellEintrag"));

            //adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            _messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            _operationClient.addMessageContext(_messageContext);

            //execute the operation client
            _operationClient.execute(true);


            org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
                    org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();


            java.lang.Object object = fromOM(
                    _returnEnv.getBody().getFirstElement(),
                    ErstelleEintragStub.ErstellEintragResponse.class,
                    getEnvelopeNamespaces(_returnEnv));


            return (ErstelleEintragStub.ErstellEintragResponse) object;

        } catch (org.apache.axis2.AxisFault f) {

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt != null) {
                if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "erstellEintrag"))) {
                    //make the fault by reflection
                    try {
                        java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "erstellEintrag"));
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.reflect.Constructor constructor = exceptionClass.getConstructor(String.class);
                        java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
                        //message class
                        java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "erstellEintrag"));
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt, messageClass, null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                new java.lang.Class[]{messageClass});
                        m.invoke(ex, new java.lang.Object[]{messageObject});

                        if (ex instanceof client.exceptionHandling.erstellen.Exception) {
                            throw (client.exceptionHandling.erstellen.Exception) ex;
                        }

                        if (ex instanceof client.exceptionHandling.erstellen.SQLException) {
                            throw (client.exceptionHandling.erstellen.SQLException) ex;
                        }


                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    } catch (java.lang.ClassCastException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                } else {
                    throw f;
                }
            } else {
                throw f;
            }
        } finally {
            if (_messageContext.getTransportOut() != null) {
                _messageContext.getTransportOut().getSender().cleanup(_messageContext);
            }
        }
    }

    /**
     * Auto generated method signature for Asynchronous Invocations
     *
     * @see soa.ErstelleEintrag#starterstellEintrag
     * @param erstellEintrag0

     */
    public void starterstellEintrag(
            ErstelleEintragStub.ErstellEintrag erstellEintrag0,
            final ErstelleEintragCallbackHandler callback)
            throws java.rmi.RemoteException {

        org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
        _operationClient.getOptions().setAction("urn:erstellEintrag");
        _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



        addPropertyToOperationClient(_operationClient, org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");



        // create SOAP envelope with that payload
        org.apache.axiom.soap.SOAPEnvelope env = null;
        final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();


        //Style is Doc.


        env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                erstellEintrag0,
                optimizeContent(new javax.xml.namespace.QName("http://soa/",
                "erstellEintrag")), new javax.xml.namespace.QName("http://soa/",
                "erstellEintrag"));

        // adding SOAP soap_headers
        _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);



        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {

            public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
                try {
                    org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

                    java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
                            ErstelleEintragStub.ErstellEintragResponse.class,
                            getEnvelopeNamespaces(resultEnv));
                    callback.receiveResulterstellEintrag(
                            (ErstelleEintragStub.ErstellEintragResponse) object);

                } catch (org.apache.axis2.AxisFault e) {
                    callback.receiveErrorerstellEintrag(e);
                }
            }

            public void onError(java.lang.Exception error) {
                if (error instanceof org.apache.axis2.AxisFault) {
                    org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
                    org.apache.axiom.om.OMElement faultElt = f.getDetail();
                    if (faultElt != null) {
                        if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "erstellEintrag"))) {
                            //make the fault by reflection
                            try {
                                java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "erstellEintrag"));
                                java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                                java.lang.reflect.Constructor constructor = exceptionClass.getConstructor(String.class);
                                java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
                                //message class
                                java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "erstellEintrag"));
                                java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                                java.lang.Object messageObject = fromOM(faultElt, messageClass, null);
                                java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                        new java.lang.Class[]{messageClass});
                                m.invoke(ex, new java.lang.Object[]{messageObject});

                                if (ex instanceof client.exceptionHandling.erstellen.Exception) {
                                    callback.receiveErrorerstellEintrag((client.exceptionHandling.erstellen.Exception) ex);
                                    return;
                                }

                                if (ex instanceof client.exceptionHandling.erstellen.SQLException) {
                                    callback.receiveErrorerstellEintrag((client.exceptionHandling.erstellen.SQLException) ex);
                                    return;
                                }


                                callback.receiveErrorerstellEintrag(new java.rmi.RemoteException(ex.getMessage(), ex));
                            } catch (java.lang.ClassCastException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorerstellEintrag(f);
                            } catch (java.lang.ClassNotFoundException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorerstellEintrag(f);
                            } catch (java.lang.NoSuchMethodException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorerstellEintrag(f);
                            } catch (java.lang.reflect.InvocationTargetException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorerstellEintrag(f);
                            } catch (java.lang.IllegalAccessException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorerstellEintrag(f);
                            } catch (java.lang.InstantiationException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorerstellEintrag(f);
                            } catch (org.apache.axis2.AxisFault e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorerstellEintrag(f);
                            }
                        } else {
                            callback.receiveErrorerstellEintrag(f);
                        }
                    } else {
                        callback.receiveErrorerstellEintrag(f);
                    }
                } else {
                    callback.receiveErrorerstellEintrag(error);
                }
            }

            public void onFault(org.apache.axis2.context.MessageContext faultContext) {
                org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
                onError(fault);
            }

            public void onComplete() {
                try {
                    _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                } catch (org.apache.axis2.AxisFault axisFault) {
                    callback.receiveErrorerstellEintrag(axisFault);
                }
            }
        });


        org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if (_operations[0].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
            _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
            _operations[0].setMessageReceiver(
                    _callbackReceiver);
        }

        //execute the operation client
        _operationClient.execute(false);

    }

    /**
     *  A utility method that copies the namepaces from the SOAPEnvelope
     */
    private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env) {
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
            org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
            returnMap.put(ns.getPrefix(), ns.getNamespaceURI());
        }
        return returnMap;
    }
    private javax.xml.namespace.QName[] opNameArray = null;

    private boolean optimizeContent(javax.xml.namespace.QName opName) {


        if (opNameArray == null) {
            return false;
        }
        for (int i = 0; i < opNameArray.length; i++) {
            if (opName.equals(opNameArray[i])) {
                return true;
            }
        }
        return false;
    }
    //http://localhost:8080/axis2/services/erstelleEintrag

    public static class SQLException extends Exception
            implements org.apache.axis2.databinding.ADBBean {
        /* This type was generated from the piece of schema that had
        name = SQLException
        Namespace URI = http://sql.java/xsd
        Namespace Prefix = ns2
         */

        /**
         * field for ErrorCode
         */
        protected int localErrorCode;
        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localErrorCodeTracker = false;

        public boolean isErrorCodeSpecified() {
            return localErrorCodeTracker;
        }

        /**
         * Auto generated getter method
         * @return int
         */
        public int getErrorCode() {
            return localErrorCode;
        }

        /**
         * Auto generated setter method
         * @param param ErrorCode
         */
        public void setErrorCode(int param) {

            // setting primitive attribute tracker to true
            localErrorCodeTracker =
                    param != java.lang.Integer.MIN_VALUE;

            this.localErrorCode = param;


        }
        /**
         * field for NextException
         */
        protected SQLException localNextException;
        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localNextExceptionTracker = false;

        public boolean isNextExceptionSpecified() {
            return localNextExceptionTracker;
        }

        /**
         * Auto generated getter method
         * @return SQLException
         */
        public SQLException getNextException() {
            return localNextException;
        }

        /**
         * Auto generated setter method
         * @param param NextException
         */
        public void setNextException(SQLException param) {
            localNextExceptionTracker = true;

            this.localNextException = param;


        }
        /**
         * field for SQLState
         */
        protected java.lang.String localSQLState;
        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localSQLStateTracker = false;

        public boolean isSQLStateSpecified() {
            return localSQLStateTracker;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getSQLState() {
            return localSQLState;
        }

        /**
         * Auto generated setter method
         * @param param SQLState
         */
        public void setSQLState(java.lang.String param) {
            localSQLStateTracker = true;

            this.localSQLState = param;


        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
                final javax.xml.namespace.QName parentQName,
                final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {



            org.apache.axiom.om.OMDataSource dataSource =
                    new org.apache.axis2.databinding.ADBDataSource(this, parentQName);
            return factory.createOMElement(dataSource, parentQName);

        }

        public void serialize(final javax.xml.namespace.QName parentQName,
                javax.xml.stream.XMLStreamWriter xmlWriter)
                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
            serialize(parentQName, xmlWriter, false);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
                javax.xml.stream.XMLStreamWriter xmlWriter,
                boolean serializeType)
                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {




            java.lang.String prefix = null;
            java.lang.String namespace = null;


            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();
            writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);


            java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://sql.java/xsd");
            if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
                writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
                        namespacePrefix + ":SQLException",
                        xmlWriter);
            } else {
                writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
                        "SQLException",
                        xmlWriter);
            }

            if (localExceptionTracker) {

                if (localException != null) {
                    if (localException instanceof org.apache.axis2.databinding.ADBBean) {
                        ((org.apache.axis2.databinding.ADBBean) localException).serialize(
                                new javax.xml.namespace.QName("http://soa/xsd", "Exception"),
                                xmlWriter, true);
                    } else {
                        writeStartElement(null, "http://soa/xsd", "Exception", xmlWriter);
                        org.apache.axis2.databinding.utils.ConverterUtil.serializeAnyType(localException, xmlWriter);
                        xmlWriter.writeEndElement();
                    }
                } else {

                    // write null attribute
                    writeStartElement(null, "http://soa/xsd", "Exception", xmlWriter);

                    // write the nil attribute
                    writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
                    xmlWriter.writeEndElement();

                }


            }
            if (localErrorCodeTracker) {
                namespace = "http://sql.java/xsd";
                writeStartElement(null, namespace, "errorCode", xmlWriter);

                if (localErrorCode == java.lang.Integer.MIN_VALUE) {

                    throw new org.apache.axis2.databinding.ADBException("errorCode cannot be null!!");

                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localErrorCode));
                }

                xmlWriter.writeEndElement();
            }
            if (localNextExceptionTracker) {
                if (localNextException == null) {

                    writeStartElement(null, "http://sql.java/xsd", "nextException", xmlWriter);

                    // write the nil attribute
                    writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
                    xmlWriter.writeEndElement();
                } else {
                    localNextException.serialize(new javax.xml.namespace.QName("http://sql.java/xsd", "nextException"),
                            xmlWriter);
                }
            }
            if (localSQLStateTracker) {
                namespace = "http://sql.java/xsd";
                writeStartElement(null, namespace, "sQLState", xmlWriter);


                if (localSQLState == null) {
                    // write the nil attribute

                    writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

                } else {


                    xmlWriter.writeCharacters(localSQLState);

                }

                xmlWriter.writeEndElement();
            }
            xmlWriter.writeEndElement();


        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if (namespace.equals("http://sql.java/xsd")) {
                return "ns2";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
                java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace, java.lang.String attName,
                java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }
            java.lang.String attributeValue;
            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                throws org.apache.axis2.databinding.ADBException {



            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();


            attribList.add(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema-instance", "type"));
            attribList.add(new javax.xml.namespace.QName("http://sql.java/xsd", "SQLException"));
            if (localExceptionTracker) {
                elementList.add(new javax.xml.namespace.QName("http://soa/xsd",
                        "Exception"));


                elementList.add(localException == null ? null : localException);
            }
            if (localErrorCodeTracker) {
                elementList.add(new javax.xml.namespace.QName("http://sql.java/xsd",
                        "errorCode"));

                elementList.add(
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localErrorCode));
            }
            if (localNextExceptionTracker) {
                elementList.add(new javax.xml.namespace.QName("http://sql.java/xsd",
                        "nextException"));


                elementList.add(localNextException == null ? null : localNextException);
            }
            if (localSQLStateTracker) {
                elementList.add(new javax.xml.namespace.QName("http://sql.java/xsd",
                        "sQLState"));

                elementList.add(localSQLState == null ? null : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSQLState));
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());



        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {

            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static SQLException parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
                SQLException object =
                        new SQLException();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";
                try {

                    while (!reader.isStartElement() && !reader.isEndElement()) {
                        reader.next();
                    }


                    if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");
                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;
                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
                            }
                            nsPrefix = nsPrefix == null ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

                            if (!"SQLException".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (SQLException) ExtensionMapper.getTypeObject(
                                        nsUri, type, reader);
                            }


                        }


                    }




                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();




                    reader.next();


                    while (!reader.isStartElement() && !reader.isEndElement()) {
                        reader.next();
                    }

                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://soa/xsd", "Exception").equals(reader.getName())) {

                        object.setException(org.apache.axis2.databinding.utils.ConverterUtil.getAnyTypeObject(reader,
                                ExtensionMapper.class));

                        reader.next();

                    } // End of if for expected property start element
                    else {
                    }


                    while (!reader.isStartElement() && !reader.isEndElement()) {
                        reader.next();
                    }

                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://sql.java/xsd", "errorCode").equals(reader.getName())) {

                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                        if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
                            throw new org.apache.axis2.databinding.ADBException("The element: " + "errorCode" + "  cannot be null");
                        }


                        java.lang.String content = reader.getElementText();

                        object.setErrorCode(
                                org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));

                        reader.next();

                    } // End of if for expected property start element
                    else {

                        object.setErrorCode(java.lang.Integer.MIN_VALUE);

                    }


                    while (!reader.isStartElement() && !reader.isEndElement()) {
                        reader.next();
                    }

                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://sql.java/xsd", "nextException").equals(reader.getName())) {

                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                        if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
                            object.setNextException(null);
                            reader.next();

                            reader.next();

                        } else {

                            object.setNextException(SQLException.Factory.parse(reader));

                            reader.next();
                        }
                    } // End of if for expected property start element
                    else {
                    }


                    while (!reader.isStartElement() && !reader.isEndElement()) {
                        reader.next();
                    }

                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://sql.java/xsd", "sQLState").equals(reader.getName())) {

                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {


                            java.lang.String content = reader.getElementText();

                            object.setSQLState(
                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

                        } else {


                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();

                    } // End of if for expected property start element
                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement()) {
                        reader.next();
                    }

                    if (reader.isStartElement()) // A start element we are not expecting indicates a trailing invalid property
                    {
                        throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                    }




                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        }//end of factory class
    }

    public static class ErstellEintrag
            implements org.apache.axis2.databinding.ADBBean {

        public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://soa/xsd",
                "erstellEintrag",
                "ns1");
        /**
         * field for Title
         */
        protected java.lang.String localTitle;
        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localTitleTracker = false;

        public boolean isTitleSpecified() {
            return localTitleTracker;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getTitle() {
            return localTitle;
        }

        /**
         * Auto generated setter method
         * @param param Title
         */
        public void setTitle(java.lang.String param) {
            localTitleTracker = true;

            this.localTitle = param;


        }
        /**
         * field for Content
         */
        protected java.lang.String localContent;
        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localContentTracker = false;

        public boolean isContentSpecified() {
            return localContentTracker;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getContent() {
            return localContent;
        }

        /**
         * Auto generated setter method
         * @param param Content
         */
        public void setContent(java.lang.String param) {
            localContentTracker = true;

            this.localContent = param;


        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
                final javax.xml.namespace.QName parentQName,
                final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {



            org.apache.axiom.om.OMDataSource dataSource =
                    new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME);
            return factory.createOMElement(dataSource, MY_QNAME);

        }

        public void serialize(final javax.xml.namespace.QName parentQName,
                javax.xml.stream.XMLStreamWriter xmlWriter)
                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
            serialize(parentQName, xmlWriter, false);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
                javax.xml.stream.XMLStreamWriter xmlWriter,
                boolean serializeType)
                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {




            java.lang.String prefix = null;
            java.lang.String namespace = null;


            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();
            writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

            if (serializeType) {


                java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://soa/xsd");
                if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
                    writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
                            namespacePrefix + ":erstellEintrag",
                            xmlWriter);
                } else {
                    writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
                            "erstellEintrag",
                            xmlWriter);
                }


            }
            if (localTitleTracker) {
                namespace = "http://soa/xsd";
                writeStartElement(null, namespace, "title", xmlWriter);


                if (localTitle == null) {
                    // write the nil attribute

                    writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

                } else {


                    xmlWriter.writeCharacters(localTitle);

                }

                xmlWriter.writeEndElement();
            }
            if (localContentTracker) {
                namespace = "http://soa/xsd";
                writeStartElement(null, namespace, "content", xmlWriter);


                if (localContent == null) {
                    // write the nil attribute

                    writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

                } else {


                    xmlWriter.writeCharacters(localContent);

                }

                xmlWriter.writeEndElement();
            }
            xmlWriter.writeEndElement();


        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if (namespace.equals("http://soa/xsd")) {
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
                java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace, java.lang.String attName,
                java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }
            java.lang.String attributeValue;
            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                throws org.apache.axis2.databinding.ADBException {



            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            if (localTitleTracker) {
                elementList.add(new javax.xml.namespace.QName("http://soa/xsd",
                        "title"));

                elementList.add(localTitle == null ? null : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTitle));
            }
            if (localContentTracker) {
                elementList.add(new javax.xml.namespace.QName("http://soa/xsd",
                        "content"));

                elementList.add(localContent == null ? null : org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localContent));
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());



        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {

            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static ErstellEintrag parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
                ErstellEintrag object =
                        new ErstellEintrag();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";
                try {

                    while (!reader.isStartElement() && !reader.isEndElement()) {
                        reader.next();
                    }


                    if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");
                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;
                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
                            }
                            nsPrefix = nsPrefix == null ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

                            if (!"erstellEintrag".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (ErstellEintrag) ExtensionMapper.getTypeObject(
                                        nsUri, type, reader);
                            }


                        }


                    }




                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();




                    reader.next();


                    while (!reader.isStartElement() && !reader.isEndElement()) {
                        reader.next();
                    }

                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://soa/xsd", "title").equals(reader.getName())) {

                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {


                            java.lang.String content = reader.getElementText();

                            object.setTitle(
                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

                        } else {


                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();

                    } // End of if for expected property start element
                    else {
                    }


                    while (!reader.isStartElement() && !reader.isEndElement()) {
                        reader.next();
                    }

                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://soa/xsd", "content").equals(reader.getName())) {

                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                        if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {


                            java.lang.String content = reader.getElementText();

                            object.setContent(
                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

                        } else {


                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();

                    } // End of if for expected property start element
                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement()) {
                        reader.next();
                    }

                    if (reader.isStartElement()) // A start element we are not expecting indicates a trailing invalid property
                    {
                        throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                    }




                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        }//end of factory class
    }

    public static class ExtensionMapper {

        public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                java.lang.String typeName,
                javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {


            if ("http://sql.java/xsd".equals(namespaceURI) &&
                    "SQLException".equals(typeName)) {

                return SQLException.Factory.parse(reader);


            }


            if ("http://soa/xsd".equals(namespaceURI) &&
                    "Exception".equals(typeName)) {

                return Exception.Factory.parse(reader);


            }


            throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
        }
    }

    public static class ExceptionE
            implements org.apache.axis2.databinding.ADBBean {

        public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://soa/xsd",
                "Exception",
                "ns1");
        /**
         * field for Exception
         */
        protected Exception localException;
        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localExceptionTracker = false;

        public boolean isExceptionSpecified() {
            return localExceptionTracker;
        }

        /**
         * Auto generated getter method
         * @return Exception
         */
        public Exception getException() {
            return localException;
        }

        /**
         * Auto generated setter method
         * @param param Exception
         */
        public void setException(Exception param) {
            localExceptionTracker = true;

            this.localException = param;


        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
                final javax.xml.namespace.QName parentQName,
                final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {



            org.apache.axiom.om.OMDataSource dataSource =
                    new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME);
            return factory.createOMElement(dataSource, MY_QNAME);

        }

        public void serialize(final javax.xml.namespace.QName parentQName,
                javax.xml.stream.XMLStreamWriter xmlWriter)
                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
            serialize(parentQName, xmlWriter, false);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
                javax.xml.stream.XMLStreamWriter xmlWriter,
                boolean serializeType)
                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {




            java.lang.String prefix = null;
            java.lang.String namespace = null;


            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();
            writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

            if (serializeType) {


                java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://soa/xsd");
                if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
                    writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
                            namespacePrefix + ":Exception",
                            xmlWriter);
                } else {
                    writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
                            "Exception",
                            xmlWriter);
                }


            }
            if (localExceptionTracker) {
                if (localException == null) {

                    writeStartElement(null, "http://soa/xsd", "Exception", xmlWriter);

                    // write the nil attribute
                    writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
                    xmlWriter.writeEndElement();
                } else {
                    localException.serialize(new javax.xml.namespace.QName("http://soa/xsd", "Exception"),
                            xmlWriter);
                }
            }
            xmlWriter.writeEndElement();


        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if (namespace.equals("http://soa/xsd")) {
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
                java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace, java.lang.String attName,
                java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }
            java.lang.String attributeValue;
            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                throws org.apache.axis2.databinding.ADBException {



            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            if (localExceptionTracker) {
                elementList.add(new javax.xml.namespace.QName("http://soa/xsd",
                        "Exception"));


                elementList.add(localException == null ? null : localException);
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());



        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {

            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static ExceptionE parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
                ExceptionE object =
                        new ExceptionE();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";
                try {

                    while (!reader.isStartElement() && !reader.isEndElement()) {
                        reader.next();
                    }


                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                    if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
                        // Skip the element and report the null value.  It cannot have subelements.
                        while (!reader.isEndElement()) {
                            reader.next();
                        }

                        return null;


                    }

                    if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");
                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;
                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
                            }
                            nsPrefix = nsPrefix == null ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

                            if (!"Exception".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (ExceptionE) ExtensionMapper.getTypeObject(
                                        nsUri, type, reader);
                            }


                        }


                    }




                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();




                    reader.next();


                    while (!reader.isStartElement() && !reader.isEndElement()) {
                        reader.next();
                    }

                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://soa/xsd", "Exception").equals(reader.getName())) {

                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                        if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
                            object.setException(null);
                            reader.next();

                            reader.next();

                        } else {

                            object.setException(Exception.Factory.parse(reader));

                            reader.next();
                        }
                    } // End of if for expected property start element
                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement()) {
                        reader.next();
                    }

                    if (reader.isStartElement()) // A start element we are not expecting indicates a trailing invalid property
                    {
                        throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                    }




                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        }//end of factory class
    }

    public static class SQLExceptionE
            implements org.apache.axis2.databinding.ADBBean {

        public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://soa/xsd",
                "SQLException",
                "ns1");
        /**
         * field for SQLException
         */
        protected SQLException localSQLException;
        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localSQLExceptionTracker = false;

        public boolean isSQLExceptionSpecified() {
            return localSQLExceptionTracker;
        }

        /**
         * Auto generated getter method
         * @return SQLException
         */
        public SQLException getSQLException() {
            return localSQLException;
        }

        /**
         * Auto generated setter method
         * @param param SQLException
         */
        public void setSQLException(SQLException param) {
            localSQLExceptionTracker = true;

            this.localSQLException = param;


        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
                final javax.xml.namespace.QName parentQName,
                final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {



            org.apache.axiom.om.OMDataSource dataSource =
                    new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME);
            return factory.createOMElement(dataSource, MY_QNAME);

        }

        public void serialize(final javax.xml.namespace.QName parentQName,
                javax.xml.stream.XMLStreamWriter xmlWriter)
                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
            serialize(parentQName, xmlWriter, false);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
                javax.xml.stream.XMLStreamWriter xmlWriter,
                boolean serializeType)
                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {




            java.lang.String prefix = null;
            java.lang.String namespace = null;


            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();
            writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

            if (serializeType) {


                java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://soa/xsd");
                if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
                    writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
                            namespacePrefix + ":SQLException",
                            xmlWriter);
                } else {
                    writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
                            "SQLException",
                            xmlWriter);
                }


            }
            if (localSQLExceptionTracker) {
                if (localSQLException == null) {

                    writeStartElement(null, "http://soa/xsd", "SQLException", xmlWriter);

                    // write the nil attribute
                    writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
                    xmlWriter.writeEndElement();
                } else {
                    localSQLException.serialize(new javax.xml.namespace.QName("http://soa/xsd", "SQLException"),
                            xmlWriter);
                }
            }
            xmlWriter.writeEndElement();


        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if (namespace.equals("http://soa/xsd")) {
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
                java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace, java.lang.String attName,
                java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }
            java.lang.String attributeValue;
            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                throws org.apache.axis2.databinding.ADBException {



            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            if (localSQLExceptionTracker) {
                elementList.add(new javax.xml.namespace.QName("http://soa/xsd",
                        "SQLException"));


                elementList.add(localSQLException == null ? null : localSQLException);
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());



        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {

            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static SQLExceptionE parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
                SQLExceptionE object =
                        new SQLExceptionE();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";
                try {

                    while (!reader.isStartElement() && !reader.isEndElement()) {
                        reader.next();
                    }


                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                    if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
                        // Skip the element and report the null value.  It cannot have subelements.
                        while (!reader.isEndElement()) {
                            reader.next();
                        }

                        return null;


                    }

                    if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");
                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;
                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
                            }
                            nsPrefix = nsPrefix == null ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

                            if (!"SQLException".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (SQLExceptionE) ExtensionMapper.getTypeObject(
                                        nsUri, type, reader);
                            }


                        }


                    }




                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();




                    reader.next();


                    while (!reader.isStartElement() && !reader.isEndElement()) {
                        reader.next();
                    }

                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://soa/xsd", "SQLException").equals(reader.getName())) {

                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                        if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
                            object.setSQLException(null);
                            reader.next();

                            reader.next();

                        } else {

                            object.setSQLException(SQLException.Factory.parse(reader));

                            reader.next();
                        }
                    } // End of if for expected property start element
                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement()) {
                        reader.next();
                    }

                    if (reader.isStartElement()) // A start element we are not expecting indicates a trailing invalid property
                    {
                        throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                    }




                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        }//end of factory class
    }

    public static class ErstellEintragResponse
            implements org.apache.axis2.databinding.ADBBean {

        public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://soa/xsd",
                "erstellEintragResponse",
                "ns1");
        /**
         * field for _return
         */
        protected boolean local_return;
        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean local_returnTracker = false;

        public boolean is_returnSpecified() {
            return local_returnTracker;
        }

        /**
         * Auto generated getter method
         * @return boolean
         */
        public boolean get_return() {
            return local_return;
        }

        /**
         * Auto generated setter method
         * @param param _return
         */
        public void set_return(boolean param) {

            // setting primitive attribute tracker to true
            local_returnTracker =
                    true;

            this.local_return = param;


        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
                final javax.xml.namespace.QName parentQName,
                final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {



            org.apache.axiom.om.OMDataSource dataSource =
                    new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME);
            return factory.createOMElement(dataSource, MY_QNAME);

        }

        public void serialize(final javax.xml.namespace.QName parentQName,
                javax.xml.stream.XMLStreamWriter xmlWriter)
                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
            serialize(parentQName, xmlWriter, false);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
                javax.xml.stream.XMLStreamWriter xmlWriter,
                boolean serializeType)
                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {




            java.lang.String prefix = null;
            java.lang.String namespace = null;


            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();
            writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

            if (serializeType) {


                java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://soa/xsd");
                if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
                    writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
                            namespacePrefix + ":erstellEintragResponse",
                            xmlWriter);
                } else {
                    writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
                            "erstellEintragResponse",
                            xmlWriter);
                }


            }
            if (local_returnTracker) {
                namespace = "http://soa/xsd";
                writeStartElement(null, namespace, "return", xmlWriter);

                if (false) {

                    throw new org.apache.axis2.databinding.ADBException("return cannot be null!!");

                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(local_return));
                }

                xmlWriter.writeEndElement();
            }
            xmlWriter.writeEndElement();


        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if (namespace.equals("http://soa/xsd")) {
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
                java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace, java.lang.String attName,
                java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }
            java.lang.String attributeValue;
            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                throws org.apache.axis2.databinding.ADBException {



            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            if (local_returnTracker) {
                elementList.add(new javax.xml.namespace.QName("http://soa/xsd",
                        "return"));

                elementList.add(
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(local_return));
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());



        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {

            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static ErstellEintragResponse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
                ErstellEintragResponse object =
                        new ErstellEintragResponse();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";
                try {

                    while (!reader.isStartElement() && !reader.isEndElement()) {
                        reader.next();
                    }


                    if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");
                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;
                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
                            }
                            nsPrefix = nsPrefix == null ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

                            if (!"erstellEintragResponse".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (ErstellEintragResponse) ExtensionMapper.getTypeObject(
                                        nsUri, type, reader);
                            }


                        }


                    }




                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();




                    reader.next();


                    while (!reader.isStartElement() && !reader.isEndElement()) {
                        reader.next();
                    }

                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://soa/xsd", "return").equals(reader.getName())) {

                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                        if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
                            throw new org.apache.axis2.databinding.ADBException("The element: " + "return" + "  cannot be null");
                        }


                        java.lang.String content = reader.getElementText();

                        object.set_return(
                                org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(content));

                        reader.next();

                    } // End of if for expected property start element
                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement()) {
                        reader.next();
                    }

                    if (reader.isStartElement()) // A start element we are not expecting indicates a trailing invalid property
                    {
                        throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                    }




                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        }//end of factory class
    }

    public static class Exception
            implements org.apache.axis2.databinding.ADBBean {
        /* This type was generated from the piece of schema that had
        name = Exception
        Namespace URI = http://soa/xsd
        Namespace Prefix = ns1
         */

        /**
         * field for Exception
         */
        protected java.lang.Object localException;
        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localExceptionTracker = false;

        public boolean isExceptionSpecified() {
            return localExceptionTracker;
        }

        /**
         * Auto generated getter method
         * @return java.lang.Object
         */
        public java.lang.Object getException() {
            return localException;
        }

        /**
         * Auto generated setter method
         * @param param Exception
         */
        public void setException(java.lang.Object param) {
            localExceptionTracker = true;

            this.localException = param;


        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
                final javax.xml.namespace.QName parentQName,
                final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {



            org.apache.axiom.om.OMDataSource dataSource =
                    new org.apache.axis2.databinding.ADBDataSource(this, parentQName);
            return factory.createOMElement(dataSource, parentQName);

        }

        public void serialize(final javax.xml.namespace.QName parentQName,
                javax.xml.stream.XMLStreamWriter xmlWriter)
                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
            serialize(parentQName, xmlWriter, false);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
                javax.xml.stream.XMLStreamWriter xmlWriter,
                boolean serializeType)
                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {




            java.lang.String prefix = null;
            java.lang.String namespace = null;


            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();
            writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

            if (serializeType) {


                java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://soa/xsd");
                if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
                    writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
                            namespacePrefix + ":Exception",
                            xmlWriter);
                } else {
                    writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
                            "Exception",
                            xmlWriter);
                }


            }
            if (localExceptionTracker) {

                if (localException != null) {
                    if (localException instanceof org.apache.axis2.databinding.ADBBean) {
                        ((org.apache.axis2.databinding.ADBBean) localException).serialize(
                                new javax.xml.namespace.QName("http://soa/xsd", "Exception"),
                                xmlWriter, true);
                    } else {
                        writeStartElement(null, "http://soa/xsd", "Exception", xmlWriter);
                        org.apache.axis2.databinding.utils.ConverterUtil.serializeAnyType(localException, xmlWriter);
                        xmlWriter.writeEndElement();
                    }
                } else {

                    // write null attribute
                    writeStartElement(null, "http://soa/xsd", "Exception", xmlWriter);

                    // write the nil attribute
                    writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
                    xmlWriter.writeEndElement();

                }


            }
            xmlWriter.writeEndElement();


        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if (namespace.equals("http://soa/xsd")) {
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
                java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace, java.lang.String attName,
                java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }
            java.lang.String attributeValue;
            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                throws org.apache.axis2.databinding.ADBException {



            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            if (localExceptionTracker) {
                elementList.add(new javax.xml.namespace.QName("http://soa/xsd",
                        "Exception"));


                elementList.add(localException == null ? null : localException);
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());



        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {

            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static Exception parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
                Exception object =
                        new Exception();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";
                try {

                    while (!reader.isStartElement() && !reader.isEndElement()) {
                        reader.next();
                    }


                    if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");
                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;
                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
                            }
                            nsPrefix = nsPrefix == null ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

                            if (!"Exception".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Exception) ExtensionMapper.getTypeObject(
                                        nsUri, type, reader);
                            }


                        }


                    }




                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();




                    reader.next();


                    while (!reader.isStartElement() && !reader.isEndElement()) {
                        reader.next();
                    }

                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://soa/xsd", "Exception").equals(reader.getName())) {

                        object.setException(org.apache.axis2.databinding.utils.ConverterUtil.getAnyTypeObject(reader,
                                ExtensionMapper.class));

                        reader.next();

                    } // End of if for expected property start element
                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement()) {
                        reader.next();
                    }

                    if (reader.isStartElement()) // A start element we are not expecting indicates a trailing invalid property
                    {
                        throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                    }




                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        }//end of factory class
    }

    private org.apache.axiom.om.OMElement toOM(ErstelleEintragStub.ErstellEintrag param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {


        try {
            return param.getOMElement(ErstelleEintragStub.ErstellEintrag.MY_QNAME,
                    org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }


    }

    private org.apache.axiom.om.OMElement toOM(ErstelleEintragStub.ErstellEintragResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {


        try {
            return param.getOMElement(ErstelleEintragStub.ErstellEintragResponse.MY_QNAME,
                    org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }


    }

    private org.apache.axiom.om.OMElement toOM(ErstelleEintragStub.ExceptionE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {


        try {
            return param.getOMElement(ErstelleEintragStub.ExceptionE.MY_QNAME,
                    org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }


    }

    private org.apache.axiom.om.OMElement toOM(ErstelleEintragStub.SQLExceptionE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {


        try {
            return param.getOMElement(ErstelleEintragStub.SQLExceptionE.MY_QNAME,
                    org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }


    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, ErstelleEintragStub.ErstellEintrag param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
            throws org.apache.axis2.AxisFault {


        try {

            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
            emptyEnvelope.getBody().addChild(param.getOMElement(ErstelleEintragStub.ErstellEintrag.MY_QNAME, factory));
            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }


    }

    /* methods to provide back word compatibility */
    /**
     *  get the default envelope
     */
    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory) {
        return factory.getDefaultEnvelope();
    }

    private java.lang.Object fromOM(
            org.apache.axiom.om.OMElement param,
            java.lang.Class type,
            java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault {

        try {

            if (ErstelleEintragStub.ErstellEintrag.class.equals(type)) {

                return ErstelleEintragStub.ErstellEintrag.Factory.parse(param.getXMLStreamReaderWithoutCaching());


            }

            if (ErstelleEintragStub.ErstellEintragResponse.class.equals(type)) {

                return ErstelleEintragStub.ErstellEintragResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());


            }

            if (ErstelleEintragStub.ExceptionE.class.equals(type)) {

                return ErstelleEintragStub.ExceptionE.Factory.parse(param.getXMLStreamReaderWithoutCaching());


            }

            if (ErstelleEintragStub.SQLExceptionE.class.equals(type)) {

                return ErstelleEintragStub.SQLExceptionE.Factory.parse(param.getXMLStreamReaderWithoutCaching());


            }

        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
        return null;
    }
}
   