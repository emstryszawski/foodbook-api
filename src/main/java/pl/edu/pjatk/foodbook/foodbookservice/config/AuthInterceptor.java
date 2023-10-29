package pl.edu.pjatk.foodbook.foodbookservice.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getCredentials();
        template.header("Authorization", "Bearer " + authentication);
    }

}
