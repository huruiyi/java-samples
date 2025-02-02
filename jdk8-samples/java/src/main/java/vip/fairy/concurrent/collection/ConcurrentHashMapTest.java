package vip.fairy.concurrent.collection;

import com.alibaba.nacos.api.cmdb.pojo.Entity;
import com.alibaba.nacos.api.cmdb.pojo.EntityEvent;
import com.alibaba.nacos.api.cmdb.pojo.EntityEventType;
import com.alibaba.nacos.api.cmdb.pojo.Label;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.jupiter.api.Test;

public class ConcurrentHashMapTest {

  private int index = 1;

  private Map<String, Map<String, Entity>> entityMap = new ConcurrentHashMap<>();

  private Map<String, Label> labelMap = new ConcurrentHashMap<>();

  @Test
  public void Test1() {

    Label label = new Label();
    label.setName("label1");

    Set<String> values = new HashSet<>();
    values.add("x11");
    values.add("x12");
    values.add("x13");
    label.setValues(values);

    labelMap.put(label.getName(), label);

    label = new Label();
    label.setName("label2");

    values = new HashSet<>();
    values.add("x21");
    values.add("x22");
    values.add("x23");
    label.setValues(values);

    labelMap.put(label.getName(), label);

    label = new Label();
    label.setName("label3");

    values = new HashSet<>();
    values.add("x31");
    values.add("x32");
    values.add("x33");
    label.setValues(values);

    labelMap.put(label.getName(), label);

    label = new Label();
    label.setName("label4");

    values = new HashSet<>();
    values.add("x41");
    values.add("x42");
    values.add("x43");
    label.setValues(values);

    labelMap.put(label.getName(), label);

    entityMap.put("ip", new HashMap<String, Entity>());

    Entity entity = new Entity();
    entity.setName("11.11.11.11");
    entity.setType("ip");
    Map<String, String> labels = new HashMap<>();

    labels.put("label1", "x11");
    labels.put("label2", "x21");

    entity.setLabels(labels);

    entityMap.get("ip").put("11.11.11.11", entity);

    entity = new Entity();
    entity.setName("22.22.22.22");
    entity.setType("ip");
    labels = new HashMap<>();

    labels.put("label1", "x12");
    labels.put("label2", "x22");

    entity.setLabels(labels);

    entityMap.get("ip").put("22.22.22.22", entity);

    entity = new Entity();
    entity.setName("33.33.33.33");
    entity.setType("ip");
    labels = new HashMap<>();

    labels.put("label1", "x13");
    labels.put("label2", "x23");
    labels.put("label3", "x33");
    labels.put("label4", "x43");

    entity.setLabels(labels);

    entityMap.get("ip").put("33.33.33.33", entity);

    entity = new Entity();
    entity.setName("44.44.44.44");
    entity.setType("ip");
    labels = new HashMap<>();

    labels.put("label4", "x41");

    entity.setLabels(labels);

    entityMap.get("ip").put("44.44.44.44", entity);

    entity = new Entity();
    entity.setName("66.66.66.66");
    entity.setType("ip");
    labels = new HashMap<>();

    labels.put("label4", "x41");
    labels.put("label2", "x23");

    entity.setLabels(labels);

    entityMap.get("ip").put("66.66.66.66", entity);

    entity = new Entity();
    entity.setName("77.77.77.77");
    entity.setType("ip");
    labels = new HashMap<>();

    labels.put("label3", "x31");
    labels.put("label4", "x43");

    entity.setLabels(labels);

    entityMap.get("ip").put("77.77.77.77", entity);

  }

  void Test2() {
    Entity entity = new Entity();
    entity.setName("8.8.8.8");
    entity.setType("ip");
    Map<String, String> labels = new HashMap<>();

    labels.put("labelA", "a3");
    labels.put("labelB", "b3");

    entity.setLabels(labels);

    entityMap.get("ip").put("8.8.8.8", entity);

    entity = new Entity();
    entity.setName("9.9.9.9");
    entity.setType("ip");
    labels = new HashMap<>();

    labels.put("labelA", "a3");
    labels.put("labelB", "b3");

    entity.setLabels(labels);

    entityMap.get("ip").put("9.9.9.9", entity);
  }

  public Set<String> getLabelNames() {
    Set<String> labelNames = new HashSet<>();
    labelNames.add("label1");
    labelNames.add("label2");
    labelNames.add("label3");
    labelNames.add("label4");
    return labelNames;
  }

  public Set<String> getEntityTypes() {
    Set<String> types = new HashSet<>();
    types.add("ip");
    return types;
  }

  public Label getLabel(String labelName) {
    return labelMap.get(labelName);
  }

  public String getLabelValue(String entityValue, String entityType, String labelName) {
    return entityMap.get(entityType).get(entityValue).getLabels().get(labelName);
  }

  public Map<String, String> getLabelValues(String entityValue, String entityType) {
    return entityMap.get(entityType).get(entityValue).getLabels();
  }

  public Map<String, Map<String, Entity>> getAllEntities() {
    return entityMap;
  }

  public List<EntityEvent> getEntityEvents(long timestamp) {

    Entity entity = new Entity();
    entity.setName("1.1.1.1");
    entity.setType("ip");
    Map<String, String> labels = new HashMap<>();

    labels.put("label1", "x1" + ((index % 3) + 1));
    labels.put("label2", "x2" + ((index++ % 3) + 1));

    entity.setLabels(labels);

    entityMap.get("ip").put("1.1.1.1", entity);

    EntityEvent entityEvent = new EntityEvent();
    entityEvent.setEntityName("1.1.1.1");
    entityEvent.setEntityType("ip");
    entityEvent.setType(EntityEventType.ENTITY_ADD_OR_UPDATE);
    List<EntityEvent> list = new ArrayList<>();
    list.add(entityEvent);

    return list;
  }

  public Entity getEntity(String entityName, String entityType) {
    return entityMap.get(entityType).get(entityName);
  }
}
