package com.github.eeriefoods.snsserver;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "SchupfnudelSalat",
                description = "Studentenverwaltung für Java-Vorlesung",
                version = "v1",
                contact = @Contact(
                        url = "https://github.com/JayReturns",
                        name = "Jan-Luca",
                        email = "jan-luca-wolf@web.de"
                ),
                license = @License(
                        name = "GNU General Public License v3.0",
                        url = "https://www.gnu.de/documents/gpl-3.0.en.html"
                )
        )
)
@SpringBootApplication
public class SnsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnsServerApplication.class, args);
    }

}
