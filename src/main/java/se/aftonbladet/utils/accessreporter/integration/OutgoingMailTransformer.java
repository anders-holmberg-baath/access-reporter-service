package se.aftonbladet.utils.accessreporter.integration;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.util.Collections;
import java.util.Map;

@Component
public class OutgoingMailTransformer {
	@Autowired
	private VelocityEngine velocityEngine;

	public Message<SimpleMailMessage> transform(Message<InternalMailRepresentation> message) {
		InternalMailRepresentation payload = message.getPayload();
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(payload.getAddress());
		mailMessage.setFrom(payload.getSender());
		mailMessage.setSubject(payload.getSubject());

		Map<String, ReportModel> context
				= Collections.singletonMap("model", payload.getReport().getModel());

		StringWriter evaluatedTemplate = new StringWriter();
		Template template = velocityEngine.getTemplate(payload.getReport().getTemplate());
		template.merge(new VelocityContext(context), evaluatedTemplate);
		mailMessage.setText(evaluatedTemplate.toString());

		return MessageBuilder.withPayload(mailMessage)
				.build();
	}
}
