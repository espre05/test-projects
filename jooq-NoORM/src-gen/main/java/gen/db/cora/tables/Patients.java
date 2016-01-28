/**
 * This class is generated by jOOQ
 */
package gen.db.cora.tables;


import gen.db.cora.Cora;
import gen.db.cora.Keys;
import gen.db.cora.tables.records.PatientsRecord;

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
public class Patients extends TableImpl<PatientsRecord> {

	private static final long serialVersionUID = 1899225815;

	/**
	 * The reference instance of <code>cora.patients</code>
	 */
	public static final Patients PATIENTS = new Patients();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<PatientsRecord> getRecordType() {
		return PatientsRecord.class;
	}

	/**
	 * The column <code>cora.patients.id</code>.
	 */
	public final TableField<PatientsRecord, UUID> ID = createField("id", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

	/**
	 * The column <code>cora.patients.firstname</code>.
	 */
	public final TableField<PatientsRecord, String> FIRSTNAME = createField("firstname", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>cora.patients.middlename</code>.
	 */
	public final TableField<PatientsRecord, String> MIDDLENAME = createField("middlename", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>cora.patients.lastname</code>.
	 */
	public final TableField<PatientsRecord, String> LASTNAME = createField("lastname", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>cora.patients.mrn</code>.
	 */
	public final TableField<PatientsRecord, String> MRN = createField("mrn", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

	/**
	 * The column <code>cora.patients.gender</code>.
	 */
	public final TableField<PatientsRecord, String> GENDER = createField("gender", org.jooq.impl.SQLDataType.VARCHAR.length(16), this, "");

	/**
	 * The column <code>cora.patients.dateofbirth</code>.
	 */
	public final TableField<PatientsRecord, Timestamp> DATEOFBIRTH = createField("dateofbirth", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

	/**
	 * The column <code>cora.patients.unique_patient_id</code>.
	 */
	public final TableField<PatientsRecord, String> UNIQUE_PATIENT_ID = createField("unique_patient_id", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

	/**
	 * The column <code>cora.patients.active</code>.
	 */
	public final TableField<PatientsRecord, Boolean> ACTIVE = createField("active", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>cora.patients.version</code>.
	 */
	public final TableField<PatientsRecord, Long> VERSION = createField("version", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * The column <code>cora.patients.created</code>.
	 */
	public final TableField<PatientsRecord, Timestamp> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>cora.patients.modified</code>.
	 */
	public final TableField<PatientsRecord, Timestamp> MODIFIED = createField("modified", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>cora.patients.created_by</code>.
	 */
	public final TableField<PatientsRecord, UUID> CREATED_BY = createField("created_by", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

	/**
	 * The column <code>cora.patients.modified_by</code>.
	 */
	public final TableField<PatientsRecord, UUID> MODIFIED_BY = createField("modified_by", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

	/**
	 * Create a <code>cora.patients</code> table reference
	 */
	public Patients() {
		this("patients", null);
	}

	/**
	 * Create an aliased <code>cora.patients</code> table reference
	 */
	public Patients(String alias) {
		this(alias, PATIENTS);
	}

	private Patients(String alias, Table<PatientsRecord> aliased) {
		this(alias, aliased, null);
	}

	private Patients(String alias, Table<PatientsRecord> aliased, Field<?>[] parameters) {
		super(alias, Cora.CORA, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<PatientsRecord> getPrimaryKey() {
		return Keys.PATIENTS_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<PatientsRecord>> getKeys() {
		return Arrays.<UniqueKey<PatientsRecord>>asList(Keys.PATIENTS_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Patients as(String alias) {
		return new Patients(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Patients rename(String name) {
		return new Patients(name, null);
	}
}
