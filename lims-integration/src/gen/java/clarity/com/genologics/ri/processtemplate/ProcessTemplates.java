//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.18 at 10:21:26 AM PDT 
//


package com.genologics.ri.processtemplate;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.genologics.ri.Page;


/**
 * 
 *         The representation of a list of Process Template links.
 * <p>The system enforces a maximum number of elements when generating the list of links. When the size of
 * the request result set is larger than the system maximum, the list represents a paged view of the overall
 * results, and the previous-page and next-page elements provide URIs linking to the previous or next page
 * of links in the overall results.
 * </p>
 *       
 * 
 * <p>Java class for process-templates complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="process-templates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="process-template" type="{http://genologics.com/ri/processtemplate}process-template-link" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="previous-page" type="{http://genologics.com/ri}page" minOccurs="0"/>
 *         &lt;element name="next-page" type="{http://genologics.com/ri}page" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "process-templates", propOrder = {
    "processTemplate",
    "previousPage",
    "nextPage"
})
public class ProcessTemplates {

    @XmlElement(name = "process-template")
    protected List<ProcessTemplateLink> processTemplate;
    @XmlElement(name = "previous-page")
    protected Page previousPage;
    @XmlElement(name = "next-page")
    protected Page nextPage;

    /**
     * Gets the value of the processTemplate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the processTemplate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProcessTemplate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProcessTemplateLink }
     * 
     * 
     */
    public List<ProcessTemplateLink> getProcessTemplate() {
        if (processTemplate == null) {
            processTemplate = new ArrayList<ProcessTemplateLink>();
        }
        return this.processTemplate;
    }

    /**
     * Gets the value of the previousPage property.
     * 
     * @return
     *     possible object is
     *     {@link Page }
     *     
     */
    public Page getPreviousPage() {
        return previousPage;
    }

    /**
     * Sets the value of the previousPage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Page }
     *     
     */
    public void setPreviousPage(Page value) {
        this.previousPage = value;
    }

    /**
     * Gets the value of the nextPage property.
     * 
     * @return
     *     possible object is
     *     {@link Page }
     *     
     */
    public Page getNextPage() {
        return nextPage;
    }

    /**
     * Sets the value of the nextPage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Page }
     *     
     */
    public void setNextPage(Page value) {
        this.nextPage = value;
    }

}
