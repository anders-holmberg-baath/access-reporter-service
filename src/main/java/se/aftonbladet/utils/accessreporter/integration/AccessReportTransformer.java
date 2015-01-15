package se.aftonbladet.utils.accessreporter.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

public class AccessReportTransformer implements GenericTransformer<Message<Report<?>>, Message<InternalMailRepresentation>> {
	private static final String SUBJECT = "AccessReport service report.";

	@Value("${mail.headers.to}")
	private String address;

	@Value("${mail.headers.from}")
	private String sender;

	@Override
	public Message<InternalMailRepresentation> transform(Message<Report<?>> message) {
		InternalMailRepresentation mailRepresentation
				= new InternalMailRepresentation(address, sender, SUBJECT, message.getPayload());
		return MessageBuilder.withPayload(mailRepresentation)
				.build();
	}
}
