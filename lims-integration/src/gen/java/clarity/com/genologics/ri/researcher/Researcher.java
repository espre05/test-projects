//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.18 at 10:21:26 AM PDT 
//


package com.genologics.ri.researcher;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.genologics.ri.Externalid;
import com.genologics.ri.userdefined.Field;
import com.genologics.ri.userdefined.Type;


/**
 * 
 *         The detailed representation of a researcher.
 *       
 * 
 * <p>Java class for researcher complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="researcher">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="first-name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="last-name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lab" type="{http://genologics.com/ri/researcher}lab" minOccurs="0"/>
 *         &lt;element ref="{http://genologics.com/ri/userdefined}type" minOccurs="0"/>
 *         &lt;element ref="{http://genologics.com/ri/userdefined}field" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://genologics.com/ri}externalid" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="credentials" type="{http://genologics.com/ri/researcher}credentials" minOccurs="0"/>
 *         &lt;element name="initials" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="uri" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "researcher", propOrder = {
    "firstName",
    "lastName",
    "phone",
    "fax",
    "email",
    "lab",
    "type",
    "field",
    "externalid",
    "credentials",
    "initials"
})
public class Researcher {

    @XmlElement(name = "first-name")
    protected String firstName;
    @XmlElement(name = "last-name")
    protected String lastName;
    protected String phone;
    protected String fax;
    protected String email;
    protected Lab lab;
    @XmlElement(namespace = "http://genologics.com/ri/userdefined")
    protected Type type;
    @XmlElement(namespace = "http://genologics.com/ri/userdefined")
    protected List<Field> field;
    @XmlElement(namespace = "http://genologics.com/ri")
    protected List<Externalid> externalid;
    protected Credentials credentials;
    protected String initials;
    @XmlAttribute(name = "uri")
    @XmlSchemaType(name = "anyURI")
    protected String uri;

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    /**
     * Gets the value of the fax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFax() {
        return fax;
    }

    /**
     * Sets the value of the fax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFax(String value) {
        this.fax = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the lab property.
     * 
     * @return
     *     possible object is
     *     {@link Lab }
     *     
     */
    public Lab getLab() {
        return lab;
    }

    /**
     * Sets the value of the lab property.
     * 
     * @param value
     *     allowed object is
     *     {@link Lab }
     *     
     */
    public void setLab(Lab value) {
        this.lab = value;
    }

    /**
     * 
     *             The User-Defined Type that is associated with the researcher.
     * <br/>Always returns with GET: No
     * <br/>Creatable with POST: Yes
     * <br/>Required for POST: No, unless the UDT has been configured as required.
     * <br/>Updatable with PUT: Yes
     * <br/>Required for PUT: No, unless the UDT has been configured as required. If a current UDT is not provided, existing values are deleted.
     *           
     * 
     * @return
     *     possible object is
     *     {@link Type }
     *     
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link Type }
     *     
     */
    public void setType(Type value) {
        this.type = value;
    }

    /**
     * 
     *             A User-Defined Field that is associated with the researcher.
     * This element is repeated for each UDF associated with the researcher.
     * <br/>Always returns with GET: No
     * <br/>Creatable with POST: Yes
     * <br/>Required for POST: No, unless the UDF has been configured as required.
     * <br/>Updatable with PUT: Yes
     * <br/>Required for PUT: No, unless the UDF has been configured as required. If a current UDF is not provided, existing values are deleted.
     *           Gets the value of the field property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the field property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getField().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Field }
     * 
     * 
     */
    public List<Field> getField() {
        if (field == null) {
            field = new ArrayList<Field>();
        }
        return this.field;
    }

    /**
     * 
     *             An identifier that allows an external system to retrieve information about the researcher.
     * <br/>Always returns with GET: No
     * <br/>Creatable with POST: Yes
     * <br/>Required for POST: No
     * <br/>Updatable with PUT: Yes
     * <br/>Required for PUT: No
     *           Gets the value of the externalid property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the externalid property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExternalid().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Externalid }
     * 
     * 
     */
    public List<Externalid> getExternalid() {
        if (externalid == null) {
            externalid = new ArrayList<Externalid>();
        }
        return this.externalid;
    }

    /**
     * Gets the value of the credentials property.
     * 
     * @return
     *     possible object is
     *     {@link Credentials }
     *     
     */
    public Credentials getCredentials() {
        return credentials;
    }

    /**
     * Sets the value of the credentials property.
     * 
     * @param value
     *     allowed object is
     *     {@link Credentials }
     *     
     */
    public void setCredentials(Credentials value) {
        this.credentials = value;
    }

    /**
     * Gets the value of the initials property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitials() {
        return initials;
    }

    /**
     * Sets the value of the initials property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitials(String value) {
        this.initials = value;
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

    @Override
    public String toString() {
      final int maxLen = 10;
      StringBuilder builder = new StringBuilder();
      builder.append("Researcher [firstName=");
      builder.append(firstName);
      builder.append(", lastName=");
      builder.append(lastName);
      builder.append(", phone=");
      builder.append(phone);
      builder.append(", fax=");
      builder.append(fax);
      builder.append(", email=");
      builder.append(email);
      builder.append(", lab=");
      builder.append(lab);
      builder.append(", type=");
      builder.append(type);
      builder.append(", field=");
      builder.append(field != null ? field.subList(0, Math.min(field.size(), maxLen)) : null);
      builder.append(", externalid=");
      builder.append(externalid != null ? externalid.subList(0, Math.min(externalid.size(), maxLen)) : null);
      builder.append(", credentials=");
      builder.append(credentials);
      builder.append(", initials=");
      builder.append(initials);
      builder.append(", uri=");
      builder.append(uri);
      builder.append("]");
      return builder.toString();
    }

}