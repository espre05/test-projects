//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.18 at 10:21:26 AM PDT 
//


package com.genologics.ri.stepconfiguration;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         <p>Detailed representation of a Step</p>
 * <p>Steps are the elements that compose
 * protocols. They have various properties
 * regarding different UDFs contained on each view of the step
 * as well as configuration option and filters</p>
 *       
 * 
 * <p>Java class for step complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="step">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="protocol-step-index" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="process-type" type="{http://genologics.com/ri/stepconfiguration}process-type" minOccurs="0"/>
 *         &lt;element name="permitted-containers" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="container-type" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="permitted-reagent-categories" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="reagent-category" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="permitted-control-types" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="control-type" type="{http://genologics.com/ri/stepconfiguration}control-type-link" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="transitions" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="transition" type="{http://genologics.com/ri/stepconfiguration}next-step" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="queue-fields" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="queue-field" type="{http://genologics.com/ri/stepconfiguration}queuefield" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="step-fields" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="step-field" type="{http://genologics.com/ri/stepconfiguration}field" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="sample-fields" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="sample-field" type="{http://genologics.com/ri/stepconfiguration}field" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="step-properties" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="step-property" type="{http://genologics.com/ri/stepconfiguration}step-property" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="step-setup" type="{http://genologics.com/ri/stepconfiguration}step-setup" minOccurs="0"/>
 *         &lt;element name="epp-triggers" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="epp-trigger" type="{http://genologics.com/ri/stepconfiguration}epp-trigger" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="uri" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="protocol-uri" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "step", propOrder = {
    "protocolStepIndex",
    "processType",
    "permittedContainers",
    "permittedReagentCategories",
    "permittedControlTypes",
    "transitions",
    "queueFields",
    "stepFields",
    "sampleFields",
    "stepProperties",
    "stepSetup",
    "eppTriggers"
})
public class Step {

    @XmlElement(name = "protocol-step-index")
    protected Integer protocolStepIndex;
    @XmlElement(name = "process-type")
    protected ProcessType processType;
    @XmlElement(name = "permitted-containers")
    protected Step.PermittedContainers permittedContainers;
    @XmlElement(name = "permitted-reagent-categories")
    protected Step.PermittedReagentCategories permittedReagentCategories;
    @XmlElement(name = "permitted-control-types")
    protected Step.PermittedControlTypes permittedControlTypes;
    protected Step.Transitions transitions;
    @XmlElement(name = "queue-fields")
    protected Step.QueueFields queueFields;
    @XmlElement(name = "step-fields")
    protected Step.StepFields stepFields;
    @XmlElement(name = "sample-fields")
    protected Step.SampleFields sampleFields;
    @XmlElement(name = "step-properties")
    protected Step.StepProperties stepProperties;
    @XmlElement(name = "step-setup")
    protected StepSetup stepSetup;
    @XmlElement(name = "epp-triggers")
    protected Step.EppTriggers eppTriggers;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "uri")
    @XmlSchemaType(name = "anyURI")
    protected String uri;
    @XmlAttribute(name = "protocol-uri")
    @XmlSchemaType(name = "anyURI")
    protected String protocolUri;

    /**
     * Gets the value of the protocolStepIndex property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProtocolStepIndex() {
        return protocolStepIndex;
    }

    /**
     * Sets the value of the protocolStepIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProtocolStepIndex(Integer value) {
        this.protocolStepIndex = value;
    }

    /**
     * Gets the value of the processType property.
     * 
     * @return
     *     possible object is
     *     {@link ProcessType }
     *     
     */
    public ProcessType getProcessType() {
        return processType;
    }

    /**
     * Sets the value of the processType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessType }
     *     
     */
    public void setProcessType(ProcessType value) {
        this.processType = value;
    }

    /**
     * Gets the value of the permittedContainers property.
     * 
     * @return
     *     possible object is
     *     {@link Step.PermittedContainers }
     *     
     */
    public Step.PermittedContainers getPermittedContainers() {
        return permittedContainers;
    }

    /**
     * Sets the value of the permittedContainers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Step.PermittedContainers }
     *     
     */
    public void setPermittedContainers(Step.PermittedContainers value) {
        this.permittedContainers = value;
    }

    /**
     * Gets the value of the permittedReagentCategories property.
     * 
     * @return
     *     possible object is
     *     {@link Step.PermittedReagentCategories }
     *     
     */
    public Step.PermittedReagentCategories getPermittedReagentCategories() {
        return permittedReagentCategories;
    }

    /**
     * Sets the value of the permittedReagentCategories property.
     * 
     * @param value
     *     allowed object is
     *     {@link Step.PermittedReagentCategories }
     *     
     */
    public void setPermittedReagentCategories(Step.PermittedReagentCategories value) {
        this.permittedReagentCategories = value;
    }

    /**
     * Gets the value of the permittedControlTypes property.
     * 
     * @return
     *     possible object is
     *     {@link Step.PermittedControlTypes }
     *     
     */
    public Step.PermittedControlTypes getPermittedControlTypes() {
        return permittedControlTypes;
    }

    /**
     * Sets the value of the permittedControlTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Step.PermittedControlTypes }
     *     
     */
    public void setPermittedControlTypes(Step.PermittedControlTypes value) {
        this.permittedControlTypes = value;
    }

    /**
     * Gets the value of the transitions property.
     * 
     * @return
     *     possible object is
     *     {@link Step.Transitions }
     *     
     */
    public Step.Transitions getTransitions() {
        return transitions;
    }

    /**
     * Sets the value of the transitions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Step.Transitions }
     *     
     */
    public void setTransitions(Step.Transitions value) {
        this.transitions = value;
    }

    /**
     * Gets the value of the queueFields property.
     * 
     * @return
     *     possible object is
     *     {@link Step.QueueFields }
     *     
     */
    public Step.QueueFields getQueueFields() {
        return queueFields;
    }

    /**
     * Sets the value of the queueFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link Step.QueueFields }
     *     
     */
    public void setQueueFields(Step.QueueFields value) {
        this.queueFields = value;
    }

    /**
     * Gets the value of the stepFields property.
     * 
     * @return
     *     possible object is
     *     {@link Step.StepFields }
     *     
     */
    public Step.StepFields getStepFields() {
        return stepFields;
    }

    /**
     * Sets the value of the stepFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link Step.StepFields }
     *     
     */
    public void setStepFields(Step.StepFields value) {
        this.stepFields = value;
    }

    /**
     * Gets the value of the sampleFields property.
     * 
     * @return
     *     possible object is
     *     {@link Step.SampleFields }
     *     
     */
    public Step.SampleFields getSampleFields() {
        return sampleFields;
    }

    /**
     * Sets the value of the sampleFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link Step.SampleFields }
     *     
     */
    public void setSampleFields(Step.SampleFields value) {
        this.sampleFields = value;
    }

    /**
     * Gets the value of the stepProperties property.
     * 
     * @return
     *     possible object is
     *     {@link Step.StepProperties }
     *     
     */
    public Step.StepProperties getStepProperties() {
        return stepProperties;
    }

    /**
     * Sets the value of the stepProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link Step.StepProperties }
     *     
     */
    public void setStepProperties(Step.StepProperties value) {
        this.stepProperties = value;
    }

    /**
     * Gets the value of the stepSetup property.
     * 
     * @return
     *     possible object is
     *     {@link StepSetup }
     *     
     */
    public StepSetup getStepSetup() {
        return stepSetup;
    }

    /**
     * Sets the value of the stepSetup property.
     * 
     * @param value
     *     allowed object is
     *     {@link StepSetup }
     *     
     */
    public void setStepSetup(StepSetup value) {
        this.stepSetup = value;
    }

    /**
     * Gets the value of the eppTriggers property.
     * 
     * @return
     *     possible object is
     *     {@link Step.EppTriggers }
     *     
     */
    public Step.EppTriggers getEppTriggers() {
        return eppTriggers;
    }

    /**
     * Sets the value of the eppTriggers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Step.EppTriggers }
     *     
     */
    public void setEppTriggers(Step.EppTriggers value) {
        this.eppTriggers = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the uri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUri() {
        return uri;
    }

    /**
     * Sets the value of the uri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUri(String value) {
        this.uri = value;
    }

    /**
     * Gets the value of the protocolUri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocolUri() {
        return protocolUri;
    }

    /**
     * Sets the value of the protocolUri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocolUri(String value) {
        this.protocolUri = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="epp-trigger" type="{http://genologics.com/ri/stepconfiguration}epp-trigger" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "eppTrigger"
    })
    public static class EppTriggers {

        @XmlElement(name = "epp-trigger")
        protected List<EppTrigger> eppTrigger;

        /**
         * Gets the value of the eppTrigger property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the eppTrigger property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEppTrigger().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EppTrigger }
         * 
         * 
         */
        public List<EppTrigger> getEppTrigger() {
            if (eppTrigger == null) {
                eppTrigger = new ArrayList<EppTrigger>();
            }
            return this.eppTrigger;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="container-type" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "containerType"
    })
    public static class PermittedContainers {

        @XmlElement(name = "container-type")
        protected List<String> containerType;

        /**
         * Gets the value of the containerType property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the containerType property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContainerType().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getContainerType() {
            if (containerType == null) {
                containerType = new ArrayList<String>();
            }
            return this.containerType;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="control-type" type="{http://genologics.com/ri/stepconfiguration}control-type-link" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "controlType"
    })
    public static class PermittedControlTypes {

        @XmlElement(name = "control-type")
        protected List<ControlTypeLink> controlType;

        /**
         * Gets the value of the controlType property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the controlType property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getControlType().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ControlTypeLink }
         * 
         * 
         */
        public List<ControlTypeLink> getControlType() {
            if (controlType == null) {
                controlType = new ArrayList<ControlTypeLink>();
            }
            return this.controlType;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="reagent-category" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "reagentCategory"
    })
    public static class PermittedReagentCategories {

        @XmlElement(name = "reagent-category")
        protected List<String> reagentCategory;

        /**
         * Gets the value of the reagentCategory property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the reagentCategory property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getReagentCategory().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getReagentCategory() {
            if (reagentCategory == null) {
                reagentCategory = new ArrayList<String>();
            }
            return this.reagentCategory;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="queue-field" type="{http://genologics.com/ri/stepconfiguration}queuefield" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "queueField"
    })
    public static class QueueFields {

        @XmlElement(name = "queue-field")
        protected List<Queuefield> queueField;

        /**
         * Gets the value of the queueField property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the queueField property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getQueueField().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Queuefield }
         * 
         * 
         */
        public List<Queuefield> getQueueField() {
            if (queueField == null) {
                queueField = new ArrayList<Queuefield>();
            }
            return this.queueField;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="sample-field" type="{http://genologics.com/ri/stepconfiguration}field" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "sampleField"
    })
    public static class SampleFields {

        @XmlElement(name = "sample-field")
        protected List<Field> sampleField;

        /**
         * Gets the value of the sampleField property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the sampleField property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSampleField().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Field }
         * 
         * 
         */
        public List<Field> getSampleField() {
            if (sampleField == null) {
                sampleField = new ArrayList<Field>();
            }
            return this.sampleField;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="step-field" type="{http://genologics.com/ri/stepconfiguration}field" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "stepField"
    })
    public static class StepFields {

        @XmlElement(name = "step-field")
        protected List<Field> stepField;

        /**
         * Gets the value of the stepField property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the stepField property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getStepField().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Field }
         * 
         * 
         */
        public List<Field> getStepField() {
            if (stepField == null) {
                stepField = new ArrayList<Field>();
            }
            return this.stepField;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="step-property" type="{http://genologics.com/ri/stepconfiguration}step-property" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "stepProperty"
    })
    public static class StepProperties {

        @XmlElement(name = "step-property")
        protected List<StepProperty> stepProperty;

        /**
         * Gets the value of the stepProperty property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the stepProperty property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getStepProperty().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link StepProperty }
         * 
         * 
         */
        public List<StepProperty> getStepProperty() {
            if (stepProperty == null) {
                stepProperty = new ArrayList<StepProperty>();
            }
            return this.stepProperty;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="transition" type="{http://genologics.com/ri/stepconfiguration}next-step" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "transition"
    })
    public static class Transitions {

        protected List<NextStep> transition;

        /**
         * Gets the value of the transition property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the transition property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTransition().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link NextStep }
         * 
         * 
         */
        public List<NextStep> getTransition() {
            if (transition == null) {
                transition = new ArrayList<NextStep>();
            }
            return this.transition;
        }

    }

}