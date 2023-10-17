package com.example.jmock;


import static org.junit.Assert.assertEquals;

import com.example.entities.Singer;
import com.example.entities.Singers;
import com.example.services.SingerService;
import com.example.web.SingerController;
import java.util.ArrayList;
import java.util.List;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;

public class SingerControllerTest {

  private final List<Singer> singers = new ArrayList<>();

  private final Mockery mockery = new Mockery();

  private SingerService mockSingerService;
  private SingerController singerController;

  @Before
  public void initSingers() {
    Singer singer = new Singer();
    singer.setId(1L);
    singer.setFirstName("John");
    singer.setLastName("Mayer");
    singers.add(singer);
    mockSingerService = mockery.mock(SingerService.class);
    singerController = new SingerController();
    ReflectionTestUtils.setField(singerController, "singerService", mockSingerService);
  }

  @Test
  public void testList() throws Exception {
    mockery.checking(new Expectations() {{
      oneOf(mockSingerService).findAll();
      will(returnValue(singers));
    }});
    ExtendedModelMap uiModel = new ExtendedModelMap();

    uiModel.addAttribute("singers", singerController.listData());
    Singers modelSingers = (Singers) uiModel.get("singers");
    assertEquals(1, modelSingers.getSingers().size());
    mockery.assertIsSatisfied();
  }

  @Test
  public void testCreate() {
    final Singer newSinger = new Singer();
    newSinger.setId(999L);
    newSinger.setFirstName("Stevie");
    newSinger.setLastName("Vaughan");

    mockery.checking(new Expectations() {{
      oneOf(mockSingerService).save(newSinger);
      singers.add(newSinger);
      will(returnValue(newSinger));
    }});

    Singer singer = singerController.create(newSinger);
    assertEquals(Long.valueOf(999L), singer.getId());
    assertEquals("Stevie", singer.getFirstName());
    assertEquals("Vaughan", singer.getLastName());

    assertEquals(2, singers.size());
    mockery.assertIsSatisfied();
  }
}
