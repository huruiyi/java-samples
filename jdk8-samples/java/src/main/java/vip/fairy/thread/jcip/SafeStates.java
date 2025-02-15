package vip.fairy.thread.jcip;

import java.util.HashMap;
import java.util.Map;
import vip.fairy.thread.jcip.annotations.ThreadSafe;

/**
 * SafeStates
 * <p/>
 * Initialization safety for immutable objects
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class SafeStates {

  private final Map<String, String> states;

  public SafeStates() {
    states = new HashMap<String, String>();
    states.put("alaska", "AK");
    states.put("alabama", "AL");
    /*...*/
    states.put("wyoming", "WY");
  }

  public String getAbbreviation(String s) {
    return states.get(s);
  }
}
