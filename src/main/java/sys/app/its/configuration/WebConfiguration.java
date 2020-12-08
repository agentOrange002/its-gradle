/*
 * package sys.app.its.configuration;
 * 
 * import org.springframework.context.annotation.Configuration; import
 * org.springframework.web.servlet.config.annotation.CorsRegistry; import
 * org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 * 
 * @Configuration public class WebConfiguration implements WebMvcConfigurer {
 * 
 * @Override public void addCorsMappings(CorsRegistry registry) {
 * registry.addMapping("/*").allowCredentials(true)
 * .allowedHeaders("Authorization", "Cache-Control", "Content-Type", "Accept",
 * "X-Requested-With", "Access-Control-Allow-Origin",
 * "Access-Control-Allow-Headers", "Origin") .exposedHeaders("Authorization",
 * "UserID").allowedMethods("*").allowedOrigins("*"); } }
 */