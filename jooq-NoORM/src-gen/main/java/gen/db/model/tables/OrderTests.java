/**
 * This class is generated by jOOQ
 */
package gen.db.model.tables;


import gen.db.model.Cora;
import gen.db.model.Keys;
import gen.db.model.tables.records.OrderTestsRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class OrderTests extends TableImpl<OrderTestsRecord> {

	private static final long serialVersionUID = -513766153;

	/**
	 * The reference instance of <code>cora.order_tests</code>
	 */
	public static final OrderTests ORDER_TESTS = new OrderTests();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<OrderTestsRecord> getRecordType() {
		return OrderTestsRecord.class;
	}

	/**
	 * The column <code>cora.order_tests.id</code>.
	 */
	public final TableField<OrderTestsRecord, UUID> ID = createField("id", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

	/**
	 * The column <code>cora.order_tests.order_id</code>.
	 */
	public final TableField<OrderTestsRecord, UUID> ORDER_ID = createField("order_id", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

	/**
	 * The column <code>cora.order_tests.test_id</code>.
	 */
	public final TableField<OrderTestsRecord, UUID> TEST_ID = createField("test_id", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

	/**
	 * The column <code>cora.order_tests.specimen_id</code>.
	 */
	public final TableField<OrderTestsRecord, UUID> SPECIMEN_ID = createField("specimen_id", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

	/**
	 * The column <code>cora.order_tests.sample_name</code>.
	 */
	public final TableField<OrderTestsRecord, String> SAMPLE_NAME = createField("sample_name", org.jooq.impl.SQLDataType.VARCHAR.length(256), this, "");

	/**
	 * The column <code>cora.order_tests.order_test_status_type</code>.
	 */
	public final TableField<OrderTestsRecord, String> ORDER_TEST_STATUS_TYPE = createField("order_test_status_type", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>cora.order_tests.properties</code>.
	 */
	public final TableField<OrderTestsRecord, String> PROPERTIES = createField("properties", org.jooq.impl.SQLDataType.VARCHAR.length(8192), this, "");

	/**
	 * The column <code>cora.order_tests.active</code>.
	 */
	public final TableField<OrderTestsRecord, Boolean> ACTIVE = createField("active", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>cora.order_tests.version</code>.
	 */
	public final TableField<OrderTestsRecord, Long> VERSION = createField("version", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * The column <code>cora.order_tests.created</code>.
	 */
	public final TableField<OrderTestsRecord, Timestamp> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>cora.order_tests.modified</code>.
	 */
	public final TableField<OrderTestsRecord, Timestamp> MODIFIED = createField("modified", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>cora.order_tests.created_by</code>.
	 */
	public final TableField<OrderTestsRecord, String> CREATED_BY = createField("created_by", org.jooq.impl.SQLDataType.VARCHAR.length(256).nullable(false), this, "");

	/**
	 * The column <code>cora.order_tests.modified_by</code>.
	 */
	public final TableField<OrderTestsRecord, String> MODIFIED_BY = createField("modified_by", org.jooq.impl.SQLDataType.VARCHAR.length(256).nullable(false), this, "");

	/**
	 * Create a <code>cora.order_tests</code> table reference
	 */
	public OrderTests() {
		this("order_tests", null);
	}

	/**
	 * Create an aliased <code>cora.order_tests</code> table reference
	 */
	public OrderTests(String alias) {
		this(alias, ORDER_TESTS);
	}

	private OrderTests(String alias, Table<OrderTestsRecord> aliased) {
		this(alias, aliased, null);
	}

	private OrderTests(String alias, Table<OrderTestsRecord> aliased, Field<?>[] parameters) {
		super(alias, Cora.CORA, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<OrderTestsRecord> getPrimaryKey() {
		return Keys.ORDER_TESTS_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<OrderTestsRecord>> getKeys() {
		return Arrays.<UniqueKey<OrderTestsRecord>>asList(Keys.ORDER_TESTS_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<OrderTestsRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<OrderTestsRecord, ?>>asList(Keys.ORDER_TESTS__ORDER_TESTS_ORDER_ID_FKEY, Keys.ORDER_TESTS__ORDER_TESTS_TEST_ID_FKEY, Keys.ORDER_TESTS__ORDER_TESTS_SPECIMEN_ID_FKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrderTests as(String alias) {
		return new OrderTests(alias, this);
	}

	/**
	 * Rename this table
	 */
	public OrderTests rename(String name) {
		return new OrderTests(name, null);
	}
}
