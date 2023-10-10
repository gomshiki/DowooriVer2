//package com.jun.DowooriVer2.securingweb;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import sun.security.util.Password;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean // security 5.7.0-M2  부터 WebSecurityConfigureAdapter 지원 X
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable() // CSRF토큰 비활성화
//                .authorizeHttpRequests()
//                    .antMatchers("/", "/css/**" , "/vendor/**", "/register").permitAll()
//                    .anyRequest().authenticated() // 나머지 요청은 허용된(로그인된) 인원만 사용 가능
//                    .and()
//                .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
//                    .and()
//                .logout()
//                    .permitAll();
//
//        return http.build();
//    }
//
//
//    public void configureGlobal(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder())
//                .usersByUsernameQuery("select username, password, enabled, email, create_time "
//                        + "from account_table "
//                        + "where email = ?")
//                .authoritiesByUsernameQuery("select user_name, name  "
//                        + "from user_role_table ur inner join account_table at on ur.role_id = at.account_id "
//                        + "inner join role_table rt on ur.user_id = rt.role_id "
//                        + "where email = ?");
//    }
//
//    //Authorization = 권한
//    //Authentication = 로그인
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//}