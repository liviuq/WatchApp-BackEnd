package platform.webapplication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LogoutController extends SecurityContextLogoutHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    public LogoutController(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       Authentication authentication) {

        // Invalidate the session and clear the security context
        super.logout(httpServletRequest, httpServletResponse, authentication);

        // Build the URL to log the user out of Auth0 and redirect them to the home page
        String issuer = (String) getClientRegistration().getProviderDetails().getConfigurationMetadata().get("issuer");
        String clientId = getClientRegistration().getClientId();
        String returnTo = ServletUriComponentsBuilder.fromCurrentContextPath().build().toString();

        String logoutUrl = UriComponentsBuilder
                .fromHttpUrl(issuer + "v2/logout?client_id={clientId}&returnTo={returnTo}")
                .encode()
                .buildAndExpand(clientId, returnTo)
                .toUriString();

        log.info("Will attempt to redirect to logout URL: {}", logoutUrl);
        try {
            httpServletResponse.sendRedirect(logoutUrl);
        } catch (IOException ioe) {
            log.error("Error redirecting to logout URL", ioe);
            ioe.printStackTrace();
        }
    }

    /**
     * get the registered client ID and issuer
     */
    private ClientRegistration getClientRegistration() {
        return this.clientRegistrationRepository.findByRegistrationId("auth0");
    }
}