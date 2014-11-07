package se.aftonbladet.utils.accessreporter.integration;

public class InternalMailRepresentation {
	private final String address;
	private final String sender;
	private final String subject;
	private final Report report;

	public InternalMailRepresentation(final String address, final String sender, final String subject, final Report report) {
		this.address = address;
		this.sender = sender;
		this.subject = subject;
		this.report = report;
	}

	public String getAddress() {
		return address;
	}

	public String getSender() {
		return sender;
	}

	public String getSubject() {
		return subject;
	}

	public Report getReport() {
		return report;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final InternalMailRepresentation that = (InternalMailRepresentation) o;

		if (!address.equals(that.address)) return false;
		if (!report.equals(that.report)) return false;
		if (!sender.equals(that.sender)) return false;
		if (!subject.equals(that.subject)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = address.hashCode();
		result = 31 * result + sender.hashCode();
		result = 31 * result + subject.hashCode();
		result = 31 * result + report.hashCode();
		return result;
	}
}
