import com.apress.prospring4.ch2.beans.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans.xml"})
public class TestDaoDI {

  @Autowired
  protected Person chinese;

  @Autowired
  protected Person american;

  @Test
  public void saveDataCenter() throws Exception {
    chinese.Speak();
    american.Speak();
  }

}
