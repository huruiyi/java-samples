package vip.fairy.xml.ch.ch09.GsonDemo.v14;

import static java.lang.System.out;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GsonDemo {

  public static void main(String[] args) {
    Gson gson = new Gson();
    Country c =
        new Country("England", 53012456 /* 2011 census */,
            "London", "Birmingham", "Cambridge");
    String json = gson.toJson(c);
    out.println(json);
    c = gson.fromJson(json, c.getClass());
    out.printf("Name = %s%n", c.name);
    out.printf("Population = %d%n", c.population);
    out.print("Cities = ");
    for (String city : c.cities) {
      out.print(city + " ");
    }
    out.println();
  }

  @JsonAdapter(CountryAdapter.class)
  static
  class Country {

    String name;
    int population;
    String[] cities;

    Country() {
    }

    Country(String name, int population, String... cities) {
      this.name = name;
      this.population = population;
      this.cities = cities;
    }
  }

  static
  class CountryAdapter extends TypeAdapter<Country> {

    @Override
    public Country read(JsonReader in) throws IOException {
      out.println("read() called");
      Country c = new Country();
      List<String> cities = new ArrayList<>();
      in.beginObject();
      while (in.hasNext()) {
        switch (in.nextName()) {
          case "name":
            c.name = in.nextString();
            break;

          case "population":
            c.population = in.nextInt();
            break;

          case "cities":
            in.beginArray();
            while (in.hasNext()) {
              cities.add(in.nextString());
            }
            in.endArray();
            c.cities = cities.toArray(new String[0]);
        }
      }
      in.endObject();
      return c;
    }

    @Override
    public void write(JsonWriter out, Country c)
        throws IOException {
      System.out.println("write() called");
      out.beginObject();
      out.name("name").value(c.name);
      out.name("population").value(c.population);
      out.name("cities");
      out.beginArray();
      for (int i = 0; i < c.cities.length; i++) {
        out.value(c.cities[i]);
      }
      out.endArray();
      out.endObject();
    }
  }
}
