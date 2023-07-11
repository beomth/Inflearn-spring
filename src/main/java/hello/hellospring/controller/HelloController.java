package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //Spring에서는 Controller로 쓰려면 어노테이션 추가해줘야 한다.
public class HelloController {

    @GetMapping("hello")    //map application에서 hello라고 들어오면, 아래 메서드를 호출
    //HTTP에서 GET 메서드를 요청했을 때 매핑해줌
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; //templates의 hello.html을 찾아감
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) { //@RequestParam은 HTTP 요청 파라미터를 컨트롤러 메소드의 파라미터로 전달받을 때 사용된다.
        model.addAttribute("name", name);   //"name" 키, name value
        return "hello-templates";
    }

    @GetMapping("hello-spring")
    @ResponseBody   //HTTP body부에 데이터를 넣는 어노테이션
    public String helloSpring(@RequestParam(value = "name") String name){

        return "hello" + name; // 문자열만 그대로 출력..
    }

    @GetMapping("hello-api")    //api 구현. 데이터를 JSON 형태로 출력
    @ResponseBody
    public Hello helloapi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }


    static class Hello{ //api에서 데이터를 넘겨주기 위해, 객체를 생성
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
