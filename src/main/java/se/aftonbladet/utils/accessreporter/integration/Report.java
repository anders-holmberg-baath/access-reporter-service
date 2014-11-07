package se.aftonbladet.utils.accessreporter.integration;

import java.util.Map;

public class Report {
	private final String service;
	private final String template;
	private final Map<String, Object> context;

	public Report(final String service, final String template, final Map<String, Object> context) {
		this.service = service;
		this.template = template;
		this.context = context;
	}

	public String getService() {
		return service;
	}

	public String getTemplate() {
		return template;
	}

	public Map<String, Object> getContext() {
		return context;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final Report report = (Report) o;

		if (!context.equals(report.context)) return false;
		if (!template.equals(report.template)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = template.hashCode();
		result = 31 * result + context.hashCode();
		return result;
	}
}
