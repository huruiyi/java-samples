package org.example;

import com.example.config.NamedJdbcCfg;
import com.example.dao.SingerDao;
import com.example.entities.Album;
import com.example.entities.Singer;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NamedJdbcCfgTest {

    @Test
    public void testCfg() {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(NamedJdbcCfg.class);

        SingerDao singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);

        assertEquals("John Mayer", singerDao.findNameById2(1L));

        ctx.close();
    }

    @Test
    public void testResultSetExtractor() {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(NamedJdbcCfg.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
        List<Singer> singers = singerDao.findAllWithAlbums();
        assertEquals(3, singers.size());
        singers.forEach(singer -> {
            System.out.println(singer);
            if (singer.getAlbums() != null) {
                for (Album album : singer.getAlbums()) {
                    System.out.println("\t--> " + album);
                }
                if (singer.getId() == 1L) {
                    assertEquals(2, singer.getAlbums().size());
                } else if (singer.getId() == 2L) {
                    assertEquals(1, singer.getAlbums().size());
                } else if (singer.getId() == 3L) {
                    assertEquals(0, singer.getAlbums().size());
                }
            }
        });
        ctx.close();
    }

    @Test
    public void testRowMapper() {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(NamedJdbcCfg.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
        List<Singer> singers = singerDao.findAll();
        assertEquals(3, singers.size());

        singers.forEach(singer -> {
            System.out.println(singer);
            if (singer.getAlbums() != null) {
                for (Album album : singer.getAlbums()) {
                    System.out.println("---" + album);
                }
            }
        });

        ctx.close();
    }
}
