//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.18 at 10:21:26 AM PDT 
//


package com.genologics.ri.processexecution;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Processes link inputs to outputs and this relationship is called an input-output map.
 * Input-output-map is a child element of the Process element.
 * <p>
 * When a Process creates multiple outputs per input, there is a distinct input-output map for each input to output relationship.
 * When a Process produces a shared output, there is a single input-output map for the shared output and all its related inputs.
 * <p>
 * In situations where a Process is used to affect the properties of inputs only and therefore, does not create outputs, you can omit the output element.
 *       
 * 
 * <p>Java class for input-output-map complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="input-output-map">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="input" type="{http://genologics.com/ri/processexecution}input" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="output" type="{http://genologics.com/ri/processexecution}output" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="shared" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "input-output-map", propOrder = {
    "input",
    "output"
})
public class InputOutputMap {

    protected List<Input> input;
    protected Output output;
    @XmlAttribute(name = "shared")
    protected Boolean shared;

    /**
     * Gets the value of the input property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the input property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInput().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Input }
     * 
     * 
     */
    public List<Input> getInput() {
        if (input == null) {
            input = new ArrayList<Input>();
        }
        return this.input;
    }

    /**
     * Gets the value of the output property.
     * 
     * @return
     *     possible object is
     *     {@link Output }
     *     
     */
    public Output getOutput() {
        return output;
    }

    /**
     * Sets the value of the output property.
     * 
     * @param value
     *     allowed object is
     *     {@link Output }
     *     
     */
    public void setOutput(Output value) {
        this.output = value;
    }

    /**
     * Gets the value of the shared property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShared() {
        return shared;
    }

    /**
     * Sets the value of the shared property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShared(Boolean value) {
        this.shared = value;
    }

}