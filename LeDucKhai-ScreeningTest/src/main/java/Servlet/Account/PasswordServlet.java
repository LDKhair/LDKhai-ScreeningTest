package Servlet.Account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DAO.AccountDAO;
import Entity.Account;
import Helper.JSONHelper;
import Helper.MailHelper;
import Model.AccChangePass;

@WebServlet({ "/forgotpass", "/forgotpass/*", "/changepass/*", "/resetpass" })
public class PasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AccountDAO accDao = new AccountDAO();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (req.getRequestURI().contains("forgotpass")) {
            handleForgotPassword(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.contains("resetpass")) {
            handleResetPassword(req, res);
        } else {
            handleChangePassword(req, res);
        }
    }

    private void handleForgotPassword(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String email = req.getParameter("email");

        if (email == null) {
        	JSONHelper.respondWithError(res, "Email not found", 400);
            return;
        }

        MailHelper.sendMail(email, generateForgotPasswordEmailContent());
        JSONHelper.respondWithSuccess(res, "Check your email");
    }

    private void handleResetPassword(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            AccChangePass accRequest = JSONHelper.parseJSON(JSONHelper.readJSON(req), AccChangePass.class);
            Account currentAcc = accDao.findAccByUsername(accRequest.getUsername());

            if (currentAcc == null) {
            	JSONHelper.respondWithError(res, "Incorrect instructor information", 400);
                return;
            }

            accDao.changePassword(currentAcc.getUsername(), accRequest.getNewPassword());
            JSONHelper.respondWithSuccess(res, "Reset password success");
        } catch (Exception e) {
            handleException(res, e);
        }
    }

    private void handleChangePassword(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            AccChangePass accRequest = JSONHelper.parseJSON(JSONHelper.readJSON(req), AccChangePass.class);
            Account currentAcc = accDao.findAccByUsername(accRequest.getUsername());

            if (currentAcc == null) {
            	JSONHelper.respondWithError(res, "Incorrect login information", 400);
                return;
            }

            if (currentAcc.isFirstLogin()) {
                if (!currentAcc.getPassword().equals(accRequest.getPassword())) {
                	JSONHelper.respondWithError(res, "Incorrect login information", 400);
                    return;
                }
                updatePassword(currentAcc.getUsername(), accRequest.getNewPassword(), res);
            } else {
                if (!AccountDAO.checkPassword(accRequest.getPassword(), currentAcc.getPassword())) {
                	JSONHelper.respondWithError(res, "Incorrect login information", 400);
                    return;
                }
                updatePassword(currentAcc.getUsername(), accRequest.getNewPassword(), res);
            }
        } catch (Exception e) {
            handleException(res, e);
        }
    }

    private void updatePassword(String username, String newPassword, HttpServletResponse res) throws IOException {
        accDao.changePassword(username, newPassword);
        JSONHelper.respondWithSuccess(res, "Password updated successfully");
    }

    private String generateForgotPasswordEmailContent() {
        return """
            <div>
                <p style="font-size: 14px;">
                    Dear Instructor,<br>
                    We received a request to reset your password. Click the link below to set a new password:<br>
                    <a href="http://127.0.0.1:5500/index.html#!/resetpassword">Reset Password</a><br>
                    If you did not request this password reset, please ignore this email.<br>
                    Best regards,<br>
                    <strong>FPoly</strong> Support Team
                </p>
            </div>
        """;
    }

    private void handleException(HttpServletResponse res, Exception e) throws IOException {
        e.printStackTrace();
        JSONHelper.respondWithError(res, "Internal server error", 500);
    }
}
