package site.leui.ssiach6.mainpage;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import site.leui.ssiach6.product.ProductService;

@Controller
public class MainPageController {

    private final ProductService productService;

    public MainPageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/main")
    public String mainPage(Authentication a, Model model) {
        model.addAttribute("username", a.getName());
        model.addAttribute("products", productService.getAll());

        return "main";
    }
}
