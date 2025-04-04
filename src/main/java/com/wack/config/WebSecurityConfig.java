package com.wack.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userService")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/", "/index.html", "/assets/*", "/static/index.html", "/home", "/about").permitAll()
                .antMatchers("/favicon.ico","/*.js","/*.jpg","/*.css","/*.png","/*.js.map").permitAll()
                .antMatchers("/service/token/*").permitAll()
                .antMatchers("/service/user/forgot/*").permitAll() 
                .antMatchers("/service/com.wack.model.website.SectionItem/allByCriteriaAndOrderBy").permitAll()
                .antMatchers("/service/com.wack.model.website.Testimony/allByCriteriaAndOrderBy").permitAll()
                .antMatchers("/service/com.wack.model.website.Section/allByCriteriaAndOrderBy").permitAll()
                .antMatchers("/service/com.wack.model.website.SectionItem/allByCriteria").permitAll()
                .antMatchers("/service/com.wack.model.website.Section/allByCriteria").permitAll()
                .antMatchers("/service/com.wack.model.website.Slider/allByCriteria").permitAll()
                .antMatchers("/service/com.wack.poll.Poll/allByCriteriaAndOrderBy").permitAll()                
                .antMatchers("/service/crud/MailingList/save").permitAll() 
                .antMatchers("/service/crud/Comment/save").permitAll()
                .antMatchers("/service/crud/News/save").permitAll() 
                .antMatchers("/service/com.wack.model.News/allByCriteriaAndOrderByAndFiles").permitAll() 
                .antMatchers("/service/com.wack.model.Project/*").permitAll()
                .antMatchers("/service/crud/Project/save").permitAll()
                .antMatchers("/service/payment/saveTransaction").permitAll()
                .antMatchers("/service/crud/Transaction/save").permitAll()
                .antMatchers("/service/com.wack.model.Project/allByCriteriaAndOrderByAndFiles").permitAll() 
                .antMatchers("/service/com.wack.model.ProjectDesc/allByCriteriaAndOrderByAndFiles").permitAll() 
                .antMatchers("/service/com.wack.poll.PollDesc/allByCriteriaAndOrderByAndFiles").permitAll() 
                .antMatchers("/service/com.wack.poll.PollQuestionDesc/allByCriteriaAndOrderByAndFiles").permitAll() 
                .antMatchers("/service/com.wack.poll.PollChoice/allByCriteriaAndOrderBy").permitAll()
                .antMatchers("/service/com.wack.poll.PollChoiceDesc/allByCriteriaAndOrderBy").permitAll()
                .antMatchers("/service/com.wack.poll.PollChoiceDesc/allByCriteriaAndOrderByAndFiles").permitAll() 
                .antMatchers("/service/poll/getPendingPollQuestions/*/*").permitAll()
                .antMatchers("/service/com.wack.model.Project/withChildsAndFiles/*").permitAll()
                .antMatchers("/service/com.wack.model.website.MailingList/allByCriteriaAndOrderBy").permitAll()             
                .antMatchers("/service/com.wack.model.News/withChildsAndFiles/*").permitAll()
                .antMatchers("/service/com.wack.model.News/allByCriteriaAndOrderByAndFiles").permitAll()                
                .antMatchers("/service/com.wack.model.website.SliderText/allByCriteria").permitAll()
                .antMatchers("/service/com.wack.model.website.SliderText/allByCriteriaAndOrderBy").permitAll()
                .antMatchers("/service/Employee/allByCriteriaAndOrderBy").permitAll()  
                .antMatchers("/service/Employee/allByCriteria").permitAll()  
                .antMatchers("/service/com.wack.model.website.Comment/allByCriteriaAndOrderBy").permitAll()  
                .antMatchers("/service/com.wack.model.News/*").permitAll()
                .antMatchers("/service/Company/*").permitAll()
                .antMatchers("/service/Company/allByCriteriaAndOrderBy").permitAll()
                .antMatchers("/service/com.wack.model.website.CompanyHistory/allByCriteriaAndOrderBy").permitAll()
                .antMatchers("/service/Company/allByCriteria").permitAll()
                .antMatchers("/service/com.wack.model.website.CompanyHistory/allByCriteria").permitAll()
                .antMatchers("/service/Location/allByCriteriaAndOrderBy").permitAll()    
                .antMatchers("/service/*/allByCriteriaAndOrderBy").permitAll()          
                .antMatchers("/service/Department/all").permitAll()
                .antMatchers("/service/UserGroup/all").permitAll()
                .antMatchers("/service/User/withChildsAndFiles/*").permitAll()
                .antMatchers("/service/ContactUsMessage/save").permitAll()
                .antMatchers("/service/crud/ContactUsMessage/save").permitAll()
                .antMatchers("/service/StatusText/allByCriteriaAndOrderByAndFiles").permitAll()
                .antMatchers("/service/Transaction/allByCriteriaAndOrderByAndFiles").permitAll()
                .antMatchers("/service/Regulation/allByCriteriaAndOrderByAndFiles").permitAll()
                .antMatchers("/service/JobPosition/withChildsAndFiles/*").permitAll()
                .antMatchers("/service/JobPositionDesc/withChildsAndFiles/*").permitAll()
                .antMatchers("/service/JobAppli/withChildsAndFiles/*").permitAll()
                .antMatchers("/service/crud/JobAppli/saveWithFile").permitAll()
                .antMatchers("/service/project/getChart").permitAll()
                .antMatchers("/service/Project/withChildsAndFiles/*").permitAll()
                .antMatchers("/service/com.wack.model.stock.Project/allByCriteriaAndOrderBy").permitAll()
                .antMatchers("/service/com.wack.model.stock.PaymentType/allByCriteriaAndOrderBy").permitAll()
                .antMatchers("/service/user/*/saveWithoutPicture").permitAll()
                .antMatchers("/service/project/saveCompany").permitAll()
                .antMatchers("/procon/assets/*").permitAll()
                .antMatchers("/service/Payment/stripe-key").permitAll()
                .antMatchers("/service/Payment/clientSecret").permitAll()
                .antMatchers("/service/ProjectDesc/allByCriteriaAndOrderByAndFiles").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
