/**
 * This class is generated by jOOQ
 */
package gen.db.cora.tables.records;


import gen.db.cora.tables.Samples;

import java.sql.Timestamp;
import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record14;
import org.jooq.Row;
import org.jooq.Row14;
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
public class SamplesRecord extends UpdatableRecordImpl<SamplesRecord> implements Record14<UUID, UUID, UUID, String, Timestamp, Timestamp, String, String, Boolean, Long, Timestamp, Timestamp, UUID, UUID> {

	private static final long serialVersionUID = 1586308648;

	/**
	 * Setter for <code>cora.samples.id</code>.
	 */
	public void setId(UUID value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>cora.samples.id</code>.
	 */
	public UUID getId() {
		return (UUID) getValue(0);
	}

	/**
	 * Setter for <code>cora.samples.specimen_id</code>.
	 */
	public void setSpecimenId(UUID value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>cora.samples.specimen_id</code>.
	 */
	public UUID getSpecimenId() {
		return (UUID) getValue(1);
	}

	/**
	 * Setter for <code>cora.samples.order_test_id</code>.
	 */
	public void setOrderTestId(UUID value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>cora.samples.order_test_id</code>.
	 */
	public UUID getOrderTestId() {
		return (UUID) getValue(2);
	}

	/**
	 * Setter for <code>cora.samples.calculated_sample_name</code>.
	 */
	public void setCalculatedSampleName(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>cora.samples.calculated_sample_name</code>.
	 */
	public String getCalculatedSampleName() {
		return (String) getValue(3);
	}

	/**
	 * Setter for <code>cora.samples.disposal_date</code>.
	 */
	public void setDisposalDate(Timestamp value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>cora.samples.disposal_date</code>.
	 */
	public Timestamp getDisposalDate() {
		return (Timestamp) getValue(4);
	}

	/**
	 * Setter for <code>cora.samples.failure_date</code>.
	 */
	public void setFailureDate(Timestamp value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>cora.samples.failure_date</code>.
	 */
	public Timestamp getFailureDate() {
		return (Timestamp) getValue(5);
	}

	/**
	 * Setter for <code>cora.samples.failure_reason</code>.
	 */
	public void setFailureReason(String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>cora.samples.failure_reason</code>.
	 */
	public String getFailureReason() {
		return (String) getValue(6);
	}

	/**
	 * Setter for <code>cora.samples.plate_id</code>.
	 */
	public void setPlateId(String value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>cora.samples.plate_id</code>.
	 */
	public String getPlateId() {
		return (String) getValue(7);
	}

	/**
	 * Setter for <code>cora.samples.active</code>.
	 */
	public void setActive(Boolean value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>cora.samples.active</code>.
	 */
	public Boolean getActive() {
		return (Boolean) getValue(8);
	}

	/**
	 * Setter for <code>cora.samples.version</code>.
	 */
	public void setVersion(Long value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>cora.samples.version</code>.
	 */
	public Long getVersion() {
		return (Long) getValue(9);
	}

	/**
	 * Setter for <code>cora.samples.created</code>.
	 */
	public void setCreated(Timestamp value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>cora.samples.created</code>.
	 */
	public Timestamp getCreated() {
		return (Timestamp) getValue(10);
	}

	/**
	 * Setter for <code>cora.samples.modified</code>.
	 */
	public void setModified(Timestamp value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>cora.samples.modified</code>.
	 */
	public Timestamp getModified() {
		return (Timestamp) getValue(11);
	}

	/**
	 * Setter for <code>cora.samples.created_by</code>.
	 */
	public void setCreatedBy(UUID value) {
		setValue(12, value);
	}

	/**
	 * Getter for <code>cora.samples.created_by</code>.
	 */
	public UUID getCreatedBy() {
		return (UUID) getValue(12);
	}

	/**
	 * Setter for <code>cora.samples.modified_by</code>.
	 */
	public void setModifiedBy(UUID value) {
		setValue(13, value);
	}

	/**
	 * Getter for <code>cora.samples.modified_by</code>.
	 */
	public UUID getModifiedBy() {
		return (UUID) getValue(13);
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
	// Record14 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row14<UUID, UUID, UUID, String, Timestamp, Timestamp, String, String, Boolean, Long, Timestamp, Timestamp, UUID, UUID> fieldsRow() {
		return (Row14) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row14<UUID, UUID, UUID, String, Timestamp, Timestamp, String, String, Boolean, Long, Timestamp, Timestamp, UUID, UUID> valuesRow() {
		return (Row14) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UUID> field1() {
		return Samples.SAMPLES.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UUID> field2() {
		return Samples.SAMPLES.SPECIMEN_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UUID> field3() {
		return Samples.SAMPLES.ORDER_TEST_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field4() {
		return Samples.SAMPLES.CALCULATED_SAMPLE_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Timestamp> field5() {
		return Samples.SAMPLES.DISPOSAL_DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Timestamp> field6() {
		return Samples.SAMPLES.FAILURE_DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field7() {
		return Samples.SAMPLES.FAILURE_REASON;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field8() {
		return Samples.SAMPLES.PLATE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Boolean> field9() {
		return Samples.SAMPLES.ACTIVE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field10() {
		return Samples.SAMPLES.VERSION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Timestamp> field11() {
		return Samples.SAMPLES.CREATED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Timestamp> field12() {
		return Samples.SAMPLES.MODIFIED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UUID> field13() {
		return Samples.SAMPLES.CREATED_BY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UUID> field14() {
		return Samples.SAMPLES.MODIFIED_BY;
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
		return getSpecimenId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UUID value3() {
		return getOrderTestId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value4() {
		return getCalculatedSampleName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp value5() {
		return getDisposalDate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp value6() {
		return getFailureDate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value7() {
		return getFailureReason();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value8() {
		return getPlateId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean value9() {
		return getActive();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value10() {
		return getVersion();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp value11() {
		return getCreated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp value12() {
		return getModified();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UUID value13() {
		return getCreatedBy();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UUID value14() {
		return getModifiedBy();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplesRecord value1(UUID value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplesRecord value2(UUID value) {
		setSpecimenId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplesRecord value3(UUID value) {
		setOrderTestId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplesRecord value4(String value) {
		setCalculatedSampleName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplesRecord value5(Timestamp value) {
		setDisposalDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplesRecord value6(Timestamp value) {
		setFailureDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplesRecord value7(String value) {
		setFailureReason(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplesRecord value8(String value) {
		setPlateId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplesRecord value9(Boolean value) {
		setActive(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplesRecord value10(Long value) {
		setVersion(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplesRecord value11(Timestamp value) {
		setCreated(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplesRecord value12(Timestamp value) {
		setModified(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplesRecord value13(UUID value) {
		setCreatedBy(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplesRecord value14(UUID value) {
		setModifiedBy(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SamplesRecord values(UUID value1, UUID value2, UUID value3, String value4, Timestamp value5, Timestamp value6, String value7, String value8, Boolean value9, Long value10, Timestamp value11, Timestamp value12, UUID value13, UUID value14) {
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
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached SamplesRecord
	 */
	public SamplesRecord() {
		super(Samples.SAMPLES);
	}

	/**
	 * Create a detached, initialised SamplesRecord
	 */
	public SamplesRecord(UUID id, UUID specimenId, UUID orderTestId, String calculatedSampleName, Timestamp disposalDate, Timestamp failureDate, String failureReason, String plateId, Boolean active, Long version, Timestamp created, Timestamp modified, UUID createdBy, UUID modifiedBy) {
		super(Samples.SAMPLES);

		setValue(0, id);
		setValue(1, specimenId);
		setValue(2, orderTestId);
		setValue(3, calculatedSampleName);
		setValue(4, disposalDate);
		setValue(5, failureDate);
		setValue(6, failureReason);
		setValue(7, plateId);
		setValue(8, active);
		setValue(9, version);
		setValue(10, created);
		setValue(11, modified);
		setValue(12, createdBy);
		setValue(13, modifiedBy);
	}
}
