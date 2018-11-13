package architectural;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.junit.Test;
import org.springframework.web.bind.annotation.RestController;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;

import service.AuctionController;

import org.springframework.stereotype.Service;

public class ArqTest {
	
	@Test
	public void All_controller_have_REST_Annotation() {
		JavaClasses classes = new ClassFileImporter().importPackages("service");
		ArchRule myRule = classes().that().haveSimpleNameEndingWith("Controller").
				should().beAnnotatedWith(RestController.class);

		    myRule.check(classes);
	}
	
	
	@Test
	public void All_services_have_Service_Annotation() {
		JavaClasses classes = new ClassFileImporter().importPackages("service");
		ArchRule myRule = classes().that().haveSimpleNameEndingWith("Service").
				should().beAnnotatedWith(Service.class);

		    myRule.check(classes);
	}
	
}
