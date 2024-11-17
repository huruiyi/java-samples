package vip.fairy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import vip.fairy.config.TestConfig;

/**
 * Tests for XML config with placeholders in bean definitions.
 *
 * @author Madhura Bhave
 */
@SpringBootTest(classes = {
    SampleSpringXmlApplication.class,
    TestConfig.class
})
@ExtendWith(OutputCaptureExtension.class)
class SampleSpringXmlPlaceholderBeanDefinitionTests {

  @Test
  void beanWithPlaceholderShouldNotFail(CapturedOutput output) {
    assertThat(output).contains("Hello Other World");
  }


}
