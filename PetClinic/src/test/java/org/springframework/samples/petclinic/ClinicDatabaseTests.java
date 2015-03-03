package org.springframework.samples.petclinic;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import junit.framework.TestCase;



@ContextConfiguration(locations="/META-INF/spring/petclinic-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ClinicDatabaseTests extends TestCase {

	@Autowired
	Clinic clinic;

	@Autowired
	private DataSource dataSource;

	private SimpleJdbcTemplate jdbcTemplate;

	@Before
	public void createSimpleJdbcTemplate() {
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	@Test
	@Transactional
	public void insertOwner() {
		final String firstName = "InsertOwner";
		final String lastName = "Test";
		// verify that the record is not in the DB yet
		assertEquals(0, jdbcTemplate.queryForInt("SELECT count(*) FROM owners WHERE first_name = ? AND last_name = ?",
				firstName, lastName));
		Owner owner = new Owner();
		owner.setFirstName(firstName);
		owner.setLastName(lastName);
		this.clinic.storeOwner(owner);
		// verify that the record is in the DB now
		assertEquals(1, jdbcTemplate.queryForInt("SELECT count(*) FROM owners WHERE first_name = ? AND last_name = ?",
				firstName, lastName));
	}

}
