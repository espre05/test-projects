/**
 * This class is generated by jOOQ
 */
package gen.db.cora.tables;


import gen.db.cora.Cora;
import gen.db.cora.Keys;
import gen.db.cora.tables.records.TestsRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Field;
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
		"jOOQ version:3.6.0"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tests extends TableImpl<TestsRecord> {

	private static final long serialVersionUID = -1269273314;

	/**
	 * The reference instance of <code>cora.tests</code>
	 */
	public static final Tests TESTS = new Tests();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<TestsRecord> getRecordType() {
		return TestsRecord.class;
	}

	/**
	 * The column <code>cora.tests.id</code>.
	 */
	public final TableField<TestsRecord, UUID> ID = createField("id", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

	/**
	 * The column <code>cora.tests.name</code>.
	 */
	public final TableField<TestsRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

	/**
	 * The column <code>cora.tests.family</code>.
	 */
	public final TableField<TestsRecord, String> FAMILY = createField("family", org.jooq.impl.SQLDataType.VARCHAR.length(16), this, "");

	/**
	 * The column <code>cora.tests.measure</code>.
	 */
	public final TableField<TestsRecord, String> MEASURE = createField("measure", org.jooq.impl.SQLDataType.VARCHAR.length(16), this, "");

	/**
	 * The column <code>cora.tests.assay_version</code>.
	 */
	public final TableField<TestsRecord, String> ASSAY_VERSION = createField("assay_version", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>cora.tests.saleable</code>.
	 */
	public final TableField<TestsRecord, Boolean> SALEABLE = createField("saleable", org.jooq.impl.SQLDataType.BOOLEAN, this, "");

	/**
	 * The column <code>cora.tests.product_code</code>.
	 */
	public final TableField<TestsRecord, String> PRODUCT_CODE = createField("product_code", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>cora.tests.tax_code</code>.
	 */
	public final TableField<TestsRecord, String> TAX_CODE = createField("tax_code", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>cora.tests.description</code>.
	 */
	public final TableField<TestsRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR.length(256), this, "");

	/**
	 * The column <code>cora.tests.active</code>.
	 */
	public final TableField<TestsRecord, Boolean> ACTIVE = createField("active", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>cora.tests.version</code>.
	 */
	public final TableField<TestsRecord, Long> VERSION = createField("version", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * The column <code>cora.tests.created</code>.
	 */
	public final TableField<TestsRecord, Timestamp> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>cora.tests.modified</code>.
	 */
	public final TableField<TestsRecord, Timestamp> MODIFIED = createField("modified", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>cora.tests.created_by</code>.
	 */
	public final TableField<TestsRecord, UUID> CREATED_BY = createField("created_by", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

	/**
	 * The column <code>cora.tests.modified_by</code>.
	 */
	public final TableField<TestsRecord, UUID> MODIFIED_BY = createField("modified_by", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

	/**
	 * Create a <code>cora.tests</code> table reference
	 */
	public Tests() {
		this("tests", null);
	}

	/**
	 * Create an aliased <code>cora.tests</code> table reference
	 */
	public Tests(String alias) {
		this(alias, TESTS);
	}

	private Tests(String alias, Table<TestsRecord> aliased) {
		this(alias, aliased, null);
	}

	private Tests(String alias, Table<TestsRecord> aliased, Field<?>[] parameters) {
		super(alias, Cora.CORA, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<TestsRecord> getPrimaryKey() {
		return Keys.TESTS_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<TestsRecord>> getKeys() {
		return Arrays.<UniqueKey<TestsRecord>>asList(Keys.TESTS_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Tests as(String alias) {
		return new Tests(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Tests rename(String name) {
		return new Tests(name, null);
	}
}
