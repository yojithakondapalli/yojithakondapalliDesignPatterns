package design_patterns.behavioral;
class PhoneCall {
	   private int callId;
	   private String callerNumber;
	   private PhoneCallResponse call;

	   public PhoneCall(int callId, String callerNumber, PhoneCallResponse call) {
	      super();
	      this.callId = callId;
	      this.callerNumber = callerNumber;
	      this.call = call;
	   }

	   @Override
	   public String toString() {
	      return "PhoneCall [callId=" + callId + ", callerNumber=" + callerNumber
	         + ", call=" + call + "]";
	   }
	}

	 enum PhoneCallResponse {
	   ACCEPTED,
	   REJECTED
	}

	 enum Status {
	   ONDESK,
	   OFFDESK
	}

	 abstract class CallHandlerBase {
	   protected CallHandlerBase redirectedTo;
	   protected Status status = Status.ONDESK;
	   public Status getStatus() {
	      return status;
	   }
	   public void setStatus(Status status) {
	      this.status = status;
	   }
	   public void setRedirect(CallHandlerBase r) {
	      redirectedTo = r;
	   }
	   public abstract PhoneCallResponse response(PhoneCall call);

	}

	 class ReceptionHandler extends CallHandlerBase {

	   @Override
	   public PhoneCallResponse response(PhoneCall call) {
	      if (status == Status.ONDESK) {
	         System.out.println("Call:"+call.toString()+" received by the reception");
	         return PhoneCallResponse.ACCEPTED;
	      }
	      if (redirectedTo != null) {
	         return redirectedTo.response(call);
	      }
	      return PhoneCallResponse.REJECTED;
	   }
	}

	 class AdministrativeOfficeHandler extends CallHandlerBase {

	   @Override
	   public PhoneCallResponse response(PhoneCall call) {
	      if (status == Status.ONDESK) {
	         System.out.println("Call:" + call.toString()
	            + " received by the Office Administration");
	         return PhoneCallResponse.ACCEPTED;
	      }
	      if (redirectedTo != null) {
	         return redirectedTo.response(call);
	      }
	       return PhoneCallResponse.REJECTED;
	   }
	}

	 class ManagerHandler extends CallHandlerBase {

	   @Override
	   public PhoneCallResponse response(PhoneCall call) {
	      if (status==Status.ONDESK) {
	         System.out.println("Call:"+call.toString()+" received by the Manager");
	         return PhoneCallResponse.ACCEPTED;
	      }
	      if (redirectedTo != null) {
	         return redirectedTo.response(call);
	      }
	      return PhoneCallResponse.REJECTED;
	   }
	}

	 class AutomatedSpeakerHandler extends CallHandlerBase {

	   @Override
	   public PhoneCallResponse response(PhoneCall call) {
	      System.out.println("Busy! "+call.toString()+" Please call later");
	      return PhoneCallResponse.ACCEPTED;
	   }
	}

	public class RespChain{

	    public static void main(String[] args) {
	      CallHandlerBase reception = new ReceptionHandler();
	      CallHandlerBase admin = new AdministrativeOfficeHandler();
	      CallHandlerBase manager = new ManagerHandler();
	      CallHandlerBase auto = new AutomatedSpeakerHandler();

	      reception.setRedirect(admin);
	      admin.setRedirect(manager);
	      manager.setRedirect(auto);

	      PhoneCall call1 = new PhoneCall(1, "9876543210",
	         PhoneCallResponse.ACCEPTED);
	      PhoneCall call2 = new PhoneCall(2, "9182736451",
	         PhoneCallResponse.ACCEPTED);

	      reception.setStatus(Status.ONDESK);

	      reception.response(call1);

	      reception.setStatus(Status.OFFDESK);
	      admin.setStatus(Status.OFFDESK);
	      manager.setStatus(Status.OFFDESK);
	      reception.response(call2);
	   }
	}

