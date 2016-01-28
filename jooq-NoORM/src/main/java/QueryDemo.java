
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Column;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.SelectJoinStep;
import org.jooq.impl.DSL;
import gen.db.cora.tables.Patients;
import gen.db.cora.tables.records.PatientsRecord;

public class QueryDemo {

	// private static void demonstrateInsert(final DSLContext dsl) {
	//
	// Long evansId = dsl.nextval(AUTHOR_SEQ);
	// Long vernonId = dsl.nextval(AUTHOR_SEQ);
	//
	// dsl.batch(
	// dsl.insertInto(AUTHOR)
	// .set(AUTHOR.AUTHOR_ID, evansId)
	// .set(AUTHOR.FULL_NAME, "Eric Evans"),
	//
	// dsl.insertInto(AUTHOR)
	// .set(AUTHOR.AUTHOR_ID, vernonId)
	// .set(AUTHOR.FULL_NAME, "Vaughn Vernon"),
	//
	// dsl.insertInto(BOOK)
	// .set(BOOK.BOOK_ID, dsl.nextval(BOOK_SEQ))
	// .set(BOOK.AUTHOR_ID, evansId)
	// .set(BOOK.TITLE, "Domain Driven Design"),
	//
	// dsl.insertInto(BOOK)
	// .set(BOOK.BOOK_ID, dsl.nextval(BOOK_SEQ))
	// .set(BOOK.AUTHOR_ID, vernonId)
	// .set(BOOK.TITLE, "Implementing Domain Driven Design")
	// ).execute();
	// }
	//
	// private static void demonstrateActiveRecord(final DSLContext dsl) {
	// dsl.selectFrom(AUTHOR)
	// .fetch()
	// .stream()
	// .forEach(a -> {
	// System.out.println("======From Fetch Into =======\n"
	// + a.getFullName());
	// });
	// }
	//
	// private static void demonstrateDataConversion(final DSLContext dsl) {
	//
	// ZonedDateTime date = dsl
	// .select(BOOK.DATE_ADDED)
	// .from(BOOK)
	// .where(BOOK.TITLE.like("Domain%"))
	// .fetchOne(BOOK.DATE_ADDED);
	//
	//
	// System.out.printf(
	// "=============== Data Conversion ================\n"
	// + "Date Added: %s\n",
	// date
	// );
	// }

	private static void selectSimple(final DSLContext dsl) {

		// Fetch any one
		Patient patient = dsl.select().from(Patients.PATIENTS).fetchAny().into(Patient.class);
		System.out.println(patient);

		// Fetch all
		List<Patient> patients = dsl.select().from(Patients.PATIENTS).fetch().into(Patient.class);
		System.out.println(patients);

		// stream
		Result<PatientsRecord> patientRecs = dsl.selectFrom(Patients.PATIENTS).fetch();
		patientRecs.stream().forEach(a -> {
			System.out.println(a.getFirstname() + " " + a.getLastname());
		});

		// count
		Record1<Integer> count = dsl.select(DSL.count()).from(Patients.PATIENTS).fetchOne();
		System.out.printf("Num of Patients  = %d\n", count.getValue(0));

		// group by, order by
		dsl.select(Patients.PATIENTS.GENDER, DSL.count()).from(Patients.PATIENTS)//
				.groupBy(Patients.PATIENTS.GENDER)//
				.orderBy(Patients.PATIENTS.GENDER.desc())//
				.fetch().stream().forEach(a -> {
					System.out.println(a.getValue("gender") + " | " + a.getValue("count"));
				});

	}

	// private static void demonstrateDelete(final DSLContext dsl) {
	// int numBooksDeleted = dsl.delete(BOOK).execute();
	// int numAuthorsDeleted = dsl.delete(AUTHOR).execute();
	//
	// System.out.printf("============ Deletes =============\n"
	// + "Deleted %d books and %d authors\n",
	// numBooksDeleted, numAuthorsDeleted);
	// }

	public static void main(final String[] args) {

		String userName = "cora"; // System.getProperty("user.name");
		String password = "cora";
		String url = "jdbc:postgresql://localhost:5432/cora_db";

		// String password = "PI'Q20qf";
		// String url =
		// "jdbc:postgresql://coradb.c1stksmafm4z.us-west-2.rds.amazonaws.com:5432/coradb";

		try (Connection conn = DriverManager.getConnection(url, userName, password)) {
			DSLContext dsl = DSL.using(conn, SQLDialect.POSTGRES);
			// demonstrateInsert(dsl);
			// demonstrateActiveRecord(dsl);
			selectSimple(dsl);
			// demonstrateDataConversion(dsl);
			// demonstrateDelete(dsl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Patient {
	@Column(name = "firstname")
	public String firstName;
	@Column(name = "lastname")
	public String lastName;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Patient [firstName=").append(firstName).append(", lastName=").append(lastName).append("]");
		return builder.toString();
	}
}