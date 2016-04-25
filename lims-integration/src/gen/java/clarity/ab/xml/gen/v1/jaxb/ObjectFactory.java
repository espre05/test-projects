//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.18 at 11:58:21 AM PDT 
//


package ab.xml.gen.v1.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ab.xml.gen.v1.jaxb package. 
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

    private final static QName _ExperimentTypeAssay_QNAME = new QName("", "assay");
    private final static QName _ExperimentTypeSampleExternalId_QNAME = new QName("", "sample-external-id");
    private final static QName _ExperimentTypeSubmitterId_QNAME = new QName("", "submitter-id");
    private final static QName _ExperimentTypeSampleType_QNAME = new QName("", "sample-type");
    private final static QName _ExperimentTypeCellProcessRoute_QNAME = new QName("", "cell-process-route");
    private final static QName _ExperimentTypeAliquot_QNAME = new QName("", "aliquot");
    private final static QName _ExperimentTypeSubmitterName_QNAME = new QName("", "submitter-name");
    private final static QName _ExperimentTypeSamplePath_QNAME = new QName("", "sample-path");
    private final static QName _ExperimentTypeReceivedMaterial_QNAME = new QName("", "received-material");
    private final static QName _ExperimentTypeProjectGroup_QNAME = new QName("", "project-group");
    private final static QName _ExperimentTypeTissueType_QNAME = new QName("", "tissue-type");
    private final static QName _ExperimentTypePrimerSet_QNAME = new QName("", "primer-set");
    private final static QName _ExperimentTypeDiseaseType_QNAME = new QName("", "disease-type");
    private final static QName _ExperimentTypeCompartment_QNAME = new QName("", "compartment");
    private final static QName _ExperimentTypePatientId_QNAME = new QName("", "patient-id");
    private final static QName _ExperimentTypeSample_QNAME = new QName("", "sample");
    private final static QName _SequentaOrders_QNAME = new QName("", "SequentaOrders");
    private final static QName _Request_QNAME = new QName("", "request");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ab.xml.gen.v1.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SequentaOrdersType }
     * 
     */
    public SequentaOrdersType createSequentaOrdersType() {
        return new SequentaOrdersType();
    }

    /**
     * Create an instance of {@link RequestType }
     * 
     */
    public RequestType createRequestType() {
        return new RequestType();
    }

    /**
     * Create an instance of {@link PrimerType }
     * 
     */
    public PrimerType createPrimerType() {
        return new PrimerType();
    }

    /**
     * Create an instance of {@link RunsType }
     * 
     */
    public RunsType createRunsType() {
        return new RunsType();
    }

    /**
     * Create an instance of {@link ExperimentType }
     * 
     */
    public ExperimentType createExperimentType() {
        return new ExperimentType();
    }

    /**
     * Create an instance of {@link RunType }
     * 
     */
    public RunType createRunType() {
        return new RunType();
    }

    /**
     * Create an instance of {@link PatientType }
     * 
     */
    public PatientType createPatientType() {
        return new PatientType();
    }

    /**
     * Create an instance of {@link ExperimentsType }
     * 
     */
    public ExperimentsType createExperimentsType() {
        return new ExperimentsType();
    }

    /**
     * Create an instance of {@link ClinicalTestType }
     * 
     */
    public ClinicalTestType createClinicalTestType() {
        return new ClinicalTestType();
    }

    /**
     * Create an instance of {@link LaneType }
     * 
     */
    public LaneType createLaneType() {
        return new LaneType();
    }

    /**
     * Create an instance of {@link OrderType }
     * 
     */
    public OrderType createOrderType() {
        return new OrderType();
    }

    /**
     * Create an instance of {@link PrimerSetType }
     * 
     */
    public PrimerSetType createPrimerSetType() {
        return new PrimerSetType();
    }

    /**
     * Create an instance of {@link HoldsType }
     * 
     */
    public HoldsType createHoldsType() {
        return new HoldsType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "assay", scope = ExperimentType.class)
    public JAXBElement<String> createExperimentTypeAssay(String value) {
        return new JAXBElement<String>(_ExperimentTypeAssay_QNAME, String.class, ExperimentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "sample-external-id", scope = ExperimentType.class)
    public JAXBElement<String> createExperimentTypeSampleExternalId(String value) {
        return new JAXBElement<String>(_ExperimentTypeSampleExternalId_QNAME, String.class, ExperimentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "submitter-id", scope = ExperimentType.class)
    public JAXBElement<Short> createExperimentTypeSubmitterId(Short value) {
        return new JAXBElement<Short>(_ExperimentTypeSubmitterId_QNAME, Short.class, ExperimentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "sample-type", scope = ExperimentType.class)
    public JAXBElement<String> createExperimentTypeSampleType(String value) {
        return new JAXBElement<String>(_ExperimentTypeSampleType_QNAME, String.class, ExperimentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "cell-process-route", scope = ExperimentType.class)
    public JAXBElement<String> createExperimentTypeCellProcessRoute(String value) {
        return new JAXBElement<String>(_ExperimentTypeCellProcessRoute_QNAME, String.class, ExperimentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "aliquot", scope = ExperimentType.class)
    public JAXBElement<String> createExperimentTypeAliquot(String value) {
        return new JAXBElement<String>(_ExperimentTypeAliquot_QNAME, String.class, ExperimentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "submitter-name", scope = ExperimentType.class)
    public JAXBElement<String> createExperimentTypeSubmitterName(String value) {
        return new JAXBElement<String>(_ExperimentTypeSubmitterName_QNAME, String.class, ExperimentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "sample-path", scope = ExperimentType.class)
    public JAXBElement<String> createExperimentTypeSamplePath(String value) {
        return new JAXBElement<String>(_ExperimentTypeSamplePath_QNAME, String.class, ExperimentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "received-material", scope = ExperimentType.class)
    public JAXBElement<String> createExperimentTypeReceivedMaterial(String value) {
        return new JAXBElement<String>(_ExperimentTypeReceivedMaterial_QNAME, String.class, ExperimentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "project-group", scope = ExperimentType.class)
    public JAXBElement<String> createExperimentTypeProjectGroup(String value) {
        return new JAXBElement<String>(_ExperimentTypeProjectGroup_QNAME, String.class, ExperimentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "tissue-type", scope = ExperimentType.class)
    public JAXBElement<String> createExperimentTypeTissueType(String value) {
        return new JAXBElement<String>(_ExperimentTypeTissueType_QNAME, String.class, ExperimentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrimerSetType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "primer-set", scope = ExperimentType.class)
    public JAXBElement<PrimerSetType> createExperimentTypePrimerSet(PrimerSetType value) {
        return new JAXBElement<PrimerSetType>(_ExperimentTypePrimerSet_QNAME, PrimerSetType.class, ExperimentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "disease-type", scope = ExperimentType.class)
    public JAXBElement<String> createExperimentTypeDiseaseType(String value) {
        return new JAXBElement<String>(_ExperimentTypeDiseaseType_QNAME, String.class, ExperimentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "compartment", scope = ExperimentType.class)
    public JAXBElement<String> createExperimentTypeCompartment(String value) {
        return new JAXBElement<String>(_ExperimentTypeCompartment_QNAME, String.class, ExperimentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "patient-id", scope = ExperimentType.class)
    public JAXBElement<String> createExperimentTypePatientId(String value) {
        return new JAXBElement<String>(_ExperimentTypePatientId_QNAME, String.class, ExperimentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "sample", scope = ExperimentType.class)
    public JAXBElement<String> createExperimentTypeSample(String value) {
        return new JAXBElement<String>(_ExperimentTypeSample_QNAME, String.class, ExperimentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SequentaOrdersType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "SequentaOrders")
    public JAXBElement<SequentaOrdersType> createSequentaOrders(SequentaOrdersType value) {
        return new JAXBElement<SequentaOrdersType>(_SequentaOrders_QNAME, SequentaOrdersType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "request")
    public JAXBElement<RequestType> createRequest(RequestType value) {
        return new JAXBElement<RequestType>(_Request_QNAME, RequestType.class, null, value);
    }

}
