package se.aftonbladet.utils.accessreporter.integration;

public interface Report<T extends ReportModel> {
	String getService();

	String getTemplate();

	T getModel();
}
