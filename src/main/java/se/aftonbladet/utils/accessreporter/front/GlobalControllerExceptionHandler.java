package se.aftonbladet.utils.accessreporter.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger("APPLICATION_LOGGER");

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> handleException(HttpServletRequest req, Exception e) throws Exception {
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		}

		LOGGER.error("exception: ", e);

		return new ResponseEntity<String>("error", HttpStatus.I_AM_A_TEAPOT);
	}
}
