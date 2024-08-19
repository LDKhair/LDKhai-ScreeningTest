package Helper;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import Model.NotificationResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JSONHelper {
	private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.registerModule(new Hibernate5Module());
    }

    public static void writeJsonResponse(HttpServletResponse res, Object data) throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        String jsonResponse = mapper.writeValueAsString(data);
        res.getWriter().write(jsonResponse);
    }
    
    public static <T> T parseJSON(String json, Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }
    
    public static String readJSON(HttpServletRequest req) {
		StringBuilder jsonBuilder = new StringBuilder();
		String line;
		try {			
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null) {
				jsonBuilder.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonBuilder.toString();
    }
    
    public static void respondWithError(HttpServletResponse res, String message, int statusCode) throws IOException {
        JSONHelper.writeJsonResponse(res, new NotificationResult(statusCode, message));
    }
    
    public static void respondWithSuccess(HttpServletResponse res, String message) throws IOException {
        JSONHelper.writeJsonResponse(res, new NotificationResult(200, message));
    }
}
