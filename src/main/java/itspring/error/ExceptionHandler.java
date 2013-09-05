package itspring.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Throwables;

/**
 * General error handler for the application.
 */
@ControllerAdvice
class ExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);


    /**
	 * Handle exceptions thrown by handlers.
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)	
	public ModelAndView exception(Exception exception, WebRequest request) {
        log.error("Server side error", exception);
		ModelAndView modelAndView = new ModelAndView("generalError");
		modelAndView.addObject("errorMessage", Throwables.getRootCause(exception));
		return modelAndView;
	}
}