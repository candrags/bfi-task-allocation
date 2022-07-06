package id.co.bfi.bfitaskallocation.config;

import id.co.bfi.bfitaskallocation.auth.AuthenticationFilter;
import id.co.bfi.bfitaskallocation.auth.SecurityContextService;
import id.co.bfi.bfitaskallocation.constant.SecurityConstants.AuthenticationClaim;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private SecurityContextService authenticationService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .csrf()
      .disable()
      // make sure we use stateless session; session won't be used to store user's
      // state.
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      // handle an authorized attempts (if we happen to have any other custom
      // response)
      .exceptionHandling()
      .authenticationEntryPoint((request, response, authError) ->
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
      )
      .and()
      // Add a filter to validate the tokens with every request (service will take
      // care of that)
      .addFilterAfter(
        // This is where we hook our auth filter
        new AuthenticationFilter(authenticationService),
        UsernamePasswordAuthenticationFilter.class
      )
      // Authorization requests config
      .authorizeRequests()
      // Enabled swagger end points
      .antMatchers("/api-docs", "/api-docs/**", "/configuration/**", "/swagger*/**", "/webjars/**")
      .permitAll()
      // Allow OPTIONS call for preflight requests
      .antMatchers(HttpMethod.OPTIONS)
      .permitAll()
      // Allow internal endpoints for service users only !
      .antMatchers("/internal/", "/internal/**")
      .hasAuthority(AuthenticationClaim.SYSTEM_SERVICE.toString())
      // Only registration claim should be able to register
      .antMatchers(HttpMethod.POST, "/")
      .hasAuthority(AuthenticationClaim.REGISTRATION.toString())
      // All others should have LOGIN claim
      .antMatchers("/**")
      .hasAuthority(AuthenticationClaim.LOGIN.toString())
      .anyRequest()
      .authenticated();
  }
}
