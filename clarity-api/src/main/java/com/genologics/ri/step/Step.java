//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.18 at 10:21:26 AM PDT 
//


package com.genologics.ri.step;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         The detailed representation of a step.
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
 *         &lt;element name="configuration" type="{http://genologics.com/ri/step}step-configuration" minOccurs="0"/>
 *         &lt;element name="actions" type="{http://genologics.com/ri/step}actions-link" minOccurs="0"/>
 *         &lt;element name="reagents" type="{http://genologics.com/ri/step}reagents-link" minOccurs="0"/>
 *         &lt;element name="pools" type="{http://genologics.com/ri/step}pools-link" minOccurs="0"/>
 *         &lt;element name="placements" type="{http://genologics.com/ri/step}placements-link" minOccurs="0"/>
 *         &lt;element name="program-status" type="{http://genologics.com/ri/step}program-status-link" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="uri" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="limsid" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "step", propOrder = {
    "configuration",
    "actions",
    "reagents",
    "pools",
    "placements",
    "programStatus"
})
public class Step {

    protected StepConfiguration configuration;
    protected ActionsLink actions;
    protected ReagentsLink reagents;
    protected PoolsLink pools;
    protected PlacementsLink placements;
    @XmlElement(name = "program-status")
    protected ProgramStatusLink programStatus;
    @XmlAttribute(name = "uri")
    @XmlSchemaType(name = "anyURI")
    protected String uri;
    @XmlAttribute(name = "limsid")
    protected String limsid;

    /**
     * Gets the value of the configuration property.
     * 
     * @return
     *     possible object is
     *     {@link StepConfiguration }
     *     
     */
    public StepConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * Sets the value of the configuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link StepConfiguration }
     *     
     */
    public void setConfiguration(StepConfiguration value) {
        this.configuration = value;
    }

    /**
     * Gets the value of the actions property.
     * 
     * @return
     *     possible object is
     *     {@link ActionsLink }
     *     
     */
    public ActionsLink getActions() {
        return actions;
    }

    /**
     * Sets the value of the actions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionsLink }
     *     
     */
    public void setActions(ActionsLink value) {
        this.actions = value;
    }

    /**
     * Gets the value of the reagents property.
     * 
     * @return
     *     possible object is
     *     {@link ReagentsLink }
     *     
     */
    public ReagentsLink getReagents() {
        return reagents;
    }

    /**
     * Sets the value of the reagents property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReagentsLink }
     *     
     */
    public void setReagents(ReagentsLink value) {
        this.reagents = value;
    }

    /**
     * Gets the value of the pools property.
     * 
     * @return
     *     possible object is
     *     {@link PoolsLink }
     *     
     */
    public PoolsLink getPools() {
        return pools;
    }

    /**
     * Sets the value of the pools property.
     * 
     * @param value
     *     allowed object is
     *     {@link PoolsLink }
     *     
     */
    public void setPools(PoolsLink value) {
        this.pools = value;
    }

    /**
     * Gets the value of the placements property.
     * 
     * @return
     *     possible object is
     *     {@link PlacementsLink }
     *     
     */
    public PlacementsLink getPlacements() {
        return placements;
    }

    /**
     * Sets the value of the placements property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlacementsLink }
     *     
     */
    public void setPlacements(PlacementsLink value) {
        this.placements = value;
    }

    /**
     * Gets the value of the programStatus property.
     * 
     * @return
     *     possible object is
     *     {@link ProgramStatusLink }
     *     
     */
    public ProgramStatusLink getProgramStatus() {
        return programStatus;
    }

    /**
     * Sets the value of the programStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProgramStatusLink }
     *     
     */
    public void setProgramStatus(ProgramStatusLink value) {
        this.programStatus = value;
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
     * Gets the value of the limsid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLimsid() {
        return limsid;
    }

    /**
     * Sets the value of the limsid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLimsid(String value) {
        this.limsid = value;
    }

}
