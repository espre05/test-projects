//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.18 at 10:21:26 AM PDT 
//


package com.genologics.ri.stage;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.genologics.ri.stage package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Stage_QNAME = new QName("http://genologics.com/ri/stage", "stage");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.genologics.ri.stage
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Stage }
     * 
     */
    public Stage createStage() {
        return new Stage();
    }

    /**
     * Create an instance of {@link Protocol }
     * 
     */
    public Protocol createProtocol() {
        return new Protocol();
    }

    /**
     * Create an instance of {@link Workflow }
     * 
     */
    public Workflow createWorkflow() {
        return new Workflow();
    }

    /**
     * Create an instance of {@link Step }
     * 
     */
    public Step createStep() {
        return new Step();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Stage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://genologics.com/ri/stage", name = "stage")
    public JAXBElement<Stage> createStage(Stage value) {
        return new JAXBElement<Stage>(_Stage_QNAME, Stage.class, null, value);
    }

}