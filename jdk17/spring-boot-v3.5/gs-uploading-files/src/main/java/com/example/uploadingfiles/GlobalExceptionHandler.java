package com.example.uploadingfiles;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class GlobalExceptionHandler {

//  @ExceptionHandler(MaxUploadSizeExceededException.class)
//  public ModelAndView handleMaxSizeException(MaxUploadSizeExceededException exc) {
//    ModelAndView modelAndView = new ModelAndView("uploadError"); // 指定错误页面路径
//    modelAndView.addObject("message", "上传的文件太大了！请上传不超过限制大小的文件。");
//    return modelAndView;
//  }

//   @ExceptionHandler(MaxUploadSizeExceededException.class)
//    public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException exc) {
//        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
//                .body("文件大小超出限制！");
//    }

//  @ExceptionHandler(MaxUploadSizeExceededException.class)
//  public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException exc) {
//    return ResponseEntity.internalServerError().body("上传的文件太大了！请上传不超过限制大小的文件。");
//  }

/*  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ModelAndView handleMaxSizeException(MaxUploadSizeExceededException exc,
      HttpServletRequest request) {
    // 判断是否是AJAX请求
    boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    // 针对AJAX请求，返回JSON格式的错误信息
    ModelAndView modelAndView = new ModelAndView("uploadError");
    modelAndView.addObject("message", "上传的文件太大了！请上传不超过10MB的文件。");
    return modelAndView;
  }*/

//  @ResponseBody
//  @ExceptionHandler(MaxUploadSizeExceededException.class)
//  public ResponseEntity<Map<String, String>> handleMaxSizeException(MaxUploadSizeExceededException exc) {
//    Map<String, String> errorMap = new HashMap<>();
//    errorMap.put("message", "上传的文件太大了！请上传不超过10MB的文件。");
//    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(errorMap);
//  }
//

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public String handleMaxSizeException(MaxUploadSizeExceededException e, Model model) {
    model.addAttribute("message", "File size exceeds the maximum limit!,文件太大了");
    return "uploadError";
  }


//   @ExceptionHandler(MaxUploadSizeExceededException.class)
//    public ResponseEntity<?> handleMaxSizeException(
//        MaxUploadSizeExceededException exc,
//        HttpServletRequest request
//    ) {
//        // 根据请求类型动态返回格式
//        if (isHtmlRequest(request)) {
//            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
//                .contentType(MediaType.TEXT_HTML)
//                .body("<h1>文件过大！最大允许" + parseSize(exc.getMaxUploadSize()) + "</h1>");
//        } else {
//            Map<String, String> error = new HashMap<>();
//            error.put("error", "文件大小超过限制");
//            error.put("maxSize", parseSize(exc.getMaxUploadSize()));
//            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(error);
//        }
//    }
//
//    private boolean isHtmlRequest(HttpServletRequest request) {
//        String accept = request.getHeader("Accept");
//        return accept != null && accept.contains(MediaType.TEXT_HTML_VALUE);
//    }
//
//    private String parseSize(long bytes) {
//        return bytes > 1024 * 1024
//            ? (bytes / (1024 * 1024)) + "MB"
//            : (bytes / 1024) + "KB";
//    }
}
