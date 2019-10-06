package pl.javastart.sellegro.home;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.javastart.sellegro.auction.entity.Auction;
import pl.javastart.sellegro.auction.repository.AuctionRepository;

import java.util.List;

@Controller
public class HomeController {

    private AuctionRepository auctionRepository;

    public HomeController(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Auction> auctions = auctionRepository.findAllByOrderByPriceDesc(PageRequest.of(0, 4));
        model.addAttribute("cars", auctions);
        return "home";
    }
}
