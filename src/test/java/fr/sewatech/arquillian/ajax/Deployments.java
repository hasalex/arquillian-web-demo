package fr.sewatech.arquillian.ajax;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

import java.io.File;

public class Deployments {
    public static final String WEBAPP_SRC = "src/main/webapp";

    public static Archive<?> deploy() {
        File[] requiredLibraries = Maven.resolver().loadPomFromFile("pom.xml")
                .resolve("com.google.guava:guava", "org.codehaus.jackson:jackson-mapper-asl")
                .withTransitivity().asFile();

        return ShrinkWrap.create(WebArchive.class)
                .addAsLibraries(requiredLibraries)
                .addPackages(true, JsonServlet.class.getPackage())
                .addAsResource("devoxx-2013-talks.json")
                .addAsResource("mixit-2013-talks.json")
                .as(ExplodedImporter.class).importDirectory(WEBAPP_SRC).as(WebArchive.class);
    }
}
