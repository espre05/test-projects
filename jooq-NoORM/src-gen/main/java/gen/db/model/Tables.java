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
import gen.db.model.tables.StageGenoPatients;
import gen.db.model.tables.StageGenoSamples;
import gen.db.model.tables.StageImmunoOrders;
import gen.db.model.tables.StageSforceOrderitem;
import gen.db.model.tables.TestCategories;
import gen.db.model.tables.Tests;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in cora
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

	/**
	 * The table cora.accounts
	 */
	public static final Accounts ACCOUNTS = gen.db.model.tables.Accounts.ACCOUNTS;

	/**
	 * The table cora.categories
	 */
	public static final Categories CATEGORIES = gen.db.model.tables.Categories.CATEGORIES;

	/**
	 * The table cora.curated_patients
	 */
	public static final CuratedPatients CURATED_PATIENTS = gen.db.model.tables.CuratedPatients.CURATED_PATIENTS;

	/**
	 * The table cora.icd_codes
	 */
	public static final IcdCodes ICD_CODES = gen.db.model.tables.IcdCodes.ICD_CODES;

	/**
	 * The table cora.order_tests
	 */
	public static final OrderTests ORDER_TESTS = gen.db.model.tables.OrderTests.ORDER_TESTS;

	/**
	 * The table cora.orders
	 */
	public static final Orders ORDERS = gen.db.model.tables.Orders.ORDERS;

	/**
	 * The table cora.patients
	 */
	public static final Patients PATIENTS = gen.db.model.tables.Patients.PATIENTS;

	/**
	 * The table cora.providers
	 */
	public static final Providers PROVIDERS = gen.db.model.tables.Providers.PROVIDERS;

	/**
	 * The table cora.specimens
	 */
	public static final Specimens SPECIMENS = gen.db.model.tables.Specimens.SPECIMENS;

	/**
	 * The table cora.stage_geno_patients
	 */
	public static final StageGenoPatients STAGE_GENO_PATIENTS = gen.db.model.tables.StageGenoPatients.STAGE_GENO_PATIENTS;

	/**
	 * The table cora.stage_geno_samples
	 */
	public static final StageGenoSamples STAGE_GENO_SAMPLES = gen.db.model.tables.StageGenoSamples.STAGE_GENO_SAMPLES;

	/**
	 * The table cora.stage_immuno_orders
	 */
	public static final StageImmunoOrders STAGE_IMMUNO_ORDERS = gen.db.model.tables.StageImmunoOrders.STAGE_IMMUNO_ORDERS;

	/**
	 * The table cora.stage_sforce_orderitem
	 */
	public static final StageSforceOrderitem STAGE_SFORCE_ORDERITEM = gen.db.model.tables.StageSforceOrderitem.STAGE_SFORCE_ORDERITEM;

	/**
	 * The table cora.test_categories
	 */
	public static final TestCategories TEST_CATEGORIES = gen.db.model.tables.TestCategories.TEST_CATEGORIES;

	/**
	 * The table cora.tests
	 */
	public static final Tests TESTS = gen.db.model.tables.Tests.TESTS;
}