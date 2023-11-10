package egovframework.example.board.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.example.board.service.BoardService;
import egovframework.example.board.vo.Search;
import egovframework.example.board.vo.BoardVo;
import egovframework.example.board.vo.CmntVo;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	// 게시글 목록
//	@RequestMapping(value = "/testList.do")
//	public String testListDo(boardVo boardVo, Model model) throws Exception {
//
//		model.addAttribute("list", testService.selectTest(boardVo));
//
//		return "test/testList";
//	}

	// 글목록페이지,페이징,검색
	@RequestMapping(value = "/list.do")
	public String testListDo(Model model, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String keyword, @ModelAttribute("search") Search search) throws Exception {

		// 검색
		model.addAttribute("search", search);
		search.setSearchType(searchType);
		search.setKeyword(keyword);

		// 전체 게시글 개수
		int listCnt = boardService.getBoardListCnt(search);
		
		System.out.println("전체 게시글 개수 : " + listCnt);
		
		// 검색 후 페이지
		search.pageInfo(page, range, listCnt);
		// 페이징
		model.addAttribute("pagination", search);
		// 게시글 화면 출력
		model.addAttribute("list", boardService.selectTest(search));
		

		return "board/list";
	}
	
	
	// 게시글 작성 페이지 이동
	@RequestMapping(value = "/register.do")
	public String testRegister() {
		return "board/register";
	}

	// 게시글 등록
	@RequestMapping(value = "/insertTest.do")
	public String write(@ModelAttribute("boardVo") BoardVo boardVo, HttpSession session) throws Exception {
//		System.out.println("내용:"+ boardVo.getContent());

		boardService.insertTest(boardVo);
		return "redirect:list.do";
	}

	// 게시글 상세보기
	@RequestMapping(value = "/detail.do")
	public String viewForm(Model model, CmntVo cmntVo, HttpServletRequest request) throws Exception {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		//게시글 상세보기
		BoardVo boardVo = boardService.selectDetail(boardNo);
		model.addAttribute("vo", boardVo);
		
		//댓글 목록
//		List<CmntVo> comment = boardService.listCmnt(boardNo);
//		model.addAttribute("comment",comment);
		
		//대댓글 목록
		List<CmntVo> reply = boardService.listReply(boardNo);
		model.addAttribute("comment",reply);
		
		//댓글 갯수
		int cmntCnt = boardService.updateReplyCount(boardNo);
		model.addAttribute("cmntCnt",cmntCnt);

		return "board/detail";
	}
	
	// 게시글 수정 페이지로 이동
	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public String updateTest(int boardNo, Model model) throws Exception {
		BoardVo boardVo = this.boardService.selectDetail(boardNo);
		model.addAttribute("vo", boardVo);
		return "board/update";
	}
	
	// 게시글 수정
	@RequestMapping(value = "/updateTest.do", method = RequestMethod.POST)
	public String updateTest(BoardVo boardVo) throws Exception {
		boardService.updateTest(boardVo);
		return "redirect:list.do";
	}

	// 게시글 삭제
	@RequestMapping(value = "/deleteTest.do")
	public String deleteTest(HttpServletRequest request) throws Exception {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		boardService.deleteTest(boardNo);
		return "redirect:list.do";
	}
	
	//차트페이지로 이동
	@RequestMapping(value = "/chart.do", method = RequestMethod.GET)
	public String showChartGo() throws Exception {
		System.out.println("============== 차트 페이지 ============");		
		return "board/chart";
	}
	
	//차트
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/chart2.do") public List<CmntVo> showChart(Model
	 * model) throws Exception {
	 * 
	 * System.out.println("==============차트============");
	 * 
	 * List<CmntVo> chart = boardService.showChart(); chart.clear();
	 * model.addAttribute("chart", chart); return chart;
	 * 
	 * }
	 */
	
	/*
	//차트1
	@RequestMapping(value = "/chartAjax.do")
	public ModelAndView showChart(
					   @RequestParam HashMap<String, Object> paramMap
					  ,CmntVo cmntVo
//	                  ,HttpSession session
	         ) throws Exception {
		  System.out.println("==============차트============");
	      
//	      CmntVo myMapping = new CmntVo();
	      List<CmntVo> result; 
	      result =  boardService.showChart(cmntVo);
	      
	      
	      //
	      for (int i = 0; i < result.size(); i++) {
	    	  System.out.println("갯수:"+result.get(i).getReCnt());
		}
	      
	      System.out.println("result: " + result);
	      
	      
	      ModelAndView mav = new ModelAndView();
    	  mav.setViewName("jsonView");  //경로 보내는거 아닐때 ajax에서 데이터만 받을때는 이렇게 처리한다.  dispatcher-sevlet.xml에서 설정.
    	  mav.addObject("result",result);
    	  return mav;
	      
	   }
	*/
		//차트2
		@ResponseBody
		@RequestMapping(value = "/chartAjax.do")
		public void showChart(
						   @RequestParam HashMap<String, Object> paramMap
		                  ,HttpSession session
		                  ,HttpServletResponse response
		         ) throws Exception {
			  System.out.println("==============차트============");
		      
//		      CmntVo myMapping = new CmntVo();
		      List<CmntVo> result; 
		      result =  boardService.showChart();
		      
//		      for (int i = 0; i < result.size(); i++) {
//		    	  List<Integer> result1 = new ArrayList<Integer>();
//		    	  result1.set(i, result.get(i).getReCnt());
//		    	  System.out.println("갯수:"+result1.get(i));
//			}
		      List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
//		      Map<String, Object> mapList = new HashMap<String, Object>();
		      for (int i = 0; i < result.size(); i++) {
		    	  Map<String, Object> node = new HashMap<>();
		    	  
		    	  node.put("recount", result.get(i).getReCnt());
		    	  node.put("reDate", result.get(i).getReDate1());
		    	  
		    	  mapList.add(node);
		    	  
		    	  
		      }
//		      Map<String, Object> mapList = new HashMap<String, Object>();
//		      for (int i = 0; i < result.size(); i++) {
//		    	  mapList.put("reCnt", result.get(i).getReCnt());
//		    	  mapList.put("reDate", result.get(i).getReDate1());
//		    	  System.out.println("mapList:"+mapList);
//		    	  response.setContentType("text/html;charset=utf-8");
////			      response.getWriter().print(result);
//		    	  response.getWriter().print(mapList);
//		    	  response.getWriter().flush();
//		    	  response.getWriter().close();
//		      }
		      
		      System.out.println("===========result: " + result);
		      
		      // JSON으로 변환
		      ObjectMapper objectMapper = new ObjectMapper();
		      String json = objectMapper.writeValueAsString(mapList);

		      System.out.println("json: " + json);
		      
		      response.setContentType("application/json;charset=utf-8"); 
//		      response.setContentType("text/html;charset=utf-8");
//		      response.getWriter().print(result);
		      response.getWriter().print(json);
		      response.getWriter().flush();
		      response.getWriter().close();
		      
		   }	
	/*
	//차트3
	@ResponseBody
	@RequestMapping(value = "/chartAjax.do")
	public void showChart(
					   @RequestParam(value="reCnt") int reCnt
					  ,@RequestParam(value="reDate1") String reDate1
//					   @RequestBody CmntVo cmntVo
					  ,HttpServletResponse response
	         ) throws Exception {
		
		  System.out.println("==============차트============");
//		  System.out.println("==============showChart: " + paramMap);
		  
		  CmntVo myMapping = new CmntVo();
	      myMapping.setReCnt(reCnt);
	      myMapping.setReDate1(reDate1);
	      
	      List<CmntVo> result = boardService.showChart(myMapping);
	      
	      response.setContentType("application/json;charset=utf-8");
	      response.getWriter().print(result);
	      response.getWriter().flush();
	      response.getWriter().close();
		  
		  
		  
		  
		  
//		  List<CmntVo> result; 
//	      result =  boardService.showChart();
//	      System.out.println("==============result: " + result);
//		  
//	      ModelAndView mav = new ModelAndView();
//	      mav.setViewName("jsonView");  //경로 보내는거 아닐때 ajax에서 데이터만 받을때는 이렇게 처리한다.  dispatcher-sevlet.xml에서 설정.
//	      mav.addObject("result",result);
//	      
//	      return mav;
	      
	}
	*/
	
	//댓글 등록, 수정
	@RequestMapping(value = "/insertCmnt.do")
	public void insertCmnt(@RequestParam HashMap<String, Object> paramMap
	                  ,HttpSession session
	                  ,HttpServletResponse response
	         ) throws Exception {
	      
	      CmntVo myMapping = new CmntVo();
	      myMapping.setCmntContent(paramMap.get("cmntContent").toString());
	      myMapping.setBoardNo(Integer.parseInt(paramMap.get("boardNo").toString()));
	      int result; 
	      
	      if(paramMap.containsKey("cmntNo")){
	         myMapping.setCmntNo(Integer.parseInt(paramMap.get("cmntNo").toString()));
	         result = boardService.updateCmnt(myMapping);
	      } else {
	         myMapping.setMemId(paramMap.get("memId").toString());
	         result = boardService.insertCmnt(myMapping);
	      }

	      response.setContentType("text/html;charset=utf-8");
	      response.getWriter().print(result);
	      response.getWriter().flush();
	      response.getWriter().close();
	      
	   }

	   
	
	
	//댓글 삭제
	@PostMapping(value="/deleteCmnt.do")
	@ResponseBody
	public void deleteCmnt(HttpServletRequest request
						,HttpSession session
						,HttpServletResponse response
			) throws Exception {
//		System.out.println("여기까지 오긴 왔나요");
		
		int cmntNo = Integer.parseInt(request.getParameter("cmntNo"));
		boardService.deleteCmnt(cmntNo);
		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(cmntNo);
		response.getWriter().flush();
		response.getWriter().close();
		
	}
	
	//댓글 수정
	/*
	@RequestMapping(value = "/updateCmnt.do", method = RequestMethod.POST)
	@ResponseBody
	public void updateCmnt(
						 @RequestParam(value="cmntContent") String cmntContent
						,@RequestParam(value="boardNo") int boardNo
						,@RequestParam(value="boardNo") int cmntNo
						
//						@RequestBody CmntVo cmntVo
//						@RequestBody HashMap<String, Object> paramMap
						
						,HttpSession session
						,HttpServletResponse response
						
			) throws Exception {
		
		System.out.println("===========여기까지 왔나요????===========");
		
//		Map<String, Object> reMap = new HashMap<String, Object>();
//		reMap.put("reMsg", "OK!!");
//		return reMap;
		
		CmntVo myMapping = new CmntVo();
		myMapping.setCmntContent(cmntContent);
		myMapping.setBoardNo(boardNo);
		myMapping.setCmntNo(cmntNo);
		
		int result = boardService.updateCmnt(myMapping);
//		int result = boardService.updateCmnt(cmntVo);
		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(result);
		response.getWriter().flush();
		response.getWriter().close();
		
		
//		if (result > 0) {
//			return "success";
//		} else {
//			return "fail";
//		}
		
		
	}	
	*/

	/*
   @PostMapping(value="/updateCmntTemp.do")
   public void updateCmntTmp(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request,
            HttpServletResponse response, HttpSession session
            ) throws Exception{
	   
	   		System.out.println("===========여기까지 왔나요????===========");
	   
            CmntVo myMapping = new CmntVo();
            myMapping.setCmntContent(paramMap.get("cmntContent").toString());
            myMapping.setBoardNo(Integer.parseInt(paramMap.get("boardNo").toString()));
            myMapping.setCmntNo(Integer.parseInt(paramMap.get("cmntNo").toString()));
            
            int result = boardService.updateCmnt(myMapping);
      //      int result = boardService.updateCmnt(cmntVo);
            
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(result);
            response.getWriter().flush();
            response.getWriter().close();
         };
	
	*/
	
	
	//===============대댓글===============
	
	
	//대댓글 등록
	@RequestMapping(value="/insertReply.do")
	@ResponseBody
	public void insertReply(
						 @RequestParam(value="boardNo") int boardNo
						,@RequestParam(value="cmntContent") String cmntContent
						,@RequestParam(value="memId") String memId
						,@RequestParam(value="cmntNo") int cmntNo
//						,@RequestParam(value="cmntGroup") int cmntGroup
						,HttpSession session
						,HttpServletResponse response
			) throws Exception {
		
		System.out.println("===========여기까지 왔나요????(대댓글 등록)===========");
		
		CmntVo myMapping = new CmntVo();
		
		myMapping.setCmntContent(cmntContent);
		myMapping.setBoardNo(boardNo);
		myMapping.setCmntGroup(cmntNo);
//		myMapping.setCmntNo(cmntNo);
		myMapping.setMemId(memId);
		int cmntDepth = 1;
		myMapping.setCmntDepth(cmntDepth);
//		myMapping.setUpCmntNo(upCmntNo);
//		
		
		int result = boardService.insertReply(myMapping);
		
		//ajax로 db는 보냈는데 response가 안됐을때 이거 추가해서 해결함 +HttpServletResponse response
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(result);
		response.getWriter().flush();
		response.getWriter().close();
		
	}	
	
	/*
	//대댓글 수정
	@RequestMapping(value="/updateReply.do")
	@ResponseBody
	public void updateReply(
						 @RequestParam(value="boardNo") int boardNo
						,@RequestParam(value="cmntContent") String cmntContent
						,@RequestParam(value="memId") String memId
						,@RequestParam(value="cmntNo") int cmntNo
						,HttpSession session
						,HttpServletResponse response
			) throws Exception {
		
		System.out.println("===========여기까지 왔나요????(댓글 수정)===========");
		
		CmntVo myMapping = new CmntVo();
		
		myMapping.setCmntContent(cmntContent);
//		myMapping.setBoardNo(boardNo);
//		myMapping.setCmntGroup(cmntNo);
//		myMapping.setMemId(memId);
		
		int result = boardService.insertReply(myMapping);
		
		//ajax로 db는 보냈는데 response가 안됐을때 이거 추가해서 해결함 +HttpServletResponse response
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(result);
		response.getWriter().flush();
		response.getWriter().close();
		
	}	
	*/
	
	
	//======================== 세션 =============================
	/*
	@Controller
	public class HeartbeatController {

	    @GetMapping("/heartbeat.do")
	    @ResponseBody
	    public String heartbeat() {
	    	
	        // 세션 유효성을 확인하고 필요한 경우 세션을 갱신 또는 다른 작업을 수행
	        return "OK"; // 클라이언트에게 성공적인 응답을 보내거나 세션 상태에 따라 적절한 응답을 처리
	    }
	}
	*/
	
	
	

}






