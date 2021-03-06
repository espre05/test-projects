/**
 * This class is generated by jOOQ
 */
package gen.db.model.tables.records;


import gen.db.model.tables.StageSforceOrderitem;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class StageSforceOrderitemRecord extends UpdatableRecordImpl<StageSforceOrderitemRecord> {

	private static final long serialVersionUID = 1532565869;

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Id</code>.
	 */
	public void setId(String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Id</code>.
	 */
	public String getId() {
		return (String) getValue(0);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.IsDeleted</code>.
	 */
	public void setIsdeleted(Boolean value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.IsDeleted</code>.
	 */
	public Boolean getIsdeleted() {
		return (Boolean) getValue(1);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.OrderId</code>.
	 */
	public void setOrderid(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.OrderId</code>.
	 */
	public String getOrderid() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.PricebookEntryId</code>.
	 */
	public void setPricebookentryid(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.PricebookEntryId</code>.
	 */
	public String getPricebookentryid() {
		return (String) getValue(3);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.OriginalOrderItemId</code>.
	 */
	public void setOriginalorderitemid(String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.OriginalOrderItemId</code>.
	 */
	public String getOriginalorderitemid() {
		return (String) getValue(4);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.AvailableQuantity</code>.
	 */
	public void setAvailablequantity(Double value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.AvailableQuantity</code>.
	 */
	public Double getAvailablequantity() {
		return (Double) getValue(5);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Quantity</code>.
	 */
	public void setQuantity(Double value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Quantity</code>.
	 */
	public Double getQuantity() {
		return (Double) getValue(6);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.CurrencyIsoCode</code>.
	 */
	public void setCurrencyisocode(String value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.CurrencyIsoCode</code>.
	 */
	public String getCurrencyisocode() {
		return (String) getValue(7);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.UnitPrice</code>.
	 */
	public void setUnitprice(Double value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.UnitPrice</code>.
	 */
	public Double getUnitprice() {
		return (Double) getValue(8);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.ListPrice</code>.
	 */
	public void setListprice(Double value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.ListPrice</code>.
	 */
	public Double getListprice() {
		return (Double) getValue(9);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.ServiceDate</code>.
	 */
	public void setServicedate(Timestamp value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.ServiceDate</code>.
	 */
	public Timestamp getServicedate() {
		return (Timestamp) getValue(10);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Description</code>.
	 */
	public void setDescription(String value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Description</code>.
	 */
	public String getDescription() {
		return (String) getValue(11);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.CreatedDate</code>.
	 */
	public void setCreateddate(Timestamp value) {
		setValue(12, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.CreatedDate</code>.
	 */
	public Timestamp getCreateddate() {
		return (Timestamp) getValue(12);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.CreatedById</code>.
	 */
	public void setCreatedbyid(String value) {
		setValue(13, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.CreatedById</code>.
	 */
	public String getCreatedbyid() {
		return (String) getValue(13);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.LastModifiedDate</code>.
	 */
	public void setLastmodifieddate(Timestamp value) {
		setValue(14, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.LastModifiedDate</code>.
	 */
	public Timestamp getLastmodifieddate() {
		return (Timestamp) getValue(14);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.LastModifiedById</code>.
	 */
	public void setLastmodifiedbyid(String value) {
		setValue(15, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.LastModifiedById</code>.
	 */
	public String getLastmodifiedbyid() {
		return (String) getValue(15);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.SystemModstamp</code>.
	 */
	public void setSystemmodstamp(Timestamp value) {
		setValue(16, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.SystemModstamp</code>.
	 */
	public Timestamp getSystemmodstamp() {
		return (Timestamp) getValue(16);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.OrderItemNumber</code>.
	 */
	public void setOrderitemnumber(String value) {
		setValue(17, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.OrderItemNumber</code>.
	 */
	public String getOrderitemnumber() {
		return (String) getValue(17);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Discount_Amount__c</code>.
	 */
	public void setDiscountAmount_C(Double value) {
		setValue(18, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Discount_Amount__c</code>.
	 */
	public Double getDiscountAmount_C() {
		return (Double) getValue(18);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Discount__c</code>.
	 */
	public void setDiscount_C(Double value) {
		setValue(19, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Discount__c</code>.
	 */
	public Double getDiscount_C() {
		return (Double) getValue(19);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Discounted_Subtotal__c</code>.
	 */
	public void setDiscountedSubtotal_C(Double value) {
		setValue(20, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Discounted_Subtotal__c</code>.
	 */
	public Double getDiscountedSubtotal_C() {
		return (Double) getValue(20);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Sample_Deadline_Date__c</code>.
	 */
	public void setSampleDeadlineDate_C(Timestamp value) {
		setValue(21, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Sample_Deadline_Date__c</code>.
	 */
	public Timestamp getSampleDeadlineDate_C() {
		return (Timestamp) getValue(21);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Order_Number__c</code>.
	 */
	public void setOrderNumber_C(String value) {
		setValue(22, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Order_Number__c</code>.
	 */
	public String getOrderNumber_C() {
		return (String) getValue(22);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Lot__c</code>.
	 */
	public void setLot_C(String value) {
		setValue(23, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Lot__c</code>.
	 */
	public String getLot_C() {
		return (String) getValue(23);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Kit_ID__c</code>.
	 */
	public void setKitId_C(String value) {
		setValue(24, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Kit_ID__c</code>.
	 */
	public String getKitId_C() {
		return (String) getValue(24);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Diagnosis_Clinical_Indication_ICD_9__c</code>.
	 */
	public void setDiagnosisClinicalIndicationIcd_9_C(String value) {
		setValue(25, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Diagnosis_Clinical_Indication_ICD_9__c</code>.
	 */
	public String getDiagnosisClinicalIndicationIcd_9_C() {
		return (String) getValue(25);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Patient_Name__c</code>.
	 */
	public void setPatientName_C(String value) {
		setValue(26, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Patient_Name__c</code>.
	 */
	public String getPatientName_C() {
		return (String) getValue(26);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.DOB__c</code>.
	 */
	public void setDob_C(Timestamp value) {
		setValue(27, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.DOB__c</code>.
	 */
	public Timestamp getDob_C() {
		return (Timestamp) getValue(27);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Sex__c</code>.
	 */
	public void setSex_C(String value) {
		setValue(28, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Sex__c</code>.
	 */
	public String getSex_C() {
		return (String) getValue(28);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Patient_Medical_Record__c</code>.
	 */
	public void setPatientMedicalRecord_C(String value) {
		setValue(29, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Patient_Medical_Record__c</code>.
	 */
	public String getPatientMedicalRecord_C() {
		return (String) getValue(29);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Days_until_report_is_due__c</code>.
	 */
	public void setDaysUntilReportIsDue_C(Double value) {
		setValue(30, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Days_until_report_is_due__c</code>.
	 */
	public Double getDaysUntilReportIsDue_C() {
		return (Double) getValue(30);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Patient_First_Name__c</code>.
	 */
	public void setPatientFirstName_C(String value) {
		setValue(31, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Patient_First_Name__c</code>.
	 */
	public String getPatientFirstName_C() {
		return (String) getValue(31);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Patient_Last_Name__c</code>.
	 */
	public void setPatientLastName_C(String value) {
		setValue(32, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Patient_Last_Name__c</code>.
	 */
	public String getPatientLastName_C() {
		return (String) getValue(32);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Date_Collected__c</code>.
	 */
	public void setDateCollected_C(Timestamp value) {
		setValue(33, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Date_Collected__c</code>.
	 */
	public Timestamp getDateCollected_C() {
		return (Timestamp) getValue(33);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Expiration_Date__c</code>.
	 */
	public void setExpirationDate_C(Timestamp value) {
		setValue(34, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Expiration_Date__c</code>.
	 */
	public Timestamp getExpirationDate_C() {
		return (Timestamp) getValue(34);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.OP_Ship_Detail__c</code>.
	 */
	public void setOpShipDetail_C(String value) {
		setValue(35, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.OP_Ship_Detail__c</code>.
	 */
	public String getOpShipDetail_C() {
		return (String) getValue(35);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Ship_Quantity__c</code>.
	 */
	public void setShipQuantity_C(Double value) {
		setValue(36, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Ship_Quantity__c</code>.
	 */
	public Double getShipQuantity_C() {
		return (Double) getValue(36);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Ship_Date__c</code>.
	 */
	public void setShipDate_C(Timestamp value) {
		setValue(37, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Ship_Date__c</code>.
	 */
	public Timestamp getShipDate_C() {
		return (Timestamp) getValue(37);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Product_Family__c</code>.
	 */
	public void setProductFamily_C(String value) {
		setValue(38, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Product_Family__c</code>.
	 */
	public String getProductFamily_C() {
		return (String) getValue(38);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Order_Name__c</code>.
	 */
	public void setOrderName_C(String value) {
		setValue(39, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Order_Name__c</code>.
	 */
	public String getOrderName_C() {
		return (String) getValue(39);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Total__c</code>.
	 */
	public void setTotal_C(Double value) {
		setValue(40, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Total__c</code>.
	 */
	public Double getTotal_C() {
		return (Double) getValue(40);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Order_Product_Name__c</code>.
	 */
	public void setOrderProductName_C(String value) {
		setValue(41, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Order_Product_Name__c</code>.
	 */
	public String getOrderProductName_C() {
		return (String) getValue(41);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Sales_Tax_Details__c</code>.
	 */
	public void setSalesTaxDetails_C(String value) {
		setValue(42, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Sales_Tax_Details__c</code>.
	 */
	public String getSalesTaxDetails_C() {
		return (String) getValue(42);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Sales_Tax_Amount__c</code>.
	 */
	public void setSalesTaxAmount_C(Double value) {
		setValue(43, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Sales_Tax_Amount__c</code>.
	 */
	public Double getSalesTaxAmount_C() {
		return (Double) getValue(43);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Sales_Tax_Rate__c</code>.
	 */
	public void setSalesTaxRate_C(Double value) {
		setValue(44, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Sales_Tax_Rate__c</code>.
	 */
	public Double getSalesTaxRate_C() {
		return (Double) getValue(44);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Budget_Family__c</code>.
	 */
	public void setBudgetFamily_C(String value) {
		setValue(45, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Budget_Family__c</code>.
	 */
	public String getBudgetFamily_C() {
		return (String) getValue(45);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Product_Code__c</code>.
	 */
	public void setProductCode_C(String value) {
		setValue(46, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Product_Code__c</code>.
	 */
	public String getProductCode_C() {
		return (String) getValue(46);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Sample_Indication__c</code>.
	 */
	public void setSampleIndication_C(String value) {
		setValue(47, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Sample_Indication__c</code>.
	 */
	public String getSampleIndication_C() {
		return (String) getValue(47);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.ICD_10_Code__c</code>.
	 */
	public void setIcd_10Code_C(String value) {
		setValue(48, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.ICD_10_Code__c</code>.
	 */
	public String getIcd_10Code_C() {
		return (String) getValue(48);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Sample_Report_Release_Date__c</code>.
	 */
	public void setSampleReportReleaseDate_C(Timestamp value) {
		setValue(49, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Sample_Report_Release_Date__c</code>.
	 */
	public Timestamp getSampleReportReleaseDate_C() {
		return (Timestamp) getValue(49);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Sample_Arrival_Date__c</code>.
	 */
	public void setSampleArrivalDate_C(Timestamp value) {
		setValue(50, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Sample_Arrival_Date__c</code>.
	 */
	public Timestamp getSampleArrivalDate_C() {
		return (Timestamp) getValue(50);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Sample_Type__c</code>.
	 */
	public void setSampleType_C(String value) {
		setValue(51, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Sample_Type__c</code>.
	 */
	public String getSampleType_C() {
		return (String) getValue(51);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Data_Release_Date__c</code>.
	 */
	public void setDataReleaseDate_C(Timestamp value) {
		setValue(52, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Data_Release_Date__c</code>.
	 */
	public Timestamp getDataReleaseDate_C() {
		return (Timestamp) getValue(52);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Quantity_Released__c</code>.
	 */
	public void setQuantityReleased_C(Double value) {
		setValue(53, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Quantity_Released__c</code>.
	 */
	public Double getQuantityReleased_C() {
		return (Double) getValue(53);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Data_Release_Notes__c</code>.
	 */
	public void setDataReleaseNotes_C(String value) {
		setValue(54, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Data_Release_Notes__c</code>.
	 */
	public String getDataReleaseNotes_C() {
		return (String) getValue(54);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Sample_Rejection_Jeopardy__c</code>.
	 */
	public void setSampleRejectionJeopardy_C(Boolean value) {
		setValue(55, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Sample_Rejection_Jeopardy__c</code>.
	 */
	public Boolean getSampleRejectionJeopardy_C() {
		return (Boolean) getValue(55);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Sample_Rejection_Criteria__c</code>.
	 */
	public void setSampleRejectionCriteria_C(String value) {
		setValue(56, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Sample_Rejection_Criteria__c</code>.
	 */
	public String getSampleRejectionCriteria_C() {
		return (String) getValue(56);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Disposal_Date__c</code>.
	 */
	public void setDisposalDate_C(Timestamp value) {
		setValue(57, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Disposal_Date__c</code>.
	 */
	public Timestamp getDisposalDate_C() {
		return (Timestamp) getValue(57);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Primer_Version__c</code>.
	 */
	public void setPrimerVersion_C(String value) {
		setValue(58, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Primer_Version__c</code>.
	 */
	public String getPrimerVersion_C() {
		return (String) getValue(58);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Flow_Cell_ID__c</code>.
	 */
	public void setFlowCellId_C(String value) {
		setValue(59, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Flow_Cell_ID__c</code>.
	 */
	public String getFlowCellId_C() {
		return (String) getValue(59);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Sample_Failure_QNS__c</code>.
	 */
	public void setSampleFailureQns_C(Boolean value) {
		setValue(60, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Sample_Failure_QNS__c</code>.
	 */
	public Boolean getSampleFailureQns_C() {
		return (Boolean) getValue(60);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Sample_Failure_Criteria__c</code>.
	 */
	public void setSampleFailureCriteria_C(String value) {
		setValue(61, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Sample_Failure_Criteria__c</code>.
	 */
	public String getSampleFailureCriteria_C() {
		return (String) getValue(61);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Sample_ID__c</code>.
	 */
	public void setSampleId_C(String value) {
		setValue(62, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Sample_ID__c</code>.
	 */
	public String getSampleId_C() {
		return (String) getValue(62);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Other_Indication__c</code>.
	 */
	public void setOtherIndication_C(String value) {
		setValue(63, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Other_Indication__c</code>.
	 */
	public String getOtherIndication_C() {
		return (String) getValue(63);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Accession_Date__c</code>.
	 */
	public void setAccessionDate_C(Timestamp value) {
		setValue(64, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Accession_Date__c</code>.
	 */
	public Timestamp getAccessionDate_C() {
		return (Timestamp) getValue(64);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Anticoagulant__c</code>.
	 */
	public void setAnticoagulant_C(String value) {
		setValue(65, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Anticoagulant__c</code>.
	 */
	public String getAnticoagulant_C() {
		return (String) getValue(65);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Other_Anticoagulant__c</code>.
	 */
	public void setOtherAnticoagulant_C(String value) {
		setValue(66, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Other_Anticoagulant__c</code>.
	 */
	public String getOtherAnticoagulant_C() {
		return (String) getValue(66);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Sample_Reconciliation_Date__c</code>.
	 */
	public void setSampleReconciliationDate_C(Timestamp value) {
		setValue(67, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Sample_Reconciliation_Date__c</code>.
	 */
	public Timestamp getSampleReconciliationDate_C() {
		return (Timestamp) getValue(67);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.clonoSEQ_Turnaround_Time__c</code>.
	 */
	public void setClonoseqTurnaroundTime_C(Double value) {
		setValue(68, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.clonoSEQ_Turnaround_Time__c</code>.
	 */
	public Double getClonoseqTurnaroundTime_C() {
		return (Double) getValue(68);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Sample_Descrepancy__c</code>.
	 */
	public void setSampleDescrepancy_C(Boolean value) {
		setValue(69, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Sample_Descrepancy__c</code>.
	 */
	public Boolean getSampleDescrepancy_C() {
		return (Boolean) getValue(69);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Sample_Descrepancy_Comments__c</code>.
	 */
	public void setSampleDescrepancyComments_C(String value) {
		setValue(70, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Sample_Descrepancy_Comments__c</code>.
	 */
	public String getSampleDescrepancyComments_C() {
		return (String) getValue(70);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Corrected_Report_Issued__c</code>.
	 */
	public void setCorrectedReportIssued_C(Boolean value) {
		setValue(71, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Corrected_Report_Issued__c</code>.
	 */
	public Boolean getCorrectedReportIssued_C() {
		return (Boolean) getValue(71);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Corrected_Report_Comments__c</code>.
	 */
	public void setCorrectedReportComments_C(String value) {
		setValue(72, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Corrected_Report_Comments__c</code>.
	 */
	public String getCorrectedReportComments_C() {
		return (String) getValue(72);
	}

	/**
	 * Setter for <code>cora.stage_sforce_orderitem.Discounted_Price__c</code>.
	 */
	public void setDiscountedPrice_C(Double value) {
		setValue(73, value);
	}

	/**
	 * Getter for <code>cora.stage_sforce_orderitem.Discounted_Price__c</code>.
	 */
	public Double getDiscountedPrice_C() {
		return (Double) getValue(73);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<String> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached StageSforceOrderitemRecord
	 */
	public StageSforceOrderitemRecord() {
		super(StageSforceOrderitem.STAGE_SFORCE_ORDERITEM);
	}

	/**
	 * Create a detached, initialised StageSforceOrderitemRecord
	 */
	public StageSforceOrderitemRecord(String id, Boolean isdeleted, String orderid, String pricebookentryid, String originalorderitemid, Double availablequantity, Double quantity, String currencyisocode, Double unitprice, Double listprice, Timestamp servicedate, String description, Timestamp createddate, String createdbyid, Timestamp lastmodifieddate, String lastmodifiedbyid, Timestamp systemmodstamp, String orderitemnumber, Double discountAmount_C, Double discount_C, Double discountedSubtotal_C, Timestamp sampleDeadlineDate_C, String orderNumber_C, String lot_C, String kitId_C, String diagnosisClinicalIndicationIcd_9_C, String patientName_C, Timestamp dob_C, String sex_C, String patientMedicalRecord_C, Double daysUntilReportIsDue_C, String patientFirstName_C, String patientLastName_C, Timestamp dateCollected_C, Timestamp expirationDate_C, String opShipDetail_C, Double shipQuantity_C, Timestamp shipDate_C, String productFamily_C, String orderName_C, Double total_C, String orderProductName_C, String salesTaxDetails_C, Double salesTaxAmount_C, Double salesTaxRate_C, String budgetFamily_C, String productCode_C, String sampleIndication_C, String icd_10Code_C, Timestamp sampleReportReleaseDate_C, Timestamp sampleArrivalDate_C, String sampleType_C, Timestamp dataReleaseDate_C, Double quantityReleased_C, String dataReleaseNotes_C, Boolean sampleRejectionJeopardy_C, String sampleRejectionCriteria_C, Timestamp disposalDate_C, String primerVersion_C, String flowCellId_C, Boolean sampleFailureQns_C, String sampleFailureCriteria_C, String sampleId_C, String otherIndication_C, Timestamp accessionDate_C, String anticoagulant_C, String otherAnticoagulant_C, Timestamp sampleReconciliationDate_C, Double clonoseqTurnaroundTime_C, Boolean sampleDescrepancy_C, String sampleDescrepancyComments_C, Boolean correctedReportIssued_C, String correctedReportComments_C, Double discountedPrice_C) {
		super(StageSforceOrderitem.STAGE_SFORCE_ORDERITEM);

		setValue(0, id);
		setValue(1, isdeleted);
		setValue(2, orderid);
		setValue(3, pricebookentryid);
		setValue(4, originalorderitemid);
		setValue(5, availablequantity);
		setValue(6, quantity);
		setValue(7, currencyisocode);
		setValue(8, unitprice);
		setValue(9, listprice);
		setValue(10, servicedate);
		setValue(11, description);
		setValue(12, createddate);
		setValue(13, createdbyid);
		setValue(14, lastmodifieddate);
		setValue(15, lastmodifiedbyid);
		setValue(16, systemmodstamp);
		setValue(17, orderitemnumber);
		setValue(18, discountAmount_C);
		setValue(19, discount_C);
		setValue(20, discountedSubtotal_C);
		setValue(21, sampleDeadlineDate_C);
		setValue(22, orderNumber_C);
		setValue(23, lot_C);
		setValue(24, kitId_C);
		setValue(25, diagnosisClinicalIndicationIcd_9_C);
		setValue(26, patientName_C);
		setValue(27, dob_C);
		setValue(28, sex_C);
		setValue(29, patientMedicalRecord_C);
		setValue(30, daysUntilReportIsDue_C);
		setValue(31, patientFirstName_C);
		setValue(32, patientLastName_C);
		setValue(33, dateCollected_C);
		setValue(34, expirationDate_C);
		setValue(35, opShipDetail_C);
		setValue(36, shipQuantity_C);
		setValue(37, shipDate_C);
		setValue(38, productFamily_C);
		setValue(39, orderName_C);
		setValue(40, total_C);
		setValue(41, orderProductName_C);
		setValue(42, salesTaxDetails_C);
		setValue(43, salesTaxAmount_C);
		setValue(44, salesTaxRate_C);
		setValue(45, budgetFamily_C);
		setValue(46, productCode_C);
		setValue(47, sampleIndication_C);
		setValue(48, icd_10Code_C);
		setValue(49, sampleReportReleaseDate_C);
		setValue(50, sampleArrivalDate_C);
		setValue(51, sampleType_C);
		setValue(52, dataReleaseDate_C);
		setValue(53, quantityReleased_C);
		setValue(54, dataReleaseNotes_C);
		setValue(55, sampleRejectionJeopardy_C);
		setValue(56, sampleRejectionCriteria_C);
		setValue(57, disposalDate_C);
		setValue(58, primerVersion_C);
		setValue(59, flowCellId_C);
		setValue(60, sampleFailureQns_C);
		setValue(61, sampleFailureCriteria_C);
		setValue(62, sampleId_C);
		setValue(63, otherIndication_C);
		setValue(64, accessionDate_C);
		setValue(65, anticoagulant_C);
		setValue(66, otherAnticoagulant_C);
		setValue(67, sampleReconciliationDate_C);
		setValue(68, clonoseqTurnaroundTime_C);
		setValue(69, sampleDescrepancy_C);
		setValue(70, sampleDescrepancyComments_C);
		setValue(71, correctedReportIssued_C);
		setValue(72, correctedReportComments_C);
		setValue(73, discountedPrice_C);
	}
}
