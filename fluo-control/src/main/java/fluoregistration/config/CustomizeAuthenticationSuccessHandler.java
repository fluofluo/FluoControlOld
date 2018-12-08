package fluoregistration.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

//class for the custom login success handler
//class as a Spring component which implements Spring Security AuthenticationSuccessHandler by add this annotation and implements
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	

	//override method for custom landing page after successful login
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
	        HttpServletResponse response, Authentication authentication)
	        throws IOException, ServletException {
	    //set our response to OK status
	    response.setStatus(HttpServletResponse.SC_OK);

	    for (GrantedAuthority auth : authentication.getAuthorities()) {
	        if ("ADMIN".equals(auth.getAuthority())) {
	            response.sendRedirect("/admin_dashboard");
	        }
	        else if ("USER".equals(auth.getAuthority())) {
	            response.sendRedirect("/user_dashboard");
	        }
	        else if ("HYPERADMIN".equals(auth.getAuthority())) {
	            response.sendRedirect("/hyperadmin_dashboard");
	        }
	    }
	}
}
