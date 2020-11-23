package org.masbas.idvn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

//@Configuration
public class ThemeConfig {
//	@Bean
//	public ViewResolver htmlViewResolver() {
//		String[] ext = {"*.html"};
//	    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//	    resolver.setTemplateEngine(templateEngine(htmlTemplateResolver()));
//	    resolver.setContentType("text/html");
//	    resolver.setCharacterEncoding("UTF-8");
//	    resolver.setViewNames(ext);
//	    return resolver;
//	}
//
//	private ITemplateResolver htmlTemplateResolver() {
//	    SpringResourceTemplateResolver resolver
//	      = new SpringResourceTemplateResolver();
//	    resolver.setApplicationContext(applicationContext);
//	    resolver.setPrefix("/WEB-INF/views/");
//	    resolver.setCacheable(false);
//	    resolver.setTemplateMode(TemplateMode.HTML);
//	    return resolver;
//	}
//	 
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	    registry.addResourceHandler("/resources/**", "/css/**")
//	      .addResourceLocations("/WEB-INF/resources/", "/WEB-INF/css/");
//	}
	
//	@Bean
//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(thymeleafTemplateResolver());
//        return templateEngine;
//    }
// 
//    @Bean
//    public SpringResourceTemplateResolver thymeleafTemplateResolver() {
//        SpringResourceTemplateResolver templateResolver 
//          = new SpringResourceTemplateResolver();
//        templateResolver.setPrefix("/templates/content/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode("HTML5");
//        return templateResolver;
//    }
//    
//    @Bean
//    public ThymeleafViewResolver thymeleafViewResolver() {
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(templateEngine());
//        return viewResolver;
//    }
}
