package com.concat.projetointegrador.configuration;

import com.concat.projetointegrador.security.CustomAuthenticationFilter;
import com.concat.projetointegrador.security.CustomAuthorizationFIlter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

		private final UserDetailsService userDetailsService;
		private final BCryptPasswordEncoder bCryptPasswordEncoder;

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
				auth.userDetailsService(this.userDetailsService).passwordEncoder(bCryptPasswordEncoder);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
				String[]  WHITELIST = {
								"/**/login",
								"/**/seller",
								"/**/buyer",
								"/**/refresh-token"
				};

				http.csrf().disable();
				http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

				http.authorizeRequests()
								.antMatchers(WHITELIST)
								.permitAll()
								.antMatchers("/supervisor")
									.hasAuthority("ADMIN")
								.antMatchers("/inboundorder")
								.hasAuthority("Supervisor")
								.anyRequest().authenticated();

				http.addFilterBefore(new CustomAuthorizationFIlter(), UsernamePasswordAuthenticationFilter.class);
				http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
		}

		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception{
				return super.authenticationManagerBean();
		}

}
