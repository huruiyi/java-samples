package com.example.demo;


import com.example.demo.model.Topic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DataserviceApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;



    @Test
    public void testBasicSearch() {
        Topic[] topics = this.restTemplate.getForObject("/topics?searchString=spring", Topic[].class);
        for (Topic topic : topics) {
            System.out.println(topic);
        }
    }

    @Test
    public void testBasicPost() {
        Topic topic = new Topic();
        String name = "The new topic";
        String desc = "New topic description";
        String tfield1 = "Text field 1";
        String tfield2 = "Text field 2";
        topic.setName(name);
        topic.setDescription(desc);
        topic.setTextField1(tfield1);
        topic.setTextField2(tfield2);
        Topic createdTopic = this.restTemplate.postForObject("/topics", topic, Topic.class);
        System.out.println(createdTopic);

    }

    @Test
    public void testBasicGet() {
        Topic topic = doGetTopic(2L);
        System.out.println(topic);
    }

    @Test
    public void testBasicPut() {
        Topic topic = new Topic();
        String name = "A new topic to update";
        topic.setName(name);
        topic.setDescription("Description");
        topic.setTextField1("Text field 1");
        topic.setTextField2("Text field 2");
        Topic createdTopic = this.restTemplate.postForObject("/topics", topic, Topic.class);

        String originalName = createdTopic.getName();

        topic = new Topic();
        topic.setId(createdTopic.getId());
        name = "Replaced Topic";
        topic.setName(name);

        // There no "putForObject" in RestTemplate
        HttpEntity<Topic> entity = new HttpEntity<Topic>(topic);
        ResponseEntity<Topic> updatedTopic = this.restTemplate.exchange("/topic/" + createdTopic.getId(), HttpMethod.PUT, entity, Topic.class);

        // after replacement all other fields should be null
        topic = doGetTopic(createdTopic.getId());
    }


    @Test
    public void testBasicPatch() {
        Topic topic = doGetTopic(1L);

        String originalName = topic.getName();
        String changedName = "Changed Name";
        //topic.setName(changedName);

        Topic aNewTopic = new Topic();
        aNewTopic.setName(changedName);

        Topic updatedTopic = this.restTemplate.patchForObject("/topic/1", aNewTopic, Topic.class);

        topic = doGetTopic(1L);

        // revert back to original name and double check
        aNewTopic.setName(originalName);
        updatedTopic = this.restTemplate.patchForObject("/topic/1", aNewTopic, Topic.class);
        topic = doGetTopic(1L);
    }

    private Topic doGetTopic(Long id) {
        return this.restTemplate.getForObject("/topic/" + id, Topic.class);
    }

}
