package org.kakahu.safe;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hk {

    @RequestMapping("/hk")
    public String index() {
        org.springframework.asm.ClassReader k;
        return "welcome to hk";
    }

}
