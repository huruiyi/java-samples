package vip.fairy.xml.ch.ch12.JSONPDemo.v13;

import static java.lang.System.err;
import static java.lang.System.out;

import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.json.Json;
import javax.json.stream.JsonParser;

public class JSONPDemo {

  public static void main(String[] args)
      throws FileNotFoundException {
    if (args.length != 1) {
      err.println("usage: java JSONPDemo filespec");
      return;
    }
    JsonParser parser =
        Json.createParser(new FileReader(args[0]));
    while (parser.hasNext()) {
      if (parser.next() == JsonParser.Event.START_ARRAY) {
        parser.getArrayStream()
            // convert to Stream<JsonObject>
            .map(v -> v.asJsonObject())
            .filter(obj -> obj.getString("state")
                .equals("avail"))
            .limit(5)
            .forEach(obj ->
                out.printf("ISBN: %s, " +
                        "TITLE: %s%n%n",
                    obj.getString("isbn"),
                    obj.getString("title")));

        // skip the rest of the JsonArray

        parser.skipArray();
      }
    }
  }
}
