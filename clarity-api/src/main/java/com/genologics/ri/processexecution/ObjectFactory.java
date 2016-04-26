//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.18 at 10:21:26 AM PDT 
//


package com.genologics.ri.processexecution;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.genologics.ri.processexecution package. 
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

    private final static QName _Process_QNAME = new QName("http://genologics.com/ri/processexecution", "process");
    private final static QName _ProcessTechnician_QNAME = new QName("", "technician");
    private final static QName _ProcessInstrument_QNAME = new QName("", "instrument");
    private final static QName _ProcessType_QNAME = new QName("", "type");
    private final static QName _ProcessInputOutputMap_QNAME = new QName("", "input-output-map");
    private final static QName _ProcessProcessParameter_QNAME = new QName("", "process-parameter");
    private final static QName _ProcessDateRun_QNAME = new QName("", "date-run");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.genologics.ri.processexecution
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Process }
     * 
     */
    public Process createProcess() {
        return new Process();
    }

    /**
     * Create an instance of {@link Input }
     * 
     */
    public Input createInput() {
        return new Input();
    }

    /**
     * Create an instance of {@link Technician }
     * 
     */
    public Technician createTechnician() {
        return new Technician();
    }

    /**
     * Create an instance of {@link Parameter }
     * 
     */
    public Parameter createParameter() {
        return new Parameter();
    }

    /**
     * Create an instance of {@link Instrument }
     * 
     */
    public Instrument createInstrument() {
        return new Instrument();
    }

    /**
     * Create an instance of {@link Output }
     * 
     */
    public Output createOutput() {
        return new Output();
    }

    /**
     * Create an instance of {@link InputOutputMap }
     * 
     */
    public InputOutputMap createInputOutputMap() {
        return new InputOutputMap();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Process }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://genologics.com/ri/processexecution", name = "process")
    public JAXBElement<Process> createProcess(Process value) {
        return new JAXBElement<Process>(_Process_QNAME, Process.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Technician }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "technician", scope = Process.class)
    public JAXBElement<Technician> createProcessTechnician(Technician value) {
        return new JAXBElement<Technician>(_ProcessTechnician_QNAME, Technician.class, Process.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Instrument }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "instrument", scope = Process.class)
    public JAXBElement<Instrument> createProcessInstrument(Instrument value) {
        return new JAXBElement<Instrument>(_ProcessInstrument_QNAME, Instrument.class, Process.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "type", scope = Process.class)
    public JAXBElement<String> createProcessType(String value) {
        return new JAXBElement<String>(_ProcessType_QNAME, String.class, Process.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InputOutputMap }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "input-output-map", scope = Process.class)
    public JAXBElement<InputOutputMap> createProcessInputOutputMap(InputOutputMap value) {
        return new JAXBElement<InputOutputMap>(_ProcessInputOutputMap_QNAME, InputOutputMap.class, Process.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Parameter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "process-parameter", scope = Process.class)
    public JAXBElement<Parameter> createProcessProcessParameter(Parameter value) {
        return new JAXBElement<Parameter>(_ProcessProcessParameter_QNAME, Parameter.class, Process.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "date-run", scope = Process.class)
    public JAXBElement<String> createProcessDateRun(String value) {
        return new JAXBElement<String>(_ProcessDateRun_QNAME, String.class, Process.class, value);
    }

}
