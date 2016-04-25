//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.18 at 10:21:26 AM PDT 
//


package com.genologics.ri.configuration;

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
 *         The detailed representation of the configuration of a user-defined field.
 *       
 * 
 * <p>Java class for field complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="field">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attach-to-name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="precision" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type-definition" type="{http://genologics.com/ri/configuration}type-definition" minOccurs="0"/>
 *         &lt;element name="show-in-lablink" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="allow-non-preset-values" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="first-preset-is-default-value" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="show-in-tables" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is-editable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is-deviation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is-controlled-vocabulary" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="parent-uri" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="child-uri" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="preset" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="min-value" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="max-value" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="is-required" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="attach-to-category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" type="{http://genologics.com/ri/configuration}field-type" />
 *       &lt;attribute name="uri" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "field", propOrder = {
    "name",
    "attachToName",
    "precision",
    "unit",
    "typeDefinition",
    "showInLablink",
    "allowNonPresetValues",
    "firstPresetIsDefaultValue",
    "showInTables",
    "isEditable",
    "isDeviation",
    "isControlledVocabulary",
    "parentUri",
    "childUri",
    "preset",
    "minValue",
    "maxValue",
    "isRequired",
    "attachToCategory"
})
public class Field {

    protected String name;
    @XmlElement(name = "attach-to-name")
    protected String attachToName;
    protected Integer precision;
    protected String unit;
    @XmlElement(name = "type-definition")
    protected TypeDefinition typeDefinition;
    @XmlElement(name = "show-in-lablink")
    protected Boolean showInLablink;
    @XmlElement(name = "allow-non-preset-values")
    protected Boolean allowNonPresetValues;
    @XmlElement(name = "first-preset-is-default-value")
    protected Boolean firstPresetIsDefaultValue;
    @XmlElement(name = "show-in-tables")
    protected Boolean showInTables;
    @XmlElement(name = "is-editable")
    protected Boolean isEditable;
    @XmlElement(name = "is-deviation")
    protected Boolean isDeviation;
    @XmlElement(name = "is-controlled-vocabulary")
    protected Boolean isControlledVocabulary;
    @XmlElement(name = "parent-uri")
    @XmlSchemaType(name = "anyURI")
    protected String parentUri;
    @XmlElement(name = "child-uri")
    protected List<String> childUri;
    protected List<String> preset;
    @XmlElement(name = "min-value")
    protected Double minValue;
    @XmlElement(name = "max-value")
    protected Double maxValue;
    @XmlElement(name = "is-required")
    protected Boolean isRequired;
    @XmlElement(name = "attach-to-category")
    protected String attachToCategory;
    @XmlAttribute(name = "type")
    protected FieldType type;
    @XmlAttribute(name = "uri")
    @XmlSchemaType(name = "anyURI")
    protected String uri;

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
     * Gets the value of the attachToName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttachToName() {
        return attachToName;
    }

    /**
     * Sets the value of the attachToName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttachToName(String value) {
        this.attachToName = value;
    }

    /**
     * Gets the value of the precision property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPrecision() {
        return precision;
    }

    /**
     * Sets the value of the precision property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPrecision(Integer value) {
        this.precision = value;
    }

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnit(String value) {
        this.unit = value;
    }

    /**
     * Gets the value of the typeDefinition property.
     * 
     * @return
     *     possible object is
     *     {@link TypeDefinition }
     *     
     */
    public TypeDefinition getTypeDefinition() {
        return typeDefinition;
    }

    /**
     * Sets the value of the typeDefinition property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeDefinition }
     *     
     */
    public void setTypeDefinition(TypeDefinition value) {
        this.typeDefinition = value;
    }

    /**
     * Gets the value of the showInLablink property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowInLablink() {
        return showInLablink;
    }

    /**
     * Sets the value of the showInLablink property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowInLablink(Boolean value) {
        this.showInLablink = value;
    }

    /**
     * Gets the value of the allowNonPresetValues property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowNonPresetValues() {
        return allowNonPresetValues;
    }

    /**
     * Sets the value of the allowNonPresetValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowNonPresetValues(Boolean value) {
        this.allowNonPresetValues = value;
    }

    /**
     * Gets the value of the firstPresetIsDefaultValue property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFirstPresetIsDefaultValue() {
        return firstPresetIsDefaultValue;
    }

    /**
     * Sets the value of the firstPresetIsDefaultValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFirstPresetIsDefaultValue(Boolean value) {
        this.firstPresetIsDefaultValue = value;
    }

    /**
     * Gets the value of the showInTables property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowInTables() {
        return showInTables;
    }

    /**
     * Sets the value of the showInTables property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowInTables(Boolean value) {
        this.showInTables = value;
    }

    /**
     * Gets the value of the isEditable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsEditable() {
        return isEditable;
    }

    /**
     * Sets the value of the isEditable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsEditable(Boolean value) {
        this.isEditable = value;
    }

    /**
     * Gets the value of the isDeviation property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsDeviation() {
        return isDeviation;
    }

    /**
     * Sets the value of the isDeviation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsDeviation(Boolean value) {
        this.isDeviation = value;
    }

    /**
     * Gets the value of the isControlledVocabulary property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsControlledVocabulary() {
        return isControlledVocabulary;
    }

    /**
     * Sets the value of the isControlledVocabulary property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsControlledVocabulary(Boolean value) {
        this.isControlledVocabulary = value;
    }

    /**
     * Gets the value of the parentUri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentUri() {
        return parentUri;
    }

    /**
     * Sets the value of the parentUri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentUri(String value) {
        this.parentUri = value;
    }

    /**
     * Gets the value of the childUri property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the childUri property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChildUri().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getChildUri() {
        if (childUri == null) {
            childUri = new ArrayList<String>();
        }
        return this.childUri;
    }

    /**
     * Gets the value of the preset property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the preset property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPreset().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPreset() {
        if (preset == null) {
            preset = new ArrayList<String>();
        }
        return this.preset;
    }

    /**
     * Gets the value of the minValue property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinValue() {
        return minValue;
    }

    /**
     * Sets the value of the minValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinValue(Double value) {
        this.minValue = value;
    }

    /**
     * Gets the value of the maxValue property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaxValue() {
        return maxValue;
    }

    /**
     * Sets the value of the maxValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaxValue(Double value) {
        this.maxValue = value;
    }

    /**
     * Gets the value of the isRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsRequired() {
        return isRequired;
    }

    /**
     * Sets the value of the isRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsRequired(Boolean value) {
        this.isRequired = value;
    }

    /**
     * Gets the value of the attachToCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttachToCategory() {
        return attachToCategory;
    }

    /**
     * Sets the value of the attachToCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttachToCategory(String value) {
        this.attachToCategory = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link FieldType }
     *     
     */
    public FieldType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldType }
     *     
     */
    public void setType(FieldType value) {
        this.type = value;
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

}