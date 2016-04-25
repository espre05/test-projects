//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.18 at 10:21:26 AM PDT 
//


package com.genologics.ri.routing;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for extArtifactAssignments complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="extArtifactAssignments">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="artifact" type="{http://genologics.com/ri/routing}artifact" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="workflow-uri" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="stage-uri" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "extArtifactAssignments", propOrder = {
    "artifact"
})
public class ExtArtifactAssignments {

    protected List<Artifact> artifact;
    @XmlAttribute(name = "workflow-uri")
    @XmlSchemaType(name = "anyURI")
    protected String workflowUri;
    @XmlAttribute(name = "stage-uri")
    @XmlSchemaType(name = "anyURI")
    protected String stageUri;

    /**
     * Gets the value of the artifact property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the artifact property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArtifact().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Artifact }
     * 
     * 
     */
    public List<Artifact> getArtifact() {
        if (artifact == null) {
            artifact = new ArrayList<Artifact>();
        }
        return this.artifact;
    }

    /**
     * Gets the value of the workflowUri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkflowUri() {
        return workflowUri;
    }

    /**
     * Sets the value of the workflowUri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkflowUri(String value) {
        this.workflowUri = value;
    }

    /**
     * Gets the value of the stageUri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStageUri() {
        return stageUri;
    }

    /**
     * Sets the value of the stageUri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStageUri(String value) {
        this.stageUri = value;
    }

}
