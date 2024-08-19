package Servlet.Account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DAO.AccountDAO;
import DTO.AccountDTO;
import Entity.Account;
import Helper.JSONHelper;
import Model.AccountResponse;
import Model.NotificationResult;

@WebServlet({ "/login/*", "/viewacc/*" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final AccountDAO accDao = new AccountDAO();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accId = req.getParameter("id");
		Account viewAcc = accDao.findAccByUsername(accId);

		if (viewAcc == null)
			return;

		JSONHelper.writeJsonResponse(res, viewAcc);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		AccountDTO accRequest = JSONHelper.parseJSON(JSONHelper.readJSON(req), AccountDTO.class);

		Account accLogin = accDao.findAccByUsername(accRequest.getUsername());
		if (accLogin == null) {
			JSONHelper.respondWithError(res, "Account not found", 400);
			return;
		}

		if (accLogin.isFirstLogin()) {
			handleFirstLogin(res, accRequest, accLogin);
		} else {
			handleSubsequentLogin(res, accRequest, accLogin);
		}
	}
	
	
	
	private void handleFirstLogin(HttpServletResponse res, AccountDTO accRequest, Account acc) throws IOException {
	    if (!acc.getPassword().equals(accRequest.getPassword())) {
	    	JSONHelper.respondWithError(res, "Incorrect account information", 400);
	    } else {
	    	AccountResponse accRes = AccountDAO.convertToAccountResponse(acc);
	        JSONHelper.writeJsonResponse(res, new NotificationResult(200, accRes));
	    }
	}

	private void handleSubsequentLogin(HttpServletResponse res, AccountDTO accRequest, Account acc) throws IOException {
	    Boolean checkPassword = AccountDAO.checkPassword(accRequest.getPassword(), acc.getPassword());

	    if (checkPassword) {
	    	AccountResponse accRes = AccountDAO.convertToAccountResponse(acc);
	    	JSONHelper.writeJsonResponse(res, new NotificationResult(200, accRes));
	    } else {
	    	JSONHelper.respondWithError(res, "Incorrect account information", 400);
	    }
	}

}
