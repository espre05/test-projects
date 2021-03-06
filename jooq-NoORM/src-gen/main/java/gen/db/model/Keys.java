/**
 * This class is generated by jOOQ
 */
package gen.db.model;


import gen.db.model.tables.Accounts;
import gen.db.model.tables.Categories;
import gen.db.model.tables.CuratedPatients;
import gen.db.model.tables.IcdCodes;
import gen.db.model.tables.OrderTests;
import gen.db.model.tables.Orders;
import gen.db.model.tables.Patients;
import gen.db.model.tables.Providers;
import gen.db.model.tables.Specimens;
import gen.db.model.tables.StageSforceOrderitem;
import gen.db.model.tables.TestCategories;
import gen.db.model.tables.Tests;
import gen.db.model.tables.records.AccountsRecord;
import gen.db.model.tables.records.CategoriesRecord;
import gen.db.model.tables.records.CuratedPatientsRecord;
import gen.db.model.tables.records.IcdCodesRecord;
import gen.db.model.tables.records.OrderTestsRecord;
import gen.db.model.tables.records.OrdersRecord;
import gen.db.model.tables.records.PatientsRecord;
import gen.db.model.tables.records.ProvidersRecord;
import gen.db.model.tables.records.SpecimensRecord;
import gen.db.model.tables.records.StageSforceOrderitemRecord;
import gen.db.model.tables.records.TestCategoriesRecord;
import gen.db.model.tables.records.TestsRecord;

import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling foreign key relationships between tables of the <code>cora</code> 
 * schema
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

	// -------------------------------------------------------------------------
	// IDENTITY definitions
	// -------------------------------------------------------------------------

	public static final Identity<CuratedPatientsRecord, Integer> IDENTITY_CURATED_PATIENTS = Identities0.IDENTITY_CURATED_PATIENTS;
	public static final Identity<PatientsRecord, Integer> IDENTITY_PATIENTS = Identities0.IDENTITY_PATIENTS;

	// -------------------------------------------------------------------------
	// UNIQUE and PRIMARY KEY definitions
	// -------------------------------------------------------------------------

	public static final UniqueKey<AccountsRecord> ACCOUNTS_PKEY = UniqueKeys0.ACCOUNTS_PKEY;
	public static final UniqueKey<AccountsRecord> ACCOUNTS_NAME_KEY = UniqueKeys0.ACCOUNTS_NAME_KEY;
	public static final UniqueKey<CategoriesRecord> CATEGORIES_PKEY = UniqueKeys0.CATEGORIES_PKEY;
	public static final UniqueKey<CuratedPatientsRecord> CURATED_PATIENTS_PKEY = UniqueKeys0.CURATED_PATIENTS_PKEY;
	public static final UniqueKey<CuratedPatientsRecord> CURATED_PATIENTS_PATIENT_CODE_KEY = UniqueKeys0.CURATED_PATIENTS_PATIENT_CODE_KEY;
	public static final UniqueKey<IcdCodesRecord> ICD_CODES_PKEY = UniqueKeys0.ICD_CODES_PKEY;
	public static final UniqueKey<OrderTestsRecord> ORDER_TESTS_PKEY = UniqueKeys0.ORDER_TESTS_PKEY;
	public static final UniqueKey<OrdersRecord> ORDERS_PKEY = UniqueKeys0.ORDERS_PKEY;
	public static final UniqueKey<PatientsRecord> PATIENTS_PKEY = UniqueKeys0.PATIENTS_PKEY;
	public static final UniqueKey<PatientsRecord> PATIENTS_PATIENT_CODE_KEY = UniqueKeys0.PATIENTS_PATIENT_CODE_KEY;
	public static final UniqueKey<ProvidersRecord> PROVIDERS_PKEY = UniqueKeys0.PROVIDERS_PKEY;
	public static final UniqueKey<SpecimensRecord> SPECIMENS_PKEY = UniqueKeys0.SPECIMENS_PKEY;
	public static final UniqueKey<StageSforceOrderitemRecord> STAGE_SFORCE_ORDERITEM_PKEY = UniqueKeys0.STAGE_SFORCE_ORDERITEM_PKEY;
	public static final UniqueKey<TestCategoriesRecord> TEST_CATEGORIES_PKEY = UniqueKeys0.TEST_CATEGORIES_PKEY;
	public static final UniqueKey<TestsRecord> TESTS_PKEY = UniqueKeys0.TESTS_PKEY;

	// -------------------------------------------------------------------------
	// FOREIGN KEY definitions
	// -------------------------------------------------------------------------

	public static final ForeignKey<AccountsRecord, AccountsRecord> ACCOUNTS__ACCOUNTS_PARENT_ID_FKEY = ForeignKeys0.ACCOUNTS__ACCOUNTS_PARENT_ID_FKEY;
	public static final ForeignKey<OrderTestsRecord, OrdersRecord> ORDER_TESTS__ORDER_TESTS_ORDER_ID_FKEY = ForeignKeys0.ORDER_TESTS__ORDER_TESTS_ORDER_ID_FKEY;
	public static final ForeignKey<OrderTestsRecord, TestsRecord> ORDER_TESTS__ORDER_TESTS_TEST_ID_FKEY = ForeignKeys0.ORDER_TESTS__ORDER_TESTS_TEST_ID_FKEY;
	public static final ForeignKey<OrderTestsRecord, SpecimensRecord> ORDER_TESTS__ORDER_TESTS_SPECIMEN_ID_FKEY = ForeignKeys0.ORDER_TESTS__ORDER_TESTS_SPECIMEN_ID_FKEY;
	public static final ForeignKey<OrdersRecord, PatientsRecord> ORDERS__ORDERS_PATIENT_ID_FKEY = ForeignKeys0.ORDERS__ORDERS_PATIENT_ID_FKEY;
	public static final ForeignKey<OrdersRecord, ProvidersRecord> ORDERS__ORDERS_AUTHORIZING_PROVIDER_ID_FKEY = ForeignKeys0.ORDERS__ORDERS_AUTHORIZING_PROVIDER_ID_FKEY;
	public static final ForeignKey<OrdersRecord, AccountsRecord> ORDERS__ORDERS_ACCOUNT_ID_FKEY = ForeignKeys0.ORDERS__ORDERS_ACCOUNT_ID_FKEY;
	public static final ForeignKey<OrdersRecord, CategoriesRecord> ORDERS__ORDERS_CATEGORY_ID_FKEY = ForeignKeys0.ORDERS__ORDERS_CATEGORY_ID_FKEY;
	public static final ForeignKey<ProvidersRecord, AccountsRecord> PROVIDERS__PROVIDERS_ACCOUNT_ID_FKEY = ForeignKeys0.PROVIDERS__PROVIDERS_ACCOUNT_ID_FKEY;
	public static final ForeignKey<SpecimensRecord, OrdersRecord> SPECIMENS__SPECIMENS_ORDER_ID_FKEY = ForeignKeys0.SPECIMENS__SPECIMENS_ORDER_ID_FKEY;
	public static final ForeignKey<TestCategoriesRecord, TestsRecord> TEST_CATEGORIES__TEST_CATEGORIES_TEST_ID_FKEY = ForeignKeys0.TEST_CATEGORIES__TEST_CATEGORIES_TEST_ID_FKEY;
	public static final ForeignKey<TestCategoriesRecord, CategoriesRecord> TEST_CATEGORIES__TEST_CATEGORIES_CATEGORY_ID_FKEY = ForeignKeys0.TEST_CATEGORIES__TEST_CATEGORIES_CATEGORY_ID_FKEY;

	// -------------------------------------------------------------------------
	// [#1459] distribute members to avoid static initialisers > 64kb
	// -------------------------------------------------------------------------

	private static class Identities0 extends AbstractKeys {
		public static Identity<CuratedPatientsRecord, Integer> IDENTITY_CURATED_PATIENTS = createIdentity(CuratedPatients.CURATED_PATIENTS, CuratedPatients.CURATED_PATIENTS.PATIENT_CODE);
		public static Identity<PatientsRecord, Integer> IDENTITY_PATIENTS = createIdentity(Patients.PATIENTS, Patients.PATIENTS.PATIENT_CODE);
	}

	private static class UniqueKeys0 extends AbstractKeys {
		public static final UniqueKey<AccountsRecord> ACCOUNTS_PKEY = createUniqueKey(Accounts.ACCOUNTS, Accounts.ACCOUNTS.ID);
		public static final UniqueKey<AccountsRecord> ACCOUNTS_NAME_KEY = createUniqueKey(Accounts.ACCOUNTS, Accounts.ACCOUNTS.NAME);
		public static final UniqueKey<CategoriesRecord> CATEGORIES_PKEY = createUniqueKey(Categories.CATEGORIES, Categories.CATEGORIES.ID);
		public static final UniqueKey<CuratedPatientsRecord> CURATED_PATIENTS_PKEY = createUniqueKey(CuratedPatients.CURATED_PATIENTS, CuratedPatients.CURATED_PATIENTS.ID);
		public static final UniqueKey<CuratedPatientsRecord> CURATED_PATIENTS_PATIENT_CODE_KEY = createUniqueKey(CuratedPatients.CURATED_PATIENTS, CuratedPatients.CURATED_PATIENTS.PATIENT_CODE);
		public static final UniqueKey<IcdCodesRecord> ICD_CODES_PKEY = createUniqueKey(IcdCodes.ICD_CODES, IcdCodes.ICD_CODES.ID);
		public static final UniqueKey<OrderTestsRecord> ORDER_TESTS_PKEY = createUniqueKey(OrderTests.ORDER_TESTS, OrderTests.ORDER_TESTS.ID);
		public static final UniqueKey<OrdersRecord> ORDERS_PKEY = createUniqueKey(Orders.ORDERS, Orders.ORDERS.ID);
		public static final UniqueKey<PatientsRecord> PATIENTS_PKEY = createUniqueKey(Patients.PATIENTS, Patients.PATIENTS.ID);
		public static final UniqueKey<PatientsRecord> PATIENTS_PATIENT_CODE_KEY = createUniqueKey(Patients.PATIENTS, Patients.PATIENTS.PATIENT_CODE);
		public static final UniqueKey<ProvidersRecord> PROVIDERS_PKEY = createUniqueKey(Providers.PROVIDERS, Providers.PROVIDERS.ID);
		public static final UniqueKey<SpecimensRecord> SPECIMENS_PKEY = createUniqueKey(Specimens.SPECIMENS, Specimens.SPECIMENS.ID);
		public static final UniqueKey<StageSforceOrderitemRecord> STAGE_SFORCE_ORDERITEM_PKEY = createUniqueKey(StageSforceOrderitem.STAGE_SFORCE_ORDERITEM, StageSforceOrderitem.STAGE_SFORCE_ORDERITEM.ID);
		public static final UniqueKey<TestCategoriesRecord> TEST_CATEGORIES_PKEY = createUniqueKey(TestCategories.TEST_CATEGORIES, TestCategories.TEST_CATEGORIES.ID);
		public static final UniqueKey<TestsRecord> TESTS_PKEY = createUniqueKey(Tests.TESTS, Tests.TESTS.ID);
	}

	private static class ForeignKeys0 extends AbstractKeys {
		public static final ForeignKey<AccountsRecord, AccountsRecord> ACCOUNTS__ACCOUNTS_PARENT_ID_FKEY = createForeignKey(gen.db.model.Keys.ACCOUNTS_PKEY, Accounts.ACCOUNTS, Accounts.ACCOUNTS.PARENT_ID);
		public static final ForeignKey<OrderTestsRecord, OrdersRecord> ORDER_TESTS__ORDER_TESTS_ORDER_ID_FKEY = createForeignKey(gen.db.model.Keys.ORDERS_PKEY, OrderTests.ORDER_TESTS, OrderTests.ORDER_TESTS.ORDER_ID);
		public static final ForeignKey<OrderTestsRecord, TestsRecord> ORDER_TESTS__ORDER_TESTS_TEST_ID_FKEY = createForeignKey(gen.db.model.Keys.TESTS_PKEY, OrderTests.ORDER_TESTS, OrderTests.ORDER_TESTS.TEST_ID);
		public static final ForeignKey<OrderTestsRecord, SpecimensRecord> ORDER_TESTS__ORDER_TESTS_SPECIMEN_ID_FKEY = createForeignKey(gen.db.model.Keys.SPECIMENS_PKEY, OrderTests.ORDER_TESTS, OrderTests.ORDER_TESTS.SPECIMEN_ID);
		public static final ForeignKey<OrdersRecord, PatientsRecord> ORDERS__ORDERS_PATIENT_ID_FKEY = createForeignKey(gen.db.model.Keys.PATIENTS_PKEY, Orders.ORDERS, Orders.ORDERS.PATIENT_ID);
		public static final ForeignKey<OrdersRecord, ProvidersRecord> ORDERS__ORDERS_AUTHORIZING_PROVIDER_ID_FKEY = createForeignKey(gen.db.model.Keys.PROVIDERS_PKEY, Orders.ORDERS, Orders.ORDERS.AUTHORIZING_PROVIDER_ID);
		public static final ForeignKey<OrdersRecord, AccountsRecord> ORDERS__ORDERS_ACCOUNT_ID_FKEY = createForeignKey(gen.db.model.Keys.ACCOUNTS_PKEY, Orders.ORDERS, Orders.ORDERS.ACCOUNT_ID);
		public static final ForeignKey<OrdersRecord, CategoriesRecord> ORDERS__ORDERS_CATEGORY_ID_FKEY = createForeignKey(gen.db.model.Keys.CATEGORIES_PKEY, Orders.ORDERS, Orders.ORDERS.CATEGORY_ID);
		public static final ForeignKey<ProvidersRecord, AccountsRecord> PROVIDERS__PROVIDERS_ACCOUNT_ID_FKEY = createForeignKey(gen.db.model.Keys.ACCOUNTS_PKEY, Providers.PROVIDERS, Providers.PROVIDERS.ACCOUNT_ID);
		public static final ForeignKey<SpecimensRecord, OrdersRecord> SPECIMENS__SPECIMENS_ORDER_ID_FKEY = createForeignKey(gen.db.model.Keys.ORDERS_PKEY, Specimens.SPECIMENS, Specimens.SPECIMENS.ORDER_ID);
		public static final ForeignKey<TestCategoriesRecord, TestsRecord> TEST_CATEGORIES__TEST_CATEGORIES_TEST_ID_FKEY = createForeignKey(gen.db.model.Keys.TESTS_PKEY, TestCategories.TEST_CATEGORIES, TestCategories.TEST_CATEGORIES.TEST_ID);
		public static final ForeignKey<TestCategoriesRecord, CategoriesRecord> TEST_CATEGORIES__TEST_CATEGORIES_CATEGORY_ID_FKEY = createForeignKey(gen.db.model.Keys.CATEGORIES_PKEY, TestCategories.TEST_CATEGORIES, TestCategories.TEST_CATEGORIES.CATEGORY_ID);
	}
}
