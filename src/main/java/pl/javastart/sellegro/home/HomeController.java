package pl.javastart.sellegro.home;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
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
    public String home(@PageableDefault(size = 4)
                       @SortDefault(sort = "price", direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model) {
        Page<Auction> auctions = auctionRepository.findAll(pageable);
        model.addAttribute("cars", auctions);
        return "home";
    }
}
