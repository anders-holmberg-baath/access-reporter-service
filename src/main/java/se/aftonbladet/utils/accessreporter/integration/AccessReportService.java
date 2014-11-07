package se.aftonbladet.utils.accessreporter.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component("accessReportService")
public class AccessReportService {
	private static final String SUBJECT = "AccessReport service report.";

	@Value("${mail.to}")
	private String address;

	@Value("${mail.from}")
	private String sender;

	public Message<InternalMailRepresentation> reportAccess(Message<Report<?>> message) {
		InternalMailRepresentation mailRepresentation
				= new InternalMailRepresentation(address, sender, SUBJECT, message.getPayload());
		return MessageBuilder.withPayload(mailRepresentation)
				.build();
	}
}
