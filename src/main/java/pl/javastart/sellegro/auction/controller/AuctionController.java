package pl.javastart.sellegro.auction.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.sellegro.auction.entity.Auction;
import pl.javastart.sellegro.auction.filter.AuctionFilters;
import pl.javastart.sellegro.auction.repository.AuctionRepository;
import pl.javastart.sellegro.auction.service.AuctionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class AuctionController {

    private AuctionRepository auctionRepository;
    private AuctionService auctionService;
    private Page<Auction> auctions;

    public AuctionController(AuctionRepository auctionRepository, AuctionService auctionService) {
        this.auctionRepository = auctionRepository;
        this.auctionService = auctionService;
    }

    @GetMapping("/auctions")
    public String auctions(@RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @RequestParam(required = false) String sort,
                           AuctionFilters auctionFilters,
                           Pageable pageable,
                           Model model) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(50);

        auctions = auctionService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        auctions = auctionService.getFilteredAuctions(auctionFilters, pageable);

        if (sort != null) {
            auctions = auctionRepository.findAllSorted(pageable);
        }

        model.addAttribute("cars", auctions);
        model.addAttribute("filters", auctionFilters);

        int totalPages = auctions.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "auctions";
    }
}
