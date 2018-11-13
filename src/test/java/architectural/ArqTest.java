package architectural;

import org.junit.Test;
import org.springframework.web.bind.annotation.RestController;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "")
public class ArqTest {
	
	@Test
	public void All_controller_have_REST_Annotation() {
		JavaClasses classes = new ClassFileImporter().importPackages("service");
		ArchRule myRule = classes().that().haveSimpleNameEndingWith("Controller").
				should().beAnnotatedWith(RestController.class);

		    myRule.check(classes);
	}
}
