package se.aftonbladet.utils.accessreporter.front;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import se.aftonbladet.utils.accessreporter.integration.FacebookOpenGraphReportModel;
import se.aftonbladet.utils.accessreporter.integration.FacebookOpenGraphReport;
import se.aftonbladet.utils.accessreporter.integration.Report;

import java.net.URI;

@Controller
@RequestMapping("/facebook-open-graph-api")
public class FacebookOpenGraphMockController {
	private static final String SERVICE_NAME = "Facebook Open Graph API Mock";
	private static final String TEMPLATE_FILENAME = "templates/facebook-open-graph-template.vm";
	private static final DateTimeZone TIMEZONE = DateTimeZone.forID("Europe/Stockholm");
	private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
			.appendYear(4, 4)
			.appendLiteral("-")
			.appendMonthOfYear(2)
			.appendLiteral("-")
			.appendDayOfMonth(2)
			.appendLiteral(" ")
			.appendHourOfDay(2)
			.appendLiteral(":")
			.appendMinuteOfHour(2)
			.appendLiteral(":")
			.appendSecondOfMinute(2)
			.toFormatter();

	@Autowired
	private MessageChannel accessReportChannel;

	@RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
	public ResponseEntity<String> openGraphMock(@RequestParam URI id, @RequestParam Boolean scrape) {
		FacebookOpenGraphReportModel model = new FacebookOpenGraphReportModel(
				FORMATTER.print(DateTime.now(TIMEZONE)),
				id.toASCIIString(),
				Boolean.toString(scrape)
		);

		Report report = new FacebookOpenGraphReport(SERVICE_NAME, TEMPLATE_FILENAME, model);
		Message<Report> message = MessageBuilder.withPayload(report)
				.build();
		accessReportChannel.send(message);

		return new ResponseEntity<String>("request registered", HttpStatus.OK);
	}
}
