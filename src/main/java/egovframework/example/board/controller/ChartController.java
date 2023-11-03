package egovframework.example.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.example.board.service.BoardService;
import egovframework.example.board.vo.BoardVo;

@Controller
public class ChartController {
	
//	@Autowired
//	private BoardService boardService;
//	
//	//차트
//	@RequestMapping(value = "/chart.do")
//	public @ResponseBody List<BoardVo> showChart(Model model, BoardVo vo) throws Exception {
//		List<BoardVo> chart = boardService.showChart(vo);
//		model.addAttribute("chart", chart);
//		return chart;
//	}
//	

}
