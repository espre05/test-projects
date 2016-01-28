/**
 * This class is generated by jOOQ
 */
package gen.db.cora.tables.records;


import gen.db.cora.tables.Reports;

import java.sql.Timestamp;
import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record21;
import org.jooq.Row;
import org.jooq.Row21;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.0"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ReportsRecord extends UpdatableRecordImpl<ReportsRecord> implements Record21<UUID, UUID, String, Timestamp, Boolean, String, String, Boolean, Integer, String, String, String, Timestamp, String, Timestamp, Boolean, Long, Timestamp, Timestamp, UUID, UUID> {

	private static final long serialVersionUID = 691713685;

	/**
	 * Setter for <code>cora.reports.id</code>.
	 */
	public void setId(UUID value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>cora.reports.id</code>.
	 */
	public UUID getId() {
		return (UUID) getValue(0);
	}

	/**
	 * Setter for <code>cora.reports.order_test_id</code>.
	 */
	public void setOrderTestId(UUID value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>cora.reports.order_test_id</code>.
	 */
	public UUID getOrderTestId() {
		return (UUID) getValue(1);
	}

	/**
	 * Setter for <code>cora.reports.report_type</code>.
	 */
	public void setReportType(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>cora.reports.report_type</code>.
	 */
	public String getReportType() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>cora.reports.delivery_date</code>.
	 */
	public void setDeliveryDate(Timestamp value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>cora.reports.delivery_date</code>.
	 */
	public Timestamp getDeliveryDate() {
		return (Timestamp) getValue(3);
	}

	/**
	 * Setter for <code>cora.reports.qc_pass</code>.
	 */
	public void setQcPass(Boolean value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>cora.reports.qc_pass</code>.
	 */
	public Boolean getQcPass() {
		return (Boolean) getValue(4);
	}

	/**
	 * Setter for <code>cora.reports.indication</code>.
	 */
	public void setIndication(String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>cora.reports.indication</code>.
	 */
	public String getIndication() {
		return (String) getValue(5);
	}

	/**
	 * Setter for <code>cora.reports.report_path</code>.
	 */
	public void setReportPath(String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>cora.reports.report_path</code>.
	 */
	public String getReportPath() {
		return (String) getValue(6);
	}

	/**
	 * Setter for <code>cora.reports.mrd_detected</code>.
	 */
	public void setMrdDetected(Boolean value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>cora.reports.mrd_detected</code>.
	 */
	public Boolean getMrdDetected() {
		return (Boolean) getValue(7);
	}

	/**
	 * Setter for <code>cora.reports.residual_cells</code>.
	 */
	public void setResidualCells(Integer value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>cora.reports.residual_cells</code>.
	 */
	public Integer getResidualCells() {
		return (Integer) getValue(8);
	}

	/**
	 * Setter for <code>cora.reports.interpretation</code>.
	 */
	public void setInterpretation(String value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>cora.reports.interpretation</code>.
	 */
	public String getInterpretation() {
		return (String) getValue(9);
	}

	/**
	 * Setter for <code>cora.reports.summary_results</code>.
	 */
	public void setSummaryResults(String value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>cora.reports.summary_results</code>.
	 */
	public String getSummaryResults() {
		return (String) getValue(10);
	}

	/**
	 * Setter for <code>cora.reports.clinical_consultant</code>.
	 */
	public void setClinicalConsultant(String value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>cora.reports.clinical_consultant</code>.
	 */
	public String getClinicalConsultant() {
		return (String) getValue(11);
	}

	/**
	 * Setter for <code>cora.reports.clinical_consultant_signoff</code>.
	 */
	public void setClinicalConsultantSignoff(Timestamp value) {
		setValue(12, value);
	}

	/**
	 * Getter for <code>cora.reports.clinical_consultant_signoff</code>.
	 */
	public Timestamp getClinicalConsultantSignoff() {
		return (Timestamp) getValue(12);
	}

	/**
	 * Setter for <code>cora.reports.lab_director</code>.
	 */
	public void setLabDirector(String value) {
		setValue(13, value);
	}

	/**
	 * Getter for <code>cora.reports.lab_director</code>.
	 */
	public String getLabDirector() {
		return (String) getValue(13);
	}

	/**
	 * Setter for <code>cora.reports.lab_director_signoff</code>.
	 */
	public void setLabDirectorSignoff(Timestamp value) {
		setValue(14, value);
	}

	/**
	 * Getter for <code>cora.reports.lab_director_signoff</code>.
	 */
	public Timestamp getLabDirectorSignoff() {
		return (Timestamp) getValue(14);
	}

	/**
	 * Setter for <code>cora.reports.active</code>.
	 */
	public void setActive(Boolean value) {
		setValue(15, value);
	}

	/**
	 * Getter for <code>cora.reports.active</code>.
	 */
	public Boolean getActive() {
		return (Boolean) getValue(15);
	}

	/**
	 * Setter for <code>cora.reports.version</code>.
	 */
	public void setVersion(Long value) {
		setValue(16, value);
	}

	/**
	 * Getter for <code>cora.reports.version</code>.
	 */
	public Long getVersion() {
		return (Long) getValue(16);
	}

	/**
	 * Setter for <code>cora.reports.created</code>.
	 */
	public void setCreated(Timestamp value) {
		setValue(17, value);
	}

	/**
	 * Getter for <code>cora.reports.created</code>.
	 */
	public Timestamp getCreated() {
		return (Timestamp) getValue(17);
	}

	/**
	 * Setter for <code>cora.reports.modified</code>.
	 */
	public void setModified(Timestamp value) {
		setValue(18, value);
	}

	/**
	 * Getter for <code>cora.reports.modified</code>.
	 */
	public Timestamp getModified() {
		return (Timestamp) getValue(18);
	}

	/**
	 * Setter for <code>cora.reports.created_by</code>.
	 */
	public void setCreatedBy(UUID value) {
		setValue(19, value);
	}

	/**
	 * Getter for <code>cora.reports.created_by</code>.
	 */
	public UUID getCreatedBy() {
		return (UUID) getValue(19);
	}

	/**
	 * Setter for <code>cora.reports.modified_by</code>.
	 */
	public void setModifiedBy(UUID value) {
		setValue(20, value);
	}

	/**
	 * Getter for <code>cora.reports.modified_by</code>.
	 */
	public UUID getModifiedBy() {
		return (UUID) getValue(20);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<UUID> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record21 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row21<UUID, UUID, String, Timestamp, Boolean, String, String, Boolean, Integer, String, String, String, Timestamp, String, Timestamp, Boolean, Long, Timestamp, Timestamp, UUID, UUID> fieldsRow() {
		return (Row21) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row21<UUID, UUID, String, Timestamp, Boolean, String, String, Boolean, Integer, String, String, String, Timestamp, String, Timestamp, Boolean, Long, Timestamp, Timestamp, UUID, UUID> valuesRow() {
		return (Row21) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UUID> field1() {
		return Reports.REPORTS.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UUID> field2() {
		return Reports.REPORTS.ORDER_TEST_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return Reports.REPORTS.REPORT_TYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Timestamp> field4() {
		return Reports.REPORTS.DELIVERY_DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Boolean> field5() {
		return Reports.REPORTS.QC_PASS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field6() {
		return Reports.REPORTS.INDICATION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field7() {
		return Reports.REPORTS.REPORT_PATH;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Boolean> field8() {
		return Reports.REPORTS.MRD_DETECTED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field9() {
		return Reports.REPORTS.RESIDUAL_CELLS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field10() {
		return Reports.REPORTS.INTERPRETATION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field11() {
		return Reports.REPORTS.SUMMARY_RESULTS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field12() {
		return Reports.REPORTS.CLINICAL_CONSULTANT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Timestamp> field13() {
		return Reports.REPORTS.CLINICAL_CONSULTANT_SIGNOFF;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field14() {
		return Reports.REPORTS.LAB_DIRECTOR;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Timestamp> field15() {
		return Reports.REPORTS.LAB_DIRECTOR_SIGNOFF;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Boolean> field16() {
		return Reports.REPORTS.ACTIVE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field17() {
		return Reports.REPORTS.VERSION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Timestamp> field18() {
		return Reports.REPORTS.CREATED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Timestamp> field19() {
		return Reports.REPORTS.MODIFIED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UUID> field20() {
		return Reports.REPORTS.CREATED_BY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UUID> field21() {
		return Reports.REPORTS.MODIFIED_BY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UUID value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UUID value2() {
		return getOrderTestId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getReportType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp value4() {
		return getDeliveryDate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean value5() {
		return getQcPass();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value6() {
		return getIndication();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value7() {
		return getReportPath();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean value8() {
		return getMrdDetected();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value9() {
		return getResidualCells();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value10() {
		return getInterpretation();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value11() {
		return getSummaryResults();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value12() {
		return getClinicalConsultant();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp value13() {
		return getClinicalConsultantSignoff();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value14() {
		return getLabDirector();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp value15() {
		return getLabDirectorSignoff();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean value16() {
		return getActive();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value17() {
		return getVersion();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp value18() {
		return getCreated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp value19() {
		return getModified();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UUID value20() {
		return getCreatedBy();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UUID value21() {
		return getModifiedBy();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportsRecord value1(UUID value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportsRecord value2(UUID value) {
		setOrderTestId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportsRecord value3(String value) {
		setReportType(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportsRecord value4(Timestamp value) {
		setDeliveryDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportsRecord value5(Boolean value) {
		setQcPass(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportsRecord value6(String value) {
		setIndication(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportsRecord value7(String value) {
		setReportPath(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportsRecord value8(Boolean value) {
		setMrdDetected(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportsRecord value9(Integer value) {
		setResidualCells(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportsRecord value10(String value) {
		setInterpretation(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportsRecord value11(String value) {
		setSummaryResults(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportsRecord value12(String value) {
		setClinicalConsultant(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportsRecord value13(Timestamp value) {
		setClinicalConsultantSignoff(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportsRecord value14(String value) {
		setLabDirector(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportsRecord value15(Timestamp value) {
		setLabDirectorSignoff(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportsRecord value16(Boolean value) {
		setActive(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportsRecord value17(Long value) {
		setVersion(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportsRecord value18(Timestamp value) {
		setCreated(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportsRecord value19(Timestamp value) {
		setModified(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportsRecord value20(UUID value) {
		setCreatedBy(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportsRecord value21(UUID value) {
		setModifiedBy(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReportsRecord values(UUID value1, UUID value2, String value3, Timestamp value4, Boolean value5, String value6, String value7, Boolean value8, Integer value9, String value10, String value11, String value12, Timestamp value13, String value14, Timestamp value15, Boolean value16, Long value17, Timestamp value18, Timestamp value19, UUID value20, UUID value21) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		value7(value7);
		value8(value8);
		value9(value9);
		value10(value10);
		value11(value11);
		value12(value12);
		value13(value13);
		value14(value14);
		value15(value15);
		value16(value16);
		value17(value17);
		value18(value18);
		value19(value19);
		value20(value20);
		value21(value21);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached ReportsRecord
	 */
	public ReportsRecord() {
		super(Reports.REPORTS);
	}

	/**
	 * Create a detached, initialised ReportsRecord
	 */
	public ReportsRecord(UUID id, UUID orderTestId, String reportType, Timestamp deliveryDate, Boolean qcPass, String indication, String reportPath, Boolean mrdDetected, Integer residualCells, String interpretation, String summaryResults, String clinicalConsultant, Timestamp clinicalConsultantSignoff, String labDirector, Timestamp labDirectorSignoff, Boolean active, Long version, Timestamp created, Timestamp modified, UUID createdBy, UUID modifiedBy) {
		super(Reports.REPORTS);

		setValue(0, id);
		setValue(1, orderTestId);
		setValue(2, reportType);
		setValue(3, deliveryDate);
		setValue(4, qcPass);
		setValue(5, indication);
		setValue(6, reportPath);
		setValue(7, mrdDetected);
		setValue(8, residualCells);
		setValue(9, interpretation);
		setValue(10, summaryResults);
		setValue(11, clinicalConsultant);
		setValue(12, clinicalConsultantSignoff);
		setValue(13, labDirector);
		setValue(14, labDirectorSignoff);
		setValue(15, active);
		setValue(16, version);
		setValue(17, created);
		setValue(18, modified);
		setValue(19, createdBy);
		setValue(20, modifiedBy);
	}
}