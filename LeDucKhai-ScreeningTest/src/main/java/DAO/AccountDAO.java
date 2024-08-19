package DAO;

import java.security.SecureRandom;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import Entity.Account;
import Entity.Instructor;
import Helper.JPAHelper;
import Helper.JPAUtils;
import Model.AccountResponse;

public class AccountDAO {

	public Account findAccByUsername(String username) {
		EntityManager em = JPAUtils.em();
		try (JPAHelper helper = new JPAHelper(em)) {
			String jpql = "SELECT a FROM Account a WHERE a.username = :username";
			return helper.getSingleResult(jpql, Account.class, "username", username);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Account insertNewAccount(Account account) {
		EntityManager em = JPAUtils.em();
		Account newAcc = new Account();
		try {
			em.getTransaction().begin();
			newAcc = em.merge(account);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}

		return newAcc;
	}

	public void changePassword(String username, String newPassword) {
		EntityManager em = JPAUtils.em();
		try {
			String encodePass = encodePassword(newPassword);
			em.getTransaction().begin();
			String jpql = "UPDATE Account a SET a.password = :newPassword, a.isFirstLogin = false WHERE a.username = :username";
			Query query = em.createQuery(jpql);
			query.setParameter("newPassword", encodePass);
			query.setParameter("username", username);
			query.executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static String encodePassword(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
	
	public static boolean checkPassword(String plaintextPassword, String hashedPassword) {
	    return BCrypt.checkpw(plaintextPassword, hashedPassword);
	}

	public static Account createNewAccountWithInstructor(Instructor ins) {
		Account newAccount = new Account();
		newAccount.setUsername(ins.getInstructorID());
		newAccount.setInstructor(ins);
		newAccount.setPassword(generateDefaultPassword());
		newAccount.setFirstLogin(true);

		newAccount = AccountDAO.insertNewAccount(newAccount);
		return newAccount;
	}

	private static String generateDefaultPassword() {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		SecureRandom random = new SecureRandom();
		StringBuilder password = new StringBuilder(6);

		for (int i = 0; i < 6; i++) {
			password.append(chars.charAt(random.nextInt(chars.length())));
		}

		return password.toString();
	}
	
	public static AccountResponse convertToAccountResponse(Account acc) {
		AccountResponse accRes = new AccountResponse();
		accRes.setUsername(acc.getUsername());
		accRes.setFirstLogin(acc.isFirstLogin());
		accRes.setRole(acc.isRole());
		accRes.setIdInstructor(acc.getInstructor().getId());
		accRes.setInstructorName(acc.getInstructor().getFirstName() + " " + acc.getInstructor().getLastName());
		
		return accRes;
	}

}
