package pojoForRestAssure;

public class BookingDetails {
	
	private String firstname;
	private String lastname;
	private double totalprice;
	private boolean depositpaid;
	private BookingDates bookingdates;
	private String additionalneeds;
	
	public BookingDetails() {
		
	}
	
	public BookingDetails(String firstname, String lastname, double totalprice, boolean depositpad, BookingDates bookingdates, String additionalneeds) {
	
		this.firstname=firstname;
		this.lastname=lastname;
		this.totalprice=totalprice;
		this.depositpaid=depositpad;
		this.bookingdates=bookingdates;
		this.additionalneeds=additionalneeds;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public boolean isDepositpaid() {
		return depositpaid;
	}

	public BookingDates getBookingdates() {
		return bookingdates;
	}

	public String getAdditionalneeds() {
		return additionalneeds;
	}

	
}
