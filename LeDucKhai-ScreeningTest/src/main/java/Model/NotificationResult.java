package Model;

public class NotificationResult {
	private int status;
	private Object message;

	public NotificationResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotificationResult(int status, Object message) {
		super();
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

}
