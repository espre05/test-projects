//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.18 at 11:58:21 AM PDT 
//


package ab.xml.gen.v1.jaxb;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ClinicalTestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ClinicalTestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ClinicalTestCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TestCollectionDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TestCollectionTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Questions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TestOID" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="OrderID" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="TissueType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SpecimenType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TubeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StoredSpecimenRetreivalDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TubeCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="DiseaseType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Receptors" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Compartment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClinicalTestType", propOrder = {
    "clinicalTestCode",
    "testCollectionDate",
    "testCollectionTime",
    "questions",
    "testOID",
    "orderID",
    "tissueType",
    "specimenType",
    "tubeType",
    "storedSpecimenRetreivalDate",
    "tubeCount",
    "diseaseType",
    "receptors",
    "compartment"
})
public class ClinicalTestType {

    @XmlElement(name = "ClinicalTestCode", required = true)
    protected String clinicalTestCode;
    @XmlElement(name = "TestCollectionDate", required = true)
    protected String testCollectionDate;
    @XmlElement(name = "TestCollectionTime")
    protected String testCollectionTime;
    @XmlElement(name = "Questions")
    protected String questions;
    @XmlElement(name = "TestOID")
    protected BigInteger testOID;
    @XmlElement(name = "OrderID")
    protected BigInteger orderID;
    @XmlElement(name = "TissueType")
    protected String tissueType;
    @XmlElement(name = "SpecimenType")
    protected String specimenType;
    @XmlElement(name = "TubeType")
    protected String tubeType;
    @XmlElement(name = "StoredSpecimenRetreivalDate")
    protected String storedSpecimenRetreivalDate;
    @XmlElement(name = "TubeCount")
    protected Integer tubeCount;
    @XmlElement(name = "DiseaseType", required = true)
    protected String diseaseType;
    @XmlElement(name = "Receptors", required = true)
    protected String receptors;
    @XmlElement(name = "Compartment", required = true)
    protected String compartment;

    /**
     * Gets the value of the clinicalTestCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClinicalTestCode() {
        return clinicalTestCode;
    }

    /**
     * Sets the value of the clinicalTestCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClinicalTestCode(String value) {
        this.clinicalTestCode = value;
    }

    /**
     * Gets the value of the testCollectionDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestCollectionDate() {
        return testCollectionDate;
    }

    /**
     * Sets the value of the testCollectionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestCollectionDate(String value) {
        this.testCollectionDate = value;
    }

    /**
     * Gets the value of the testCollectionTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestCollectionTime() {
        return testCollectionTime;
    }

    /**
     * Sets the value of the testCollectionTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestCollectionTime(String value) {
        this.testCollectionTime = value;
    }

    /**
     * Gets the value of the questions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuestions() {
        return questions;
    }

    /**
     * Sets the value of the questions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuestions(String value) {
        this.questions = value;
    }

    /**
     * Gets the value of the testOID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTestOID() {
        return testOID;
    }

    /**
     * Sets the value of the testOID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTestOID(BigInteger value) {
        this.testOID = value;
    }

    /**
     * Gets the value of the orderID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOrderID() {
        return orderID;
    }

    /**
     * Sets the value of the orderID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOrderID(BigInteger value) {
        this.orderID = value;
    }

    /**
     * Gets the value of the tissueType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTissueType() {
        return tissueType;
    }

    /**
     * Sets the value of the tissueType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTissueType(String value) {
        this.tissueType = value;
    }

    /**
     * Gets the value of the specimenType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecimenType() {
        return specimenType;
    }

    /**
     * Sets the value of the specimenType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecimenType(String value) {
        this.specimenType = value;
    }

    /**
     * Gets the value of the tubeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTubeType() {
        return tubeType;
    }

    /**
     * Sets the value of the tubeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTubeType(String value) {
        this.tubeType = value;
    }

    /**
     * Gets the value of the storedSpecimenRetreivalDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoredSpecimenRetreivalDate() {
        return storedSpecimenRetreivalDate;
    }

    /**
     * Sets the value of the storedSpecimenRetreivalDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoredSpecimenRetreivalDate(String value) {
        this.storedSpecimenRetreivalDate = value;
    }

    /**
     * Gets the value of the tubeCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTubeCount() {
        return tubeCount;
    }

    /**
     * Sets the value of the tubeCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTubeCount(Integer value) {
        this.tubeCount = value;
    }

    /**
     * Gets the value of the diseaseType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiseaseType() {
        return diseaseType;
    }

    /**
     * Sets the value of the diseaseType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiseaseType(String value) {
        this.diseaseType = value;
    }

    /**
     * Gets the value of the receptors property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceptors() {
        return receptors;
    }

    /**
     * Sets the value of the receptors property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceptors(String value) {
        this.receptors = value;
    }

    /**
     * Gets the value of the compartment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompartment() {
        return compartment;
    }

    /**
     * Sets the value of the compartment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompartment(String value) {
        this.compartment = value;
    }

}
