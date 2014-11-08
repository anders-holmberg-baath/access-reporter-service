package se.aftonbladet.utils.accessreporter.integration;

public class FacebookOpenGraphReport implements Report<FacebookOpenGraphReportModel> {
	private final String service;
	private final String template;
	private final FacebookOpenGraphReportModel model;

	public FacebookOpenGraphReport(final String service, final String template, final FacebookOpenGraphReportModel model) {
		this.service = service;
		this.template = template;
		this.model = model;
	}

	@Override
	public String getService() {
		return service;
	}

	@Override
	public String getTemplate() {
		return template;
	}

	@Override
	public FacebookOpenGraphReportModel getModel() {
		return model;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final FacebookOpenGraphReport report = (FacebookOpenGraphReport) o;

		if (!model.equals(report.model)) return false;
		if (!template.equals(report.template)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = template.hashCode();
		result = 31 * result + model.hashCode();
		return result;
	}
}
