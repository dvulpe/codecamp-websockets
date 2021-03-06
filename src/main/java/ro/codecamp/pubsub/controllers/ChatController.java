package ro.codecamp.pubsub.controllers;

import org.atmosphere.cpr.AtmosphereResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ChatController {

    @RequestMapping("/websockets")
    @ResponseBody
    public void subscribe(AtmosphereResource<HttpServletRequest,HttpServletResponse> resource) throws IOException {
        resource.getResponse().setContentType("text/html");
        resource.suspend(-1);
    }

}
