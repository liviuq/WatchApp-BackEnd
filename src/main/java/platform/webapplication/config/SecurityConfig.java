package platform.webapplication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import platform.webapplication.controller.LogoutController;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // logout handler
    private final LogoutController logoutController;

    public SecurityConfig(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                // require authentication on all paths except the home page
                .mvcMatchers("/", "/user", "/product","/product/*").permitAll()
                .antMatchers(POST,"/user/register", "/favorites/*","/product/*", "/product").permitAll()
                .antMatchers(DELETE,"/favorites/delete/*", "/homepage/*/delete/*").permitAll()
//                .anyRequest().authenticated()
                // enable users to login with Auth0
                .and().oauth2Login()
                .and().logout()
                // the request path that trigger logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .addLogoutHandler(logoutController);
    }
}
