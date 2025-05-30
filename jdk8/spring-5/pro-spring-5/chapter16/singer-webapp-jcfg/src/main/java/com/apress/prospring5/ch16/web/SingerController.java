package com.apress.prospring5.ch16.web;

import com.apress.prospring5.ch16.entities.Singer;
import com.apress.prospring5.ch16.services.SingerService;
import com.apress.prospring5.ch16.util.Message;
import com.apress.prospring5.ch16.util.SingerGrid;
import com.apress.prospring5.ch16.util.UrlUtil;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/singers")
public class SingerController {

  public static final String SINGER = "singer";

  private final Logger logger = LoggerFactory.getLogger(SingerController.class);

  private SingerService singerService;
  private MessageSource messageSource;

  @GetMapping
  public String list(Model uiModel) {
    logger.info("Listing singers");
    List<Singer> singers = singerService.findAll();
    uiModel.addAttribute("singers", singers);
    return "singers/list";
  }

  @GetMapping(value = "/{id}")
  public String show(@PathVariable("id") Long id, Model uiModel) {
    Singer singer = singerService.findById(id);
    uiModel.addAttribute(SINGER, singer);

    return "singers/show";
  }

  @PostMapping(value = "/{id}", params = "form")
  public String update(@Valid Singer singer, BindingResult bindingResult, Model uiModel,
      HttpServletRequest httpServletRequest,
      RedirectAttributes redirectAttributes, Locale locale) {
    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message("error", messageSource.getMessage("singer_save_fail", new Object[]{}, locale)));
      uiModel.addAttribute(SINGER, singer);
      return "singers/update";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("singer_save_success", new Object[]{}, locale)));
    singerService.save(singer);
    return "redirect:/singers/" + UrlUtil.encodeUrlPathSegment(singer.getId().toString(), httpServletRequest);
  }

  @GetMapping(value = "/{id}", params = "form")
  public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    uiModel.addAttribute(SINGER, singerService.findById(id));
    return "singers/update";
  }

  @PostMapping
  public String create(@Valid Singer singer, BindingResult bindingResult, Model uiModel, RedirectAttributes redirectAttributes, Locale locale,
      @RequestParam(value = "file", required = false) Part file) {
    logger.info("Creating singer");
    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message("error", messageSource.getMessage("singer_save_fail", new Object[]{}, locale)));
      uiModel.addAttribute(SINGER, singer);
      return "singers/create";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("singer_save_success", new Object[]{}, locale)));

    if (file != null) {
      byte[] fileContent = null;
      try {
        InputStream inputStream = file.getInputStream();
        if (inputStream == null) {
          logger.info("File inputstream is null");
        }
        fileContent = IOUtils.toByteArray(inputStream);
      } catch (IOException ex) {
        logger.error("Error saving uploaded file");
      }
      singer.setPhoto(fileContent);
    }

    singerService.save(singer);
    return "redirect:/singers/";
  }

  @GetMapping(value = "/photo/{id}")
  @ResponseBody
  public byte[] downloadPhoto(@PathVariable("id") Long id) {
    Singer singer = singerService.findById(id);

    if (singer.getPhoto() != null) {
      logger.info("Downloading photo for id: {} with size: {}", singer.getId(), singer.getPhoto().length);
    }

    return singer.getPhoto();
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping(params = "form")
  public String createForm(Model uiModel) {
    Singer singer = new Singer();
    uiModel.addAttribute(SINGER, singer);

    return "singers/create";
  }

  @ResponseBody
  @GetMapping(value = "/listgrid", produces = "application/json")
  public SingerGrid listGrid(@RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "sidx", required = false) String sortBy,
      @RequestParam(value = "sord", required = false) String order) {

    logger.info("Listing singers for grid with page: {}, rows: {}", page, rows);
    logger.info("Listing singers for grid with sort: {}, order: {}", sortBy, order);

    // Process order by
    Sort sort = null;
    String orderBy = sortBy;
    if (orderBy != null && orderBy.equals("birthDateString")) {
      orderBy = "birthDate";
    }

    if (orderBy != null && order != null) {
      if (order.equals("desc")) {
        sort = new Sort(Sort.Direction.DESC, orderBy);
      } else {
        sort = new Sort(Sort.Direction.ASC, orderBy);
      }
    }

    // Constructs page request for current page
    // Note: page number for Spring Data JPA starts with 0, while jqGrid starts with 1
    PageRequest pageRequest = null;

    if (sort != null) {
      pageRequest = PageRequest.of(page - 1, rows, sort);
    } else {
      pageRequest = PageRequest.of(page - 1, rows);
    }

    Page<Singer> singerPage = singerService.findAllByPage(pageRequest);

    // Construct the grid data that will return as JSON data
    SingerGrid singerGrid = new SingerGrid();

    singerGrid.setCurrentPage(singerPage.getNumber() + 1);
    singerGrid.setTotalPages(singerPage.getTotalPages());
    singerGrid.setTotalRecords(singerPage.getTotalElements());

    singerGrid.setSingerData(Lists.newArrayList(singerPage.iterator()));

    return singerGrid;
  }

  @Autowired
  public void setSingerService(SingerService singerService) {
    this.singerService = singerService;
  }

  @Autowired
  public void setMessageSource(MessageSource messageSource) {
    this.messageSource = messageSource;
  }
}
