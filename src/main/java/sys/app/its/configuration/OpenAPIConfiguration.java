package sys.app.its.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfiguration {
	Contact contact = new Contact().name("Nehemias C. Belong JR.").email("nehemiasbelong@gmail.com")
			.url("https://github.com/agentorange002");

	@Bean
	public OpenAPI customConfiguration() {
		return new OpenAPI().components(new Components()).info(new Info().title("Issue Tracking System")
				.version("v.0.1.0").description("Issue Tracking System REST API Services - BackEnd").contact(contact));
	}

}
