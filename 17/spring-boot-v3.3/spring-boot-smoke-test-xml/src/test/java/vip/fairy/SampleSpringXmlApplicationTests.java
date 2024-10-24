package vip.fairy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@ExtendWith(OutputCaptureExtension.class)
class SampleSpringXmlApplicationTests {

  @Test
  void testDefaultSettings(CapturedOutput output) {
    SampleSpringXmlApplication.main(new String[0]);
    assertThat(output).contains("Hello World");
  }

}
