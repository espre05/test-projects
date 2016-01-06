package demo.prem.dao;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import demo.prem.dao.repo.AccountRepository;
import demo.prem.model.Account;
import demo.prem.model.AccountType;

//@ContextConfiguration("classpath:/demo/prem/cfg/db-config-postgres.xml")
@ContextConfiguration("classpath:/demo/prem/cfg/db-config-postgres_spring-data.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PgDBTestAccountDao {
	@Autowired
	AccountRepository acctRepo;

	@Test
	@Rollback(false)
	public void testAcctSpringDataRepo() {
		Account acct = new Account();
		acct.setAccountType(AccountType.Hospital);
		acct.setDescription("Whatever1");
		acct.setName("Seattle Childrens hospital");
		Account savedAcct  = acctRepo.save(acct);
		System.out.println("Saved Acct = " + savedAcct);

		//	
		Account acctLab = new Account();
		acctLab.setAccountType(AccountType.IndependentLaboratory);
		acctLab.setDescription("Whatever1");
		acctLab.setName("SSF Clia Lab");
		Account savedAcctlab  = acctRepo.save(acctLab);

		Iterable<Account> acctList = acctRepo.findAll();
		System.out.println("Act = " + acctList);
	}

}
