/**
 * This class is generated by jOOQ
 */
package gen.db.model.tables.records;


import gen.db.model.tables.OrderTests;

import java.sql.Timestamp;
import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record13;
import org.jooq.Row13;
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
public class OrderTestsRecord extends UpdatableRecordImpl<OrderTestsRecord> implements Record13<UUID, UUID, UUID, UUID, String, String, String, Boolean, Long, Timestamp, Timestamp, String, String> {

	private static final long serialVersionUID = 1083970198;

	/**
	 * Setter for <code>cora.order_tests.id</code>.
	 */
	public void setId(UUID value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>cora.order_tests.id</code>.
	 */
	public UUID getId() {
		return (UUID) getValue(0);
	}

	/**
	 * Setter for <code>cora.order_tests.order_id</code>.
	 */
	public void setOrderId(UUID value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>cora.order_tests.order_id</code>.
	 */
	public UUID getOrderId() {
		return (UUID) getValue(1);
	}

	/**
	 * Setter for <code>cora.order_tests.test_id</code>.
	 */
	public void setTestId(UUID value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>cora.order_tests.test_id</code>.
	 */
	public UUID getTestId() {
		return (UUID) getValue(2);
	}

	/**
	 * Setter for <code>cora.order_tests.specimen_id</code>.
	 */
	public void setSpecimenId(UUID value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>cora.order_tests.specimen_id</code>.
	 */
	public UUID getSpecimenId() {
		return (UUID) getValue(3);
	}

	/**
	 * Setter for <code>cora.order_tests.sample_name</code>.
	 */
	public void setSampleName(String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>cora.order_tests.sample_name</code>.
	 */
	public String getSampleName() {
		return (String) getValue(4);
	}

	/**
	 * Setter for <code>cora.order_tests.order_test_status_type</code>.
	 */
	public void setOrderTestStatusType(String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>cora.order_tests.order_test_status_type</code>.
	 */
	public String getOrderTestStatusType() {
		return (String) getValue(5);
	}

	/**
	 * Setter for <code>cora.order_tests.properties</code>.
	 */
	public void setProperties(String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>cora.order_tests.properties</code>.
	 */
	public String getProperties() {
		return (String) getValue(6);
	}

	/**
	 * Setter for <code>cora.order_tests.active</code>.
	 */
	public void setActive(Boolean value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>cora.order_tests.active</code>.
	 */
	public Boolean getActive() {
		return (Boolean) getValue(7);
	}

	/**
	 * Setter for <code>cora.order_tests.version</code>.
	 */
	public void setVersion(Long value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>cora.order_tests.version</code>.
	 */
	public Long getVersion() {
		return (Long) getValue(8);
	}

	/**
	 * Setter for <code>cora.order_tests.created</code>.
	 */
	public void setCreated(Timestamp value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>cora.order_tests.created</code>.
	 */
	public Timestamp getCreated() {
		return (Timestamp) getValue(9);
	}

	/**
	 * Setter for <code>cora.order_tests.modified</code>.
	 */
	public void setModified(Timestamp value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>cora.order_tests.modified</code>.
	 */
	public Timestamp getModified() {
		return (Timestamp) getValue(10);
	}

	/**
	 * Setter for <code>cora.order_tests.created_by</code>.
	 */
	public void setCreatedBy(String value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>cora.order_tests.created_by</code>.
	 */
	public String getCreatedBy() {
		return (String) getValue(11);
	}

	/**
	 * Setter for <code>cora.order_tests.modified_by</code>.
	 */
	public void setModifiedBy(String value) {
		setValue(12, value);
	}

	/**
	 * Getter for <code>cora.order_tests.modified_by</code>.
	 */
	public String getModifiedBy() {
		return (String) getValue(12);
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
	// Record13 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row13<UUID, UUID, UUID, UUID, String, String, String, Boolean, Long, Timestamp, Timestamp, String, String> fieldsRow() {
		return (Row13) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row13<UUID, UUID, UUID, UUID, String, String, String, Boolean, Long, Timestamp, Timestamp, String, String> valuesRow() {
		return (Row13) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UUID> field1() {
		return OrderTests.ORDER_TESTS.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UUID> field2() {
		return OrderTests.ORDER_TESTS.ORDER_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UUID> field3() {
		return OrderTests.ORDER_TESTS.TEST_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UUID> field4() {
		return OrderTests.ORDER_TESTS.SPECIMEN_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field5() {
		return OrderTests.ORDER_TESTS.SAMPLE_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field6() {
		return OrderTests.ORDER_TESTS.ORDER_TEST_STATUS_TYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field7() {
		return OrderTests.ORDER_TESTS.PROPERTIES;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Boolean> field8() {
		return OrderTests.ORDER_TESTS.ACTIVE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field9() {
		return OrderTests.ORDER_TESTS.VERSION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Timestamp> field10() {
		return OrderTests.ORDER_TESTS.CREATED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Timestamp> field11() {
		return OrderTests.ORDER_TESTS.MODIFIED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field12() {
		return OrderTests.ORDER_TESTS.CREATED_BY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field13() {
		return OrderTests.ORDER_TESTS.MODIFIED_BY;
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
		return getOrderId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UUID value3() {
		return getTestId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UUID value4() {
		return getSpecimenId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value5() {
		return getSampleName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value6() {
		return getOrderTestStatusType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value7() {
		return getProperties();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean value8() {
		return getActive();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value9() {
		return getVersion();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp value10() {
		return getCreated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp value11() {
		return getModified();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value12() {
		return getCreatedBy();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value13() {
		return getModifiedBy();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrderTestsRecord value1(UUID value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrderTestsRecord value2(UUID value) {
		setOrderId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrderTestsRecord value3(UUID value) {
		setTestId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrderTestsRecord value4(UUID value) {
		setSpecimenId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrderTestsRecord value5(String value) {
		setSampleName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrderTestsRecord value6(String value) {
		setOrderTestStatusType(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrderTestsRecord value7(String value) {
		setProperties(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrderTestsRecord value8(Boolean value) {
		setActive(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrderTestsRecord value9(Long value) {
		setVersion(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrderTestsRecord value10(Timestamp value) {
		setCreated(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrderTestsRecord value11(Timestamp value) {
		setModified(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrderTestsRecord value12(String value) {
		setCreatedBy(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrderTestsRecord value13(String value) {
		setModifiedBy(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrderTestsRecord values(UUID value1, UUID value2, UUID value3, UUID value4, String value5, String value6, String value7, Boolean value8, Long value9, Timestamp value10, Timestamp value11, String value12, String value13) {
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
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached OrderTestsRecord
	 */
	public OrderTestsRecord() {
		super(OrderTests.ORDER_TESTS);
	}

	/**
	 * Create a detached, initialised OrderTestsRecord
	 */
	public OrderTestsRecord(UUID id, UUID orderId, UUID testId, UUID specimenId, String sampleName, String orderTestStatusType, String properties, Boolean active, Long version, Timestamp created, Timestamp modified, String createdBy, String modifiedBy) {
		super(OrderTests.ORDER_TESTS);

		setValue(0, id);
		setValue(1, orderId);
		setValue(2, testId);
		setValue(3, specimenId);
		setValue(4, sampleName);
		setValue(5, orderTestStatusType);
		setValue(6, properties);
		setValue(7, active);
		setValue(8, version);
		setValue(9, created);
		setValue(10, modified);
		setValue(11, createdBy);
		setValue(12, modifiedBy);
	}
}
