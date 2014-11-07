package se.aftonbladet.utils.accessreporter.integration;

public class FacebookOpenGraphModel implements ReportModel {
	private final String dateTime;
	private final String id;
	private final String scrape;

	public FacebookOpenGraphModel(final String dateTime, final String id, final String scrape) {
		this.dateTime = dateTime;
		this.id = id;
		this.scrape = scrape;
	}

	public String getDateTime() {
		return dateTime;
	}

	public String getId() {
		return id;
	}

	public String getScrape() {
		return scrape;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final FacebookOpenGraphModel that = (FacebookOpenGraphModel) o;

		if (!dateTime.equals(that.dateTime)) return false;
		if (!id.equals(that.id)) return false;
		if (!scrape.equals(that.scrape)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = dateTime.hashCode();
		result = 31 * result + id.hashCode();
		result = 31 * result + scrape.hashCode();
		return result;
	}
}
