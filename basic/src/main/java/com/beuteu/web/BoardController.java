package com.beuteu.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.ProcessBuilder.Redirect;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.beuteu.domain.BoardVO;
import com.beuteu.domain.PageMaker;
import com.beuteu.domain.Search;
import com.beuteu.factory.ContextFactory;
import com.beuteu.serivce.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Inject
	private BoardService service;

	@Resource(name = "uploadPath")
	private String uploadPath;
	
	
	/*
	 * 리스트 페이지 20180601 이종열
	 */
	@RequestMapping(value = "/list")
	public ModelAndView boardList(@ModelAttribute("search") Search search, Integer page) throws Exception {

		ModelAndView mv = new ModelAndView();

		int total = service.pageCount(search);

		System.out.println("total" + total);    // 토탈 카운트 
		System.out.println("search" + search);  // 검색
		System.out.println("page" + page);      // 페이지넘버

		page = (page == null ? 1 : page);
		int curpage = page - 1;

		search.setBno(curpage * 10);

		mv.addObject("page", new PageMaker(page, total));
		mv.addObject("list", service.boardList(search));
		mv.addObject("pageNum", page);
		mv.addObject("search", search);
		mv.addObject("total", total);

		mv.setViewName("/board/list");
		return mv;
		
		
	}

	/*
	 * 등록GET 페이지 20180601 이종열
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView boardRegister(BoardVO vo) throws Exception {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("/board/register");
		return mv;
	}

	/*
	 * 등록POST 페이지 20180601 이종열
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String boardRegister2(MultipartFile file, Model model, BoardVO vo) throws Exception {

		System.out.println("FILE" + file);
		System.out.println("model" + model);
		System.out.println("vo" + vo);

		System.out.println("originalName: " + file.getOriginalFilename());
		System.out.println("size: " + file.getSize());
		System.out.println("contentType: " + file.getContentType());

		System.out.println("기본들어왔어");

		String savedName = uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
		vo.setFilepath(savedName);
		service.boardRegist(vo);

		return "redirect:/board/list";

	}

	/*
	 * 등록POST 페이지 20180601 이종열
	 */
	@RequestMapping(value = "/register3", method = RequestMethod.POST)
	public String boardRegister3(MultipartFile file, Model model, BoardVO vo) throws Exception {

		service.boardRegist(vo);

		return "redirect:/board/list";

	}

	/*
	 * 파일 업로드 페이지 20180601 이종열
	 */
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {

		UUID uid = UUID.randomUUID();

		String savedName = uid.toString() + "_" + originalName;

		String savedPath = calcPath(uploadPath);

		File target = new File(uploadPath + savedPath, savedName);

		FileCopyUtils.copy(fileData, target);

		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);

		String uploadedFileName = null;

		if (MediaUtils.getMediaType(formatName) != null) {
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		} else {
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}

		return uploadedFileName;

	}

	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {

		String iconName = uploadPath + path + File.separator + fileName;

		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {

		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));

		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);

		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;

		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	private static String calcPath(String uploadPath) {

		Calendar cal = Calendar.getInstance();

		String yearPath = File.separator + cal.get(Calendar.YEAR);

		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);

		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

		makeDir(uploadPath, yearPath, monthPath, datePath);

		return datePath;
	}

	private static void makeDir(String uploadPath, String... paths) {

		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}

		for (String path : paths) {

			File dirPath = new File(uploadPath + path);

			if (!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}

	/*
	 * private String uploadFile(String originalName, byte[] fileData) throws
	 * Exception {
	 * 
	 * UUID uid = UUID.randomUUID();
	 * 
	 * String savedName = uid.toString() + "_" + originalName;
	 * 
	 * File target = new File(uploadPath, savedName);
	 * 
	 * FileCopyUtils.copy(fileData, target);
	 * 
	 * return savedName;
	 * 
	 * }
	 */

	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {

		InputStream in = null;
		ResponseEntity<byte[]> entity = null;

		// logger.info("FILE NAME: " + fileName);

		try {

			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

			MediaType mType = MediaUtils.getMediaType(formatName);

			HttpHeaders headers = new HttpHeaders();

			in = new FileInputStream(uploadPath + fileName);

			if (mType != null) {
				headers.setContentType(mType);
			} else {

				fileName = fileName.substring(fileName.indexOf("_") + 1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition",
						"attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			}

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}

	/*
	 * 삭제 페이지 20180601 이종열
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request) throws Exception {

		String[] bno = request.getParameter("bno").toString().split(",");

		for (int i = 0; i < bno.length; i++) {
			service.boardDelete(Integer.parseInt(bno[i]));
			System.out.println(Integer.parseInt(bno[i]));
		}

		return "redirect:/board/list";

	}

	/*
	 * 뷰페이지 페이지 20180601 이종열
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView boardView(BoardVO vo, Search search, Integer page) throws Exception {

		ModelAndView mv = new ModelAndView();

		service.viewPoint(vo);

		mv.addObject("view", service.boardView(vo));
		mv.addObject("vo", vo);
		mv.addObject("search", search);
		mv.addObject("pageNum", page);
		mv.setViewName("/board/view");
		return mv;
	}

	/*
	 * 뷰페이지 페이지 20180601 이종열
	 * 
	 * @RequestMapping(value = "/view", method = RequestMethod.GET) public
	 * ModelAndView boardView(BoardVO vo) throws Exception {
	 * 
	 * ModelAndView mv = new ModelAndView();
	 * 
	 * service.viewPoint(vo);
	 * 
	 * mv.addObject("view", service.boardView(vo)); mv.setViewName("/board/view");
	 * return mv; }
	 */
	/*
	 * 리스트JOSN 20180601 이종열
	 */
	@RequestMapping(value = "/listAjax.do")
	public @ResponseBody Map<String, Object> getJsonByMap(BoardVO vo, Integer page, Search search) throws Exception {

		Map<String, Object> jsonObject = new HashMap<String, Object>();

		System.out.println("JSON VO들어왔고 VO출력:" + vo);
		System.out.println("JSON page들어왔고 page출력:" + page);

		int total = service.pageCount(search);

		page = (page == null ? 1 : page);
		int curpage = page - 1;

		search.setBno(curpage * 10);

		jsonObject.put("list", service.boardList(search));
		jsonObject.put("pageMaker", new PageMaker(page, total));
		jsonObject.put("page", page);

		return jsonObject;

	}

	/*
	 * 리스트JOSN 20180601 이종열
	 */
	@RequestMapping(value = "/listAjax")
	public ModelAndView boardListAjax() throws Exception {

		ModelAndView mv = new ModelAndView();

		mv.setViewName("/board/listAjax");
		return mv;
	}

}
