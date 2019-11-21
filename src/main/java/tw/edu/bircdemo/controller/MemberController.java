package tw.edu.bircdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.edu.bircdemo.bean.MemberBean;
import tw.edu.bircdemo.service.MemberService;

@Controller
@RequestMapping(path = "/member")
public class MemberController {
	private MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@ResponseBody
	@GetMapping(path="/search")
	public String search() {
		System.out.println("get request");
		return"{\"result\":true}";

	}

	@ResponseBody
	@PostMapping(path = "",produces="application/json;charset=UTF-8")
	public String create(@RequestBody MemberBean memberBean) throws JsonProcessingException {
		memberService.createAndReturn(memberBean);
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode result = mapper.createObjectNode();
		result.put("result", true);
		result.put("errorCode", 200);
		result.put("message", "�s�W���\");
		ObjectNode dateNode = result.putObject("date");
		dateNode.put("id", memberBean.getId());
		dateNode.put("firstName", memberBean.getFirstName());
		dateNode.put("lastName", memberBean.getLastName());
		dateNode.put("gender", memberBean.getGender());
		dateNode.put("email", memberBean.getEmail());
		
		return mapper.writeValueAsString(result);
	}
}
